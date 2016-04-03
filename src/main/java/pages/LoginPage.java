package pages;
import utils.Reporter;
import wrappers.OpentapsWrappers;

public class LoginPage extends OpentapsWrappers{

	public LoginPage() {

		if(!verifyTitle("Opentaps Open Source ERP + CRM"))
		{
			Reporter.reportStep("This is not Login Page", "FAIL");
		}
		// TODO Auto-generated constructor stub
	}
	public LoginPage enterUsername(String data) {

		enterById("username", data);
		
		return this;

	}
	public LoginPage enterPassword(String data) {

		enterById("password", data);
		return this;

	}

	public HomePage clickLogin() {
		
		clickByClassName("decorativeSubmit");
//		HomePage home=new HomePage();
		return new HomePage();
//		return home;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
