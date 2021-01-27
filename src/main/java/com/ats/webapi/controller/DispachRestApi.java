package com.ats.webapi.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.webapi.model.AllMenus;
import com.ats.webapi.model.section.Section;
import com.ats.webapi.repository.MainMenuConfigurationRepository;
import com.ats.webapi.repository.SectionRepository;

@RestController
public class DispachRestApi {

	@Autowired
	SectionRepository sectionMasterRepository;
	
	@Autowired
	MainMenuConfigurationRepository mainMenuConfigurationRepository;

	
	@RequestMapping(value = { "/getSectionListOnly" }, method = RequestMethod.GET)
	public @ResponseBody List<Section> getSectionListOnly() {

		List<Section> sectionList = new ArrayList<Section>();
		try {

			sectionList = sectionMasterRepository.findByDelStatus(0);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return sectionList;
	}
	
	@RequestMapping(value = { "/getSectionById" }, method = RequestMethod.POST)
	public @ResponseBody Section getSectionById(@RequestParam("sectionId") int sectionId) {

		Section getSectionById = new Section();
		try {

			getSectionById = sectionMasterRepository.findBySectionIdAndDelStatus(sectionId, 0);
			try {

				String[] menuIds = getSectionById.getMenuIds().split(",");
				List<String> ids = new ArrayList<String>(Arrays.asList(menuIds)); 
				
				System.err.println("ids---------------"+ids);
				
				List<AllMenus> menus = mainMenuConfigurationRepository.getMenuIdIn(ids);
				System.err.println("Menues---------------"+menus);
				getSectionById.setMenuList(menus);

			} catch (Exception e) {

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return getSectionById;
	}
}
