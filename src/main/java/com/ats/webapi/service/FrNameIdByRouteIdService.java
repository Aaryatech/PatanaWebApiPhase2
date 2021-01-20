package com.ats.webapi.service;

import com.ats.webapi.model.FrNameIdByRouteIdList;

public interface FrNameIdByRouteIdService {
	
	FrNameIdByRouteIdList getFrNameIdByRouteId(int routeId);
	
	
	//Akhilesh 2020-01-20
	FrNameIdByRouteIdList getFrNameIdByMultiRouteId(String routeId);

}
