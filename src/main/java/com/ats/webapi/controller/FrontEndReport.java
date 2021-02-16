package com.ats.webapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.webapi.commons.Common;
import com.ats.webapi.model.GrnGvnReport;
import com.ats.webapi.model.TSellReport;
import com.ats.webapi.model.report.SalesReportFranchisee;
import com.ats.webapi.repository.GrnGvnReportRepository;
import com.ats.webapi.repository.SalesReportFranchiseeRepo;
import com.ats.webapi.repository.TSellReportRepository; 

@RestController
public class FrontEndReport {
	
	@Autowired
	TSellReportRepository tSellReportRepository;
	
	@Autowired
	GrnGvnReportRepository grnGvnReportRepository;
	
	@RequestMapping(value = { "/tSellReport" }, method = RequestMethod.POST)
	public @ResponseBody List<TSellReport> tSellReport(@RequestParam("frId")int frId,@RequestParam("fromDate")String fromDate,
			@RequestParam("toDate")String toDate)
	{
		List<TSellReport> tSellReport = new ArrayList<TSellReport>();
		try
		{
			//System.out.println("suppId : "+frId);
			//System.out.println("fromDate : "+fromDate);
			//System.out.println("toDate : "+toDate);
			tSellReport = tSellReportRepository.hsnCodeWiseReport(frId, fromDate, toDate);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		  return tSellReport ;

	}
	
	@RequestMapping(value = { "/grnGvnReport" }, method = RequestMethod.POST)
	public @ResponseBody List<GrnGvnReport> grnGvnReport(@RequestParam("frId")int frId,@RequestParam("fromDate")String fromDate,
			@RequestParam("toDate")String toDate,@RequestParam("isGrn")String isGrn)
	{
		List<GrnGvnReport> tSellReport = new ArrayList<GrnGvnReport>();
		try
		{
			//System.out.println("suppId : "+frId);
			//System.out.println("fromDate : "+fromDate);
			//System.out.println("toDate : "+toDate);
			//System.out.println("isGrn : "+isGrn);
			if(isGrn.equals("1")) {
			tSellReport = grnGvnReportRepository.grnGvnReportDateWise(frId, fromDate, toDate, isGrn);
			}else {
				tSellReport = grnGvnReportRepository.grnGvnReportDateWiseOfGvn(frId, fromDate, toDate, isGrn);
				List<GrnGvnReport> tSellList=grnGvnReportRepository.grnGvnReportDateWiseOfGvnForSp(frId, fromDate, toDate, isGrn);
				if(tSellList.size()>0) {
				tSellReport.addAll(tSellList);
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		  return tSellReport ;

	}
	
	@Autowired
	SalesReportFranchiseeRepo salesReportFranchiseeRepo;
	@RequestMapping(value = { "/getSaleReportFrwiseSummery" }, method = RequestMethod.POST)
	public @ResponseBody List<SalesReportFranchisee> getSaleReportFrwiseSummery(
			@RequestParam("frIdList") List<String> frIdList, @RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate) {

		List<SalesReportFranchisee> salesReportFranchisee = null;
		try {
			fromDate = Common.convertToYMD(fromDate);
			toDate = Common.convertToYMD(toDate);
			// System.out.println("Input received " + fromDate + "" + toDate + "" +
			// frIdList);
			salesReportFranchisee = salesReportFranchiseeRepo.getSaleReportBillwise(frIdList, fromDate, toDate);

		} catch (Exception e) {
			System.out.println(" Exce in sale Report Billwise  " + e.getMessage());
			e.printStackTrace();
		}
		return salesReportFranchisee;
	}

}
