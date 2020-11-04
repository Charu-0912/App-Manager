package com.appmanager.qa.testcases;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.appmanager.qa.testrail.APIClient;
import com.appmanager.qa.testrail.APIException;

/**
 * Unit test for simple App.
 */
public class TestNGProject
{
	String PROJECT_ID = "17";
	String Suite_Id = "174";
	APIClient client = null;

	@BeforeSuite
	public void createSuite(ITestContext ctx) throws IOException, APIException {
		client = new APIClient("https://openmethods.testrail.net/");
		client.setUser("amit.mhetre@openmethods.com");
		client.setPassword("Aloha@7890");
		Map data = new HashMap();
		data.put("include_all",true);
		data.put("name","Test Run "+System.currentTimeMillis());
		JSONObject c = null;
		c = (JSONObject)client.sendPost("add_run/"+PROJECT_ID,data);
		Long suite_id = (Long)c.get("id");
		ctx.setAttribute("suiteId",suite_id);

	}

	@BeforeMethod
	public void beforeTest(ITestContext ctx,Method method) throws NoSuchMethodException {
		Method m = TestNGProject.class.getMethod(method.getName());

		if (m.isAnnotationPresent(TestRails.class)) {
			TestRails ta = m.getAnnotation(TestRails.class);
			System.out.println(ta.id());
			ctx.setAttribute("caseId",ta.id());
		}
	}
    @TestRails(id="1")
    @Test
    public void validLogin()
    {
		Assert.assertTrue(true);
    }

    @TestRails(id="2")
    @Test
    public void invalidLogin()
    {
		Assert.assertTrue(false);
    }

    @AfterMethod
	public void afterTest(ITestResult result, ITestContext ctx) throws IOException, APIException {
		Map data = new HashMap();
		if(result.isSuccess()) {
			data.put("status_id",1);
		}
		else {
			data.put("status_id", 5);
			data.put("comment", result.getThrowable().toString());
		}

		String caseId = (String)ctx.getAttribute("caseId");
		Long suiteId = (Long)ctx.getAttribute("suiteId");
		client.sendPost("add_result_for_case/"+suiteId+"/"+caseId,data);

	}
}
