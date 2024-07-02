package testBrain;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class CommonUtility {
    private static AppiumDriver driver;
    private static WebDriverWait wait;
    private static Actions actions;
    public static final String screenShotPath = System.getProperty("user.dir") + "/src/test/resources/";
    public static final Logger logger = LogManager.getLogger(CommonUtility.class);

    public CommonUtility(AppiumDriver driver, WebDriverWait wait){
        this.driver=driver;
        this.wait=wait;
        actions=new Actions(driver);
    }

    public static void click(WebElement ele){
        logger.info("Click on element");
        wait.until(ExpectedConditions.visibilityOf(ele));
        ele.click();
    }

    public static void enter(WebElement ele,String value){
        logger.info("Pass the value");
        wait.until(ExpectedConditions.visibilityOf(ele));
        wait.until(ExpectedConditions.elementToBeClickable(ele));
        ele.sendKeys(value);
    }

    public static void takeScreeShot()  {
        logger.info("Taking screenshot");
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);

        TakesScreenshot takesScreenshot=(TakesScreenshot)driver;
        File screenShotFile=takesScreenshot.getScreenshotAs(OutputType.FILE);
        File screenShotFileToReplace=new File(screenShotPath+"img"+formattedDateTime+".jpg");

        try {
            FileUtils.copyFile(screenShotFile,screenShotFileToReplace);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
