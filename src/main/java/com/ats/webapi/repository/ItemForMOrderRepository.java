package com.ats.webapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ats.webapi.model.ItemForMOrder;
@Repository
public interface ItemForMOrderRepository extends JpaRepository<ItemForMOrder, Integer>{

	//@Query(value="select m_item.*,coalesce((select disc_per from m_fr_discount where is_active=1 and category_id=:itemGrp1 and FIND_IN_SET(m_item.id,item_id) and FIND_IN_SET(:frId,franch_id) order by disc_id desc limit 1),0) as disc_per from m_item where m_item.item_grp1=:itemGrp1 and m_item.del_status=0",nativeQuery=true)
	@Query(value = "Select m_item.*,m_fr_configure.disc_per as disc_per,\n" + 
			"			0 as order_qty \n" + 
			"			from m_item,m_fr_configure where  m_item.del_status=0 and FIND_IN_SET(m_item.id,m_fr_configure.item_show)\n" + 
			"            and m_fr_configure.menu_id=:menuId order by m_item.item_grp2,m_item.item_name", nativeQuery = true)
	List<ItemForMOrder> getItemListForMOrder(@Param("menuId")int menuId);

	@Query(value="select m_item.*,0 as order_qty,coalesce((select disc_per from m_fr_discount where is_active=1 and category_id=:itemGrp1 and FIND_IN_SET(m_item.id,item_id) and FIND_IN_SET(:frId,franch_id) order by disc_id desc limit 1),0) as disc_per from m_item where m_item.item_grp1=:itemGrp1 and m_item.del_status=0 order by m_item.item_grp2,m_item.item_name",nativeQuery=true)
	List<ItemForMOrder> getItemListForMOrderPrev(@Param("itemGrp1") int itemGrp1,@Param("frId") int frId);

	@Query(value="select m_item.*,0 as order_qty,0 as disc_per from m_item where m_item.item_grp1=:itemGrp1 and m_item.del_status=0 order by m_item.item_grp2,m_item.item_name",nativeQuery=true)
	List<ItemForMOrder> getItemListForMOrderMul(@Param("itemGrp1")int itemGrp1);

}
