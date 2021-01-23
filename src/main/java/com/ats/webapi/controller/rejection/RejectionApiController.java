package com.ats.webapi.controller.rejection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
//Akhilesh 2021-01-23

import com.ats.webapi.model.rejection.RejectionDetail;
import com.ats.webapi.model.rejection.RejectionHeader;
import com.ats.webapi.repository.rejection.RejectionDetailRepository;
import com.ats.webapi.repository.rejection.RejectionHeaderRepository;
@RestController
public class RejectionApiController {

	@Autowired
	RejectionDetailRepository rejDetailRepo;
	
	@Autowired
	RejectionHeaderRepository rejHeadRepo;
	
	
	
	//To Save Rejection Header
	@RequestMapping(value="/addNewRejectionHeader",method=RequestMethod.POST)
	public RejectionHeader addNewRejectionHeader(@RequestBody RejectionHeader rejHead) {
		RejectionHeader headResp =new RejectionHeader();
		try {
		
			headResp=rejHeadRepo.save(rejHead);
			System.err.println("Saved Header-->"+headResp);
			if(headResp.getRejectId()>0) {
				for(RejectionDetail detail :rejHead.getDetailList()) {
					
					detail.setRejectId(headResp.getRejectId());
					rejDetailRepo.save(detail);
				}
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Exception Occuered In /addNewRejectionHeader");
			e.printStackTrace();
		}
		
		return headResp;
	}
	
	
	
	
}
