package pages;

import wrappers.OpentapsWrappers;

public class FindLeadPage extends OpentapsWrappers {

	public FindLeadPage enterFirstName(String data) {

		enterByXpath("//span[contains(text(),'Find by')]/following::input[2]", data);

		return this;
	}
	public FindLeadPage clickFindLeadButton() {
		clickByXpath("//button[contains(text(),'Find Leads')]");
		return this;
	}
	public FindLeadPage enterLeadID(String data) {

		enterByName("id", data);
		return this;
		}
	public ViewLead clickFirstName() {
		clickByXpath("//div[@class='x-grid3-cell-inner x-grid3-col-friendlyPartyName']/following::a[1]");
		return new ViewLead();
	}
	public FindLeadPage clickPhone() {
		clickByXpath("//span[contains(text(),'Phone')]");
		return this;
	}
/**
 * 
 * @param data
 * @return
 */
	public FindLeadPage enterPhoneNumber(String data) {
		enterByName("phoneNumber", data);
		return this;
	}
	
	public ViewLead clickLeadId() {
		sleep(3000);
		clickByXpath("//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a[1]");
		return new ViewLead();
	}
}
