package com.ats.webapi.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.section.Section;


//Akhilesh 2020-20-01
public interface SectionRepository extends JpaRepository<Section, Integer> {

	@Query(value="SELECT * FROM m_section WHERE del_status=0 AND section_id=:sectionId",nativeQuery=true)
	Section getSingleSectionById(@Param("sectionId") Integer sectionId);
	
	@Query(value="SELECT * FROM m_section WHERE del_status=0",nativeQuery=true)
	List<Section> getAllSection();
	
	@Transactional
	@Modifying
	@Query(value="UPDATE m_section SET del_status=1 WHERE section_id=:sectionId",nativeQuery=true)
	int deleteSection(@Param("sectionId") Integer sectionId);

	List<Section> findByDelStatus(int i);

	Section findBySectionIdAndDelStatus(int sectionId, int i);
	

	//Section With Menu id And Menu title In Extra Field
	
	
}
