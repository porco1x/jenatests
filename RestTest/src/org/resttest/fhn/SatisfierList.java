package org.resttest.fhn;

import java.util.ArrayList;

public class SatisfierList {
	
	String agentName;
	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	ArrayList<String> satList = new ArrayList<String>();

	public ArrayList<String> getSatList() {
		return satList;
	}

	public void setSatList(ArrayList<String> satList) {
		this.satList = satList;
	}

}
