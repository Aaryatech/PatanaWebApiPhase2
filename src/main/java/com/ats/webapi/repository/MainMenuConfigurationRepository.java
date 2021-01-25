package com.ats.webapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ats.webapi.model.AllMenus;
import com.ats.webapi.model.SubCategory;
@Repository
public interface MainMenuConfigurationRepository extends JpaRepository<AllMenus, Integer> {
	
	public AllMenus save(AllMenus  allMenus);

	public List<AllMenus> findByDelStatusOrderByMenuTitleAsc(int i);
	
	@Query(value="SELECT m_fr_menu_show.* from m_fr_menu_show WHERE m_fr_menu_show.cat_id=:catId AND m_fr_menu_show.del_status=:i",nativeQuery=true)
	public List<AllMenus> findByMainCatId(@Param("catId") int catId, @Param("i")int i);

	
	@Query(value="select menu_id from m_fr_menu_show where cat_id=:catId",nativeQuery=true)
	public List<Integer> findMenuIdByMainCatId(@Param("catId")int catId);

	public AllMenus findByMenuIdAndDelStatus(int menuId, int del);
	
	//Sachin 19-01-2021
	@Query(value="SELECT m_fr_menu_show.* from m_fr_menu_show WHERE  m_fr_menu_show.del_status=0 and m_fr_menu_show.menu_id NOT IN(select menu_id from m_fr_configure where is_del=0)",nativeQuery=true)
	public List<AllMenus> getAllNonConfMenus();
	
	//Sachin 25-01-2021
	@Query(value="SELECT m_fr_menu_show.* from m_fr_menu_show WHERE m_fr_menu_show.menu_id IN(:menuIds) AND m_fr_menu_show.del_status=0",nativeQuery=true)
	public List<AllMenus> findByMenuIdIn(@Param("menuIds") List<Integer> menuIds);

	//Sachin 25-01-2021 For Menu Id in Manual Order Page Impl At Sachin Work Controller RestAPI
	@Query(value=" SELECT m_fr_menu_show.* from m_fr_menu_show,m_fr_menu_configure,m_section WHERE "
			+ " m_fr_menu_show.menu_id=m_fr_menu_configure.menu_id and m_fr_menu_configure.fr_id=:frId "
			+ "and m_fr_menu_configure.menu_id=m_fr_menu_show.menu_id and FIND_IN_SET(m_fr_menu_configure.menu_id,m_section.menu_ids) "
			+ " AND m_fr_menu_show.del_status=0 and m_section.section_id=:sectionId  ",nativeQuery=true)
	public List<AllMenus> findByFrIdAndSectionId(@Param("sectionId") int sectionId,@Param("frId") int frId);

	
}
