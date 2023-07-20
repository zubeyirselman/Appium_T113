package tests.Day_04;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.KiwiPage;
import utils.Driver;

import java.time.Duration;

public class KiwiCom {
    KiwiPage kiwi = new KiwiPage();

    AndroidDriver<AndroidElement> driver = Driver.getAndroidDriver();

    TouchAction touchAction = new TouchAction<>(driver);

    // uygulamanin yuklendigi dogrulanir
    // uygulamanin basariyla acildigi dogrulanir
    // misafir olarak devam et e tiklanir
    // ardinda gelecek olan 3 adimada yesil butona basilarak devam edilir
    // Trip type,one way olarak secilir
    // kalkis ulkesi secenegine tiklanir ve default olan ulke kaldirilir
    // kalkis yapilacak ulke/sehir girilir ve sec e tiklanir
    // varis ulkesi secenegine tiklanir ve gidilecek ulke girilir
    // gidis tarihi mayis ayinin 21 i olarak secilir ve set date e tiklanir
    // search butonuna tiklanir
    // en  ucuz ve aktarmasiz filtrelemeleri yapilir
    // gelen bilet fiyati kaydedilir ve kullanicin telefonuna sms olarak gonderilir

    @Test
    public void kiwiTest() throws InterruptedException {

        // uygulamanin yuklendigi dogrulanir
        Assert.assertTrue(driver.isAppInstalled("com.skypicker.main"));

        // uygulamanin basariyla acildigi dogrulanir
        //AndroidElement guest = driver.findElementByXPath("//*[@text='Continue as a guest']");
        Assert.assertTrue(kiwi.misafirDevamEt.isDisplayed());

        // misafir olarak devam et e tiklanir
        kiwi.misafirDevamEt.click();

        // ardinda gelecek olan 3 adimda da yesil butona basilarak devam edilir
        for(int i=0; i<3; i++){
            touchAction.press(PointOption.point(530,1686)).release().perform();
            Thread.sleep(1000);
        }

        // Trip type,one way olarak secilir
        Thread.sleep(1000);
        kiwi.koordinatTiklama(200,620,1000);
        kiwi.koordinatTiklama(200,1455,1000);

        // kalkis ulkesi secenegine tiklanir ve default olan ulke kaldirilir
        kiwi.koordinatTiklama(364,785,1000);
        kiwi.koordinatTiklama(1015,142,1000);

        // kalkis yapilacak ulke/sehir girilir ve sec e tiklanir
        if (driver.isKeyboardShown()){
            driver.getKeyboard().pressKey("Antalya");
        }else {
            kiwi.fromKutucugu.sendKeys("Antalya");
        }
        Thread.sleep(1000);
        kiwi.koordinatTiklama(310,285,1000);
        kiwi.koordinatTiklama(800,975,1000);

        // varis ulkesi secenegine tiklanir ve gidilecek ulke girilir
        kiwi.koordinatTiklama(371,916,1000);
        if (driver.isKeyboardShown()){
            driver.getKeyboard().pressKey("Istanbul");
        }else {
            kiwi.fromKutucugu.sendKeys("Istanbul");
        }
        Thread.sleep(1000);
        kiwi.koordinatTiklama(270,287,2000);
        kiwi.koordinatTiklama(780,971,2000);

        // gidis tarihi agustos ayinin 23' u olarak secilir ve set date' e tiklanir
        kiwi.koordinatTiklama(425,1050,2000);
        touchAction.press(PointOption.point(650,1360))
                          .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                          .moveTo(PointOption.point(600,15))
                          .release()
                          .perform();
        kiwi.koordinatTiklama(538,900,2000);
        kiwi.koordinatTiklama(700,1700,2000);

        // search butonuna tiklanir
        kiwi.koordinatTiklama(527,1189,2000);

        // en  ucuz ve aktarmasiz filtrelemeleri yapilir
        kiwi.koordinatTiklama(250,250,2000);
        kiwi.koordinatTiklama(500,600,2000);
        kiwi.koordinatTiklama(516,258,2000);
        kiwi.koordinatTiklama(447,1451,2000);

        // gelen bilet fiyati kaydedilir ve kullanicin telefonuna sms olarak gonderilir
        String fiyat = kiwi.biletFiyati.getText();
        String phoneNumber = "5555555555";
        driver.sendSMS(phoneNumber,fiyat);

    }

}
