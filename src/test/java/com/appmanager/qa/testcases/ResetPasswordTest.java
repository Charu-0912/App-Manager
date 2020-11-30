package com.appmanager.qa.testcases;

import com.appmanager.qa.base.TestBase;
import com.appmanager.qa.pages.*;
import com.relevantcodes.extentreports.ExtentTest;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ResetPasswordTest extends TestBase {

    private LoginPage loginPage;
    private HomePage homePage;
    ResetPasswordPage ResetPassword;
    private ExtentTest logger;
    String url;
    Logger log = Logger.getLogger(ResetPasswordTest.class);

    public ResetPasswordTest() {

        super();
    }

    @BeforeMethod

    public void setup() {

        initialization();
        loginPage = new LoginPage(driver, logger);
        loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
        homePage = new HomePage(driver, logger);
        ResetPassword = new ResetPasswordPage(driver, logger);
    }
    @Test(priority=1)
    public void verifyResetPasswordUIText() {
        SoftAssert softAssert = new SoftAssert();
        url = homePage.clickOnResetPasswordLink();
        String uiText1 = ResetPassword.ResetPasswordUIText1();
        softAssert.assertEquals(uiText1,"Reset Password");
        String uiText2 = ResetPassword.ResetPasswordUIText2();
        softAssert.assertEquals(uiText2,"| enter below information to reset your password.");
        softAssert.assertAll();
    }

    @Test(priority = 2)
    public void verifyNewPasswordFieldValidation(){
        SoftAssert softAssert = new SoftAssert();
        url = homePage.clickOnResetPasswordLink();
        String validationError = ResetPassword.NewPasswordFieldValidationError();
        softAssert.assertEquals(validationError," The New Password field is required. ");

    }
    @Test(priority = 3)
    public void verifyConfirmPasswordFieldValidation(){
        SoftAssert softAssert = new SoftAssert();
        url = homePage.clickOnResetPasswordLink();
        String validationError = ResetPassword.ConfirmPasswordFieldValidationError();
        softAssert.assertEquals(validationError," The password and confirmation password do not match. ");

    }
    @Test(priority = 4)
    public void verifyResetPasswordFunctionality(){
        SoftAssert softAssert = new SoftAssert();
        url = homePage.clickOnResetPasswordLink();
        String validationError = ResetPassword.ResetPassword();
        softAssert.assertEquals(validationError," Your passsword has been reset successfully. ");

    }
    @AfterMethod
    public void teardown() {

        driver.quit();
    }

}