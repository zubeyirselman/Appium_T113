package tests.Day_03;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class ArabamComTest {

    // Arabam kac para bolumune tiklayalim
    // Aracimin fiyatini merak ediyorum bolumune tiklayalim
    // Wolkswagen markasini secelim
    // yil secimi yapalim
    // model secimi yapalim
    // govde tipini secelim
    // yakit tipini secelim
    // vites tipini secelim
    // Versiyon secimi yapalim
    // aracin km bilgilerini girelim
    // aracin rengini secelim
    // opsiyel donanim (varsa) seecelim
    // degisen bilgisi ekleyerek tramer kaydi belirtelim
    // aracimizin fiyatinin 500.000 tl den fazla oldugunu test edelim
    // uygulamayi kapatalim

    AndroidDriver<AndroidElement> driver;

    @BeforeTest
    public void arabamTestSetup() throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"Pixel 2");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,"10.0");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"UiAutomator2");
        // Hangi uygulamada calismak istiyorsak apk infodan o uygulamanin degerini aliyoruz,
        capabilities.setCapability("appPackage","com.dogan.arabam");
        // Uygulamayi actigimizda hangi sayfada baslamasini istiyorsak
        capabilities.setCapability("appActivity","com.dogan.arabam.presentation.feature.home.HomeActivity");

        driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @Test
    public void arabamTest() throws InterruptedException {

        // Uygulamanin basarili bir sekilde yuklendigi dogrulanir
        Assert.assertTrue(driver.isAppInstalled("com.dogan.arabam"));

        // Uygulamanin basarili bir sekilde acildigi dogrulanir
        AndroidElement arabamLogo = driver.findElementById("com.dogan.arabam:id/ivArabamLogo");
        Assert.assertTrue(arabamLogo.isDisplayed());

        // Arabam kac para bolumune tiklayalim
        AndroidElement arabamKacPara = driver.findElementByXPath("//*[@text='Arabam kaç para?']");
        arabamKacPara.click();

        // Aracimin fiyatini merak ediyorum bolumune tiklayalim
        driver.findElementByXPath("//*[@text='Aracımın fiyatını merak ediyorum']").click();

        // Wolkswagen markasini secelim
        TouchAction touchAction = new TouchAction<>(driver);
        touchAction.press(PointOption.point(531,1669))
                         .waitAction(WaitOptions.waitOptions(Duration.ofMillis(400)))
                         .moveTo(PointOption.point(531,465)).release().perform();
        driver.findElementByXPath("//*[@text='Volkswagen']").click();

        // yil secimi yapalim
        driver.findElementByXPath("//*[@text='2015']").click();

        // model secimi yapalim
        driver.findElementByXPath("//*[@text='CC']").click();

        // govde tipini secelim
        driver.findElementByXPath("//*[@text='Coupe']").click();

        // yakit tipini secelim
        driver.findElementByXPath("//*[@text='Benzin']").click();

        // vites tipini secelim
        driver.findElementByXPath("//*[@text='Yarı Otomatik']").click();

        // Versiyon secimi yapalim
        Thread.sleep(2000);
        touchAction.press(PointOption.point(160,1353)).release().perform();

        // aracin km bilgilerini girelim ve devam tusuna basalim
        if (driver.isKeyboardShown()){
            driver.getKeyboard().pressKey("50000");
        } else {
            driver.findElementById("com.dogan.arabam:id/et_km").sendKeys("50000");
        }
        driver.findElementByXPath("//*[@text='Devam']").click();

        // aracin rengini secelim
        Thread.sleep(2000);
        touchAction.press(PointOption.point(535,1775))
                         .waitAction(WaitOptions.waitOptions(Duration.ofMillis(300)))
                         .moveTo(PointOption.point(495,20))
                         .release()
                         .perform();
        driver.findElementByXPath("//*[@text='Yeşil (metalik)']").click();

        // opsiyel donanim (varsa) seecelim
        // sectigimiz aracta olmadıgı icin bos kaldi bu bolum

        // degisen bilgisi ekleyerek tramer kaydi belirtelim
        driver.findElementById("com.dogan.arabam:id/iv_B01101").click();
        driver.findElementByXPath("(//*[@text='Boyalı'])[2]").click();
        driver.findElementById("com.dogan.arabam:id/iv_B01201").click();
        driver.findElementByXPath("(//*[@text='Boyalı'])[2]").click();
        driver.findElementByXPath("//*[@text='Devam']").click();

        // tramer kaydi secimini yapalim
        driver.findElementById("com.dogan.arabam:id/rbHasNoTramerEntry").click();
        driver.findElementById("com.dogan.arabam:id/btnNext").click();

        // aracimizin fiyatinin 500.000 tl den fazla oldugunu test edelim
        String fiyat = driver.findElementById("com.dogan.arabam:id/tvAveragePrice").getText();
        fiyat = fiyat.replaceAll("\\D","");
        System.out.println(fiyat);

        Assert.assertTrue(Integer.parseInt(fiyat)>500000);
        // uygulamayi kapatalim
        driver.closeApp();

    }

}
