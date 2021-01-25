package com.ats.webapi.controller.rejection;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
//Akhilesh 2021-01-23

import com.ats.webapi.model.Info;
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
	
	
	//Get Rejection Header By Id
	@RequestMapping(value="/getRejectionHeaderById",method=RequestMethod.POST)
	public @ResponseBody RejectionHeader getRejectionHeaderById(@RequestParam int rejectId){
		RejectionHeader header=new RejectionHeader();
		List<RejectionDetail> detailList=new ArrayList<>();
		try {
			header=rejHeadRepo.getRejcHeaderByid(rejectId);
			detailList=rejDetailRepo.getRejDetailsByrejId(rejectId);
			header.setDetailList(detailList);
			} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Exception Occuered in /getRejectionHeaderById");
			e.printStackTrace();
		}
		return header;
	}
	
	
	//Get List Of Rejection Header
	@RequestMapping(value="/getAllRejctHeader",method=RequestMethod.GET)
	public @ResponseBody List<RejectionHeader> getAllRejctHeader(){
		List<RejectionHeader> headerList=new ArrayList<>();
		
		try {
			headerList=rejHeadRepo.getAllRejectionHeader();
			if(headerList.size()>0) {
				for(RejectionHeader head : headerList) {
					List<RejectionDetail> detailList=new ArrayList<>();
					detailList=rejDetailRepo.getRejDetailsByrejId(head.getRejectId());
					head.setDetailList(detailList);
				}
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Exception Occuered In /getAllRejctHeader");
			e.printStackTrace();
		}
		
		return headerList;
		
	}
	
	
	@RequestMapping(value="/deleteRejection",method=RequestMethod.POST)
	public @ResponseBody Info deleteRejection(@RequestParam int rejectId) {
		Info info=new Info();
		int flag=0;
		
		try {
			flag=rejHeadRepo.deleteRejHeader(rejectId);
			rejDetailRepo.deleteRejDetail(rejectId);
			if(flag>0) {
				info.setError(false);
				info.setMessage("Rejection Deleted!!!");
				
			}else {
				info.setError(true);
				info.setMessage("Rejection Unable to Delete!!!");
			}
		} catch (Exception e) {
			// TODO: handle exception
			info.setError(true);
			info.setMessage("Rejection Unable to Delete Exception Occuered");
			System.err.println("Exception Occuered in /deleteRejection");
			e.printStackTrace();
		}
		
		return info;
	}
	
	
	
	
	
	
	
	
	
}
