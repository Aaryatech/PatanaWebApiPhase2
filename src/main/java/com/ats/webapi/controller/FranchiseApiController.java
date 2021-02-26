package com.ats.webapi.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.webapi.model.Info;
import com.ats.webapi.model.Route;
import com.ats.webapi.model.RouteAbcVal;
import com.ats.webapi.model.RouteMaster;
import com.ats.webapi.model.User;
import com.ats.webapi.repository.RouteAbcValRepo;
import com.ats.webapi.repository.RouteMasterRepository;
import com.ats.webapi.util.JsonUtil;

@RestController
public class FranchiseApiController {
	public static String incrementDate(String date, int day) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(sdf.parse(date));

		} catch (ParseException e) {
			// System.out.println("Exception while incrementing date " + e.getMessage());
			e.printStackTrace();
		}
		c.add(Calendar.DATE, day); // number of days to add
		date = sdf.format(c.getTime());

		return date;

	}

	@Autowired
	RouteMasterRepository routeMasterRepository;
	
	@Autowired RouteAbcValRepo abcRepo;

	// Show Route List
	@RequestMapping(value = { "/showRouteListNew" }, method = RequestMethod.GET)
	@ResponseBody
	public List<RouteMaster> showRouteListNew() {

		List<RouteMaster> routeList = routeMasterRepository.findByDelStatusOrderByRouteNameAsc(0);

		return routeList;
	}
	
	@RequestMapping(value = { "/saveRoute" }, method = RequestMethod.POST)
	@ResponseBody
	public RouteMaster saveRoute(@RequestBody RouteMaster routeMaster) {

		RouteMaster jsonResult = routeMasterRepository.save(routeMaster);

		return jsonResult;
	}
	
	@RequestMapping(value = { "/showRouteListAndAbcType" }, method = RequestMethod.GET)
	@ResponseBody
	public List<RouteMaster> showRouteListAndAbcType() {

		List<RouteMaster> routeList = routeMasterRepository.getFrRouteAndAbcType();

		return routeList;
	}
	
	
	@RequestMapping(value = { "/getRouteNew" }, method = RequestMethod.GET)
	public @ResponseBody RouteMaster getRouteNew(@RequestParam("routeId") int routeId) {
		RouteMaster route = routeMasterRepository.findByRouteId(routeId);
		return route;
	}
	
	@RequestMapping(value = { "/showRouteAbcValList" }, method = RequestMethod.GET)
	@ResponseBody
	public List<RouteAbcVal> showRouteAbcValList() {

		List<RouteAbcVal> val = abcRepo.findByDelStatusOrderByAbcIdAsc(0);

		return val;
	}
	
	@RequestMapping(value = { "/validateRouteShortName" }, method = RequestMethod.POST)
	public @ResponseBody RouteMaster validateRouteShortName(@RequestParam String shortName, @RequestParam int routeId) {

		
		RouteMaster route = new RouteMaster();
		if(routeId==0) {
			route = routeMasterRepository.findByShortNameIgnoreCase(shortName);
		}else {
			route = routeMasterRepository.findByShortNameIgnoreCaseAndRouteIdNotIn(shortName, routeId);
		}
		
		return route;
	}
	
	@RequestMapping(value = { "/validateRoutePrefix" }, method = RequestMethod.POST)
	public @ResponseBody RouteMaster validateRoutePrefix(@RequestParam String routePrefix, @RequestParam int routeId) {

		
		RouteMaster route = new RouteMaster();
		if(routeId==0) {
			route = routeMasterRepository.findByRoutePrefixIgnoreCase(routePrefix);
		}else {
			route = routeMasterRepository.findByRoutePrefixIgnoreCaseAndRouteIdNotIn(routePrefix, routeId);
		}		
		return route;
	}

}
