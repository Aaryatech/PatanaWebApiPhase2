package com.ats.webapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.webapi.model.Info;
import com.ats.webapi.model.section.Section;
import com.ats.webapi.model.section.SectionMenu;
import com.ats.webapi.model.section.SectionMenuIdname;
import com.ats.webapi.model.section.SectionWithMenuList;
import com.ats.webapi.repository.SectionMenuidNameRepo;
import com.ats.webapi.repository.SectionRepository;

@RestController
public class SectionApiController {
	
	@Autowired
	SectionRepository sectionRepo;
	
	@Autowired
	SectionMenuidNameRepo sMenuRepo;
	
	@RequestMapping(value="/addSection",method=RequestMethod.POST)
	public @ResponseBody Section addSection(@RequestBody Section section) {
		Section sec=new Section();
		try {
			sec=sectionRepo.save(section);
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Exception Occuered In /addSection");
			e.printStackTrace();
		}
		
		return sec;
	}
	
	
	@RequestMapping(value="/getAllSection",method=RequestMethod.GET)
	public @ResponseBody List<Section> getAllSection(){
		List<Section> sectionResp=new ArrayList<>();
		try {
			sectionResp=sectionRepo.getAllSection();
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Exception Occuered In /getAllSection");
			e.printStackTrace();
		}
		
		return sectionResp;
	}
	
	
	
	
	@RequestMapping(value="/getSingleSection",method=RequestMethod.POST)
	public @ResponseBody Section getSingleSection(@RequestParam int sectionId) {
		Section secResp=new Section();
		try {
			secResp =sectionRepo.getSingleSectionById(sectionId);
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Exception Occuered In /getSingleSection");
			e.printStackTrace();
		}
		return secResp;
	}
	
	@RequestMapping(value="/deleteSection",method=RequestMethod.POST)
	public @ResponseBody Info deleteSection(@RequestParam int sectionId) {
		Info info=new Info();
		int flag=0;
		try {
			flag=sectionRepo.deleteSection(sectionId);
			if(flag>0) {
				info.setError(false);
				info.setMessage("Section Deleted Successfully!!!");
			}else {
				info.setError(true);
				info.setMessage("Unable To Delete Section!!!");
			}
		} catch (Exception e) {
			// TODO: handle exception
			info.setError(true);
			info.setMessage("Unable To Delete Section Exception Occuered !!!");
			System.err.println("Exception Occuered In /deleteSection");
			e.printStackTrace();
		}
		
		
		return info;
	}
	
	
	@RequestMapping(value="/getSectionWithMenu",method=RequestMethod.GET)
	public @ResponseBody List<SectionWithMenuList> getSectionWithMenu(){
		List<SectionWithMenuList> sectionMenulist=new ArrayList<>();
		List<Section> sectionList=new ArrayList<>();
		List<SectionMenuIdname> sectionMenu=new ArrayList<>();
		//SectionWithMenuList sMenu=new SectionWithMenuList();
		//SectionMenu menu=new SectionMenu();
		List<SectionMenu> mlist;
		try {
			sectionList=sectionRepo.getAllSection();
			sectionMenu=sMenuRepo.getAllSectionWithMenu();
			System.err.println(sectionList);
			
			System.err.println("-->"+sectionMenu);
			for(Section sec : sectionList ) {
				SectionWithMenuList sMenu=new SectionWithMenuList();
				mlist=new ArrayList<>();
				sMenu.setSectionId(sec.getSectionId());
				sMenu.setSectionName(sec.getSectionName());
				for(SectionMenuIdname secMenu : sectionMenu ) {
					if(sec.getSectionId()==secMenu.getSectionId()) {
						SectionMenu menu=new SectionMenu();
						menu.setMenuId(secMenu.getMenuId());
						menu.setMenuTitle(secMenu.getMenuTitle());
						mlist.add(menu);
					
					}
					
				}
			sMenu.setMenu(mlist);
			sectionMenulist.add(sMenu);
			}
			 
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("");
			e.printStackTrace();
		}
		
		return sectionMenulist;
		
	}
	
	
	
	
	
	

}
