package pages;

import wrappers.OpentapsWrappers;

public class MergeLeadPage extends OpentapsWrappers {
	public String parentWindowHandle;
	public MergeLeadPage ClickFromLeadImage() {

		parentWindowHandle=driver.getWindowHandle();
		clickByXpath("//input[@id='partyIdFrom']/following::img");
		return this;

	}

	public MergeLeadPage enterLeadID(String data) {

		switchToLastWindow();
		enterByName("id", data);

		return this;

	}

	public MergeLeadPage clickFindLeadsButton() {

		clickByXpath("//button[contains(text(),'Find Leads')]");
		return this;

	}

	public MergeLeadPage clickLeadID(String data) throws InterruptedException {

		Thread.sleep(3000);		
		clickByLinkText(data);
		return this;

	}

	public MergeLeadPage ClickToLeadImage() {
		switchToPrimaryWindow(parentWindowHandle);
		clickByXpath("//input[@id='partyIdTo']/following::img");
		return this;

	}

	public LeadMainPage clickMergeButton() {
		switchToPrimaryWindow(parentWindowHandle);
		clickByLinkText("Merge");
		acceptAlert();
		return new LeadMainPage();

	}


}
