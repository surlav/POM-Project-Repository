package testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.LoginPage;
import wrappers.OpentapsWrappers;

public class TC02_CreateLead extends OpentapsWrappers {

	@BeforeClass
	public void startTestCase(){
		browserName 	= "firefox";
		dataSheetName 	= "TC02_CreateLead";
		testCaseName 	= "TC02 - CreateLead (POM)";
		testDescription = "Create Lead using POM framework";
	}


	@Test(dataProvider="fetchData")
	public void CreateLead(String userName, String passWord, String companyName,String fname, String lname,String Source,String marketingCampaign,String primaryEmailid, String phoneNumber) {

		new LoginPage()
		.enterUsername(userName)
		.enterPassword(passWord)
		.clickLogin()
		.clickCRMSFA()
		.ClickCreateLead()
		.enterCompanyName(companyName)
		.enterFirstName(fname)
		.enterLastName(lname)
		.SelectDataSourceId(Source)
		.SelectMarketCampaignID(marketingCampaign)
		.enterPrimaryEmail(primaryEmailid)
		.enterPrimaryPhoneNumber(phoneNumber)
		.ClickSubmitButton()
		.VerifyLead();



	}
}