package com.ats.webapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ats.webapi.model.report.DispatchSpCake;
@Repository
public interface DispatchSpCakeRepo extends JpaRepository<DispatchSpCake, Integer> {

	@Query(value="SELECT\n" + 
			"	tsp.sp_order_no,\n" + 
			"    msp.sp_id,\n" + 
			"    msp.sp_name,\n" + 
			"    msp.sp_code,\n" + 
			"    spf.spf_name,\n" + 
			"    tsp.sp_delivery_place AS fr_order_no,\n" + 
			"    tsp.sp_selected_weight AS no_of_kgs,\n" + 
			"    tsp.sp_backend_rate\n" + 
			"FROM\n" + 
			"    t_sp_cake tsp,\n" + 
			"    m_sp_flavour spf,\n" + 
			"    m_sp_cake msp\n" + 
			"WHERE\n" + 
			"    msp.sp_id=tsp.sp_id AND\n" + 
			"    tsp.sp_flavour_id=spf.spf_id AND\n" + 
			"    tsp.del_status=0 AND\n" + 
			"    tsp.fr_id IN(:frId) AND\n" + 
			"    tsp.sp_prod_date=:productionDateYMD",nativeQuery=true)
	List<DispatchSpCake> getPDispatchSpCake(@Param("productionDateYMD")String productionDateYMD,@Param("frId") List<String> frId);
	
}
