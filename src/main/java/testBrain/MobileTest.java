package testBrain;

import io.appium.java_client.remote.MobilePlatform;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.AndroidServerFlag;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.BufferedReader;
import java.io.File;
import java.net.URL;

public class MobileTest {

    private static AppiumDriverLocalService service;
    private static String appiumServerPath = System.getProperty("user.dir")+"/node_modules/appium/build/lib/main.js";

    public static URL startTheAppiumService() {

        AppiumServiceBuilder builder = new AppiumServiceBuilder();
        builder.withAppiumJS(new File(appiumServerPath));
        builder.withIPAddress(BaseTest.properties.getProperty("hostAddress"));
        builder.usingPort(Integer.parseInt(BaseTest.properties.getProperty("port")));
        builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
        builder.withArgument(GeneralServerFlag.LOG_LEVEL, "error");

        service = AppiumDriverLocalService.buildService(builder);
        service.start();
        return service.getUrl();
    }

    public static void stopTheAppiumService() {
        service.stop();
    }


    public static DesiredCapabilities setUpDesiredCapabilities() {
        String appPath=System.getProperty("user.dir") + BaseTest.properties.getProperty("appApkPath");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("autoGrantPermissions", true);
        capabilities.setCapability("platformName", MobilePlatform.ANDROID);
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("deviceName",BaseTest.properties.getProperty("deviceName"));
        capabilities.setCapability("newCommandTimeout", 50000);
        capabilities.setCapability("appPackage", BaseTest.properties.getProperty("appPackage"));
        capabilities.setCapability("appActivity", BaseTest.properties.getProperty("appActivity"));
        capabilities.setCapability("app",appPath );
        return capabilities;
    }
}
