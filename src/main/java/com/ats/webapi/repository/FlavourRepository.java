package com.ats.webapi.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ats.webapi.model.Flavour;
import com.ats.webapi.model.Message;

@Repository
public interface FlavourRepository extends JpaRepository<Flavour, Integer>{
	/*
	 * Flavour save(Flavour flavour); Flavour findOne(int spfId); List<Flavour>
	 * findByDelStatus(int i); List<Flavour> findByDelStatusOrderBySpfNameAsc(int
	 * i); List<Flavour> findBySpfIdIn(List<Integer> spfId); List<Flavour>
	 * findBySpType(int type);
	 */
	
	//Sachin 29-01-2021
	
	Flavour save(Flavour flavour);
    Flavour findOne(int spfId);
	List<Flavour> findByDelStatus(int i);
	List<Flavour> findByOrderBySpfNameAsc();
	List<Flavour> findBySpfIdIn(List<Integer> spfId);
	List<Flavour> findBySpType(int type);
	List<Flavour> findBySpfIdNotIn(List<Integer> spfId);
	List<Flavour> findBySpfIdNotInAndSpType(List<Integer> spfId, int type);
	@Query(value="select * from m_sp_flavour where spf_id IN(select DISTINCT spf_id from m_sp_flavour_conf where m_sp_flavour_conf.del_status=0 and m_sp_flavour_conf.sp_id=:spId) and del_status=0 order by spf_name",nativeQuery=true)
	List<Flavour> findBySpId1(@Param("spId")int spId);
	List<Flavour> findByDelStatusOrderBySpfNameAsc(int i);
	
	@Query(value="	SELECT m_sp_flavour.* FROM m_sp_flavour,m_sp_cake,m_sp_flavour_conf WHERE m_sp_flavour.spf_id=m_sp_flavour_conf.spf_id AND m_sp_flavour_conf.sp_id=m_sp_cake.sp_id AND m_sp_cake.sp_id=:spId " + 
			"",nativeQuery=true)
	List<Flavour> findBySpId(@Param("spId")int spId);

	@Query(value=" select\n" + 
			"        spf_id,\n" + 
			"        del_status,\n" + 
			"        sp_type,\n" + 
			"        spf_adon_rate,\n" + 
			"        spf_name \n" + 
			"    from\n" + 
			"        m_sp_flavour\n" + 
			" ORDER BY del_status",nativeQuery=true)
	List<Flavour> findAllSpFlavourList();
	
	@Transactional
	@Modifying
	@Query(value=" update m_sp_flavour set del_status=:status where spf_id IN (:spfId)",nativeQuery=true)
	int updateFlavourIds(@Param("spfId") List<String> spfId, @Param("status") int status);
			
}
