package pages;

import utils.Reporter;
import wrappers.OpentapsWrappers;

public class EditLeadPage extends OpentapsWrappers {

	public EditLeadPage() {

		if(!verifyTitle("opentaps CRM"))
		{
//			Reporter.reportStep("This is not Home Page", "FAIL");
		}
		// TODO Auto-generated constructor stub
	}	
	
	public EditLeadPage updateCompanyName(String data) {

		enterById("updateLeadForm_companyName", data);
		return this;

	}
	
	public EditLeadPage updateFirstName(String data) {

		enterById("updateLeadForm_firstName", data);
		return this;

	}
	
	public EditLeadPage updateLastName(String data) {

		enterById("updateLeadForm_lastName", data);
		return this;

	}
	public ViewLead clickUpdate() {

		clickByName("submitButton");
		return new ViewLead();

	}
	
	public ViewLead Edit(){
		clickByLinkText("Edit");
		return new ViewLead();
	}
	
	/*selectByVisibleTextusingId("addDataSourceForm_dataSourceId", "Partner");
	clickByXpath("//select[@id='addDataSourceForm_dataSourceId']/following::input");
	selectByVisibleTextusingId("addMarketingCampaignForm_marketingCampaignId", "Road and Track");
	clickByXpath("//select[@id='addMarketingCampaignForm_marketingCampaignId']/following::input");
	clickByName("submitButton");
	verifyTextById("viewLead_dataSources_sp", "Cold Call, Cold Call, Employee, Other, Other, Direct Mail");*/
}
