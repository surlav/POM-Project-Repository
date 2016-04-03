package pages;

import wrappers.OpentapsWrappers;

public class MenuPage extends OpentapsWrappers{
	
	public LeadsPage ClickCreateLead(){
		clickByLinkText("Create Lead");;
		return new LeadsPage();
	}

	/*public LoginPage clickLeadsTab(){
		clickByLinkText("Leads");
		return new LoginPage();
	}*/

	


}
