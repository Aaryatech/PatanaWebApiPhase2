package com.ats.webapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ats.webapi.model.SellBillDetails;

@Repository
public interface SellBillDetailsRepository extends JpaRepository<SellBillDetails, Integer>{
	
	    /*@Query(value="Select s.sell_bill_detail_no,s.sell_bill_no,s.cat_id,s.item_id,i.item_name,\n" + 
	    		"	    		s.mrp,s.qty,s.mrp_base_rate,s.taxable_amt,s.sgst_per,s.sgst_rs,s.cgst_per,s.cgst_rs,\n" + 
	    		"	    		s.igst_per,s.igst_rs,s.total_tax,s.grand_total,coalesce((select m_item_sup.item_hsncd from m_item_sup where m_item_sup.item_id=i.id),0) as  remark,s.del_status \n" + 
	    		"	    		from t_sell_bill_detail s,m_item i \n" + 
	    		"	    		WHERE s.sell_bill_no=:billNo AND s.item_id=i.id AND s.del_status=0 ORDER BY s.sell_bill_detail_no DESC ",nativeQuery=true)*/
	//change code by akshay 14-10-2020
	@Query(value="Select a.sell_bill_detail_no,\n" + 
			"        a.sell_bill_no,\n" + 
			"        a.cat_id,\n" + 
			"        a.item_id,\n" + 
			"        a.item_name,\n" + 
			"        a.mrp,\n" + 
			"        a.qty,\n" + 
			"        a.mrp_base_rate,\n" + 
			"        a.taxable_amt,\n" + 
			"        a.sgst_per,\n" + 
			"        a.sgst_rs,\n" + 
			"        a.cgst_per,\n" + 
			"        a.cgst_rs,\n" + 
			"        a.igst_per,\n" + 
			"        a.igst_rs,\n" + 
			"        a.total_tax,\n" + 
			"        a.grand_total,\n" + 
			"        a.del_status,\n" + 
			"        b.remark\n" + 
			"    from (select\n" + 
			"        s.sell_bill_detail_no,\n" + 
			"        s.sell_bill_no,\n" + 
			"        s.cat_id,\n" + 
			"        s.item_id,\n" + 
			"        i.item_name,\n" + 
			"        s.mrp,\n" + 
			"        s.qty,\n" + 
			"        s.mrp_base_rate,\n" + 
			"        s.taxable_amt,\n" + 
			"        s.sgst_per,\n" + 
			"        s.sgst_rs,\n" + 
			"        s.cgst_per,\n" + 
			"        s.cgst_rs,\n" + 
			"        s.igst_per,\n" + 
			"        s.igst_rs,\n" + 
			"        s.total_tax,\n" + 
			"        s.grand_total,\n" + 
			"        i.id,\n" + 
			"        s.del_status         \n" + 
			"    from\n" + 
			"        t_sell_bill_detail s,\n" + 
			"        m_item i         \n" + 
			"    WHERE\n" + 
			"        s.sell_bill_no=:billNo\n" + 
			"        AND s.item_id=i.id \n" + 
			"        AND s.del_status=0 \n" + 
			"    ORDER BY\n" + 
			"        s.sell_bill_detail_no DESC) a\n" + 
			"left join\n" + 
			"(select\n" + 
			"            m_item_sup.item_hsncd as remark,\n" + 
			"            m_item_sup.item_id\n" + 
			"        from\n" + 
			"            m_item_sup   ) b on b.item_id=a.id\n" + 
			"         ",nativeQuery=true)
		List<SellBillDetails> findByBillNo(@Param("billNo")int billNo);
}
