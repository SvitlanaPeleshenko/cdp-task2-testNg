package core.configuration;


import core.data.provider.TestDataProvider;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.lang.reflect.Method;

public class Configuration {


  @DataProvider(name = "dataWithConfig")
  public Object[][] getTestData(Method testMethod) {
    Object[][] testData = null;
    try {
      testData = TestDataProvider.getConfiguredData(testMethod);
    } catch (IOException e) {
      System.out.println("TestDataProvider. Exception caught while receiving test data");
      e.printStackTrace();
    }
    return testData;
  }


}
