package com.appmanager.qa.pages;

import com.appmanager.qa.base.TestBase;
import com.appmanager.qa.helperclasses.BrowserHelper;
import com.appmanager.qa.helperclasses.CommonActions;
import com.appmanager.qa.helperclasses.LoggerHelper;
import com.relevantcodes.extentreports.ExtentTest;
import org.apache.log4j.Logger;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CRMAuthorizationPage extends TestBase {

    private WebDriver driver;
    private ExtentTest logger;
    private String url;
    private CommonActions commonActions;
    private BrowserHelper browserHelper;

    Logger log = LoggerHelper.getLogger(CRMAuthorizationPage.class);

    // Page factory for CRMAuthorization Page

    @FindBy(xpath = "//div[@class=\"col-sm-12\"]//h5")
    WebElement crmLoginPageUIText1;

    @FindBy(xpath = "//div[@class=\"col-sm-12\"]//span")
    WebElement crmLoginPageUIText2;

    @FindBy(xpath = "//div[@class=\"col-md-10\"]//span[@for=\"UserName\"]")
    WebElement usernameValidationErrorMsg;

    @FindBy(xpath = "//div[@class=\"col-md-10\"]//span[@for=\"Password\"]")
    WebElement passwordValidationErrorMsg;

    @FindBy(xpath = "//div[@class=\"alert alert-danger notification\"]")
    WebElement invalidLoginErrorMsg;

    @FindBy(xpath = "//div[@id=\"stage\"]//div[@class=\"alert alert-success notification\"]")
    WebElement crmLoginSuccessMsg;


    // Initialize page factory
    public CRMAuthorizationPage (WebDriver driver, ExtentTest logger) {
        this.driver = driver;
        this.logger = logger;
        commonActions = new CommonActions(driver, logger);
        browserHelper = new BrowserHelper(driver);
        log.info("Initialized page factory of CRMAuthorization Page.");
        PageFactory.initElements(driver, this);

    }

    public String crmLoginPageUIText(){
       String str1 = crmLoginPageUIText1.getText();
       String str2 = crmLoginPageUIText2.getText();
       System.out.println(str1+str2);
       String str3 = str1+str2;
       return str3;

    }

    public String emptyUsernameValidation(){
        String s=  usernameValidationErrorMsg.getText();
        return s;
    }
    public String emptyPasswordValidation(){
        String s=  passwordValidationErrorMsg.getText();
        return s;
    }
    public String invalidLoginErrorMessage(){
        String s=  invalidLoginErrorMsg.getText();
        return s;
    }
    public String successMessageForLogin(){

        String s=  crmLoginSuccessMsg.getText();
        return s;
    }

}
