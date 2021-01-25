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
import com.ats.webapi.model.PostBillDataCommon;
import com.ats.webapi.model.SellBillDataCommon;
import com.ats.webapi.model.SellBillDetail;
import com.ats.webapi.model.SellBillDetailList;
import com.ats.webapi.model.SellBillHeader;
import com.ats.webapi.model.bill.ExpressBillService;
import com.ats.webapi.model.bill.GetItemHsnCode;
import com.ats.webapi.model.bill.PendingBillCreditNote;
import com.ats.webapi.model.bill.PendingBillDetail;
import com.ats.webapi.model.bill.PendingBillHeader;
import com.ats.webapi.model.bill.PendingBills;
import com.ats.webapi.model.bill.SlabwiseBill;
import com.ats.webapi.model.bill.SlabwiseBillList;
import com.ats.webapi.repository.PostBillHeaderRepository;
import com.ats.webapi.repository.PostCreditNoteHeaderRepository;
import com.ats.webapi.repository.SlabwiseDetailsRepository;
import com.ats.webapi.repository.UpdateSellBillTimeStampRepo;
import com.ats.webapi.repository.getcreditnote.PendingBillCreditNoteRepo;
import com.ats.webapi.repository.getcreditnote.PendingBillDetailRepo;
import com.ats.webapi.repository.getcreditnote.PendingBillHeaderRepo;
import com.ats.webapi.repository.getcreditnote.PendingBillsRepo;

@RestController
public class BillingController {

	@Autowired
	ExpressBillService expressBillService;
	
	@Autowired
	SlabwiseDetailsRepository slabwiseDetailsRepository; 
	
	@Autowired
	UpdateSellBillTimeStampRepo  updateSellBillTimeStampRepo;
	
	
	@RequestMapping(value = { "/updateSellBillTimeStamp" }, method = RequestMethod.POST)
	public @ResponseBody Info updateSellBillTimeStamp(@RequestParam("sellBillNo") int sellBillNo,
			@RequestParam("timeStamp") String timeStamp) {
		
		Info info=new Info();

		int response=updateSellBillTimeStampRepo.updateTimeForSellBill(sellBillNo, timeStamp);
		
		if(response>0) {
			info.setError(false);
			info.setMessage("successfully Updated Time Stamp /updateSellBillTimeStamp");
		}
		//System.err.println("BillingController -/updateSellBillTimeStamp ->response " +response);
		    
		return info;
	  }
	
	
	@RequestMapping(value = { "/deleteExBillHeader" }, method = RequestMethod.POST)
	public @ResponseBody int deleteExPBillHeader(@RequestParam("sellBillNo") int sellBillNo) {

		int response=expressBillService.deleteSellBillHeader(sellBillNo);
		    
		return response;
	  }
	
	
	 
	@RequestMapping(value = { "/showNotDayClosedRecord" }, method = RequestMethod.POST)
	public @ResponseBody SellBillDataCommon showNotDayClosedRecord(@RequestParam("frId") int frId) {

		SellBillDataCommon dayNotCloseRecord=expressBillService.showNotDayClosedRecord(frId);
		    
			return dayNotCloseRecord;
	  }
	
	@RequestMapping(value = { "/getSellBillDetails" }, method = RequestMethod.POST)
	public @ResponseBody SellBillDetailList getSellBillDetails(@RequestParam("billNo") int billNo) {

		SellBillDetailList sellBillDetailList=expressBillService.getSellBillDetails(billNo);
		    
			return sellBillDetailList;
	  }
	@RequestMapping(value = { "/saveSellBillHeader" }, method = RequestMethod.POST)
	public @ResponseBody SellBillHeader saveSellBillHeader(@RequestBody SellBillHeader sellBillHeader) {

		SellBillHeader sellBillHeaderRes=expressBillService.saveSellBillHeader(sellBillHeader);
		    
			return sellBillHeaderRes;
	  }
	@RequestMapping(value = { "/saveSellBillDetail" }, method = RequestMethod.POST)
	public @ResponseBody SellBillDetail saveSellBillDetail(@RequestBody SellBillDetail sellBillDetail) {

		SellBillDetail SellBillDetailRes=expressBillService.saveSellBillDetail(sellBillDetail);
		    
			return SellBillDetailRes;
	  }
	
	
	@RequestMapping(value = { "/getSellBillHeaderForDayClose" }, method = RequestMethod.POST)
	public @ResponseBody SellBillHeader getSellBillHeader(@RequestParam("sellBillNo") int sellBillNo) {
		
		SellBillHeader sellBillHeader=null;
		
		try {

		 sellBillHeader=expressBillService.getSellBillHeaderBysellBillNo(sellBillNo);
		 
		}catch (Exception e) {
			
			//System.out.println("Exce in getting sell Bill Header "+e.getMessage());
			e.printStackTrace();
			
		}
			return sellBillHeader;
	  }
	
	
	
	@RequestMapping(value = { "/deleteSellBillDetail" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteSellBillDetail(
			@RequestParam("sellBillDetailNo") int sellBillDetailNo) {

		int result= expressBillService.deleteBillDetail(sellBillDetailNo);
		
		Info info=new Info();
		
		if(result>0) {
			
			info.setError(false);
			info.setMessage(" Sell Bill Detail Updated Successfully");
			
		}
		else {
			info.setError(true);
			info.setMessage("Error: Sell Bill Detail update  failed");
			
			
		}
			return info;
	  }
	@RequestMapping(value = { "/getItemHsnCode" }, method = RequestMethod.POST)
	public @ResponseBody GetItemHsnCode getItemHsnCode(@RequestParam("itemId") int itemId) {
	
		GetItemHsnCode getItemHsnCode=expressBillService.getItemHsnCode(itemId);
		
		return getItemHsnCode;
	}
	@RequestMapping(value = { "/getSlabwiseBillData" }, method = RequestMethod.POST)
	public @ResponseBody List<SlabwiseBillList> getSlabwiseBillData(@RequestParam("billNoList") List<String> billNos) {
	
		List<SlabwiseBillList> slabwiseBillList=new ArrayList<SlabwiseBillList>();
		for(int i=0;i<billNos.size();i++)
		{
			//System.out.println("billNo"+billNos.get(i));
			
		  SlabwiseBillList slabwiseBill=new SlabwiseBillList();
		  
		  List<SlabwiseBill> slabwiseBills=slabwiseDetailsRepository.getSlabwiseBillData(Integer.parseInt(billNos.get(i)));
		  //System.out.println("slabwiseBills"+slabwiseBills.toString());
		  slabwiseBill.setBillNo(Integer.parseInt(billNos.get(i)));
		  slabwiseBill.setSlabwiseBill(slabwiseBills);
		  slabwiseBillList.add(slabwiseBill);
		  //System.out.println("slabwiseBillList"+slabwiseBillList.toString());

		}
		return slabwiseBillList;
	}
	
	
	@Autowired PendingBillsRepo pendingBillRepo;
	@RequestMapping(value = { "/getPendingBillsByFrId" }, method = RequestMethod.POST)
	public @ResponseBody List<PendingBills> getPendingBillsByFrId(@RequestParam("frId") int frId) {

		List<PendingBills> pendingBills = pendingBillRepo.getAllPendingBillsByFrId(frId);
		    
			return pendingBills;
	  }
	
	@Autowired PendingBillCreditNoteRepo pendingCrNoteRepo;
	@RequestMapping(value = { "/getPendingCrNoteByFrId" }, method = RequestMethod.POST)
	public @ResponseBody List<PendingBillCreditNote> getPendingCrNoteByFrId(@RequestParam("frId") int frId) {

		List<PendingBillCreditNote> pendingcrNote = pendingCrNoteRepo.getPendingCrNote(frId);
		    
			return pendingcrNote;
	  }
	
	@Autowired PendingBillHeaderRepo billHeadRepo;
	@Autowired PendingBillDetailRepo billDetailRepo;
	@Autowired PostBillHeaderRepository headRepo;
	@Autowired PostCreditNoteHeaderRepository crnRepo;
	@RequestMapping(value = { "/insertPendingBillData" }, method = RequestMethod.POST)
	public @ResponseBody PendingBillHeader insertPendingBillData(@RequestBody PendingBillHeader billHeader) {
		int billId = 0;
		PendingBillHeader header = new PendingBillHeader();
		try {
			header = billHeadRepo.save(billHeader);
			if(header.getBillId()>0) {
				
				billId =  header.getBillId();
				
				List<PendingBillDetail> billDtlList = billHeader.getBillDetailList();
	
				for (int i = 0; i < billDtlList.size(); i++) {
					billDtlList.get(i).setBillId(billId);
				}
				
				List<PendingBillDetail> billDtlSave = billDetailRepo.save(billDtlList);	
				
				int billPaid = 0;
				for (int i = 0; i < billDtlSave.size(); i++) {					
					if(billDtlSave.get(i).getBillDetailId()>0) {
						if(billDtlSave.get(i).getType()==1) {
							
							billPaid = headRepo.updatePendingBillAsPaid(billDtlSave.get(i).getBilNoCrnId());
						}else {
							billPaid = crnRepo.updatePendinbCreditNoteAsPaid(billDtlSave.get(i).getBilNoCrnId());
						}
					}
				}
					
				
		}
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return header;
		
	}
}
