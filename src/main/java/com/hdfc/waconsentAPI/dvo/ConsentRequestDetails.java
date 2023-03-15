package com.hdfc.waconsentAPI.dvo;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hdfc.waconsentAPI.entity.Consent;

public class ConsentRequestDetails {
	
	@JsonProperty("ActionType") 
    public String actionType;
	
	@JsonProperty("list")
    public ArrayList<Consent> list;
    
    
	public String getActionType() {
		return actionType;
	}


	public void setActionType(String actionType) {
		this.actionType = actionType;
	}


	public ArrayList<Consent> getList() {
		return list;
	}


	public void setList(ArrayList<Consent> list) {
		this.list = list;
	}


	@Override
	public String toString() {
		return "ConcentDetails [actionType=" + actionType + ", list=" + list + "]";
	}
    
    

}
