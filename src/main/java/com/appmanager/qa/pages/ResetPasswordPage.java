package com.appmanager.qa.pages;

import com.appmanager.qa.base.TestBase;
import com.appmanager.qa.helperclasses.BrowserHelper;
import com.appmanager.qa.helperclasses.CommonActions;
import com.appmanager.qa.helperclasses.LoggerHelper;
import com.relevantcodes.extentreports.ExtentTest;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResetPasswordPage extends TestBase {

    private WebDriver driver;
    private ExtentTest logger;
    private String url;
    private CommonActions commonActions;
    private BrowserHelper browserHelper;

    Logger log = LoggerHelper.getLogger(ResetPasswordPage.class);

    // Page factory for  Reset Password Page
    @FindBy(xpath = "//div[@class=\"col-sm-12\"]//h6")
    WebElement ResetPasswordUIText1;
    @FindBy(xpath = "//div[@class=\"col-sm-12\"]//span")
    WebElement ResetPasswordUIText2;
    @FindBy(xpath = "//div[@class=\"col-md-10\"]//input[@id=\"Email\"]")
    WebElement EmailField;
    @FindBy(xpath = "//div[@class=\"col-md-10\"]//input[@id=\"Password\"]")
    WebElement Password;
    @FindBy(xpath = "//div[@class=\"col-md-10\"]//input[@id=\"ConfirmPassword\"]")
    WebElement ConfirmPassword;
    @FindBy(xpath = "//div[@class=\"col-md-offset-2 col-md-10\"]//input[@type=\"submit\"]")
    WebElement ResetBtn;
    @FindBy(xpath = "//span[@for=\"Password\"]")
    WebElement NwePasswordValidation;
    @FindBy(xpath = "//span[@for=\"ConfirmPassword\"]")
    WebElement ConfirmPasswordValidation;
    @FindBy(xpath = "//div[@role=\"alert\"]//strong")
    WebElement ConfirmationMsg;

    // Initialize Page factory
    public ResetPasswordPage(WebDriver driver, ExtentTest logger) {
        this.driver = driver;
        this.logger = logger;
        commonActions = new CommonActions(driver, logger);
        browserHelper = new BrowserHelper(driver);
        log.info("Initialized page factory of Reset Password Page.");
        PageFactory.initElements(driver, this);

    }
    public String ResetPasswordUIText1() {
        return ResetPasswordUIText1.getText();
    }

    public String ResetPasswordUIText2() {
        return ResetPasswordUIText2.getText();
    }

    public String NewPasswordFieldValidationError(){
        commonActions.clear(Password);
        commonActions.isElementPresent(NwePasswordValidation);
        return commonActions.getText(NwePasswordValidation);
    }

    public String ConfirmPasswordFieldValidationError(){
        commonActions.clear(Password);
        commonActions.click(ResetBtn);
        return commonActions.getText(ConfirmPasswordValidation);
    }

    public String ResetPassword(){
        commonActions.clear(Password);
        commonActions.enterData(Password,"Aloha@123");
        commonActions.enterData(ConfirmPassword,"Aloha@1234");
        commonActions.click(ResetBtn);
        commonActions.isElementPresent(ConfirmationMsg);
        return commonActions.getText(ConfirmationMsg);

    }



}
