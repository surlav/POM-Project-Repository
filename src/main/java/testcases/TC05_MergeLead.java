package testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.LoginPage;
import wrappers.OpentapsWrappers;

public class TC05_MergeLead extends OpentapsWrappers {
	@BeforeClass
	public void startTestCase(){
		browserName 	= "chrome";
		dataSheetName 	= "TC05_MergeLead";
		testCaseName 	= "TC05 - MergeLead (POM)";
		testDescription = "Create Merge using POM framework";
	}


	@Test(dataProvider="fetchData")
	public void CreateLead(String userName,String passWord,String fromLeadID, String ToLeadID) throws InterruptedException {

		new LoginPage()
		.enterUsername(userName)
		.enterPassword(passWord)
		.clickLogin()
		.clickCRMSFA()
		.clickLeadsTab()
		.mergeLeadMenu()
		.ClickFromLeadImage()
		.enterLeadID(fromLeadID)
		.clickFindLeadsButton()
		.clickLeadID(fromLeadID)
		.ClickToLeadImage()
		.enterLeadID(ToLeadID)
		.clickFindLeadsButton()
		.clickLeadID(ToLeadID)
		.clickMergeButton()
		.findLeadMenu()
		.enterLeadID(fromLeadID)
		.clickFindLeadButton()
		.verifyLinks(fromLeadID);
		





	}
}
