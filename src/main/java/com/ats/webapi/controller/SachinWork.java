package com.ats.webapi.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.webapi.model.AllFrIdName;
import com.ats.webapi.model.AllFrIdNameList;
import com.ats.webapi.model.AllMenuJsonResponse;
import com.ats.webapi.model.AllMenus;
import com.ats.webapi.model.ConfigureFrBean;
import com.ats.webapi.model.ConfigureFrBeanList;
import com.ats.webapi.model.ConfigureFranchisee;
import com.ats.webapi.model.ErrorMessage;
import com.ats.webapi.model.Flavour;
import com.ats.webapi.model.FlavourConf;
import com.ats.webapi.model.FlavourList;
import com.ats.webapi.model.FrMenuConfigure;
import com.ats.webapi.model.GenerateBill;
import com.ats.webapi.model.GetFrMenuConfigure;
import com.ats.webapi.model.HsnwiseBillExcelSummary;
import com.ats.webapi.model.Info;
import com.ats.webapi.model.Item;
import com.ats.webapi.model.ItemForMOrder;
import com.ats.webapi.model.OrderLog;
import com.ats.webapi.model.Orders;
import com.ats.webapi.model.PostFrItemStockDetail;
import com.ats.webapi.model.PostFrItemStockHeader;
import com.ats.webapi.model.SpecialCake;
import com.ats.webapi.model.SpecialCakeList;
import com.ats.webapi.model.frsetting.NewSetting;
import com.ats.webapi.repository.AllFrIdNameRepository;
import com.ats.webapi.repository.ConfigureFrListRepository;
import com.ats.webapi.repository.ConfigureFrRepository;
import com.ats.webapi.repository.FlavourConfRepository;
import com.ats.webapi.repository.FlavourRepository;
import com.ats.webapi.repository.FrMenuConfigureRepository;
import com.ats.webapi.repository.GenerateBillRepository;
import com.ats.webapi.repository.GetFrMenuConfigureRepository;
import com.ats.webapi.repository.HsnwiseBillExcelSummaryRepository;
import com.ats.webapi.repository.ItemForMOrderRepository;
import com.ats.webapi.repository.MainMenuConfigurationRepository;
import com.ats.webapi.repository.NewSettingRepository;
import com.ats.webapi.repository.OrderLogRespository;
import com.ats.webapi.repository.PostFrOpStockDetailRepository;
import com.ats.webapi.repository.PostFrOpStockHeaderRepository;
import com.ats.webapi.repository.SpecialCakeRepository;
import com.ats.webapi.service.ConfigureFranchiseeService;
import com.ats.webapi.service.ItemService;
import com.ats.webapi.service.OrderService;
import com.ats.webapi.service.SpecialCakeService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
public class SachinWork {
	@Autowired
	private MainMenuConfigurationRepository mainMenuConfigurationRepository;

	@RequestMapping(value = { "/getNonConfMenus" }, method = RequestMethod.GET)
	public @ResponseBody List<AllMenus> getNonConfMenus() {

		List<AllMenus> allMenu = mainMenuConfigurationRepository.getAllNonConfMenus();

		return allMenu;
	}

	@Autowired
	ConfigureFrListRepository configureFrListRepository;

	/*
	 * 21-01-2021 // 3-01-19
	 */ @RequestMapping(value = { "/findConfiguredMenuFrList" }, method = RequestMethod.GET)
	public @ResponseBody ConfigureFrBeanList findConfiguredMenuFrList() {
		ConfigureFrBeanList beanList = new ConfigureFrBeanList();
		List<ConfigureFrBean> configBean = configureFrListRepository.findConfiguredMenuFrList();

		beanList.setConfigureFrBean(configBean);
		Info info = new Info();
		info.setError(false);
		info.setMessage("configure Fr  Menu List displayed successfully");
		beanList.setInfo(info);

		return beanList;
	}

	@Autowired
	FrMenuConfigureRepository frMenuConfigureRepository;
	@Autowired
	private ConfigureFranchiseeService configureService;
	@Autowired
	NewSettingRepository newSettingRepository;
	@Autowired
	PostFrOpStockHeaderRepository postFrOpStockHeaderRepository;
	@Autowired
	ItemService itemService;
	@Autowired
	PostFrOpStockDetailRepository postFrOpStockDetailRepository;

	@RequestMapping(value = "/saveFrMenuConfigure", method = RequestMethod.POST)
	public @ResponseBody Info saveFrMenuConfigure(@RequestParam List<String> frIdList,
			@RequestParam List<String> menuIdList) {
		Info info = new Info();
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		System.err.println("1 currentYear=" + currentYear);
		int currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
		System.err.println("2 currentMonth=" + currentMonth);
		try {
			System.err.println("3 try main");
			for (int i = 0; i < frIdList.size(); i++) {
				System.err.println("###  ###");
				for (int j = 0; j < menuIdList.size(); j++) {
					System.err.println("***  ***");
					FrMenuConfigure prevFrMenuConfigure = frMenuConfigureRepository.findByFrIdAndMenuIdAndIsDel(
							Integer.parseInt(frIdList.get(i)), Integer.parseInt(menuIdList.get(j)), 0);
					System.err.println("4 prevFrMenuConfigure" + prevFrMenuConfigure);
					if (prevFrMenuConfigure == null) {

						FrMenuConfigure frMenu = new FrMenuConfigure();
						frMenu.setSettingId(0);
						frMenu.setFrId(Integer.parseInt(frIdList.get(i)));
						frMenu.setMenuId(Integer.parseInt(menuIdList.get(j)));
						frMenu.setIsDel(0);
						System.err.println("5 frMenu" + frMenu.toString());
						FrMenuConfigure frMenuConfigure = frMenuConfigureRepository.save(frMenu);
						System.err.println("6 frMenuConfigure afer insert" + frMenuConfigure.toString());
					}

				}
			}
			info.setError(false);
			info.setMessage("Menu Configured");
			System.err.println("7 info" + info.toString());
			for (int i = 0; i < frIdList.size(); i++) {
				System.err.println("### ###");
				for (int j = 0; j < menuIdList.size(); j++) {
					System.err.println("1111111  j" + j);
					ConfigureFranchisee configureFranchisee = configureService
							.findFranchiseeById(Integer.parseInt(menuIdList.get(j)));
					System.err.println("8 configureFranchisee" + configureFranchisee.toString());
					NewSetting newSetting = newSettingRepository.findBySettingKeyAndDelStatus("cat_id_open_stock", 0);
					List<Integer> catIdForStock = Stream.of(newSetting.getSettingValue1().split(","))
							.map(Integer::parseInt).collect(Collectors.toList());
					List<PostFrItemStockHeader> prevStockHeader = postFrOpStockHeaderRepository
							.findByFrIdAndIsMonthClosedAndCatId(Integer.parseInt(frIdList.get(i)), 0,
									configureFranchisee.getCatId());
					System.err.println("9 prevStockHeader" + prevStockHeader);
					if (catIdForStock.contains(configureFranchisee.getCatId())) {

						if (prevStockHeader.size() == 0) {
							PostFrItemStockHeader postFrItemStockHeader = new PostFrItemStockHeader();
							postFrItemStockHeader.setOpeningStockHeaderId(0);
							postFrItemStockHeader.setFrId(Integer.parseInt(frIdList.get(i)));
							postFrItemStockHeader.setCatId(configureFranchisee.getCatId());
							postFrItemStockHeader.setYear(currentYear);
							postFrItemStockHeader.setMonth(currentMonth);
							postFrItemStockHeader.setIsMonthClosed(0);
							System.err.println("10 postFrItemStockHeader" + postFrItemStockHeader.toString());
							PostFrItemStockHeader postFrItemStockHeaderRes = postFrOpStockHeaderRepository
									.save(postFrItemStockHeader);
							System.err.println(
									"11 after insert postFrItemStockHeader --" + postFrItemStockHeaderRes.toString());

							List<PostFrItemStockDetail> postFrItemStockDetailList = new ArrayList<PostFrItemStockDetail>();
							List<Integer> ids = Stream.of(configureFranchisee.getItemShow().split(","))
									.map(Integer::parseInt).collect(Collectors.toList());
							System.err.println("12 ids --" + ids.toString());

							List<Item> itemsList = itemService.findAllItemsByItemId(ids);
							System.err.println("13 itemsList --" + itemsList.toString());
							for (int k = 0; k < itemsList.size(); k++) {

								PostFrItemStockDetail postFrItemStockDetail = new PostFrItemStockDetail();
								postFrItemStockDetail
										.setOpeningStockHeaderId(postFrItemStockHeaderRes.getOpeningStockHeaderId());
								postFrItemStockDetail.setOpeningStockDetailId(0);
								postFrItemStockDetail.setRegOpeningStock(0);
								postFrItemStockDetail.setItemId(itemsList.get(k).getId());
								postFrItemStockDetail.setRemark("");
								postFrItemStockDetailList.add(postFrItemStockDetail);
								System.err.println("14 postFrItemStockDetail --" + postFrItemStockDetail.toString());
							}
							System.err
									.println("15 postFrItemStockDetailList --" + postFrItemStockDetailList.toString());
							postFrOpStockDetailRepository.save(postFrItemStockDetailList);

						} else {
							List<PostFrItemStockDetail> postFrItemStockDetailList = new ArrayList<PostFrItemStockDetail>();
							List<Integer> ids = Stream.of(configureFranchisee.getItemShow().split(","))
									.map(Integer::parseInt).collect(Collectors.toList());
							System.err.println("16 ids --" + ids.toString());
							List<Item> itemsList = itemService.findAllItemsByItemId(ids);
							System.err.println("17 itemsList --" + itemsList.toString());
							for (int k = 0; k < itemsList.size(); k++) {
								PostFrItemStockDetail prevFrItemStockDetail = postFrOpStockDetailRepository
										.findByItemIdAndOpeningStockHeaderId(itemsList.get(k).getId(),
												prevStockHeader.get(0).getOpeningStockHeaderId());
								System.err.println("18 prevFrItemStockDetail --" + prevFrItemStockDetail);
								if (prevFrItemStockDetail == null) {
									PostFrItemStockDetail postFrItemStockDetail = new PostFrItemStockDetail();
									postFrItemStockDetail
											.setOpeningStockHeaderId(prevStockHeader.get(0).getOpeningStockHeaderId());// first
																														// stock
																														// header
																														// (month
																														// closed
																														// 0
																														// status))
									postFrItemStockDetail.setOpeningStockDetailId(0);
									postFrItemStockDetail.setRegOpeningStock(0);
									postFrItemStockDetail.setItemId(itemsList.get(k).getId());
									postFrItemStockDetail.setRemark("");
									postFrItemStockDetailList.add(postFrItemStockDetail);
									System.err
											.println("19 postFrItemStockDetail --" + postFrItemStockDetail.toString());
								}
							}
							postFrOpStockDetailRepository.save(postFrItemStockDetailList);
							System.err
									.println("20 postFrItemStockDetailList --" + postFrItemStockDetailList.toString());
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			info.setError(true);
			info.setMessage("Menu Configuration Failed");
		}
		return info;
	}

	@Autowired
	GetFrMenuConfigureRepository getFrMenuConfigureRepository;

	@RequestMapping(value = "/getFrMenuConfigureList", method = RequestMethod.GET)
	public @ResponseBody List<GetFrMenuConfigure> getFrMenuConfigureList() {

		List<GetFrMenuConfigure> confList;
		try {
			confList = getFrMenuConfigureRepository.getFrMenuConfigureList();
		} catch (Exception e) {
			confList = new ArrayList<>();
			e.printStackTrace();

		}
		return confList;

	}

	@RequestMapping(value = { "/deleteFrConfMenu" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteFrConfMenu(@RequestParam int settingId) {

		int isDeleted = frMenuConfigureRepository.deleteFrConfMenu(settingId);
		Info info = new Info();
		if (isDeleted == 1) {
			info.setError(false);
			info.setMessage("Menu Conf Deleted");
		} else {
			info.setError(true);
			info.setMessage("Menu Conf Deletion Failed");
		}
		return info;
	}

	@RequestMapping(value = { "/postFrOpStockDetailList" }, method = RequestMethod.POST)
	public @ResponseBody List<PostFrItemStockDetail> postFrOpStockDetailList(
			@RequestBody List<PostFrItemStockDetail> detailList)
			throws ParseException, JsonParseException, JsonMappingException, IOException {

		System.out.println("Data Common " + detailList.toString());

		List<PostFrItemStockDetail> details = postFrOpStockDetailRepository.save(detailList);

		return details;

	}
	// 23-01-2021

	@RequestMapping(value = { "/getFrMenuConfigureByMenuFrId1" }, method = RequestMethod.POST)
	public @ResponseBody FrMenuConfigure getFrMenuConfigureByMenuFrId(@RequestParam("frId") int frId,
			@RequestParam("menuId") int menuId) {
		FrMenuConfigure frMenuConf = frMenuConfigureRepository.findByFrIdAndMenuIdAndIsDel(frId, menuId, 0);
		return frMenuConf;
	}

	@Autowired
	ConfigureFrRepository configureFrRepository;

	@RequestMapping(value = { "/getFrMenuConfigureByMenuFrId" }, method = RequestMethod.POST)
	public @ResponseBody ConfigureFranchisee getFrMenuConfigureByMenuFrId1(@RequestParam("menuId") int menuId) {
		ConfigureFranchisee menuConf = configureFrRepository.findByMenuIdAndDelStatus(menuId, 0);
		return menuConf;
	}

	// 25-01-2021
	// Get MenuBy Section Ids
	@Autowired
	MainMenuConfigurationRepository mainMenuConfRepo;

	@RequestMapping(value = { "/getMenuListByMenuIds" }, method = RequestMethod.POST)
	public @ResponseBody AllMenuJsonResponse getFrMenuConfigureByMenuFrId(
			@RequestParam("menuIds") List<Integer> menuIds) {

		AllMenuJsonResponse menuJsonResponse = new AllMenuJsonResponse();
		ErrorMessage errorMessage = new ErrorMessage();
		try {
			List<AllMenus> menuList = mainMenuConfRepo.findByMenuIdIn(menuIds);
			menuJsonResponse.setMenuConfigurationPage(menuList);
			errorMessage.setError(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		errorMessage.setMessage("Menus shown successfully");
		menuJsonResponse.setErrorMessage(errorMessage);

		return menuJsonResponse;
	}

	@Autowired
	AllFrIdNameRepository frNameIdRepo;

	@RequestMapping(value = "/getAllFrIdNameByMenuIdConfigured", method = RequestMethod.POST)
	public @ResponseBody AllFrIdNameList getAllFrIdName(@RequestParam("menuId") int menuId) {

		AllFrIdNameList allFrIdNameList = new AllFrIdNameList();
		try {
			List<AllFrIdName> allFrIdNames = frNameIdRepo.getAllFrIdNameByMenuId(menuId);
			Info info = new Info();

			if (allFrIdNames != null) {
				allFrIdNameList.setFrIdNamesList(allFrIdNames);
				info.setError(false);
				info.setMessage("Successfully displayed all fr Name and Id");
				allFrIdNameList.setInfo(info);
			} else {
				info.setError(true);
				info.setMessage("error in getting fr Id and Names");
				allFrIdNameList.setInfo(info);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return allFrIdNameList;

	}
	
	
	//Sachin 25-01-2021 For ManualOrder page Menus
	@RequestMapping(value = { "/getMenuListByFrAndSectionId" }, method = RequestMethod.POST)
	public @ResponseBody AllMenuJsonResponse getMenuListByFrAndSectionId(
			@RequestParam("frId") int frId, @RequestParam("sectionId") int sectionId) {

		AllMenuJsonResponse menuJsonResponse = new AllMenuJsonResponse();
		ErrorMessage errorMessage = new ErrorMessage();
		try {
			List<AllMenus> menuList = mainMenuConfRepo.findByFrIdAndSectionId(sectionId,frId);
			menuJsonResponse.setMenuConfigurationPage(menuList);
			errorMessage.setError(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		errorMessage.setMessage("Menus shown successfully");
		menuJsonResponse.setErrorMessage(errorMessage);

		return menuJsonResponse;
	}

	@Autowired
	 ItemForMOrderRepository itemRepositoryForMOrderRepository;
	
	@RequestMapping(value = "/getItemListForMOrder", method = RequestMethod.POST)
	public @ResponseBody List<ItemForMOrder> getItemListForMOrder(@RequestParam("itemGrp1")int itemGrp1,@RequestParam("frId")int frId,@RequestParam("menuId")int menuId,@RequestParam("ordertype")int ordertype,@RequestParam("prodDate")String prodDate) {

		List<ItemForMOrder> itemList;
		try {
			
			itemList = itemRepositoryForMOrderRepository.getItemListForMOrder(menuId);
			
			/*
			 * if(ordertype==0) {
			 * System.err.println("itemGrp1"+itemGrp1+"frId"+menuId+"ordertype"+ordertype+
			 * "prodDate"+prodDate); itemList =
			 * itemRepositoryForMOrderRepository.getItemListForMOrder(itemGrp1,frId,menuId,
			 * prodDate); } else if(ordertype==2) { itemList =
			 * itemRepositoryForMOrderRepository.getItemListForMOrderMul(itemGrp1);
			 * 
			 * }else { itemList =
			 * itemRepositoryForMOrderRepository.getItemListForMOrderPrev(itemGrp1,frId);
			 * 
			 * }
			 */
		}
		catch (Exception e) {
			itemList=new ArrayList<>();
			e.printStackTrace();

		}
		return itemList;

	}
	
	@Autowired
	GenerateBillRepository generateBillRepository;
	@Autowired
	OrderLogRespository logRespository;

	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value = { "/placeManualOrder" }, method = RequestMethod.POST)
	public @ResponseBody List<GenerateBill> placeManualOrder(@RequestBody List<Orders> orderJson)
			throws ParseException, JsonParseException, JsonMappingException, IOException {
		List<GenerateBill> billList = null;
		List<Orders> jsonResult;
		OrderLog log = new OrderLog();
		log.setFrId(orderJson.get(0).getFrId());
		log.setJson(orderJson.toString());
		logRespository.save(log);

		jsonResult = orderService.placeOrder(orderJson);
		ArrayList<Integer> list = new ArrayList<Integer>();

		if (!jsonResult.isEmpty()) {
			for (int i = 0; i < jsonResult.size(); i++) {
				list.add(jsonResult.get(i).getOrderId());
			}

			billList = generateBillRepository.getBillOfOrder(list);
		}

		return billList;
	}
	
	@RequestMapping(value = { "/placeManualOrderNew1" }, method = RequestMethod.POST)
	public @ResponseBody List<GenerateBill> placeManualOrderNew(@RequestBody List<Orders> orderJson)
			throws ParseException, JsonParseException, JsonMappingException, IOException {
		List<GenerateBill> billList = null;
		List<Orders> jsonResult;
		OrderLog log = new OrderLog();
		log.setFrId(orderJson.get(0).getFrId());
		log.setJson(orderJson.toString());
		logRespository.save(log);

		jsonResult = orderService.placeManualOrder(orderJson);
		ArrayList<Integer> list = new ArrayList<Integer>();

		if (!jsonResult.isEmpty()) {
			for (int i = 0; i < jsonResult.size(); i++) {
				list.add(jsonResult.get(i).getOrderId());
			}

			billList = generateBillRepository.getBillOfOrder(list);
		}

		return billList;
	}

	//Sachin 29-01-2021 For Showing SP Cakes in admin Flavor configuration
	
	@Autowired
	private SpecialCakeService specialcakeService;

	@Autowired
	SpecialCakeRepository specialcakeRepository;
	@RequestMapping(value = { "/showSpecialCakeListOrderBySpCode" }, method = RequestMethod.GET)
	@ResponseBody
	public SpecialCakeList showSpecialCakeListOrderBySpCode() {
		List<SpecialCake> jsonSpecialCakeList = specialcakeRepository.findByDelStatusOrderBySpCodeAsc(0);
		SpecialCakeList specialCakeList = new SpecialCakeList();
		specialCakeList.setSpecialCake(jsonSpecialCakeList);
		Info info = new Info();
		info.setError(false);
		info.setMessage("SpecialCake list displayed Successfully");
		specialCakeList.setInfo(info);
		return specialCakeList;

	}

	//Sachin 29-01-2021 For Mapping flavor and sps new Added table and all its APIS
	@Autowired
	FlavourConfRepository flavourConfRepository;
	@RequestMapping(value = { "/saveFlavourConf" }, method = RequestMethod.POST)
	public @ResponseBody List<FlavourConf> saveFlavourConf(@RequestBody List<FlavourConf> flavourConfList) {

		List<FlavourConf> flList = new ArrayList<FlavourConf>();
		try {
            for(FlavourConf flavourConf:flavourConfList)
            {
            	FlavourConf isPresent=flavourConfRepository.findByDelStatusAndSpfIdAndSpId(0,flavourConf.getSpfId(),flavourConf.getSpId());
            	if(isPresent!=null)
            	{
            	    flavourConf.setSpFlavConfId(isPresent.getSpFlavConfId());
            	    FlavourConf flr= flavourConfRepository.save(flavourConf);
            	    flList.add(flr);
            	}else
            	{
            		 FlavourConf flr= flavourConfRepository.save(flavourConf);
              	     flList.add(flr);
            	}
            }
			
		} catch (Exception e) {

			e.printStackTrace();
		
		}
		return flList;
	}
	@RequestMapping(value = "/getAllFlConf", method = RequestMethod.GET)
	public @ResponseBody List<FlavourConf> getAllFlConf() {

		List<FlavourConf> flList;
		try {
			flList = flavourConfRepository.findByDelStatus(0);
		}
		catch (Exception e) {
			flList=new ArrayList<>();
			e.printStackTrace();

		}
		return flList;

	}
	@RequestMapping(value = "/getFlConfByIds", method = RequestMethod.POST)
	public @ResponseBody FlavourConf getFlConfByIds(@RequestParam("spfId") int  spfId,@RequestParam("spId") int  spId) {

		FlavourConf flavour=new FlavourConf();
		try {
			flavour = flavourConfRepository.findBySpIdAndSpfIdAndDelStatus(spId,spfId,0);
		}
		catch (Exception e) {
			flavour=new FlavourConf();
			e.printStackTrace();

		}
		return flavour;

	}
	@RequestMapping(value ="/updateFlavourConf", method = RequestMethod.POST)
	public Info updateFlavourConf(@RequestParam("spFlavConfId") int spFlavConfId, @RequestParam("rate") float rate,@RequestParam("mrp1") float mrp1,
			@RequestParam("mrp2") float mrp2,@RequestParam("mrp3") float mrp3)
	{
		Info info=new Info();
		try {
			int isUpdated=flavourConfRepository.updateFlavourConf(spFlavConfId,rate,mrp1,mrp2,mrp3);
			if(isUpdated>0)
			{
				info.setError(false);
				info.setMessage("FlavourConf Updated Successfully.");
			}else
			{
				info.setError(true);
				info.setMessage("FlavourConf Updation Failed.");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return info;
	}
	@RequestMapping(value="/deleteFlavourConf", method=RequestMethod.POST)
	public @ResponseBody Info deleteFlavourConf(@RequestParam int spFlavConfId) {
		
		Info info =new Info();
		int isDelete = flavourConfRepository.deleteBySpFlavConfId(spFlavConfId);
		
		if(isDelete!=0) {
			info.setError(false);
			info.setMessage("Success");
		}else {
			info.setError(true);
			info.setMessage("Fail");
		}
		return info;
		
	}
	
	@Autowired
	FlavourRepository flavourRepository;
	
	@RequestMapping(value = "/getFlavoursBySpfIdNotIn", method = RequestMethod.POST)
	public @ResponseBody List<Flavour> getFlavoursBySpfIdNotIn(@RequestParam List<Integer> spfId) {
int type=0;
		List<Flavour> flavourList=null;
		if(type!=0) {
		 flavourList = flavourRepository.findBySpfIdNotInAndSpType(spfId,type);
		}
		else
		{
		 flavourList = flavourRepository.findBySpfIdNotIn(spfId);
		}
		
		return flavourList;
	}
	
	@RequestMapping(value = "/getFlavoursBySpfIdIn", method = RequestMethod.POST)
	public @ResponseBody List<Flavour> getFlavoursBySpfIdIn(@RequestParam List<Integer> spfId) {

		List<Flavour> flavourList = flavourRepository.findBySpfIdIn(spfId);
		
		return flavourList;
	}
	//29-01-2021 just copied as not available in phase2Api
	@RequestMapping(value = { "/showFlavourListBySpId" }, method = RequestMethod.POST)
	@ResponseBody
	public FlavourList showFlavourListBySpId(@RequestParam("spId") int spId) {

		List<Flavour> jsonFlavourtList = flavourRepository.findBySpId1(spId);
		FlavourList flavourList = new FlavourList();
		flavourList.setFlavour(jsonFlavourtList);
		Info info = new Info();
		info.setError(false);
		info.setMessage("Flavour list displayed Successfully");
		flavourList.setInfo(info);

		return flavourList;
	}
	
	//new Method to display at frontEnd ordersp cake flavor list 29-01-2021
	@RequestMapping(value = { "/getFlavorsAndSpConfBySpId" }, method = RequestMethod.POST)
	@ResponseBody
	public FlavourList showFlavorsAndSpConfBySpId(@RequestParam("spId") int spId) {

		List<Flavour> jsonFlavourtList = flavourRepository.findBySpId(spId);
		FlavourList flavourList = new FlavourList();
		flavourList.setFlavour(jsonFlavourtList);
		Info info = new Info();
		info.setError(false);
		info.setMessage("Flavour list displayed Successfully");
		flavourList.setInfo(info);

		return flavourList;
	}
	
	
	//Sachin 01-02-2021
		@Autowired
		HsnwiseBillExcelSummaryRepository hsnwiseBillExcelSummaryRepository;
		
		//SACHIN 11 MAY
			@RequestMapping(value = { "/getHsnwiseBillDataForExcelV2" }, method = RequestMethod.POST)
			public @ResponseBody List<HsnwiseBillExcelSummary> getHsnwiseBillDataForExcelV2(@RequestParam("frIdList")List<String> frIdList,@RequestParam("fromDate")String fromDate,@RequestParam("toDate")String toDate) {
				List<HsnwiseBillExcelSummary> hsnwiseBills=new ArrayList<HsnwiseBillExcelSummary>();
				if(frIdList.contains("-1")) {
				System.err.println("All Fr ");
				hsnwiseBills=hsnwiseBillExcelSummaryRepository.getHsnwiseBillDataForExcelAllFr(fromDate, toDate);
				}
				else
				{
					System.err.println("Multi Fr ");
					hsnwiseBills=hsnwiseBillExcelSummaryRepository.getHsnwiseBillDataForExcelMultiFr(fromDate, toDate, frIdList);

				}
				return hsnwiseBills;
			}
			
			@RequestMapping(value = { "/getHsnwiseBillDataForExcel" }, method = RequestMethod.POST)
			public @ResponseBody List<HsnwiseBillExcelSummary> getHsnwiseBillDataForExcel(@RequestParam("billNoList") List<String> billNos,@RequestParam("all")int all,@RequestParam("fromDate")String fromDate,@RequestParam("toDate")String toDate) {
				List<HsnwiseBillExcelSummary> hsnwiseBills=new ArrayList<HsnwiseBillExcelSummary>();
				if(all==0) {
				hsnwiseBills=hsnwiseBillExcelSummaryRepository.getHsnwiseBillDataForExcel(billNos);
				}
				else
				{
					hsnwiseBills=hsnwiseBillExcelSummaryRepository.getHsnwiseBillDataForExcelAll(fromDate,toDate);

				}
				return hsnwiseBills;
			}
			
}
