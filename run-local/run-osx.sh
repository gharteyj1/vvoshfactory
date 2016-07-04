#!/usr/bin/env bash

#################
# Helper Code
#################

function show_help {

    echo "This script runs the webdriver tests on a docker container locally using docker toolbox for OSX and virtualbox"
    echo "The base image used is built in Jenkins including all necessacry dependencies and code"
    echo ""
    echo "Usage: $0 OPTION [ARGS]"
    echo ""
    echo "OPTIONS:"
    echo "    -h Prints this help"
    echo "    -c Copy the local src dir and pom file into the container. Defaults to no flag which means the tests will run the code already baked in the container"
    echo "    -e Environment to test against. Defaults to uat-vpc"
    echo "    -t Image Tag to pull from the registry and use to launch the container. Defaults to latest"
    echo "    -p Port Forward for VNC between the computer and the VM. Defaults to 5900"
}

function echo_log {

    current_time=$(date +"[%d/%b/%Y:%T %z]")
    echo -e "\033[0;36m$current_time $1\033[0m"

}

# Set defaults
ENVIRONMENT="uat-vpc"
IMAGE_TAG="latest"
PF_HOST_PORT=5900
COPY=false

while getopts "h?e:t:p:c" opt; do
    case "$opt" in
    e)
        ENVIRONMENT=$OPTARG
        ;;
    t)
        IMAGE_TAG=$OPTARG
        ;;
    p)
        PF_HOST_PORT=$OPTARG
        ;;
    c)
        COPY=true
        ;;
    h|\?)
        show_help
        exit 1
        ;;
    esac
done

shift $((OPTIND-1))

[ "$1" = "--" ] && shift

docker-machine ls | grep seleniumtests | grep "Error" &> /dev/null
if [ $? -eq 0 ]; then
    echo_log "* Removing broken VM"
    echo y | docker-machine rm seleniumtests
fi

docker-machine ls | grep seleniumtests &> /dev/null
if [ $? -eq 0 ]; then
    echo_log "* Restarting the VM"
    docker-machine restart seleniumtests
else
    echo_log "* Creating a new VM"
    docker-machine create --driver virtualbox seleniumtests

    echo_log "* Setting up port forwarding"
    VBoxManage controlvm "seleniumtests" natpf1 "VNC,tcp,,5900,,5900"
fi

echo_log "* Setting up the environment"
docker-machine regenerate-certs -f seleniumtests
eval "$(docker-machine env seleniumtests)"

echo_log "* Login in the registry"
aws ecr get-login --region us-east-1 | /bin/bash -

echo_log "* Pulling docker image layers"
docker pull 020081386067.dkr.ecr.us-east-1.amazonaws.com/frontend-tests:${IMAGE_TAG}

echo_log "* Running the container"
container_id=$(docker run -ti --shm-size=512m --expose=5900 --publish=${PF_HOST_PORT}:5900 --detach 020081386067.dkr.ecr.us-east-1.amazonaws.com/frontend-tests:${IMAGE_TAG})

if $COPY; then
    DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
    echo_log "* Copying pom and src to container"
    docker cp $DIR/../src/ ${container_id}:/usr/share/webdriver-tests/
    docker cp $DIR/../pom.xml ${container_id}:/usr/share/webdriver-tests/
fi

echo_log "* Starting the tests"
docker exec ${container_id} mvn test -e -Denviron=uat-vpc

echo_log "* Stopping the Container"
docker stop ${container_id}

echo_log "* Removing the container"
docker rm ${container_id}

echo_log "* Stopping the VM"
docker-machine stop seleniumtests