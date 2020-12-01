package com.appmanager.qa.testcases;

import com.appmanager.qa.base.TestBase;
import com.appmanager.qa.pages.CRMAuthorizationPage;
import com.appmanager.qa.pages.ExternalLogins;
import com.appmanager.qa.pages.HomePage;
import com.appmanager.qa.pages.LoginPage;
import com.relevantcodes.extentreports.ExtentTest;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CRMAuthorizationTest  extends TestBase {

    private LoginPage loginPage;
    private HomePage homePage;
    CRMAuthorizationPage CRMAuthorization;
    ExternalLogins externalLogin;
    private ExtentTest logger;
    String url;
    Logger log = Logger.getLogger(LoginPageTest.class);

    public CRMAuthorizationTest() {

        super();
    }

    @BeforeMethod

    public void setup() {

        initialization();
        loginPage = new LoginPage(driver,logger);
        loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
        homePage = new HomePage(driver,logger);
        externalLogin = new ExternalLogins(driver,logger);
        CRMAuthorization = new CRMAuthorizationPage(driver,logger);

    }

    @Test(priority = 1)
    public void verifyCrmUIText()
    {
        url = homePage.clickOnExternalLoginLink();
        externalLogin.clickOnCrmReAuthorizationButton();
        String str1 = CRMAuthorization.crmLoginPageUIText();
        Assert.assertEquals(str1,"CRM AuthorizationEnter Your CRM Credentials.");
        log.info("CRM UI Text is visible");
    }

    @Test(priority = 2)
    public void verifyCrmLoginWithValidUsernameInvalidPassword()
    {
        url = homePage.clickOnExternalLoginLink();
        externalLogin.clickOnCrmReAuthorizationButton();
        CRMAuthorization = externalLogin.crmLogin(prop.getProperty("crmUsername"), prop.getProperty("invalidCrmPassword"));
        String crmLoginError = CRMAuthorization.invalidLoginErrorMessage();
        log.info(crmLoginError);
    }

    @Test(priority = 3)
    public void verifyCrmLoginWithInvalidUsernameInvalidPassword()
    {
        url = homePage.clickOnExternalLoginLink();
        externalLogin.clickOnCrmReAuthorizationButton();
        CRMAuthorization = externalLogin.crmLogin(prop.getProperty("invalidCrmUsername"), prop.getProperty("invalidCrmPassword"));
        String crmLoginError = CRMAuthorization.invalidLoginErrorMessage();
        log.info(crmLoginError);
    }
    @Test(priority = 4)
    public void verifyCrmLoginWithInvalidUsernameValidPassword()
    {
        url = homePage.clickOnExternalLoginLink();
        externalLogin.clickOnCrmReAuthorizationButton();
        CRMAuthorization = externalLogin.crmLogin(prop.getProperty("invalidCrmUsername"), prop.getProperty("crmPassword"));
        String crmLoginError = CRMAuthorization.invalidLoginErrorMessage();
        log.info(crmLoginError);
    }

    @Test(priority = 5)
    public void verifyCrmLoginWithEmptyUserName()
    {
        url = homePage.clickOnExternalLoginLink();
        externalLogin.clickOnCrmReAuthorizationButton();
        externalLogin.emptyUsernameLogin();
        String crmLoginError = CRMAuthorization.emptyUsernameValidation();
        log.info(crmLoginError);
    }

    @Test(priority = 6)
    public void verifyCrmLoginWithEmptyPassword()
    {
        url = homePage.clickOnExternalLoginLink();
        externalLogin.clickOnCrmReAuthorizationButton();
        externalLogin.emptyPassLogin();
        //CRMAuthorization = externalLogin.crmLogin(prop.getProperty("crmUsername"), prop.getProperty("empty"));
        String crmLoginError = CRMAuthorization.emptyPasswordValidation();
        log.info(crmLoginError);
    }
    @Test(priority = 7)
    public void verifyCrmLoginWithValidUsernameValidPassword()
    {
        url = homePage.clickOnExternalLoginLink();
        externalLogin.clickOnCrmReAuthorizationButton();
        CRMAuthorization = externalLogin.crmLogin(prop.getProperty("crmUsername"), prop.getProperty("crmPassword"));
        String crmSuccessMsg = CRMAuthorization.successMessageForLogin();
        log.info(crmSuccessMsg);
    }

    @AfterMethod
    public void teardown() {

        driver.quit();
    }
}
