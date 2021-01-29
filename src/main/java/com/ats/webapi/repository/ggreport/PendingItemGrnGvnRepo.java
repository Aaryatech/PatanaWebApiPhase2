package com.ats.webapi.repository.ggreport;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.grngvnreport.PendingItemGrnGvn;

public interface PendingItemGrnGvnRepo extends JpaRepository<PendingItemGrnGvn, Integer> {

	@Query(value="SELECT\n" + 
			"	t1.*,\n" + 
			"    t2.*\n" + 
			"FROM\n" + 
			"(\n" + 
			"    SELECT\n" + 
			"    i.item_id,\n" + 
			"    i.item_name,\n" + 
			"    t_grn_gvn.grn_gvn_id,\n" + 
			"    t_grn_gvn.grn_gvn_qty as req_qty,\n" + 
			"    t_grn_gvn.apr_qty_acc as apr_qty,\n" + 
			"    t_grn_gvn.apr_grand_total as apr_amt,\n" + 
			"    t_grn_gvn.grn_gvn_amt as req_amt,\n" + 
			"    hgrngvn.grn_gvn_header_id,\n" + 
			"    hgrngvn.total_amt,\n" + 
			"    hgrngvn.grngvn_srno,\n" + 
			"    hgrngvn.grngvn_date,\n" + 
			"    hgrngvn.is_grn,\n" + 
			"    hgrngvn.is_credit_note,\n" + 
			"    hgrngvn.grngvn_status,\n" + 
			"    grn_type AS grn_per\n" + 
			"FROM\n" + 
			"    t_grn_gvn_header hgrngvn,\n" + 
			"    t_grn_gvn t_grn_gvn,\n" + 
			"    m_item i\n" + 
			"WHERE\n" + 
			"	i.id=t_grn_gvn.item_id AND\n" + 
			"	hgrngvn.grn_gvn_header_id=t_grn_gvn.grn_gvn_header_id AND\n" + 
			"    hgrngvn.grngvn_date BETWEEN :fromDate AND :toDate AND\n" + 
			"    hgrngvn.grngvn_status = :status AND\n" + 
			"    hgrngvn.is_credit_note IN (:isCrnGen) AND\n" + 
			"    hgrngvn.fr_id=:frId AND\n" + 
			"    hgrngvn.is_grn IN (:isGrn)\n" + 
			"    \n" + 
			")t1\n" + 
			"LEFT JOIN\n" + 
			"(SELECT\n" + 
			" 	head.bill_no,\n" + 
			"    head.invoice_no,\n" + 
			"    head.bill_date,\n" + 
			"    head.fr_id\n" + 
			"FROM\n" + 
			"   t_bill_header head\n" + 
			"WHERE\n" + 
			"	head.del_status=0 AND\n" + 
			"    head.fr_id=:frId AND\n" + 
			"    head.bill_date BETWEEN :fromDate AND :toDate) t2\n" + 
			"    ON t1.grngvn_date=t2.bill_date\n" + 
			"    ",nativeQuery=true)
	List<PendingItemGrnGvn> getPendingItemGrnGvn(@Param("fromDate") String fromDate, @Param("toDate") String toDate,
			@Param("frId") int frId, @Param("isGrn") List<String> isGrn, @Param("isCrnGen") List<String> isCrnGen, @Param("status") int status);

}
