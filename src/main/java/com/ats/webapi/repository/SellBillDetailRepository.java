package com.ats.webapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ats.webapi.model.SellBillDetail;
@Repository
public interface SellBillDetailRepository extends JpaRepository<SellBillDetail, Long>{

	SellBillDetail save(SellBillDetail sellBillDetail);

	
	@Transactional
	@Modifying
	@Query(" DELETE FROM SellBillDetail WHERE  sellBillDetailNo=:sellBillDetailNo")
	
	int  deleteSellBillDetail(@Param("sellBillDetailNo") int sellBillDetailNo);
	
	
	@Query(value="SELECT * FROM t_sell_bill_detail WHERE del_status=0 AND sell_bill_no=:sellBillNo",nativeQuery=true)
	List<SellBillDetail> getSelBillDetailBySelBillNo(@Param("sellBillNo") int sellBillNo);
	
	
	
}
