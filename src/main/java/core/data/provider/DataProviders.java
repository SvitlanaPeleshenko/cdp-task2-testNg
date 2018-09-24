package core.data.provider;

import java.io.IOException;
import java.lang.reflect.Method;

import core.configuration.Configuration;
import org.testng.annotations.DataProvider;

public class DataProviders extends Configuration {
  
  public static final String DEFAULT = "default"; //provides Object[][]{{"en"}, {"fr"}}
  public static final String FR = "localeFr"; //provides Object[][]{{"fr"}}
  public static final String EN = "localeEn"; //provides Object[][]{{"en"}}
  public static final String LOCALE_PROVIDER = "localeProvider"; //provides Object[][]{{"/en"}, {"/fr"}}
  public static final String SHARED = "sharedProvider"; //provides previous test data
  public static final String XLS_DATA = "dataWithConfig"; //provides data from xls
  
  @DataProvider(name = XLS_DATA)
  public Object[][] getTestData(Method testMethod) {
    Object[][] testData = null;
    try {
      testData = TestDataProvider.getConfiguredData(testMethod);
    } catch (IOException e) {
      System.out.println("TestDataProvider. Exception caught while receiving test data");
      e.printStackTrace();
    }
    //setSharedProvider(testData);
    return testData;
  }

  //@DataProvider(name = SHARED)
 /* public Object[][] getSharedData() {
    return getSharedProvider();
  }
*/
  @DataProvider(name = DEFAULT)
  public Object[][] getDefaultTestData() {
    return new Object[][]{{"en"}, {"fr"}};
  }

  @DataProvider(name = LOCALE_PROVIDER)
  public Object[][] getDefaultSlashTestData() {
    return new Object[][]{{"/en"}, {"/fr"}};
  }

}
