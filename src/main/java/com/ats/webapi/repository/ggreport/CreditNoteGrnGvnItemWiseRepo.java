package com.ats.webapi.repository.ggreport;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.grngvnreport.CreditNoteGrnGvnItemWise;

public interface CreditNoteGrnGvnItemWiseRepo extends JpaRepository<CreditNoteGrnGvnItemWise, Integer> {

	@Query(value="SELECT\n" + 
			"	UUID() AS uid,\n" + 
			"	gh.grn_gvn_header_id,\n" + 
			"    gd.grn_gvn_qty AS req_qty,\n" + 
			"    cd.grn_gvn_qty AS aprv_qty,\n" + 
			"    gh.grngvn_srno,\n" + 
			"    i.item_name,\n" + 
			"    gd.is_grn,\n" + 
			"    gh.grngvn_date,\n" + 
			"    gh.grngvn_status\n" + 
			"FROM\n" + 
			"    t_grn_gvn_header gh,\n" + 
			"    t_grn_gvn gd,\n" + 
			"    t_credit_note_details cd,\n" + 
			"    m_item i\n" + 
			"WHERE\n" + 
			"	 gd.grn_gvn_header_id=gh.grn_gvn_header_id AND\n" + 
			"    cd.grn_gvn_header_id=gh.grn_gvn_header_id AND\n" + 
			"    cd.grn_gvn_id=gd.grn_gvn_id AND\n" + 
			"    i.id=gd.item_id AND\n" + 
			"    i.id=cd.item_id AND    \n" + 
			"    gh.fr_id=:frId AND\n" + 
			"    gh.grngvn_date BETWEEN :fromDate AND :toDate AND\n" + 
			"    gd.is_grn IN (:isGrn)", nativeQuery=true)
	public List<CreditNoteGrnGvnItemWise> getCrnGrnGvnItems(@Param("fromDate") String fromDate, @Param("isGrn") List<String> isGrn,
			@Param("toDate") String toDate, @Param("frId") int frId);
	
}
