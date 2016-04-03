package pages;

import wrappers.OpentapsWrappers;

public class LeadsPage extends OpentapsWrappers {
	
	public LeadsPage enterCompanyName(String data){
		enterById("createLeadForm_companyName", data);
		return this;
	}

	public LeadsPage enterFirstName(String data){
		enterById("createLeadForm_firstName", data);
		return this;
	}
	
	public LeadsPage enterLastName(String data){
		enterById("createLeadForm_lastName", data);
		return this;
	}

	public LeadsPage SelectDataSourceId(String data){
		selectByVisibleTextusingId("createLeadForm_dataSourceId", data);
		return this;
	}

	public LeadsPage SelectMarketCampaignID(String data){
		selectByVisibleTextusingId("createLeadForm_marketingCampaignId", data);
		return this;
	}

	public LeadsPage enterPrimaryEmail(String data){
		enterById("createLeadForm_primaryEmail", data);
		return this;
	}

	public LeadsPage enterPrimaryPhoneNumber(String data){
		enterById("createLeadForm_primaryPhoneNumber", data);
		return this;
	}

	public ViewLead ClickSubmitButton(){
		clickByName("submitButton");
		return new ViewLead();
	}



}
