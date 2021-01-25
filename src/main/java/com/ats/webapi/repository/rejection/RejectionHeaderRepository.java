package com.ats.webapi.repository.rejection;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.rejection.RejectionHeader;
//Akhilesh 2021-01-23
public interface RejectionHeaderRepository extends JpaRepository<RejectionHeader, Integer> {
	
	@Query(value="SELECT * FROM t_rejection_header WHERE del_status=0 AND reject_id=:rejectId",nativeQuery=true)
	RejectionHeader getRejcHeaderByid(@Param("rejectId") int rejectId);
	
	@Query(value="SELECT * FROM t_rejection_header WHERE del_status=0",nativeQuery=true)
	List<RejectionHeader> getAllRejectionHeader();
	
	
	@Transactional
	@Modifying
	@Query(value="UPDATE t_rejection_header SET del_status=1 WHERE reject_id=:rId ",nativeQuery=true)
	int deleteRejHeader(@Param("rId") int rid);

}
