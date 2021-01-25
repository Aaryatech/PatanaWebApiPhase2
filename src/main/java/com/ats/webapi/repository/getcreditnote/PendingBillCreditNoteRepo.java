package com.ats.webapi.repository.getcreditnote;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.bill.PendingBillCreditNote;

public interface PendingBillCreditNoteRepo extends JpaRepository<PendingBillCreditNote, Integer> {
	
	@Query(value="SELECT\n" + 
			"	c.crn_id,\n" + 
			"	c.crn_no,\n" + 
			"    c.crn_taxable_amt,\n" + 
			"    c.crn_total_tax,\n" + 
			"    c.crn_grand_total,\n" + 
			"    c.fr_id\n" + 
			"    \n" + 
			"FROM\n" + 
			"    t_credit_note_header c\n" + 
			"WHERE\n" + 
			"    c.fr_id=:frId AND c.ex_int1 = 0",nativeQuery=true)
	List<PendingBillCreditNote> getPendingCrNote(@Param("frId") int frId);

}
