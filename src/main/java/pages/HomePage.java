package pages;

import utils.Reporter;
import wrappers.OpentapsWrappers;

public class HomePage extends OpentapsWrappers{

	public HomePage() {

		if(!verifyTitle("Opentaps Open Source ERP + CRM"))
		{
			Reporter.reportStep("This is not Home Page", "FAIL");
		}
		// TODO Auto-generated constructor stub
	}	

	public LoginPage clickLogOut() {

		clickByClassName("decorativeSubmit");
		return new LoginPage();

	}

	public MenuPage clickCRMSFA(){
		clickByLinkText("CRM/SFA");
		return new MenuPage();
	}















}
