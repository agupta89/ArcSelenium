package com.arc.ReusableMethods;

import java.io.IOException;
import org.testng.Assert;
import com.arc.utility.BaseClass;
import com.arc.utility.CommonMethod;

public class ReusableMethodsLogin extends BaseClass {


	public String cemail;
	public String cpass;

	// click on contact link of Home page
	public void clickSignin() throws IOException, InterruptedException {
		CommonMethod.fluentWait("Login_dropdown");
		CommonMethod.click("Login_dropdown");
		Thread.sleep(1000);
		CommonMethod.testlog("Pass", "Clicking on Login Button");

	}

	// getting contact data from Excel
	public void getDataForLogin(String sheetName, int col) {

		cemail = username;
		cpass = password;
		CommonMethod.testlog("Info", "Getting Login data from Property File");
	}

	// filling contact form
	public void fillLoginDetails() throws IOException {
		CommonMethod.clear("EmailID");
		CommonMethod.sendKeys("EmailID", cemail);
		CommonMethod.clear("Password");
		CommonMethod.sendKeys("Password", cpass);
		CommonMethod.testlog("Pass", "Filling Login Details");

	}

	// click on submit button
	public void clickOnSubmit() throws IOException, Exception {
		CommonMethod.click("LoginSubmit");
		CommonMethod.testlog("Info", "Clicking on submit button");
		//CommonMethod.waitUntilAngularReady();
		Thread.sleep(5000);

	}

	public void clickAccept() throws IOException, Exception {
		CommonMethod.click("Accept");
		CommonMethod.testlog("Pass", "Accepting Checkbox");
		Thread.sleep(1000);

	}

	public void assertPostLoginText(String Exp_PostLoginText) throws IOException {
		CommonMethod.visibilityOf("Projects",maxWait);
		CommonMethod.assertEqualsmessage("Projects", Exp_PostLoginText, "Welcome Text is not correct");
		CommonMethod.testlog("Pass", "Navigate to Welcome Page");

	}
	public void LoginToArc(int col, String Postlogintext, String sheetName) throws Exception {
		
		CommonMethod.elementToBeClickable("Login_dropdown", minWait);
		clickSignin();
		getDataForLogin(sheetName, col);
		fillLoginDetails();
		clickAccept();
		clickOnSubmit();
		assertPostLoginText(Postlogintext);
		clickOnProject();
		verifyV2user();
	}
	public void clickOnProject() throws  Exception {
		CommonMethod.click("Projects");
		CommonMethod.testlog("Pass", "Clicked on Project Button");
		
		//Thread.sleep(6000);	
	}

	public void verifyV2user() throws Exception {
		CommonMethod.visibilityOf("Version", 60);
		String version = CommonMethod.getText("Version");
		System.out.println(version);
		if(version.equals("newest version."))
		{
			CommonMethod.click("Version");
			Thread.sleep(2000);
			CommonMethod.visibilityOf("Version", maxWait);
			Assert.assertEquals(CommonMethod.getText("Version"),"older version.");
			CommonMethod.testlog("Pass", "Login As V2 User");
		}
		else {
			CommonMethod.testlog("Pass", "Login As V2 User");
		}
	}
}
