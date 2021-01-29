package com.ats.webapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.StockTransferDetail;

public interface StockTransferDetailRepository extends JpaRepository<StockTransferDetail, Integer> {
	
	@Query(value="SELECT\n" + 
			"    d.transfer_detail_id,\n" + 
			"    d.transfer_id,\n" + 
			"    d.item_id,\n" + 
			"    d.qty,\n" + 
			"    d.item_mrp,\n" + 
			"    d.item_rate,\n" + 
			"    d.item_tax,\n" + 
			"    d.cgst,\n" + 
			"    d.igst,\n" + 
			"    d.sgst,\n" + 
			"    d.cgst_amt,\n" + 
			"    d.igst_amt,\n" + 
			"    d.sgst_amt,\n" + 
			"    d.discount,\n" + 
			"    d.extra_charges,\n" + 
			"    d.grand_total,\n" + 
			"    d.tax_amt,\n" + 
			"    d.ex_int1,\n" + 
			"    d.ex_int2,\n" + 
			"    d.ex_float1,\n" + 
			"    d.ex_float2,\n" + 
			"    i.item_name AS ex_var1,\n" + 
			"    d.ex_var2\n" + 
			"FROM\n" + 
			"    t_stock_transfer_detail d,\n" + 
			"    m_item i\n" + 
			"WHERE\n" + 
			"d.transfer_id=:transferId AND\n" + 
			"d.item_id=i.item_id",nativeQuery=true)
	List<StockTransferDetail> getDetailListByTransferId(@Param("transferId") int transferId);
	

}
