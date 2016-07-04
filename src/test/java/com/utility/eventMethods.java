package com.utility;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by user on 03/07/2016.
 */
public class EventMethods {

    public static boolean checkEventInList(String eventName, WebDriver driver, By list) throws Throwable {
        Boolean result = false;
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        System.out.print("LOOKING FOR: "+eventName+" " );
       // Thread.sleep(10000);
        //dirty: I scroll down the page up to 5 times - each time I check if the event entry is displayed. There is no reason this
        //should 5 times. But there should not be too many events in this page as they all get moved over to the Past Events
        //page eventually. Still it's not great.
        for (int i=0;i<=5;i++){
            jse.executeScript("window.scrollBy(0,1000)", "");
            Thread.sleep(2000);
            List<WebElement> allElements = driver.findElements(list);
            for (WebElement element: allElements) {
                //System.out.println("CHECKING: " + element.getText() + "----");
                if (element.getText().contains(eventName)){
                    System.out.print(" FOUND: "+ element.getText());
                    result=true;
                    break;}
            }
            if (result==true){break;}
        }

        return result;
    }

    public static boolean verifyEventAdded(String eventName, WebDriver driver) {
        int count = driver.findElements(By.linkText(eventName)).size();
        return count != 0;
    }

}
