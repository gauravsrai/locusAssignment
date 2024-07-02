package testBody;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.checkerframework.checker.units.qual.N;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import testBrain.CommonUtility;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class CalculatorScreen {
    private AppiumDriver driver;
    private WebDriverWait wait;
    private static final Logger logger = LogManager.getLogger(CalculatorScreen.class);

    public CalculatorScreen(AppiumDriver driver, WebDriverWait wait){
        this.driver=driver;
        this.wait=wait;
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }

    private WebElement number(String number){
        return driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'digit_"+number+"')]"));
    }

    private WebElement operator(String operatorName){
        return driver.findElement(By.xpath("//android.widget.ImageView[@content-desc='"+operatorName+"']"));
    }
    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc='equals']")
    private WebElement btnEqual;

    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc='clear']")
    private WebElement btnClear;

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'result')]")
    private WebElement txtResult;

    @AndroidFindBy(xpath = "//android.widget.Button[@text='Agree']")
    private WebElement btnAgree;

    @AndroidFindBy(xpath = "//android.widget.ImageView[contains(@resource-id,'switch')]")
    private WebElement btnSwitch;


    public void performMathOperation(String mathExpression,String expectedResult){
        Map<Character,String> operators=new HashMap<>();
        operators.put('+',"plus");
        operators.put('-',"minus");
        operators.put('*',"multiply");
        operators.put('/',"divide");
        operators.put('(',"left parenthesis");
        operators.put(')',"right parenthesis");
        CommonUtility.click(btnAgree);

        CommonUtility.click(btnSwitch);

        for(int i=0;i<mathExpression.length();i++){
            if(Character.isDigit(mathExpression.charAt(i))){
                CommonUtility.click(number(mathExpression.charAt(i)+""));
            }else{
                CommonUtility.click(operator(operators.get(mathExpression.charAt(i))));
            }
        }
        CommonUtility.click(btnEqual);
        wait.until(ExpectedConditions.visibilityOf(txtResult));
        String result=txtResult.getText().split(" ")[1];
        Assert.assertEquals(result,String.valueOf(expectedResult),"The result displayed on calculator screen after the Math operation is incorrect");
        logger.info("The result displayed on calculator screen after the Math operation is correct and the sum is "+expectedResult);
    }


}
