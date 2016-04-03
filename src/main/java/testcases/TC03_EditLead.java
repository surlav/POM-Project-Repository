package testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.LoginPage;
import wrappers.OpentapsWrappers;

public class TC03_EditLead extends OpentapsWrappers {
	@BeforeClass
	public void startTestCase(){
		browserName 	= "firefox";
		dataSheetName 	= "TC03_EditLead";
		testCaseName 	= "TC03 - EditLead (POM)";
		testDescription = "Edit Lead using POM framework";
	}
	
	@Test(dataProvider="fetchData")
	public void editLead(String userName, String passWord, String Phonenumber, String Companyname, String fname, String lname) {

		new LoginPage()
		.enterUsername(userName)
		.enterPassword(passWord)
		.clickLogin()
		.clickCRMSFA()
		.clickLeadsTab()
		.findLeadMenu()
		.clickPhone()
		.enterPhoneNumber(Phonenumber)
		.clickFindLeadButton()
		.clickLeadId()
		.Edit()
		.updateCompanyName(Companyname)
		.updateFirstName(fname)
		.updateLastName(lname)
		.clickUpdate()
		.verifyFirstName(fname);
		
		
	/*
		clickLeadId() 
		.EditLeadPage()
		.updateCompanyName(Companyname)
		.updateFirstName(fname)
		.updateLastName(lname)
		.clickUpdate()
		.verifyFirstName();
	*/	
		
		

}
}