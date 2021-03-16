package com.ats.webapi.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.ats.webapi.model.ErrorMessage;
import com.ats.webapi.model.PosConfigItem;
import com.ats.webapi.repository.PosConfigItemRepository;

@RestController
public class PosConfigItemController {
	@Autowired
	PosConfigItemRepository posConfigItemRepository;
	
	
	@RequestMapping(value = { "/insertPosConfigItem" }, method = RequestMethod.POST)
	public @ResponseBody PosConfigItem insertPosConfigItem(@RequestBody PosConfigItem pc  ) {

		PosConfigItem posItem = new PosConfigItem();
		
		//System.out.println("SpId  "+SpId);
		try {

			
			 posItem = posConfigItemRepository.save(pc);
			
			System.out.println("spCakeDetailedList "+posItem);

			
		} catch (Exception e) 
		{
			e.printStackTrace();

			//System.out.println("Exce in getlist" + e.getMessage());
		}
		return posItem;

	}

	
	@RequestMapping(value = { "/getPosConfigItem" }, method = RequestMethod.GET)
	public @ResponseBody List<PosConfigItem> getPosConfigItem( ) {

		List<PosConfigItem> posItem = new ArrayList<PosConfigItem>();
		
		try {

			posItem=posConfigItemRepository.findByDelStatus(0);
		  
		
		} catch (Exception e) 
		{
			e.printStackTrace();

			//System.out.println("Exce in getlist" + e.getMessage());
		}
		return posItem;

	}
	
	
	// Delete Item
	@RequestMapping(value = "/deleteConfigItem", method = RequestMethod.POST)
	public @ResponseBody ErrorMessage deleteConfigItem(@RequestParam List<Integer> id) {

		ErrorMessage errorMessage = new ErrorMessage();

		int isUpdated = posConfigItemRepository.deleteConfigItem(id);
		if (isUpdated == 1) {

			errorMessage.setError(false);
			errorMessage.setMessage("Items Deleted Successfully");
		} else {
			errorMessage.setError(false);
			errorMessage.setMessage("Items Deletion Failed");

		}
		return errorMessage;
	}
}
