package com.ats.webapi.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.webapi.model.AllMenus;
import com.ats.webapi.model.ConfigureFrBean;
import com.ats.webapi.model.ConfigureFrBeanList;
import com.ats.webapi.model.ConfigureFranchisee;
import com.ats.webapi.model.FrMenuConfigure;
import com.ats.webapi.model.GetFrMenuConfigure;
import com.ats.webapi.model.Info;
import com.ats.webapi.model.Item;
import com.ats.webapi.model.PostFrItemStockDetail;
import com.ats.webapi.model.PostFrItemStockHeader;
import com.ats.webapi.model.frsetting.NewSetting;
import com.ats.webapi.repository.ConfigureFrListRepository;
import com.ats.webapi.repository.FrMenuConfigureRepository;
import com.ats.webapi.repository.GetFrMenuConfigureRepository;
import com.ats.webapi.repository.MainMenuConfigurationRepository;
import com.ats.webapi.repository.NewSettingRepository;
import com.ats.webapi.repository.PostFrOpStockDetailRepository;
import com.ats.webapi.repository.PostFrOpStockHeaderRepository;
import com.ats.webapi.service.ConfigureFranchiseeService;
import com.ats.webapi.service.ItemService;

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

	/*21-01-2021
	 * // 3-01-19
	 */		@RequestMapping(value = { "/findConfiguredMenuFrList" }, method = RequestMethod.GET)
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
		public @ResponseBody Info saveFrMenuConfigure(@RequestParam List<String> frIdList,@RequestParam List<String> menuIdList)
		{
			Info info=new Info();
			int currentYear=Calendar.getInstance().get(Calendar.YEAR);    
			System.err.println("1 currentYear="+currentYear);
			int currentMonth=Calendar.getInstance().get(Calendar.MONTH ) + 1;  
			System.err.println("2 currentMonth="+currentMonth);
			try {
				System.err.println("3 try main");
				for(int i=0;i<frIdList.size();i++)
				{
					System.err.println("###  ###");
					for(int j=0;j<menuIdList.size();j++)
					{
						System.err.println("***  ***");
						FrMenuConfigure prevFrMenuConfigure=frMenuConfigureRepository.findByFrIdAndMenuIdAndIsDel(Integer.parseInt(frIdList.get(i)),Integer.parseInt(menuIdList.get(j)),0);
						System.err.println("4 prevFrMenuConfigure"+prevFrMenuConfigure);
						if(prevFrMenuConfigure==null) {
							
						FrMenuConfigure frMenu=new FrMenuConfigure();
						frMenu.setSettingId(0);
						frMenu.setFrId(Integer.parseInt(frIdList.get(i)));
						frMenu.setMenuId(Integer.parseInt(menuIdList.get(j)));
						frMenu.setIsDel(0);
						System.err.println("5 frMenu"+frMenu.toString());
						FrMenuConfigure frMenuConfigure=frMenuConfigureRepository.save(frMenu);
						System.err.println("6 frMenuConfigure afer insert"+frMenuConfigure.toString());
						}
						
					}
				}
				info.setError(false);
				info.setMessage("Menu Configured");
				System.err.println("7 info"+info.toString());
				for(int i=0;i<frIdList.size();i++)
				{
					System.err.println("### ###");
					for(int j=0;j<menuIdList.size();j++)	
					{
						System.err.println("1111111  j"+j);
						ConfigureFranchisee configureFranchisee = configureService.findFranchiseeById(Integer.parseInt(menuIdList.get(j)));
						System.err.println("8 configureFranchisee"+configureFranchisee.toString());
						NewSetting	newSetting = newSettingRepository.findBySettingKeyAndDelStatus("cat_id_open_stock",0);
						List<Integer> catIdForStock= Stream.of(newSetting.getSettingValue1().split(","))
				                .map(Integer::parseInt)
				                .collect(Collectors.toList());
						List<PostFrItemStockHeader> prevStockHeader=postFrOpStockHeaderRepository.findByFrIdAndIsMonthClosedAndCatId(Integer.parseInt(frIdList.get(i)),0,configureFranchisee.getCatId());
						System.err.println("9 prevStockHeader"+prevStockHeader);
						if (catIdForStock.contains(configureFranchisee.getCatId())) {

						if(prevStockHeader.size()==0)
						{
							PostFrItemStockHeader postFrItemStockHeader = new PostFrItemStockHeader();
							postFrItemStockHeader.setOpeningStockHeaderId(0);
							postFrItemStockHeader.setFrId(Integer.parseInt(frIdList.get(i)));
							postFrItemStockHeader.setCatId(configureFranchisee.getCatId());
							postFrItemStockHeader.setYear(currentYear);
							postFrItemStockHeader.setMonth(currentMonth);
							postFrItemStockHeader.setIsMonthClosed(0);
							System.err.println("10 postFrItemStockHeader"+postFrItemStockHeader.toString());
					PostFrItemStockHeader postFrItemStockHeaderRes = postFrOpStockHeaderRepository.save(postFrItemStockHeader);
					System.err.println("11 after insert postFrItemStockHeader --"+postFrItemStockHeaderRes.toString());
					
							List<PostFrItemStockDetail> postFrItemStockDetailList = new ArrayList<PostFrItemStockDetail>();
							List<Integer> ids = Stream.of(configureFranchisee.getItemShow().split(","))
					                .map(Integer::parseInt)
					                .collect(Collectors.toList());
							System.err.println("12 ids --"+ids.toString());

							List<Item> itemsList = itemService.findAllItemsByItemId(ids);
							System.err.println("13 itemsList --"+itemsList.toString());
							for (int k = 0; k < itemsList.size(); k++) {

								    PostFrItemStockDetail	postFrItemStockDetail = new PostFrItemStockDetail();
									postFrItemStockDetail.setOpeningStockHeaderId(postFrItemStockHeaderRes.getOpeningStockHeaderId());
									postFrItemStockDetail.setOpeningStockDetailId(0);
									postFrItemStockDetail.setRegOpeningStock(0);
								    postFrItemStockDetail.setItemId(itemsList.get(k).getId());
								    postFrItemStockDetail.setRemark("");
								    postFrItemStockDetailList.add(postFrItemStockDetail);
								    System.err.println("14 postFrItemStockDetail --"+postFrItemStockDetail.toString());
							}
							   System.err.println("15 postFrItemStockDetailList --"+postFrItemStockDetailList.toString());
						    postFrOpStockDetailRepository.save(postFrItemStockDetailList);

						}else
						{
							List<PostFrItemStockDetail> postFrItemStockDetailList = new ArrayList<PostFrItemStockDetail>();
							List<Integer> ids = Stream.of(configureFranchisee.getItemShow().split(","))
					                .map(Integer::parseInt)
					                .collect(Collectors.toList());
							  System.err.println("16 ids --"+ids.toString());
							List<Item> itemsList = itemService.findAllItemsByItemId(ids);
							  System.err.println("17 itemsList --"+itemsList.toString());
							for (int k = 0; k < itemsList.size(); k++) {
								PostFrItemStockDetail	prevFrItemStockDetail=postFrOpStockDetailRepository.findByItemIdAndOpeningStockHeaderId(itemsList.get(k).getId(),prevStockHeader.get(0).getOpeningStockHeaderId());
								  System.err.println("18 prevFrItemStockDetail --"+prevFrItemStockDetail);
								if(prevFrItemStockDetail==null) {
								    PostFrItemStockDetail	postFrItemStockDetail = new PostFrItemStockDetail();
									postFrItemStockDetail.setOpeningStockHeaderId(prevStockHeader.get(0).getOpeningStockHeaderId());//first stock header (month closed 0 status))
									postFrItemStockDetail.setOpeningStockDetailId(0);
									postFrItemStockDetail.setRegOpeningStock(0);
								    postFrItemStockDetail.setItemId(itemsList.get(k).getId());
								    postFrItemStockDetail.setRemark("");
								    postFrItemStockDetailList.add(postFrItemStockDetail);
								    System.err.println("19 postFrItemStockDetail --"+postFrItemStockDetail.toString());
								 }
							}
						    postFrOpStockDetailRepository.save(postFrItemStockDetailList);
						    System.err.println("20 postFrItemStockDetailList --"+postFrItemStockDetailList.toString());
						}
						}
					}
				}
			}catch (Exception e) {
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
			}
			catch (Exception e) {
				confList=new ArrayList<>();
				e.printStackTrace();

			}
			return confList;

		}
		
		@RequestMapping(value = { "/deleteFrConfMenu" }, method = RequestMethod.POST)
		public @ResponseBody Info deleteFrConfMenu(@RequestParam int settingId) {

			int isDeleted =frMenuConfigureRepository.deleteFrConfMenu(settingId);
			Info info=new Info();
			if(isDeleted==1)
			{
				info.setError(false);
				info.setMessage("Menu Conf Deleted");
			}
			else
			{
				info.setError(true);
				info.setMessage("Menu Conf Deletion Failed");
			}
			return info;
		}	
}
