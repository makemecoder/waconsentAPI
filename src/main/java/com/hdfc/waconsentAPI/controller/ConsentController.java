package com.hdfc.waconsentAPI.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hdfc.waconsentAPI.entity.Consent;
import com.hdfc.waconsentAPI.dvo.ConsentCheckApiResponse;
import com.hdfc.waconsentAPI.dvo.ConsentRequestDetails;
import com.hdfc.waconsentAPI.repository.ConsentRepository;
import com.hdfc.waconsentAPI.util.CommonUtility;
import com.hdfc.waconsentAPI.util.Constants;

@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class ConsentController {
	
	private static final Logger logger = LogManager.getLogger(ConsentController.class);
	
	@Autowired
	ConsentRepository consentRepository;

	@GetMapping("/hello")
	public ResponseEntity<String> hello() {
		try {
			return ResponseEntity.status(HttpStatus.OK).body("Hello Consent API !");
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Transactional
	@PostMapping("/checkconsent")
	public ResponseEntity<ConsentCheckApiResponse> checkconsent(@RequestBody String request) {
		
		if (logger.isInfoEnabled())
		logger.info("check Concent() >>>>>>>> ",request);
		
		Consent oConsent = null;
		ConsentCheckApiResponse oConsentResponse = new ConsentCheckApiResponse();
		
		Object obj=JSONValue.parse(request);    
		JSONObject jsonObject = (JSONObject) obj;    
		String mobNo = (String) jsonObject.get("Mobile_Number");
		
				
		try {
			
			oConsent = consentRepository.findByMobileNoAndCurrentStatus(mobNo,"Accepted");
			
			if (oConsent!=null) {
				oConsentResponse.setDuplicate("true");
				oConsentResponse.setConsent_Channel(oConsent.getConsent_Channel());
				oConsentResponse.setConsent_Type(oConsent.getConsent_Type());
				oConsentResponse.setStatus("success");				
			}else {
				oConsentResponse.setDuplicate("false");
				oConsentResponse.setStatus("success");
				oConsentResponse.setConsent_Channel("");
				oConsentResponse.setConsent_Type("");
			}
			
			return ResponseEntity.status(HttpStatus.OK).body(oConsentResponse);

		} catch (Exception e) {
			oConsentResponse.setDuplicate("false");
			oConsentResponse.setStatus("failure");
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	@PostMapping("/consent")
	public ResponseEntity<String> registerConcent(@RequestBody String request) {
		try {
			
			if (logger.isInfoEnabled())
			logger.info("registerConcent() >>>>>>>> ",request);
			
			int iRevokeCount = 0;
			int iModifyCount = 0;
			JSONObject json = new JSONObject();
			JSONArray arrSuccess = new JSONArray();
			JSONArray arrFailure = new JSONArray();			
			ObjectMapper objectMapper = new ObjectMapper();
			Consent oConsent = null;

			// Deserialization into the Employee class
			ConsentRequestDetails concentDetails = objectMapper.readValue(request, ConsentRequestDetails.class);			
						
			if(concentDetails.getActionType()!=null) {
				
					if(concentDetails.getActionType().equalsIgnoreCase("add")) {
						
						for (Consent obj : concentDetails.getList()) {
							
							oConsent = consentRepository.findByMobileNoAndCurrentStatus(obj.getMobileNo(),"Accepted");
							
							if (oConsent!=null) {
								JSONObject item = new JSONObject();
								item.put(obj.getMobileNo(), Constants.VALID_MSG_01);
								arrFailure.add(item);
							}else{
								obj.setDate_of_Creation(CommonUtility.getCurrentTimestampWithFormat());
								obj.setCurrentStatus("Accepted");
								consentRepository.save(obj);
								logger.info("Consent added successfully for "+obj.getMobileNo());
								JSONObject item = new JSONObject();							
								item.put(obj.getMobileNo(), "ACCEPTED");
								arrSuccess.add(item);
							}
							
							oConsent = null;
						}
					}
					else if(concentDetails.getActionType().equalsIgnoreCase("revoke")) {
						
						for (Consent obj : concentDetails.getList()) {
							
							oConsent = consentRepository.findByMobileNoAndCurrentStatus(obj.getMobileNo(),"Accepted");
							
							if(oConsent!= null) {
								if(oConsent.getCurrentStatus() != null && oConsent.getCurrentStatus().equals("Revoked")){
									JSONObject item = new JSONObject();
									item.put(obj.getMobileNo(), Constants.VALID_MSG_02);
									arrFailure.add(item);
								}else {
									iRevokeCount = consentRepository.revokeConsent(CommonUtility.getCurrentTimestampWithFormat(),obj.getMobileNo());
									logger.info(iRevokeCount+" consent revoked successfully for "+obj.getMobileNo());
									
									if(iRevokeCount > 0) {
										JSONObject item = new JSONObject();
										item.put(obj.getMobileNo(), "REVOKED");
										arrSuccess.add(item);
									}
								}
							}
							else{
								JSONObject item = new JSONObject();
								item.put(obj.getMobileNo(), Constants.VALID_MSG_02);
								arrFailure.add(item);
							}
						}
						
						oConsent = null;
					}
					else if(concentDetails.getActionType().equalsIgnoreCase("modify")) {
						
						for (Consent obj : concentDetails.getList()) {
														
							oConsent = consentRepository.findByMobileNoAndCurrentStatus(obj.getMobileNo(),"Accepted");
							
							if (oConsent!=null) {								
								
								if(oConsent.getCurrentStatus() != null && oConsent.getCurrentStatus().equals("Revoked")){
									JSONObject item = new JSONObject();
									item.put(obj.getMobileNo(), Constants.VALID_MSG_02);
									arrFailure.add(item);
								}
								else if(oConsent.getConsent_Type().equals(obj.getConsent_Type())){
									JSONObject item = new JSONObject();
									item.put(obj.getMobileNo(), Constants.VALID_MSG_03);
									arrFailure.add(item);
								}
								else {								
									iModifyCount = consentRepository.modifyConsent(obj.getConsent_Type(),CommonUtility.getCurrentTimestampWithFormat(),obj.getMobileNo());
									logger.info(iModifyCount+" consent updated successfully for "+obj.getMobileNo());
									
									if(iModifyCount > 0) {
										JSONObject item = new JSONObject();
										item.put(obj.getMobileNo(), "MODIFIED");
										arrSuccess.add(item);
									}
								}														
							}
							else {
								JSONObject item = new JSONObject();
								item.put(obj.getMobileNo(), Constants.VALID_MSG_02);
								arrFailure.add(item);
							}
							
							oConsent=null;
						}	
					}
					
					json.put("SUCCESS", arrSuccess);
					json.put("FAILED", arrFailure);
			}
			return ResponseEntity.status(HttpStatus.OK).body(json.toString());	
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	
	@Transactional
	@PostMapping("/getConsentDtls")
	public ResponseEntity<List<Consent>> getConsentDtls(@RequestBody String request) {
		
		if (logger.isInfoEnabled())
		logger.info("getConsentDtls() >>>>>>>> ",request);
		
		Object obj=JSONValue.parse(request);    
		JSONObject jsonObject = (JSONObject) obj;    
		String mobNo = (String) jsonObject.get("Mobile_Number");		
				
		try {
			List<Consent> oConsent = consentRepository.getByMobileNo(mobNo);			 
			return ResponseEntity.status(HttpStatus.OK).body(oConsent);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
}