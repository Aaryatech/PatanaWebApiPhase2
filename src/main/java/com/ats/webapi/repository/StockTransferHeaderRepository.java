package com.ats.webapi.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.StockTransferHeader;

public interface StockTransferHeaderRepository extends JpaRepository<StockTransferHeader, Integer> {

	@Query(value="SELECT\n" + 
			"h.transfer_id,\n" + 
			"h.from_fr_id,\n" + 
			"h.to_fr_id,\n" + 
			"h.maker_date,\n" + 
			"h.maker_datetime,\n" + 
			"h.taxable_amt,\n" + 
			"h.tax,\n" + 
			"h.rate_total,\n" + 
			"h.status,\n" + 
			"h.del_status,\n" + 
			"h.additional_charges,\n" + 
			"h.grand_total,\n" + 
			"h.ex_int1,\n" + 
			"h.ex_int2,\n" + 
			"m.fr_name AS ex_var1,\n" + 
			"h.ex_var2\n" + 
			"FROM\n" + 
			"    t_stock_transfer_header h,\n" + 
			"    m_franchisee m\n" + 
			"WHERE\n" + 
			"   h. from_fr_id =:fromFrId  \n" + 
			"   AND  \n" + 
			"   h.del_status = 0\n" + 
			"   AND\n" + 
			"   h.to_fr_id=m.fr_id",nativeQuery=true)
	List<StockTransferHeader> getStockHeaderByFromFrid(@Param("fromFrId") int fromFrId);
	
	@Query(value="SELECT\n" + 
			"h.transfer_id,\n" + 
			"h.from_fr_id,\n" + 
			"h.to_fr_id,\n" + 
			"h.maker_date,\n" + 
			"h.maker_datetime,\n" + 
			"h.taxable_amt,\n" + 
			"h.tax,\n" + 
			"h.rate_total,\n" + 
			"h.status,\n" + 
			"h.del_status,\n" + 
			"h.additional_charges,\n" + 
			"h.grand_total,\n" + 
			"h.ex_int1,\n" + 
			"h.ex_int2,\n" + 
			"m.fr_name AS ex_var1,\n" + 
			"h.ex_var2\n" + 
			"FROM\n" + 
			"    t_stock_transfer_header h,\n" + 
			"    m_franchisee m\n" + 
			"WHERE\n" + 
			"   h. to_fr_id =:toFrId  \n" + 
			"   AND  \n" + 
			"   h.del_status = 0 \n" + 
			"   AND\n" + 
			"   h.to_fr_id=m.fr_id",nativeQuery=true)
	List<StockTransferHeader> getStockHeaderBytoFrid(@Param("toFrId") int toFrId);
	
	
	
	@Transactional
	@Modifying
	@Query(value="UPDATE t_stock_transfer_header SET status=:status WHERE transfer_id=:tranId ",nativeQuery=true)
	int updateStatus(@Param("tranId") int tranId,@Param("status") int status);
	
	
	
	
	
}
