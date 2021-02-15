package com.ats.webapi.repository.ggreport;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.grngvnreport.GGProdWiseReport;
import com.ats.webapi.model.grngvnreport.GGReportByDate;

//Sac 15-02-2021
public interface GGProdWiseReportRepo extends JpaRepository<GGProdWiseReport, Integer> {
	
	@Query(value=" SELECT UUID() as uid, SUM(t_credit_note_details.grn_gvn_qty) as grn_gvn_qty,t_credit_note_details.item_id, " + 
			"	t_credit_note_header.fr_id ,m_item.item_name " + 
			"	FROM t_credit_note_header,t_credit_note_details,m_item " + 
			"	WHERE t_credit_note_header.crn_id=t_credit_note_details.crn_id " + 
			"	and m_item.id=t_credit_note_details.item_id " + 
			"	and t_credit_note_header.crn_date BETWEEN :fromDate and :toDate "
			+ " and t_credit_note_details.item_id "
			+ " IN(:itemIdList) and t_credit_note_header.is_grn IN(:isGrn) and t_credit_note_header.fr_id IN (:frIdList) " + 
			"	GROUP by t_credit_note_header.fr_id, t_credit_note_details.item_id ",nativeQuery=true)
	
		List<GGProdWiseReport> getGGItemwiseReport(@Param("fromDate") 
		String fromDate,@Param("toDate") 
		String toDate,@Param("frIdList") List<String> frIdList,@Param("isGrn") List<String> isGrn,
		@Param("itemIdList") List<String> itemIdList);
	

}
