package testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.LoginPage;
import pages.ViewLead;
import wrappers.OpentapsWrappers;

public class TC04_DeleteLead extends OpentapsWrappers{
	
	@BeforeClass
	public void startTestCase(){
		browserName 	= "chrome";
		dataSheetName 	= "TC04_DeleteLead";
		testCaseName 	= "TC04 - DeleteLead (POM)";
		testDescription = "Delete Lead using POM framework";
	}
	
	@Test(dataProvider = "fetchData")
	public void DeleteLead(String userName, String passWord, String PhoneNumber)
	{
		new LoginPage()
		.enterUsername(userName)
		.enterPassword(passWord)
		.clickLogin()
		.clickCRMSFA()
		.clickLeadsTab()
		.findLeadMenu()
		.clickPhone()
		.enterPhoneNumber(PhoneNumber)
		.clickFindLeadButton()
		.clickLeadId()
		.deleteButton()
		.findLeadMenu()
		.clickPhone()
		.enterPhoneNumber(PhoneNumber)
		.clickFindLeadButton()
		.verifyLinks(PhoneNumber);
		
	}

}
