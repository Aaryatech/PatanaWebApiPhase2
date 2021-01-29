package com.ats.webapi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.ats.webapi.model.SellBillDetail;
import com.ats.webapi.model.SellBillHeader;
import com.ats.webapi.repository.SellBillDetailRepository;
import com.ats.webapi.repository.SellBillHeaderRepository;

@Service
public class SellBillDataServiceImpl implements SellBillDataService{

	@Autowired
	SellBillHeaderRepository sellBillHeaderRepository;
	
	@Autowired
	SellBillDetailRepository sellBillDetailRepository;
	
	public SellBillHeader saveSellBillHeader(SellBillHeader sellBillHeader) {
		
		

		SellBillHeader sellBillHeaderRes=new SellBillHeader();
		
			 
			
		sellBillHeaderRes=sellBillHeaderRepository.save(sellBillHeader);
		
		          int sellBillNo=sellBillHeaderRes.getSellBillNo();
		
		 
		           List<SellBillDetail> sellBillDetailList=sellBillHeaderRes.getSellBillDetailsList();
		
		           List<SellBillDetail> sellBillDetailRes=new ArrayList<SellBillDetail>();
		       for(int j=0;j<sellBillDetailList.size();j++) {
			
		             	   SellBillDetail sellBillDetail=sellBillDetailList.get(j);
			
			               sellBillDetail.setSellBillNo(sellBillNo);
			
			
			               sellBillDetail=sellBillDetailRepository.save(sellBillDetail);
			               sellBillDetailRes.add(sellBillDetail);
		            }
		
	
		       sellBillHeaderRes.setSellBillDetailsList(sellBillDetailRes);
		return sellBillHeaderRes;
	}
	
	
	public SellBillHeader getSellBillBysellBillNo(int sellBillNo) {
		SellBillHeader header=new SellBillHeader();
		System.err.println("In SellBillDataServiceImpl");
		List<SellBillDetail> detailList=new ArrayList<>();
		header=sellBillHeaderRepository.getSellBillBySellBillNo(sellBillNo);
		//System.err.println("Header-->"+header);
		detailList=sellBillDetailRepository.getSelBillDetailBySelBillNo(sellBillNo);
		
		header.setSellBillDetailsList(detailList);
		
		
		return header;
	}
	
	
	public SellBillHeader updateSellBillHeader(SellBillHeader sellBillHeader) {
		SellBillHeader Header=new SellBillHeader();
		sellBillHeaderRepository.save(sellBillHeader);
		sellBillDetailRepository.save(sellBillHeader.getSellBillDetailsList());
	return Header;
	}


	
	
	
	@Override
	public int deleteSellBillHeader(int sellBillNo) {
		int flag=0;
		flag=sellBillHeaderRepository.deleteSellBillHeader(sellBillNo);
		return flag;
	}
	
	
	
	
	
	
	

}
