package com.appmanager.qa.testcases;

import org.testng.annotations.Test;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.appmanager.qa.base.TestBase;
import com.appmanager.qa.pages.HomePage;
import com.appmanager.qa.pages.LoginPage;
import com.relevantcodes.extentreports.ExtentTest;


public class HomePageTest extends TestBase {

	private LoginPage loginPage;
	private HomePage homePage;
	private ExtentTest logger; 
	private String url; 
	Logger log = Logger.getLogger(HomePageTest.class);

	public HomePageTest() {

		super();
	}

	@BeforeMethod
	public void setup() {

		initialization();
		loginPage = new LoginPage(driver,logger);
		loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		homePage = new HomePage(driver,logger);

	}

	@Test(priority=1)
	public void verifyHomePageLogo() {
		boolean flag = homePage.homePageLogo();
		Assert.assertTrue(flag, "HomePage Logo is unavailable..");
	}

	@Test(priority=2)
	public void verifyCustomerNameDisplays() {
		String Title = homePage.customerTitleAtHomePage();
		Assert.assertEquals(Title, "OPENMETHODSQA");
	}

	@Test(priority=3)
	public void verifyExternalLoginLink() {
	    url = homePage.clickOnExternalLoginLink();
		Assert.assertEquals(url, "https://openmethodsqa.qa.openmethodscloud.com/Manage/ManageLogins", "Unable to navigate external login page ");
	}
	@Test(priority=4)
	public void verifyResetPasswordLink() {

		url = homePage.clickOnResetPasswordLink();
		Assert.assertEquals(url, "https://openmethodsqa.qa.openmethodscloud.com/Account/ResetUserPassword");
	}
	@Test(priority=5)
	public void verifyAboutLink() {

		homePage.clickOnAboutLink();
	}

	@Test(priority = 6)
	public void verifyToggleTooltip(){

		boolean flag = homePage.toggleTooltip();
		Assert.assertTrue(flag,"Toggle tooltip is missing");
	}

	@Test(priority=7)
	public void verifyAppManagerLogout() {

		url = homePage.logoutAppManager();
		Assert.assertEquals(url, "https://openmethodsqa.qa.openmethodscloud.com/Account/Login", "Not logged out properly");
	}
	

	@AfterMethod
	public void teardown() {

		driver.quit();
	}
}
