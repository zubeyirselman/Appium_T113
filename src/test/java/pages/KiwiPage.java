package pages;

import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Driver;

public class KiwiPage {

    public KiwiPage(){
        PageFactory.initElements((WebDriver) Driver.getAndroidDriver(),this);
    }

    @FindBy(xpath = "//*[@text='Continue as a guest']")
    public WebElement misafirDevamEt;

    public void koordinatTiklama(int xKoordinat, int yKoordinat, int bekleme) throws InterruptedException {
        TouchAction touchAction = new TouchAction<>(Driver.getAndroidDriver());
        touchAction.press(PointOption.point(xKoordinat, yKoordinat)).release().perform();
        Thread.sleep(bekleme);
    }

    @FindBy(xpath = "(//*[@class='android.view.View'])[4]")
    public WebElement fromKutucugu;

    @FindBy(xpath = "(//*[@class='android.widget.TextView'])[12]")
    public WebElement biletFiyati;

}
