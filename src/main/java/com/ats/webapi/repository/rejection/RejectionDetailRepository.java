package com.ats.webapi.repository.rejection;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.rejection.RejectionDetail;
//Akhilesh 2021-01-23
public interface RejectionDetailRepository extends JpaRepository<RejectionDetail, Integer> {
	
	
	@Query(value="SELECT\n" + 
			"    d.rej_detail_id,\n" + 
			"    d.reject_id,\n" + 
			"    d.item_id,\n" + 
			"    d.qty,\n" + 
			"    d.del_status,\n" + 
			"    d.ex_int1,\n" + 
			"    d.ex_int2,\n" + 
			"    d.ex_var1,\n" + 
			"     i.item_name  AS ex_var2\n" + 
			"FROM\n" + 
			"    t_rejection_detail d,\n" + 
			"    m_item  i\n" + 
			"WHERE\n" + 
			"    d.del_status=0 AND\n" + 
			"    d.item_id=i.item_id AND\n" + 
			"    d.reject_id=:rejctId ",nativeQuery=true)
	List<RejectionDetail> getRejDetailsByrejId(@Param("rejctId") int rejctId);
	
	@Transactional
	@Modifying
	@Query(value="UPDATE t_rejection_detail SET del_status=1 WHERE reject_id=:rId ",nativeQuery=true)
	int deleteRejDetail(@Param("rId") int rid);
	
	
	
	

}
