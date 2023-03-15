package com.hdfc.waconsentAPI.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.hdfc.waconsentAPI.entity.Consent;

public interface ConsentRepository extends JpaRepository<Consent, Long>{
	
	boolean existsByMobileNo(String strMobNo);
	
	int deleteByMobileNo(String strMobNo);
	
	List<Consent> getByMobileNo(String strMobNo);
	
	@Modifying
	@Query("update Consent c set c.consent_Type = 'Revoked', c.currentStatus = 'Revoked', c.date_of_Last_Modification=?1 where c.mobileNo = ?2")
	int revokeConsent(String date_of_Last_Modification, String mobile_Number);
	
	@Modifying
	@Query("update Consent c set c.consent_Type = ?1, c.date_of_Last_Modification=?2 where c.mobileNo = ?3 and c.currentStatus = 'Accepted' ")
	int modifyConsent(String consent_Type, String date_of_Last_Modification, String mobile_Number);

	Consent findByMobileNo(String mobileNo);
	
	Consent findByMobileNoAndCurrentStatus(String mobileNo, String current_Status);
	
	Consent getByMobileNoAndCurrentStatus(String mobileNo, String current_Status);

}
