package com.ats.webapi.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.webapi.model.Info;
import com.ats.webapi.model.StockTransferDetail;
import com.ats.webapi.model.StockTransferHeader;
import com.ats.webapi.repository.StockTransferDetailRepository;
import com.ats.webapi.repository.StockTransferHeaderRepository;

@RestController
public class StockTransferApiController {

	@Autowired
	StockTransferHeaderRepository stockTranferHeaderRepo;
	
	@Autowired
	StockTransferDetailRepository stockTranferDetailRepo;
	
	
	@RequestMapping(value="/saveStockTransferHeader",method=RequestMethod.POST)
	public @ResponseBody StockTransferHeader saveStockTransferHeader(@RequestBody StockTransferHeader StockTranHeader) {
		StockTransferHeader header=new StockTransferHeader();
		List<StockTransferDetail> detailList=new ArrayList<>();
		try {
			header=stockTranferHeaderRepo.save(StockTranHeader);
			if(header.getTransferId()>0) {
				for(StockTransferDetail detail : StockTranHeader.getStockTransferdetailList()) {
					detail.setTransferId(header.getTransferId());
				}
				
				detailList=	stockTranferDetailRepo.save(StockTranHeader.getStockTransferdetailList());
				header.setStockTransferdetailList(detailList);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Exception Occuered In /saveStockTransferHeader");
			e.printStackTrace();
		}
	
		return header;
	}
	
	
	@RequestMapping(value="/getAllStockTransferHeadByFromFrId",method=RequestMethod.POST)
	public @ResponseBody List<StockTransferHeader> getAllStockTransferHeadByFromFrId(@RequestParam int fromFrId){
		List<StockTransferHeader> headerList=new ArrayList<>();
		try {
			headerList=stockTranferHeaderRepo.getStockHeaderByFromFrid(fromFrId);
			if(headerList.size()>0) {
				for (StockTransferHeader stockTransferHeader : headerList) {
					List<StockTransferDetail> detailList=new ArrayList<>();
					detailList=stockTranferDetailRepo.getDetailListByTransferId(stockTransferHeader.getTransferId());
					stockTransferHeader.setStockTransferdetailList(detailList);
				}
			
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Exception Occured In /getAllStockTransferHeadByFromFrId");
		e.printStackTrace();
		}
		
		return headerList;
	}
	
	
	@RequestMapping(value="/getAllStockTransferHeadByToFrId",method=RequestMethod.POST)
	public @ResponseBody List<StockTransferHeader> getAllStockTransferHeadByToFrId(@RequestParam int toFrId){
		List<StockTransferHeader> headerList=new ArrayList<>();
		try {								
			headerList=stockTranferHeaderRepo.getStockHeaderBytoFrid(toFrId);
			if(headerList.size()>0) {
				for (StockTransferHeader stockTransferHeader : headerList) {
					List<StockTransferDetail> detailList=new ArrayList<>();
					detailList=stockTranferDetailRepo.getDetailListByTransferId(stockTransferHeader.getTransferId());
					stockTransferHeader.setStockTransferdetailList(detailList);
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Exception Occured In /getAllStockTransferHeadByToFrId");
		e.printStackTrace();
		}
		
		return headerList;
	}
	
	@RequestMapping(value="/updateHeaderStatus",method=RequestMethod.POST)
	public @ResponseBody Info updateHeaderStatus(@RequestParam int tranId,@RequestParam int status) {
		Info info=new Info();
		Integer flag=0;
		try {
			flag=stockTranferHeaderRepo.updateStatus(tranId, status);
				if(flag>0){
					info.setError(false);
					info.setMessage("Stock Exchange Header Deleted ");
				}else {
					info.setError(true);
					info.setMessage("Unable To Delete Stock Exchange Header");
				}
			
		} catch (Exception e) {
			// TODO: handle exception
			info.setError(true);
			info.setMessage("Unable To Delete Stock Exchange Header Exception Occuered");
			System.err.println("Exception Occuered In /updateHeaderStatus");
			e.printStackTrace();
		}
		return info;
	

}
	
	
	
	
	
}
