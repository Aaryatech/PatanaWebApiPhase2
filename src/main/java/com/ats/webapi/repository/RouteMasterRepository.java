package com.ats.webapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.RouteMaster;
import java.lang.String;


public interface RouteMasterRepository extends JpaRepository<RouteMaster, Integer>{

	List<RouteMaster> findByDelStatusOrderByRouteNameAsc(int i);

	RouteMaster findByRouteId(int routeId);

	List<RouteMaster> findByDelStatusAndAbcTypeOrderByRouteNameAsc(int i, int abcType);
	
	RouteMaster findByShortNameIgnoreCase(@Param("shortName") String shortName);

	RouteMaster findByShortNameIgnoreCaseAndRouteIdNotIn(@Param("shortName") String shortName, @Param("routeId") int routeId);
	
	RouteMaster findByRoutePrefixIgnoreCase(@Param("routeprefix") String routeprefix);
	
	RouteMaster findByRoutePrefixIgnoreCaseAndRouteIdNotIn(@Param("routePrefix") String routePrefix, @Param("routeId") int routeId);
}
