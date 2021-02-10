package com.ats.webapi.repository.ggreport;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.grngvnreport.PendingGrnGvnItemWise;

public interface PendingGrnGvnItemWiseRepo extends JpaRepository<PendingGrnGvnItemWise, Integer> {

	@Query(value="SELECT\n" + 
			"	UUID() AS uid,\n" + 
			"    h.grngvn_srno,\n" + 
			"    h.grngvn_date,\n" + 
			"    m.item_name,\n" + 
			"    t.grn_gvn_qty,\n" + 
			"    h.grngvn_status,\n" + 
			"    h.grn_gvn_header_id, h.is_grn\n" + 
			"FROM\n" + 
			"    t_grn_gvn t,\n" + 
			"    t_grn_gvn_header h,\n" + 
			"    m_item m\n" + 
			"    \n" + 
			"WHERE\n" + 
			"    h.grngvn_date BETWEEN :fromDate AND :toDate AND\n" + 
			"    h.fr_id IN (:frIdList) AND\n" + 
			"    h.is_credit_note=0 AND\n" + 
			"    h.grngvn_status=:grnStatus AND\n" + 
			"    t.del_status=0 AND\n" + 
			"    t.grn_gvn_header_id=h.grn_gvn_header_id AND\n" + 
			"    t.item_id=m.id AND  h.is_grn IN (:isGrn)", nativeQuery=true)
	public List<PendingGrnGvnItemWise> getAcPendingGrnGvnItemsAprv(@Param("fromDate") String fromDate, @Param("isGrn") List<String> isGrn,
			@Param("toDate") String toDate, @Param("grnStatus") int grnStatus, @Param("frIdList") List<String> frIdList);
	
	@Query(value="SELECT\n" + 
			"	UUID() AS uid,\n" + 
			"    h.grngvn_srno,\n" + 
			"    h.grngvn_date,\n" + 
			"    m.item_name,\n" + 
			"    t.grn_gvn_qty,\n" + 
			"    h.grngvn_status,\n" + 
			"    h.grn_gvn_header_id, h.is_grn\n" + 
			"FROM\n" + 
			"    t_grn_gvn t,\n" + 
			"    t_grn_gvn_header h,\n" + 
			"    m_item m\n" + 
			"    \n" + 
			"WHERE\n" + 
			"    h.grngvn_date BETWEEN :fromDate AND :toDate AND\n" + 
			"    h.fr_id IN (:frIdList) AND\n" + 
			"    h.is_credit_note=0 AND\n" + 
			"    h.grngvn_status!=:grnStatus AND\n" + 
			"    t.del_status=0 AND\n" + 
			"    t.grn_gvn_header_id=h.grn_gvn_header_id AND\n" + 
			"    t.item_id=m.id AND  h.is_grn IN (:isGrn)", nativeQuery=true)
	public List<PendingGrnGvnItemWise> getAcPendingGrnGvnItems(@Param("fromDate") String fromDate, @Param("isGrn") List<String> isGrn,
			@Param("toDate") String toDate, @Param("grnStatus") int grnStatus, @Param("frIdList") List<String> frIdList);
	
}
