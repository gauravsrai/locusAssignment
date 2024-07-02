package testBrain;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import testBody.CalculatorScreen;

public class InitializingTestBody {
    public CalculatorScreen calculatorScreen;

    public InitializingTestBody(AppiumDriver driver, WebDriverWait wait){
        calculatorScreen=new CalculatorScreen(driver,wait);
    }
}
