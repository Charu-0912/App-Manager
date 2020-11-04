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
import org.testng.asserts.SoftAssert;

public class ExternalLoginsTest extends TestBase {


    private LoginPage loginPage;
    private HomePage homePage;
    private ExternalLogins ExternalLogin;
    private CRMAuthorizationPage CRMAuthorization;
    private ExtentTest logger;
    private String url;
    Logger log = Logger.getLogger(ExternalLoginsTest.class);

    public ExternalLoginsTest() {

        super();
    }

    @BeforeMethod
    public void setup() {

        initialization();
        loginPage = new LoginPage(driver,logger);
        loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
        homePage = new HomePage(driver,logger);
        ExternalLogin = new ExternalLogins(driver,logger);
        CRMAuthorization = new CRMAuthorizationPage(driver,logger);


    }

    @Test(priority=1)
    public void verifyExternalLoginUIText() {
        SoftAssert softAssert = new SoftAssert();
        url = homePage.clickOnExternalLoginLink();
        String uiText1 = ExternalLogin.externalLoginUIText1();
        softAssert.assertEquals(uiText1,"Registered Logins");
        String uiText2 = ExternalLogin.externalLoginUIText2();
        softAssert.assertEquals(uiText2,"| below is a list of instances. CRM credentials are used to access data for PopFlow.");
        log.info("External login page text present:"+uiText1+uiText2);
        String str1 = ExternalLogin.accountTableUIColumn1();
        softAssert.assertEquals(str1, "ACCOUNT TYPE");
        log.info("External login page Table column present:"+str1);
        String str2 = ExternalLogin.accountTableUIColumn2();
        softAssert.assertEquals(str2, "ACCOUNT NAME");
        log.info("External login page Table column present:"+str2);
        String str3 = ExternalLogin.accountTableUIColumn3();
        softAssert.assertEquals(str3, "ACTIONS");
        log.info("External login page Table column present:"+str3);
        softAssert.assertAll();
    }

    @Test(priority=2)
    public void verifyCrmAuthorization(){
        url = homePage.clickOnExternalLoginLink();
        ExternalLogin.clickOnCrmAuthorizationButton();
        CRMAuthorization = ExternalLogin.crmLogin(prop.getProperty("crmUsername"), prop.getProperty("crmPassword"));
        String crmSuccessMsg = CRMAuthorization.successMessageForLogin();
        log.info(crmSuccessMsg);


    }
    @Test(priority=3)

    public void verifyCrmReAuthorization(){
        url = homePage.clickOnExternalLoginLink();
        ExternalLogin.clickOnCrmReAuthorizationButton();
        CRMAuthorization = ExternalLogin.crmLogin(prop.getProperty("crmUsername"), prop.getProperty("crmPassword"));
        String crmSuccessMsg = CRMAuthorization.successMessageForLogin();
        log.info(crmSuccessMsg);
    }

    @Test(priority=4)
    public void verifyRemoveCrmAuthorization(){
        url = homePage.clickOnExternalLoginLink();
        String s =  ExternalLogin.clickOnCrmRemoveAuthorization();
        Assert.assertEquals(s,"The external login was removed.");
    }

    @AfterMethod
    public void teardown() {

        driver.quit();
    }
}
