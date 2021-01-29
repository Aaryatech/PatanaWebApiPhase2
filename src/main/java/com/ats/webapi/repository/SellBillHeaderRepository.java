package com.ats.webapi.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ats.webapi.model.SellBillHeader;

@Repository
public interface SellBillHeaderRepository extends JpaRepository<SellBillHeader, Long>{
	
	@SuppressWarnings("unchecked")
	public SellBillHeader save (SellBillHeader sellBillHeaderList);
	
	@Query(value="SELECT * FROM t_sell_bill_header WHERE del_status=0 AND sell_bill_no=:sellBillNo",nativeQuery=true)
	public SellBillHeader getSellBillBySellBillNo(@Param("sellBillNo") int sellBillNo);
	
	@Transactional
	@Modifying
	@Query(value="UPDATE t_sell_bill_header SET del_status=1 WHERE sell_bill_no=:sellBillNo",nativeQuery=true)
	public int deleteSellBillHeader(@Param("sellBillNo") int sellBillNo);
	
	
}
