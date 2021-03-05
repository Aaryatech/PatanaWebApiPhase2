package com.ats.webapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.GetFrMenuExlPdf;

public interface FrMenuExlPdfRepo extends JpaRepository<GetFrMenuExlPdf, Integer> {
	@Query(value="SELECT\n" + 
			"    m.*,\n" + 
			"    c.cat_name, '' AS fr_name\n" + 
			"FROM\n" + 
			"    m_fr_menu_show m,\n" + 
			"    m_category c\n" + 
			"WHERE\n" + 
			"    m.del_status = 0 AND m.cat_id = c.cat_id ORDER BY c.cat_id",nativeQuery=true)
	public List<GetFrMenuExlPdf> getAllFrMenus();
	
	@Query(value="SELECT\n" + 
			"    m.*,\n" + 
			"    CONCAT(fr.fr_name,'-',fr.fr_code) AS fr_name,\n" + 
			"    c.cat_name\n" + 
			"FROM\n" + 
			"    m_fr_menu_show m,\n" + 
			"  	m_fr_menu_configure f,\n" + 
			"    m_category c,\n" + 
			"    m_franchisee fr\n" + 
			"WHERE\n" + 
			"    m.del_status=0 AND\n" + 
			"    f.is_del=0 AND\n" + 
			"    m.cat_id=c.cat_id AND\n" + 
			"    f.fr_id=fr.fr_id AND\n" + 
			"    m.menu_id=f.menu_id AND\n" + 
			"    m.menu_id IN(:menuIds) AND\n" + 
			"    f.fr_id IN (:frIds)", nativeQuery=true)
	public List<GetFrMenuExlPdf> getAllFrMenusExlPdfList(@Param("menuIds") List<String> menuIds, @Param("frIds") List<String> frIds);
}
