package testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.LoginPage;
import wrappers.OpentapsWrappers;

public class TC001_Login extends OpentapsWrappers {

	@BeforeClass
	public void startTestCase(){
		browserName 	= "chrome";
		dataSheetName 	= "TC01_Login";
		testCaseName 	= "TC01 - Login (POM)";
		testDescription = "Login to Opentaps using POM framework";
	}


	@Test(dataProvider="fetchData")
	public void login(String userName, String passWord) {

		new LoginPage()
		.enterUsername(userName)
		.enterPassword(passWord)
		.clickLogin()
		.clickLogOut();







	}









}
