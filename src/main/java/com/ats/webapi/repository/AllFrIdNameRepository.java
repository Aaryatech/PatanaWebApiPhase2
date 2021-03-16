package com.ats.webapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.AllFrIdName;

public interface AllFrIdNameRepository extends JpaRepository<AllFrIdName, Integer> {
	
	@Query(value=" SELECT fr_id,CONCAT(fr_name, ' ', fr_code) AS fr_name from m_franchisee WHERE del_status=0 order by fr_Id Asc",nativeQuery=true)
	
	List<AllFrIdName> getAllFrIdName();
	
	
	//Changed for getting menu configured fr only by sachin 25-01-2021 m_fr_menu_configure table added to compare
		@Query(value="select m_franchisee.fr_id,CONCAT(m_franchisee.fr_name, ' ', m_franchisee.fr_code) AS  fr_name from m_fr_menu_configure, m_franchisee where m_franchisee.del_status=0 And  m_franchisee.fr_id NOT"
					+" IN(select t_order.fr_id from t_order where production_date=:orderDate AND menu_id=:menuId) and m_fr_menu_configure.fr_id=m_franchisee.fr_id and "
					+ "m_fr_menu_configure.menu_id=:menuId  order by m_franchisee.fr_name Asc"
					,nativeQuery=true)
	public List<AllFrIdName> findNonOrders(@Param("orderDate") String orderDate, @Param("menuId") int menuId);

		@Query(value=" SELECT fr_id,CONCAT(fr_name, ' ', fr_code) AS fr_name from m_franchisee where fr_id=:frId",nativeQuery=true)
		AllFrIdName findByFrId(@Param("frId")int frId);
		
		//Sachin 25-01-2021 For Showing Fr in push order on select of menuId.
		//Implemented at webapi In SachinWork Rest controller.
		@Query(value=" SELECT m_franchisee.fr_id,CONCAT(m_franchisee.fr_name, ' ', fr_code) AS fr_name from m_franchisee,m_fr_menu_configure WHERE del_status=0\n" + 
				" AND m_fr_menu_configure.fr_id=m_franchisee.fr_id AND m_fr_menu_configure.is_del=0 AND m_fr_menu_configure.menu_id=:menuId order by m_franchisee.fr_Id Asc",nativeQuery=true)
		List<AllFrIdName> getAllFrIdNameByMenuId(@Param("menuId")int menuId);
		

}

