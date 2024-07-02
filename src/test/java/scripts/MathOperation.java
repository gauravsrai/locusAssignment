package scripts;

import org.testng.annotations.Test;
import testBrain.BaseTest;
import testData.TestData;

public class MathOperation extends BaseTest {

    @Test(dataProvider = "calculatorData", dataProviderClass = TestData.class)
     public void performMathOperation(String expression,String expectedResult) throws InterruptedException {
        logger.info("Start the Calculator");
       initialize.calculatorScreen.performMathOperation(expression,expectedResult);

     }


}
