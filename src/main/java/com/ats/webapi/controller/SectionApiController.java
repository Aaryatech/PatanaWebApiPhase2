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
import com.ats.webapi.model.Section;
import com.ats.webapi.repository.SectionRepository;

@RestController
public class SectionApiController {
	
	@Autowired
	SectionRepository sectionRepo;
	
	
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
	
	
	
	
	
	
	

}
