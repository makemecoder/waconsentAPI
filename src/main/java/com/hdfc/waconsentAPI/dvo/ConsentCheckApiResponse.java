package com.hdfc.waconsentAPI.dvo;

public class ConsentCheckApiResponse {
	
	public String status;
	
	public String duplicate;
	
	public String consent_Channel;
	
	public String consent_Type;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDuplicate() {
		return duplicate;
	}

	public void setDuplicate(String duplicate) {
		this.duplicate = duplicate;
	}	

	public String getConsent_Channel() {
		return consent_Channel;
	}

	public void setConsent_Channel(String consent_Channel) {
		this.consent_Channel = consent_Channel;
	}

	public String getConsent_Type() {
		return consent_Type;
	}

	public void setConsent_Type(String consent_Type) {
		this.consent_Type = consent_Type;
	}

	@Override
	public String toString() {
		return "ConsentCheckApiResponse [status=" + status + ", duplicate=" + duplicate + ", consent_Channel="
				+ consent_Channel + ", consent_Type=" + consent_Type + "]";
	}

}
