package testData;

import org.testng.annotations.DataProvider;

public class TestData {
    @DataProvider(name = "calculatorData")
    public Object[][] createData() {
        return new Object[][] {
                { "9+5", "14" },
                { "8-3", "5" },
                { "14*5", "70" },
                { "70/10", "7" },
                { "((100+200-100))*((2/5))", "80" }
        };
    }
}
