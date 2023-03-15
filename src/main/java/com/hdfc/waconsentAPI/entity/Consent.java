package com.hdfc.waconsentAPI.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
@Table(name = "consentdatamaster")
public class Consent {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CONSENT_DATA_MASTER_SEQ")
    @SequenceGenerator(sequenceName = "CONSENT_DATA_MASTER_SEQ", allocationSize = 1, name = "CONSENT_DATA_MASTER_SEQ")
	@Column(name = "Consent_Seq")
	private long id;
	
	@Column(name = "Time")
	@JsonProperty("Time") 
    public String time;
    
    @JsonProperty("Consent_Channel") 
    @Column(name = "Consent_Channel")
    public String consent_Channel;  

    @JsonProperty("Mobile_Number") 
    @Column(name = "Mobile_Number") 
    public String mobileNo;
  
    
    @JsonProperty("Consent_Type") 
    @Column(name = "Consent_Type") 
    public String consent_Type;
    
    @Column(name = "Current_Status") 
    public String currentStatus;
    
    @Column(name = "Date_of_Last_Modification") 
    public String date_of_Last_Modification;

	@Column(name = "Date_of_Creation") 
    public String date_of_Creation;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getConsent_Channel() {
		return consent_Channel;
	}

	public void setConsent_Channel(String consent_Channel) {
		this.consent_Channel = consent_Channel;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getConsent_Type() {
		return consent_Type;
	}

	public void setConsent_Type(String consent_Type) {
		this.consent_Type = consent_Type;
	}

	public String getDate_of_Last_Modification() {
		return date_of_Last_Modification;
	}

	public void setDate_of_Last_Modification(String date_of_Last_Modification) {
		this.date_of_Last_Modification = date_of_Last_Modification;
	}

	public String getDate_of_Creation() {
		return date_of_Creation;
	}

	public void setDate_of_Creation(String date_of_Creation) {
		this.date_of_Creation = date_of_Creation;
	}
	
	public String getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	@Override
	public String toString() {
		return "Consent [id=" + id + ", time=" + time + ", consent_Channel=" + consent_Channel + ", mobileNo="
				+ mobileNo + ", consent_Type=" + consent_Type + ", current_Status=" + currentStatus
				+ ", date_of_Last_Modification=" + date_of_Last_Modification + ", date_of_Creation=" + date_of_Creation
				+ "]";
	}

	    
    
}
