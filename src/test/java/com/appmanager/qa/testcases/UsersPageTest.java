package com.appmanager.qa.testcases;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.appmanager.qa.base.TestBase;
import com.appmanager.qa.pages.UsersPage;
import com.appmanager.qa.util.TestUtil;
import com.appmanager.qa.pages.LoginPage;
import com.relevantcodes.extentreports.ExtentTest;

public class UsersPageTest extends TestBase {

	private LoginPage loginPage;
	private UsersPage usersPage;
	private ExtentTest logger; 
	private String s; 
	

	Logger log = Logger.getLogger(HomePageTest.class);

	public UsersPageTest() {

		super();
	}

	@BeforeMethod
	public void setup() {

		initialization();
		loginPage = new LoginPage(driver,logger);
		loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		usersPage = new UsersPage(driver,logger);

	}

	@Test(priority=1)
	public void verifyInviteUserTest() {
		usersPage.inviteUser();
		
	}

	@Test(priority=2)
	public void verifySearchWithUserNameTest1() {

		usersPage.searchWithUsername(s);

	}
		
	@DataProvider
	public Object[][] getUsersData() {
		
		Object data[][] = TestUtil.getTestData("users");
		return data;
	}
	
	@Test(priority=3, dataProvider = "getUsersData")
	public void verifyInviteMultipleUsersTest(String firstname, String lastname, String emailaddress) {
		 
		usersPage.inviteMultipeUser(firstname, lastname, emailaddress);
		
	}
	
	@AfterMethod
	
		public void teardown() {

		driver.quit();
		}
	
	
	
}
