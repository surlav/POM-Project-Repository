package pages;

import wrappers.OpentapsWrappers;

public class MenuPage extends OpentapsWrappers{
	
	public LeadsPage ClickCreateLead(){
		clickByLinkText("Create Lead");;
		return new LeadsPage();
	}

	public LeadMainPage clickLeadsTab(){
		clickByLinkText("Leads");
		return new LeadMainPage();
	}

	


}
