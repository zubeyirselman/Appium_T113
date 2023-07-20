package tests.Day_03;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.testng.annotations.Test;
import utils.Driver;

public class DriverDeneme {

    AndroidDriver<AndroidElement> driver = Driver.getAndroidDriver();
    @Test
    public void test01(){

        System.out.println(driver.getDeviceTime());

    }
}
