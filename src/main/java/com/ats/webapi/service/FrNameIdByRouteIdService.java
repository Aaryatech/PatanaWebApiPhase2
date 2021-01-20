package com.ats.webapi.service;

import java.util.List;

import com.ats.webapi.model.FrNameIdByRouteIdList;

public interface FrNameIdByRouteIdService {
	
	FrNameIdByRouteIdList getFrNameIdByRouteId(int routeId);
	
	
	//Akhilesh 2020-01-20
	FrNameIdByRouteIdList getFrNameIdByMultiRouteId(List<Integer> routeId);

}
