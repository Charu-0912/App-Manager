package com.appmanager.qa.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.appmanager.qa.base.TestBase;
import com.appmanager.qa.helperclasses.BrowserHelper;
import com.appmanager.qa.helperclasses.CommonActions;
import com.appmanager.qa.helperclasses.LoggerHelper;
import com.relevantcodes.extentreports.ExtentTest;

public class HomePage extends TestBase {

	private WebDriver driver;
	private ExtentTest logger;
	private String url;
	private CommonActions commonActions;
	private BrowserHelper browserHelper;

	Logger log = LoggerHelper.getLogger(HomePage.class);

	// Page factory for Home Page

	@FindBy(xpath = "//span[contains(text(),\"OPENMETHODSQA\")]")
	WebElement customerTitle;

	@FindBy(xpath = "//img[@src=\"/Content/images/logo-om-cloud-manager.png\" and @alt=\"OpenMethods Application Manager\"]")
	WebElement logoImageAtHomePage;

	@FindBy(xpath = "//img[@src=\"/Content/images/btn-menu-close2.png\"]")
	WebElement expandCollapseIcon;

	@FindBy(xpath = "//a[@id=\"navbarDropdown\"]")
	WebElement dropDownButton;

	@FindBy(xpath = "//span[text()=\"Version 6.1\"]")
	WebElement versionInfo;

	@FindBy(xpath = "//div[@id=\"stage\"]//ul[@class=\"navbar-nav flex-row ml-md-auto d-none d-md-flex\"]")
	WebElement MainPanel;

	@FindBy(xpath = "//div[@class=\"dropdown-menu show\"]//a")
	WebElement dropdownSet;

	@FindBy(xpath = "//div[@class=\"left-nav\"]")
	WebElement LeftPanel;

	@FindBy(xpath = "//span//img[@data-toggle=\"tooltip\"]")
	WebElement ToggelTooltip;
	// Initialize page factory
	public HomePage(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;
		commonActions = new CommonActions(driver, logger);
		browserHelper = new BrowserHelper(driver);
		log.info("Initialized page factory of Home Page.");
		// logger.info("Initialized page factory of OSC Home Page.");
		PageFactory.initElements(driver, this);

	}

	public boolean homePageLogo() {
		return logoImageAtHomePage.isDisplayed();
	}

	public String customerTitleAtHomePage() {
		return customerTitle.getText();
	}

	public String clickOnExternalLoginLink() {
		commonActions.click(dropDownButton);
		List<WebElement> list = driver.findElements(By.xpath("//div[@class=\"dropdown-menu show\"]//a"));
		System.out.println(list.size());
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getText().contains(" External Logins")) {
				list.get(i).click();
				break;
			}
		}
		return url = driver.getCurrentUrl();

	}

	public String clickOnResetPasswordLink() {
		commonActions.click(dropDownButton);
		List<WebElement> list = driver.findElements(By.xpath("//div[@class=\"dropdown-menu show\"]//a"));
		System.out.println(list.size());
		for (int i = 0; i < list.size(); i++) {

			if (list.get(i).getText().contains(" Reset Password")) {
				list.get(i).click();
				break;
			}

		}
		return url = driver.getCurrentUrl();

	}

	public void clickOnAboutLink() {
		commonActions.click(dropDownButton);
		List<WebElement> list = driver.findElements(By.xpath("//div[@class=\"dropdown-menu show\"]//a"));
		System.out.println(list.size());
		for (int i = 0; i < list.size(); i++) {

			if (list.get(i).getText().contains(" About")) {
				list.get(i).click();
				break;
			}

		}

	}

	public String toggelTooltip(){
		commonActions.isElementPresent(ToggelTooltip);
		return ToggelTooltip.getText();

	}

	public String logoutAppManager() {
		commonActions.click(dropDownButton);
		List<WebElement> list = driver.findElements(By.xpath("//div[@class=\"dropdown-menu show\"]//a"));
		System.out.println(list.size());
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getText().contains(" Logout")) {
				list.get(i).click();
				break;
			}
		}
		return url = driver.getCurrentUrl();

	}
}
