package com.uiTests;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features={"src/test/resources/features"},
        //tags = {"@login, @settings, @events, @images, @posts"},
        tags = {"@WIP"},
        glue = {"com.pageSteps"},
        plugin = {"pretty"}
)

public class runAllTest {
}
