package com.ats.webapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ats.webapi.model.section.SectionMenuIdname;
//Akhilesh 2020-01-21
public interface SectionMenuidNameRepo extends JpaRepository<SectionMenuIdname, String>{

	//Section With Menu id And Menu title In Extra Field
	@Query(value="SELECT UUId() AS id, s.section_id, s.section_name, m.menu_id, m.menu_title FROM m_section s, m_fr_menu_show m WHERE s.del_status = 0 AND FIND_IN_SET(m.menu_id, s.menu_ids) ORDER BY s.section_id",nativeQuery=true)
	List<SectionMenuIdname> getAllSectionWithMenu();
	
}
