package com.ats.webapi.repository;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.*;

public interface TestFrRepository extends CrudRepository<ConfigureFranchisee, Long>{
	
	
	
	 @Query(value = "SELECT * FROM m_fr_configure ,m_fr_menu_show WHERE m_fr_configure.menu_id = m_fr_menu_show.menu_id", nativeQuery = true)
	    List<ConfigureFranchisee> findAllProjectedNativeQuery();
	 
	 @Query(value = "SELECT * FROM m_fr_configure where fr_id =:frId AND cat_id =:catId AND is_del = 0", nativeQuery = true)
	    List<ConfigureFranchisee> findByFrAndCat(@Param("frId") int frId, @Param("catId") int catId);
	
	 //Akhilesh 2020-01-21 For Add Section On Admin
	 @Query(value="SELECT * FROM m_fr_configure WHERE is_del=0 ",nativeQuery=true)
	 List<ConfigureFranchisee> findAllFrBydelStatus();
	 
	 
	 
	 
}


