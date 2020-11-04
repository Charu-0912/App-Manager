package com.appmanager.qa.helperclasses;

import static java.awt.event.KeyEvent.VK_N;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;

public class CommonActions {

	private WebDriverWait webDriverWait;
	private WebDriver driver;
	public ExtentTest logger;

	Logger log = LoggerHelper.getLogger(CommonActions.class);

	public CommonActions(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;
	}

	public CommonActions(ExtentTest logger) {
		this.logger = logger;
	}

	public WebDriverWait waitUntilElementIsVisible(WebDriver driver, int sec, WebElement element) {
		webDriverWait = new WebDriverWait(driver, sec);
		webDriverWait.until(ExpectedConditions.visibilityOf(element));
		return webDriverWait;
	}

	public WebDriverWait waitUntilVisibilityOfElementLocated(WebDriver driver, By by, int sec) {
		webDriverWait = new WebDriverWait(driver, sec);
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(by));
		return webDriverWait;
	}

	public void waitUntilElementToBeClickable(WebDriver driver, int sec, WebElement element) {
		webDriverWait = new WebDriverWait(driver, sec);
		webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void waitFor(int mSec) {
		try {
			Thread.sleep(mSec);
		} catch (InterruptedException e) {
			log.info("Timeout exception occuured : " + e.getMessage());
		}
	}

	public boolean isAlertPresent() {
		boolean presentFlag = false;
		try {
			Alert alert = driver.switchTo().alert();
			presentFlag = true;
			alert.accept();
		} catch (NoAlertPresentException ex) {
			log.info("Alert is not present. : " + ex);
		}
		return presentFlag;
	}

	public boolean isElementPresent(WebElement element) {
		try {
			boolean flag = element.isDisplayed();
			log.info("Element " + element.toString() + " present on UI is:\t" + flag);
			return flag;
		} catch (Exception e) {
			log.info("Element is not present on UI and exception occured." + e);
			return false;
		}
	}

	public boolean isElementEnabled(WebElement element) {
		return element.isEnabled();
	}

	public boolean isElementSelected(WebElement element) {
		return element.isSelected();
	}

	public boolean isElementPresent(WebDriver driver, By by, int sec) {
		try {
			waitUntilVisibilityOfElementLocated(driver, by, sec);
			WebElement element = driver.findElement(by);
			boolean flag = element.isDisplayed();
			log.info("Element " + element.toString() + " present on UI is:\t" + flag);
			return flag;
		} catch (Exception e) {
			log.info("Element is not present on UI and excepton occured." + e);
			return false;
		}
	}

	public boolean isRadioBtnElementSelected(WebElement element) {

		boolean result = element.isSelected();
		String data = element.toString();
		data = data.substring(data.lastIndexOf('/') + 3).replace(']', ' ').trim();
		log.info("" + "'" + data + "'" + " " + "radio button is selected: " + result);
		if (!result) {
			log.info("Actual " + "'" + data + "'" + " radio button status is: " + result);
		}
		return result;
	}

	public void click(WebElement element) {
		if (isElementPresent(element)) {
			element.click();
		} else {
			log.info("Element is not present and click on element is not perform.");
			System.out.println("Element is not present and click on element is not perform.");
		}
	}
	
	public void clickbymouseHover(WebElement element) {
        try {
            if (isElementPresent(element)) {
                Actions builder = new Actions(driver);
                builder.moveToElement(element).build().perform();
                element.click();
            } else {
                assertFail("Location doesn't found. Loc-" + element);
            }
        } catch (Exception e) {
            if (isElementPresent(element)) {
            	 element.click();
            } else {
                assertFail("Location doesn't found. Loc-" + element);
            }
        }
    }

	public void clickOnElementUsingJavaScript(WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		if (isElementPresent(element)) {
			executor.executeScript("arguments[0].click();", element);
		} else {
			log.info("Element is not present and click on element is not perform.");
			System.out.println("Element is not present and click on element is not perform.");
		}
	}

	public void enterData(WebElement element, String data) {
		if (isElementPresent(element)) {
			element.sendKeys(data);
		} else {
			log.info("Element is not present and unable to send the data to element.");
			System.out.println("Element is not present and unable to send the data to element.");
		}
	}

	public void clear(WebElement element) {
		if (isElementPresent(element)) {
			element.clear();
		} else {
			log.info("Element is not present and unable to clear the element data.");
			System.out.println("Element is not present and unable to clear the element data.");
		}
	}

	public String getText(WebElement element) {
		String text = "";
		if (isElementPresent(element)) {
			text = element.getText();
			log.info("Text is:\t" + text);
		} else {
			log.info("Element is not present and unable to get the text from element.");
			System.out.println("Element is not present and unable to get the text from element.");
		}
		return text;
	}

	public String getTextFromElementUsingAttribute(WebElement element, String attribute, WebDriver driver) {
		if (isElementPresent(element)) {
			String text = element.getAttribute(attribute);
			return text;
		} else {
			assertFail("Unable to get text from element. -" + element);
			return null;
		}
	}

	public String getTitle() {
		String title = "";
		title = driver.getTitle();
		log.info("Title is:\t" + title);
		return title;
	}

	public void minimizeApp() {
		Robot robot;
		try {
			robot = new Robot();
			robot.keyPress(KeyEvent.VK_ALT);
			robot.keyPress(KeyEvent.VK_SPACE);
			robot.keyPress(VK_N);
		} catch (AWTException e) {
			System.out.println("Exception occurred when minimizing the app." + e);
		}
	}

	public void switchingWindowFocusUsingActionsClass(WebDriver driver) {
		try {
			Actions actions = new Actions(driver);
			actions.keyDown(Keys.ALT).sendKeys(Keys.TAB).keyUp(Keys.ALT).perform();
			//actions.sendKeys(Keys.chord(Keys.ALT, Keys.TAB)).build().perform();
			System.out.println("Switching tab performed.");
		} catch (Exception e) {
			System.out.println("Exception occurred when minimizing the app." + e);
		}
	}
	
	public void switchingWindowFocusUsingRobotClass(WebDriver driver) {
		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_ALT);
			robot.keyPress(KeyEvent.VK_TAB);
			this.waitFor(1000);
			robot.keyRelease(KeyEvent.VK_ALT);
			robot.keyRelease(KeyEvent.VK_TAB);
			System.out.println("Switching tab performed.");
		} catch (Exception e) {
			System.out.println("Exception occurred when minimizing the app." + e);
		}
	}

	public String getSelectedDropDownValue(WebElement element) {
		String selecteddropDownValue = "";
		if (isElementPresent(element)) {
			Select dropDownOption = new Select(element);
			WebElement selectdropDownValue = dropDownOption.getFirstSelectedOption();
			selecteddropDownValue = selectdropDownValue.getText();
		} else {
			log.info("Element is not present and unable to get the selected drop down value.");
			System.out.println("Element is not present and unable to get the selected drop down value.");
		}
		return selecteddropDownValue;
	}

	public void selectDropDownByUsingValue(WebElement element, String value) {
		String selecteddropDownValue = "";
		if (isElementPresent(element)) {
			selecteddropDownValue = getSelectedDropDownValue(element);
			log.info("Default selected drop down value is: \t" + selecteddropDownValue);
			Select dropDownOption = new Select(element);
			dropDownOption.selectByValue(value);
			waitFor(2000);
			selecteddropDownValue = getSelectedDropDownValue(element);
			log.info("Selected drop down value is: \t" + selecteddropDownValue);
		} else {
			log.info("Element is not present and unable to select the drop down value.");
			System.out.println("Element is not present and unable to select the drop down value.");
		}
	}

	public void selectDropDownUsingVisibleText(WebElement element, String value) {
		String selecteddropDownValue = "";
		if (isElementPresent(element)) {
			selecteddropDownValue = getSelectedDropDownValue(element);
			log.info("Default selected drop down value is: \t" + selecteddropDownValue);
			Select dropDownOption = new Select(element);
			dropDownOption.selectByVisibleText(value);
			waitFor(2000);
			selecteddropDownValue = getSelectedDropDownValue(element);
			log.info("Selected drop down value is: \t" + selecteddropDownValue);
		} else {
			log.info("Element is not present and unable to select the drop down value using visible text.");
			System.out.println("Element is not present and unable to select the drop down value using visible text.");
		}
	}

	// Assertion methods.

	public String passMsg(String msg) {
		return "PASS :" + msg;
	}

	public String failMsg(String msg) {
		return "FAIL :" + msg;
	}

	

	public void assertFail(String failMsg) {
		Assert.fail(failMsg);
	}

	public void verifyAssertEqual(String actual, String expected, String passMsg, String failMsg) {
		try {
			Assert.assertEquals(actual, expected, failMsg);
			log.info(passMsg(passMsg));
		} catch (Error e) {
			verifyAsserFailWithException(failMsg, e);
		}
	}

	public void verifyAssertTrue(boolean condition, String passMsg, String failMsg) {
		try {
			Assert.assertTrue(condition, failMsg(failMsg));
			log.info(passMsg(passMsg));
		} catch (Error e) {
			verifyAsserFailWithException(failMsg, e);
		}

	}

	public void verifyAssertFalse(boolean condition, String passMsg, String failMsg) {
		try {
			Assert.assertFalse(condition, failMsg);
			log.info(passMsg(passMsg));
		} catch (Error e) {
			verifyAsserFailWithException(failMsg, e);
		}
	}

	public void verifyAssertEquals(List<String> actual, List<String> expected, String passMsg, String failMsg) {
		try {
			Assert.assertEquals(actual, expected, passMsg);
			log.info(passMsg(passMsg));
		} catch (Error e) {
			verifyAsserFailWithException(failMsg, e);
		}
	}

	public boolean stringCompare(WebElement element, String expectedString) {
		String actualString = getText(element);
		if (actualString.equalsIgnoreCase(expectedString))
			return true;
		else {
			log.info("Actual message is: " + actualString);
			log.info("Expected message is: " + expectedString);
			assertFail("Actual and Expected string is not same.");
			return false;
		}
	}

	public void verifyAsserFailWithException(String msg, Error e) {
		log.info(failMsg(msg));
		// log.info(e.getMessage());
		Assert.fail(failMsg(msg));
	}

	public void verifyAssertFalse(boolean condition, String failMsg) {
		try {
			Assert.assertFalse(condition, failMsg);
		} catch (Error e) {
			verifyAsserFailWithException(failMsg, e);
		}
	}

	public void verifyAssertTrue(boolean condition, String passMsg) {
		try {
			Assert.assertTrue(condition, passMsg);
		} catch (Exception e) {
			log.info("Error occurred while verifying condition: " + e.getMessage());
		}
	}

	public void scrollUP(JavascriptExecutor js) {
		js.executeScript("scroll(0, -250);");
	}

	public void scrollDown(JavascriptExecutor js) {
		js.executeScript("scroll(0, 250);");
	}

	public void scrollByLine(JavascriptExecutor js) {
		js.executeScript("window.scrollByLines(2)");
	}

	public void scrollDownToElementDisplayed(JavascriptExecutor js, WebElement element, WebDriver driver) {
		int count = 1;
		while (count <= 10) {
			if (!element.isDisplayed()) {
				scrollDown(js);
				waitFor(1000);
			}
			count++;
		}
	}

	public void scrollUpToElementDisplayed(JavascriptExecutor js, WebElement element, WebDriver driver) {
		int count = 1;
		while (count <= 10) {
			if (!element.isDisplayed()) {
				scrollUP(js);
				waitFor(1000);
			}
			count++;
		}
	}

	public void onStartTestExecution(String method) {
		log.info("********** Started Test " + method + " Execution ********** ");
	}

	public void onEndTestExecution(String method) {
		log.info("********** Ended Test " + method + " Execution ********** ");
	}

}
