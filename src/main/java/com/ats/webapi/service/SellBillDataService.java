package com.ats.webapi.service;

import java.util.List;

import com.ats.webapi.model.SellBillHeader;

public interface SellBillDataService {

	SellBillHeader saveSellBillHeader(SellBillHeader sellBillHeader);

	
	public SellBillHeader getSellBillBysellBillNo(int sellBillNo);
	
	public SellBillHeader updateSellBillHeader(SellBillHeader sellBillHeader);
	
	public int deleteSellBillHeader(int sellBillNo);
	
}
