package com.ats.webapi.repository.dashboard;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ats.webapi.model.dashboard.FrFdaOwnerDtl;
@Repository
public interface FrFdaOwnerDtlRepo extends JpaRepository<FrFdaOwnerDtl, Integer> {

	@Query(value="SELECT\n" + 
			"        f.fr_id,\n" + 
			"        f.fr_name,\n" + 
			"        f.fr_code,\n" + 
			"        f.fba_license_date AS fda_expiry_date,\n" + 
			"        f.fr_agreement_date,\n" + 
			"        f.owner_birth_date,\n" + 
			"        COALESCE(DATEDIFF(f.fba_license_date,\n" + 
			"        CURDATE()),0) AS curr_fda_date_diff,\n" + 
			"        COALESCE(DATEDIFF(f.fr_agreement_date,\n" + 
			"        CURDATE()),0) as curr_agrmnt_date_diff\n" + 
			"    FROM\n" + 
			"        m_franchisee f \n" + 
			"    WHERE\n" + 
			"        f.del_status=0",nativeQuery=true)
	List<FrFdaOwnerDtl> getFdaExpFr();
}
