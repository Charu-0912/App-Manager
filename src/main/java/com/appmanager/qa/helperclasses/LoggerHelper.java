package com.appmanager.qa.helperclasses;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LoggerHelper {
	private static boolean root = false;

	@SuppressWarnings("rawtypes")
	public static Logger getLogger(Class clas) {
		if (root) {
			return Logger.getLogger(clas);
		} else {
			root = true;
			PropertyConfigurator.configure("/Automation/MyWorkspace1/AppManager/src/main/resources/log4j.properties");
			return Logger.getLogger(clas);
		}
	}
}
