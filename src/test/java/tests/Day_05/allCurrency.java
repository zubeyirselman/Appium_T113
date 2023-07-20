package tests.Day_05;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AllCurrency;
import utils.Driver;
import utils.ReusableMethods;

import java.io.File;
import java.io.IOException;

public class allCurrency {
    AndroidDriver<AndroidElement> driver = Driver.getAndroidDriver();
    AllCurrency currency = new AllCurrency();

    // all currency uygulamasinin yuklendigi dogulanir
    // uygulamanin acildigi dogrulanir
    // cevirmek istedigimiz para birimi zloty olarak secilir
    // cevirelecek olan para birimi Tl olarak secilir
    // cevrilen tutar screenShot olarak kaydedilir
    // Ardindan zloty nin tl karsiligi olan tl degeri kaydedilir
    // bu islem dolar-tl, sweden kron-tl, Japon yeni-tl olarak tekrarlanir
    // ve kullaniciya sms olarak bildirilir

    @Test
    public void allCurrency() throws InterruptedException, IOException {

        // all currency uygulamasinin yuklendigi dogulanir
        Assert.assertTrue(driver.isAppInstalled("com.smartwho.SmartAllCurrencyConverter"));

        // uygulamanin acildigi dogrulanir
        Assert.assertTrue(currency.currencyText.isDisplayed());

        // cevirmek istedigimiz para birimi zloty olarak secilir
        ReusableMethods.koordinatTiklama(500,345,1000);
        ReusableMethods.scrollWithUiScrollable("PLN");

        // cevirelecek olan para birimi Tl olarak secilir
        ReusableMethods.koordinatTiklama(500,493,1000);
        ReusableMethods.scrollWithUiScrollable("TRY");

        // cevrilen tutar screenShot olarak kaydedilir
        currency.bir.click();
        currency.ucSifir.click();

        /* === Manuel ScreenShot Satiri ===
        File fileSs = driver.getScreenshotAs(OutputType.FILE); // ScreenShot' u ceken satir
        FileUtils.copyFile(fileSs,new File("zlotyToTl.jpg"));
         */

        // Method ile ScreenShot Alma Satiri
        ReusableMethods.getScreenshot("zlotyToTl");

        // Ardindan zloty nin tl karsiligi olan tl degeri kaydedilir
        // ve kullaniciya sms olarak bildirilir
        String finalExchange = currency.sonuc.getText();
        String phoneNumber = "5554446789";
        driver.sendSMS(phoneNumber,finalExchange);


    }

}
