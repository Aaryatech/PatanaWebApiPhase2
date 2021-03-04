package com.ats.webapi.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.webapi.model.CakeType;
import com.ats.webapi.model.ErrorMessage;
import com.ats.webapi.model.Info;
import com.ats.webapi.model.Route;
import com.ats.webapi.model.RouteAbcVal;
import com.ats.webapi.model.RouteMaster;
import com.ats.webapi.model.Shape;
import com.ats.webapi.model.State;
import com.ats.webapi.model.User;
import com.ats.webapi.model.prod.GetProductListExlPdf;
import com.ats.webapi.repository.CakeTypeRepo;
import com.ats.webapi.repository.RouteAbcValRepo;
import com.ats.webapi.repository.RouteMasterRepository;
import com.ats.webapi.repository.ShapeRepo;
import com.ats.webapi.repository.StateRepository;
import com.ats.webapi.repository.getproddetailbysubcat.GetProductListExlPdfRepo;
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
	
	@Autowired
	StateRepository stateRepository;
	
	@Autowired CakeTypeRepo cakeTypeRepo;

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

	// ---------------State --------------------------

		@RequestMapping(value = { "/saveState" }, method = RequestMethod.POST)
		public @ResponseBody State saveState(@RequestBody State state) {

			State res = new State();

			try {

				res = stateRepository.saveAndFlush(state);

			} catch (Exception e) {

				e.printStackTrace();

			}
			return res;

		}

		@RequestMapping(value = { "/getStateByStateId" }, method = RequestMethod.POST)
		public @ResponseBody State getStateByStateId(@RequestParam("stateId") int stateId) {

			State state = null;
			try {
				state = stateRepository.findByStateId(stateId);

			} catch (Exception e) {

				e.printStackTrace();

			}
			return state;

		}

		@RequestMapping(value = { "/getAllStates" }, method = RequestMethod.GET)
		public @ResponseBody List<State> getAllStates() {

			List<State> stateList = new ArrayList<State>();

			try {

				stateList = stateRepository.findAllByIsUsed(1);

			} catch (Exception e) {

				e.printStackTrace();

			}
			return stateList;

		}

		@RequestMapping(value = { "/deleteState" }, method = RequestMethod.POST)
		public @ResponseBody ErrorMessage deleteState(@RequestParam("stateId") int stateId) {

			ErrorMessage errorMessage = new ErrorMessage();

			try {
				int delete = stateRepository.deleteState(stateId);

				if (delete == 1) {
					errorMessage.setError(false);
					errorMessage.setMessage(" Deleted Successfully");
				} else {
					errorMessage.setError(true);
					errorMessage.setMessage("Deletion Failed");
				}

			} catch (Exception e) {

				e.printStackTrace();
				errorMessage.setError(true);
				errorMessage.setMessage("Deletion Failed :EXC");

			}
			return errorMessage;
		}
		
		//----------------------------------------------------------------
		@Autowired GetProductListExlPdfRepo prodExpRepo;
		@RequestMapping(value = { "/getAllProdctExlPdf" }, method = RequestMethod.GET)
		public @ResponseBody List<GetProductListExlPdf> getAllProdctExlPdf() {

			List<GetProductListExlPdf> prod = new ArrayList<GetProductListExlPdf>();

			try {

				prod = prodExpRepo.getProductExlPdfList();

			} catch (Exception e) {

				e.printStackTrace();

			}
			return prod;

		}
		// Show Cake Type List
		@RequestMapping(value = { "/showCakeTypeList" }, method = RequestMethod.GET)
		@ResponseBody
		public List<CakeType> showCakeTypeList() {

			List<CakeType> cakeType = cakeTypeRepo.findByDelStatusOrderByCakeTypeIdDesc(0);

			return cakeType;
		}
		
		@RequestMapping(value = { "/getCakeTypeById" }, method = RequestMethod.POST)
		public @ResponseBody CakeType getCakeTypeById(@RequestParam("cakeTypeId") int cakeTypeId) {

			CakeType cakeType = new CakeType();
			try {
				cakeType = cakeTypeRepo.findBycakeTypeId(cakeTypeId);

			} catch (Exception e) {
				e.printStackTrace();
			}
			return cakeType;

		}
		
		@RequestMapping(value = { "/deleteCakeType" }, method = RequestMethod.POST)
		public @ResponseBody ErrorMessage deleteCakeType(@RequestParam("cakeTypeId") int cakeTypeId) {

			ErrorMessage errorMessage = new ErrorMessage();

			try {
				int res = cakeTypeRepo.delCakeType(cakeTypeId);

				if (res > 0) {
					errorMessage.setError(false);
					errorMessage.setMessage(" Deleted Successfully");
				} else {
					errorMessage.setError(true);
					errorMessage.setMessage("Deletion Failed");
				}

			} catch (Exception e) {

				e.printStackTrace();
				errorMessage.setError(true);
				errorMessage.setMessage("Deletion Failed :EXC");

			}
			return errorMessage;
		}
		
		@RequestMapping(value = { "/insertCakeType" }, method = RequestMethod.POST)
		public @ResponseBody CakeType insertCakeType(@RequestBody CakeType cakeType) {

			CakeType res = new CakeType();

			try {

				res = cakeTypeRepo.saveAndFlush(cakeType);

			} catch (Exception e) {

				e.printStackTrace();

			}
			return res;

		}
		

		@Autowired 
		ShapeRepo shapeRepo;
		
		@RequestMapping(value = { "/saveItemChef" }, method = RequestMethod.POST)
		public @ResponseBody Shape Object(@RequestBody Shape shape) {
			Info info=new Info();
			Shape sh	=shapeRepo.save(shape);
		    
			
			if(sh!=null)
			{
				info.setError(false);
				info.setMessage("Data Inseert Successfully");
			}
			else
			{
				info.setError(true);
				info.setMessage("Data Inseert Failed");
			}
			
			
			return sh;
		}
		
		@RequestMapping(value = { "/getAllChef" }, method = RequestMethod.GET)
		public @ResponseBody List<Shape> getAllChef() {

			Info info = new Info();
			
			List<Shape> shapeList=new ArrayList<Shape>();
		
		
			shapeList=shapeRepo.getAllShapes();
				return  shapeList;
			
		}
		
		@RequestMapping(value = { "/getAllChefById" }, method = RequestMethod.POST)
		public @ResponseBody Shape getAllChefById(@RequestParam Integer shapeId) {

			
			Shape sh=shapeRepo.findByShapeId(shapeId);
			return sh;
			 
		}
		
		
		@RequestMapping(value = { "/deleteShape" }, method = RequestMethod.POST)
		public @ResponseBody Info deleteShape(@RequestParam Integer shapeId) {
					Info info=new Info();
						int Flag=0;
					try {
						Flag =shapeRepo.deleteShape(shapeId);
						if(Flag>0) {
							info.setError(false);
							info.setMessage("Shape Deleted");
							
						}else {
							info.setError(true);
							info.setMessage("Unable To  Delete Shape");
							
						}
		}	 catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		info.setError(true);
		info.setMessage("Unable To  Delete Shape");
		System.err.println("Exception In /deleteShape");
		}
			

			return info;
			 
		}
		
		
		
		
		

}
