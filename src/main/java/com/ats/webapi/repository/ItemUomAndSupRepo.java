package com.ats.webapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.GetItemUomAndSup;

public interface ItemUomAndSupRepo extends JpaRepository<GetItemUomAndSup, Integer> {
	
	@Query(value="SELECT\n" + 
			"    m_item.*,\n" + 
			"    m_rm_uom.uom,\n" + 
			"    m_item_sup.item_hsncd\n" + 
			"FROM\n" + 
			"    m_item,\n" + 
			"    m_item_sup,\n" + 
			"    m_rm_uom\n" + 
			"WHERE\n" + 
			"    m_item.del_status=0 AND\n" + 
			"    m_item.id=m_item_sup.item_id AND\n" + 
			"    m_item_sup.uom_id=m_rm_uom.uom_id AND\n" + 
			"    m_item.item_grp2=:subCatId\n" + 
			"ORDER BY m_item.item_name ASC", nativeQuery=true)
	public List<GetItemUomAndSup> findItemDtlBySubCatId(@Param("subCatId") String subCatId);
}
