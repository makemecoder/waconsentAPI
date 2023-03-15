package com.hdfc.waconsentAPI.dvo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ConsentDVO {
	
	@JsonProperty("Time") 
    public Object time;
	
    @JsonProperty("Consent_Channel_Media") 
    public String consent_Channel_Media;
    
    @JsonProperty("Consent_Channel_Source") 
    public String consent_Channel_Source;  

    @JsonProperty("Mobile_Number") 
    public String mobile_Number;
    
    @JsonProperty("Current_Status") 
    public String current_Status;
    
    @JsonProperty("Date_of_Last_Modification") 
    public String date_of_Last_Modification;
    
    @JsonProperty("Consent_Type") 
    public String consent_Type;

	public Object getTime() {
		return time;
	}

	public void setTime(Object time) {
		this.time = time;
	}

	public String getConsent_Channel_Media() {
		return consent_Channel_Media;
	}

	public void setConsent_Channel_Media(String consent_Channel_Media) {
		this.consent_Channel_Media = consent_Channel_Media;
	}

	public String getConsent_Channel_Source() {
		return consent_Channel_Source;
	}

	public void setConsent_Channel_Source(String consent_Channel_Source) {
		this.consent_Channel_Source = consent_Channel_Source;
	}

	public String getMobile_Number() {
		return mobile_Number;
	}

	public void setMobile_Number(String mobile_Number) {
		this.mobile_Number = mobile_Number;
	}

	public String getCurrent_Status() {
		return current_Status;
	}

	public void setCurrent_Status(String current_Status) {
		this.current_Status = current_Status;
	}

	public String getDate_of_Last_Modification() {
		return date_of_Last_Modification;
	}

	public void setDate_of_Last_Modification(String date_of_Last_Modification) {
		this.date_of_Last_Modification = date_of_Last_Modification;
	}

	public String getConsent_Type() {
		return consent_Type;
	}

	public void setConsent_Type(String consent_Type) {
		this.consent_Type = consent_Type;
	}

	@Override
	public String toString() {
		return "Consent [time=" + time + ", consent_Channel=" + consent_Channel_Media + ", consent_Channel_Source="
				+ consent_Channel_Source + ", mobile_Number=" + mobile_Number + ", current_Status=" + current_Status
				+ ", date_of_Last_Modification=" + date_of_Last_Modification + ", consent_Type=" + consent_Type + "]";
	}
    
    
}
