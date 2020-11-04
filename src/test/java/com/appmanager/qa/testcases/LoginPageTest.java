package com.appmanager.qa.testcases;

import com.appmanager.qa.pages.ForgotPasswordPage;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.appmanager.qa.base.TestBase;
import com.appmanager.qa.pages.HomePage;
import com.appmanager.qa.pages.LoginPage;
import com.relevantcodes.extentreports.ExtentTest;

public class LoginPageTest extends TestBase {

	LoginPage loginPage;
	HomePage homePage;
	private ExtentTest logger;
	Logger log = Logger.getLogger(LoginPageTest.class);

	public LoginPageTest() {

		super();
	}

	@BeforeMethod
	public void setup() {

		initialization();
		loginPage = new LoginPage(driver, logger);
	}

	@Test(priority = 1)
	public void verifyLoginPageLogo() {

		boolean flag = loginPage.loginPageLogo();
		Assert.assertTrue(flag);
		log.info("Logo displays at Home Page ");

	}
	
	@Test(priority = 2)
	public void verifyAppManagerLoginWithValidUsernameInvalidPassword() {

		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("invalidPassword"));
		String loginError = loginPage.errorMessage();
		log.info(loginError);

	}
	@Test(priority = 3)
	public void verifyAppManagerLoginWithInvalidUserNameInvalidPassword() {

		homePage = loginPage.login(prop.getProperty("invalidUsername"), prop.getProperty("invalidPassword"));
		String loginError = loginPage.errorMessage();
		log.info(loginError);

	}
	@Test(priority = 4)
	public void verifyAppManagerLoginWithInvalidUsernameValidPassword() {

		homePage = loginPage.login(prop.getProperty("invalidUsername"), prop.getProperty("Password"));
		String loginError = loginPage.errorMessage();

		log.info(loginError);


	}
	@Test(priority = 5)
	public void verifyAppManagerLoginWithValidUsernameValidPassword() {

		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		String urlAfterLogin = driver.getCurrentUrl();
		Assert.assertEquals(urlAfterLogin, "https://openmethodsqa.qa.openmethodscloud.com/Popflow/Index");

	}

	@Test(priority = 6)

	public void verifyForgotPasswordLink(){

		loginPage.clickOnForgotPassword();
	}
	@AfterMethod
	public void teardown() {

		driver.quit();
	}
}
