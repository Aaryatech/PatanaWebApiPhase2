package com.ats.webapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ats.webapi.model.PosConfigItem;

public interface PosConfigItemRepository extends JpaRepository<PosConfigItem,Integer>{
	
	PosConfigItem save(PosConfigItem posConfigItem);
	
	@Query(value = " SELECT i.id, i.config_name, i.item_ids, i.ext_int1, i.ext_int2, GROUP_CONCAT(c.item_name) AS ext_var1, i.ext_var2, i.del_status FROM pos_config_item i, m_item c WHERE FIND_IN_SET(c.item_id, i.item_ids) AND i.del_status=:del GROUP BY i.config_name", nativeQuery = true)
	List<PosConfigItem> findByDelStatus(@Param("del") int del);

	@Transactional
	@Modifying
	@Query(value="UPDATE pos_config_item SET delStatus=1  WHERE id IN (:idList)",nativeQuery = true)
	public int deleteConfigItem(@Param("idList") List<Integer> id);
	
}
