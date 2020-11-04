package com.appmanager.qa.helperclasses;

import java.util.LinkedList;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BrowserHelper {

	private WebDriver driver;
	Logger log = LoggerHelper.getLogger(BrowserHelper.class);

	public BrowserHelper(WebDriver driver) {
		this.driver = driver;
	}

	public void refresh() {
		driver.navigate().refresh();
	}

	public void goBack() {
		driver.navigate().back();
	}

	public void goForward() {
		driver.navigate().forward();
	}

	public void openNewTabWindow() {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.open()");
	}

	public Set<String> getWindowHandlens() {
		log.info("");
		return driver.getWindowHandles();
	}

	public void SwitchToWindow(int index) {

		LinkedList<String> windowsId = new LinkedList<String>(getWindowHandlens());
		log.info("Window Ids: " + windowsId.size());
		if (index < 0 || index > windowsId.size()) {
			throw new IllegalArgumentException("Invalid Index : " + index);
		}
		driver.switchTo().window(windowsId.get(index));
		log.info(index);
	}

	public void switchToParentWindow() {
		LinkedList<String> windowsId = new LinkedList<String>(getWindowHandlens());
		driver.switchTo().window(windowsId.get(0));
		log.info("");
	}

	public void switchToChildWindow(WebDriver driver) {
		String parentWinHandle = driver.getWindowHandle();
		System.out.println("Parent window handle: " + parentWinHandle);
		Set<String> winHandles = driver.getWindowHandles();
		System.out.println("Parent child windows handles: " + winHandles);
		for (String handle : winHandles) {
			if (!handle.equals(parentWinHandle)) {
				driver.switchTo().window(handle);
				System.out.println("child of the new window: " + handle);
			}
		}
	}

	public void switchToParentWithChildClose() {
		LinkedList<String> windowsId = new LinkedList<String>(getWindowHandlens());

		for (int i = 1; i < windowsId.size(); i++) {
			log.info(windowsId.get(i));
			driver.switchTo().window(windowsId.get(i));
			driver.close();
		}

		switchToParentWindow();
	}

	public void switchToFrame(String nameOrId) {
		driver.switchTo().frame(nameOrId);
		log.info(nameOrId);
	}

	public void switchToFrame(WebElement element) {
		driver.switchTo().frame(element);
		log.info(element);
	}

	public void switchToDefaultPage() {
		driver.switchTo().defaultContent();
	}

}
