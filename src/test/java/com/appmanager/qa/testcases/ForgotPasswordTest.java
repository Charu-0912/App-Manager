package com.appmanager.qa.testcases;

import com.appmanager.qa.base.TestBase;
import com.appmanager.qa.pages.ForgotPasswordPage;
import com.appmanager.qa.pages.LoginPage;
import com.relevantcodes.extentreports.ExtentTest;
import org.apache.log4j.Logger;
import org.testng.Assert.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ForgotPasswordTest extends TestBase {

    private ForgotPasswordPage forgotPassword;
    private LoginPage loginPage;
    private ExtentTest logger;
    private String url;
    Logger log = Logger.getLogger(ForgotPasswordTest.class);

    public ForgotPasswordTest() {

        super();
    }

    @BeforeMethod
    public void setup() {

        initialization();
        loginPage = new LoginPage(driver, logger);
        forgotPassword = new ForgotPasswordPage(driver,logger);
    }

    @Test(priority = 1)
    public void verifyForgotPasswordLinkIsFunctional(){
        loginPage.clickOnForgotPassword();

    }

    @Test(priority = 2)

    public void verifyForgotPasswordPageUI(){
        SoftAssert softAssert = new SoftAssert();
        loginPage.clickOnForgotPassword();
        String uiText1 = forgotPassword.forgotPasswordUIText1();
        softAssert.assertEquals(uiText1,"Forgot Password?","UI Text"+uiText1+ "not found");

        String uiText2 = forgotPassword.forgotPasswordUIText2();
        softAssert.assertEquals(uiText2,"Enter your email to receive a password reset link.","UI Text Not found");

        String textFiledLabel = forgotPassword.lableText();
        softAssert.assertEquals(textFiledLabel,"Email","Text field Label is incorrect");

        softAssert.assertAll();
    }

    @Test(priority = 3)

    public void verifySubmitEmailIsFunctional(){
        SoftAssert softAssert = new SoftAssert();
        loginPage.clickOnForgotPassword();
        String url1 = forgotPassword.submitEmail();
        softAssert.assertEquals(url1,"Password Reset Started. - Openmethods","Not able to submit email for forgot password");
        String url2 = forgotPassword.returnToLoginPage();
        softAssert.assertEquals(url2,"OpenMethods Login - Openmethods","Return To Logoin url is not functional ");
        softAssert.assertAll();
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }


}
