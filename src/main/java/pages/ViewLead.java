package pages;

import wrappers.OpentapsWrappers;

public class ViewLead extends OpentapsWrappers {

	public ViewLead VerifyLead(){
		splitString("viewLead_companyName_sp");
		return this;
	}

	public EditLeadPage Edit(){
		clickByLinkText("Edit");
		return new EditLeadPage();


	}
	
	public LeadMainPage deleteButton(){
		clickByLinkText("Delete");
		return new LeadMainPage();


	}


	public ViewLead verifyFirstName(String data){

		verifyTextById("viewLead_firstName_sp", data);
		return this;

	}


}
