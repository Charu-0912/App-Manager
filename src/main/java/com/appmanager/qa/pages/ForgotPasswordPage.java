package com.appmanager.qa.pages;

import com.appmanager.qa.base.TestBase;
import com.appmanager.qa.helperclasses.BrowserHelper;
import com.appmanager.qa.helperclasses.CommonActions;
import com.appmanager.qa.helperclasses.LoggerHelper;
import com.relevantcodes.extentreports.ExtentTest;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ForgotPasswordPage extends TestBase {

    private ExtentTest logger;
    private WebDriver driver;
    private CommonActions commonActions;
    private BrowserHelper browserHelper;
    Logger log = LoggerHelper.getLogger(ForgotPasswordPage.class);

    //Page factory
    @FindBy(xpath="//form[@class=\"form-horizontal\"]//h3//b")
    WebElement uiText1;

    @FindBy(xpath="//form[@class=\"form-horizontal\"]//p")
    WebElement uiText2;

    @FindBy(xpath = "//div[@class=\"form-group\"]//label")
    WebElement labelEmail;

    @FindBy(xpath = "//div[@class=\"form-group\"]//input[@id=\"Email\"]")
    WebElement emailTextField;

    @FindBy(xpath = "//div//input[@type=\"submit\"]")
    WebElement emailLinkBtn;

    @FindBy(xpath = "//div[@class=\"login-options\"]//a[@href=\"/Account/Login\"]")
    WebElement loginLink;

    @FindBy(xpath = "//div[@class=\"login-options\"]//a[@href=\"/Account/ForgotPassword\"]")
    WebElement ForgotPasswordLink;


    //Initialize page factory
    public ForgotPasswordPage(WebDriver driver, ExtentTest logger){

        commonActions = new CommonActions(driver, logger);
        browserHelper = new BrowserHelper(driver);

        log.info("Initialized page factory of Forgot Password Page.");
        PageFactory.initElements(driver, this);

    }

    public String forgotPasswordUIText1(){
        return uiText1.getText();
    }

    public String forgotPasswordUIText2(){
        return uiText2.getText();
    }

    public String lableText(){
        return labelEmail.getText();
    }

    public String submitEmail(){
        commonActions.enterData(emailTextField,"amit@openmethods.com");
        commonActions.click(emailLinkBtn);
        return commonActions.getTitle();

    }

    public String returnToLoginPage(){

        commonActions.click(loginLink);
        return commonActions.getTitle();

    }

}
