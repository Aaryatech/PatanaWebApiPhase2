package com.ats.webapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.webapi.model.RouteAbcVal;
import com.ats.webapi.model.RouteMaster;

public interface RouteAbcValRepo extends JpaRepository<RouteAbcVal, Integer> {

	List<RouteAbcVal> findByDelStatusOrderByAbcIdAsc(int i);

}
