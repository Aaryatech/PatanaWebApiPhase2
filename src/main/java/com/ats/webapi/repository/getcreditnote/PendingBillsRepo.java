package com.ats.webapi.repository.getcreditnote;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.bill.PendingBills;

public interface PendingBillsRepo extends JpaRepository<PendingBills, Integer> {

	@Query(value="SELECT\n" + 
			"   	h.fr_id,\n" + 
			"    h.invoice_no,\n" + 
			"    h.bill_no,\n" + 
			"    h.taxable_amt,\n" + 
			"    h.total_tax,\n" + 
			"    h.grand_total,\n" + 
			"    h.pending_bill,\n" + 
			"    h.bill_date\n" + 
			"FROM\n" + 
			"    t_bill_header h\n" + 
			"WHERE    \n" + 
			"    h.del_status=0 AND pending_bill = 0 AND\n" + 
			"    h.fr_id=:frId\n" + 
			"    ORDER BY h.bill_date DESC",nativeQuery=true)
	List<PendingBills> getAllPendingBillsByFrId(@Param("frId") int frId);
	
}
