package pages;

import wrappers.OpentapsWrappers;

public class LeadMainPage extends OpentapsWrappers {
	
	public LeadsPage createLeadMenu(){
		clickByLinkText("Create Lead");
		return new LeadsPage();
	}
	
	public FindLeadPage findLeadMenu(){
		clickByLinkText("Find Leads");
		return new FindLeadPage();
	}
	
	public MergeLeadPage mergeLeadMenu(){
		clickByLinkText("Merge Leads");
		return new MergeLeadPage();
	}

}
