package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Driver;

public class AllCurrency {
    public AllCurrency(){
        PageFactory.initElements((WebDriver) Driver.getAndroidDriver(),this);
    }

    @FindBy(xpath = "//*[@text='CURRENCY CONVERTER']")
    public WebElement currencyText;

    @FindBy(id = "com.smartwho.SmartAllCurrencyConverter:id/b1")
    public WebElement bir;

    @FindBy(id="com.smartwho.SmartAllCurrencyConverter:id/b000")
    public WebElement ucSifir;

    @FindBy(id="com.smartwho.SmartAllCurrencyConverter:id/EditTextCurrencyB")
    public WebElement sonuc;
}
