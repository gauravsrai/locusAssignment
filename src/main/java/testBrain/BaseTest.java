package testBrain;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {

    private AppiumDriver appiumDriver;
    public static WebDriverWait wait;
    public InitializingTestBody initialize;
    public static Properties properties;
    public static Properties testData;
    public static final String APPIUMCONFIGPATH = System.getProperty("user.dir") + "/src/main/resources/mobileSetup.properties";
    public static final Logger logger = LogManager.getLogger(BaseTest.class);
    public static DesiredCapabilities capabilities;
    public CommonUtility commonUtility;
    public URL appiumServerUrl;

    @BeforeSuite
    public void startEmulator(){
        try{
            properties = new Properties();
            FileInputStream fis = new FileInputStream(APPIUMCONFIGPATH);
            properties.load(fis);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(BaseTest.properties.getProperty("appApkPath"));
        logger.info("Starting Emulator");
       appiumServerUrl=MobileTest.startTheAppiumService();

    }

    @BeforeMethod
    public void launchApplication()  {

        capabilities = MobileTest.setUpDesiredCapabilities();
        System.out.println(appiumServerUrl.toString());
        try {
            appiumDriver = new AndroidDriver(new URL(appiumServerUrl.toString()), capabilities);
        }catch (Exception e){
            e.printStackTrace();
        }
        appiumDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        wait=new WebDriverWait(appiumDriver,Duration.ofSeconds(60));
        initialize=new InitializingTestBody(appiumDriver,wait);
        commonUtility=new CommonUtility(appiumDriver,wait);

    }

    @AfterMethod
    public void closeApplication() {
        logger.info("Closing the Application");
        appiumDriver.quit();
    }

    @AfterSuite
    public void killEmulator() {
        logger.info("Closing Emulator");
        MobileTest.stopTheAppiumService();
    }

}
