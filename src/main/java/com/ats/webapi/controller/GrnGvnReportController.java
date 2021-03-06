package com.ats.webapi.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.webapi.model.grngvnreport.CreditNoteGrnGvnItemWise;
import com.ats.webapi.model.grngvnreport.GGProdWiseReport;
import com.ats.webapi.model.grngvnreport.GGReportByDate;
import com.ats.webapi.model.grngvnreport.GGReportGrpByFrId;
import com.ats.webapi.model.grngvnreport.GGReportGrpByItemId;
import com.ats.webapi.model.grngvnreport.GGReportGrpByMonthDate;
import com.ats.webapi.model.grngvnreport.PendingGrnGvnItemWise;
import com.ats.webapi.model.grngvnreport.PendingItemGrnGvn;
import com.ats.webapi.repository.ggreport.CreditNoteGrnGvnItemWiseRepo;
import com.ats.webapi.repository.ggreport.GGProdWiseReportRepo;
import com.ats.webapi.repository.ggreport.GGReportByDateRepo;
import com.ats.webapi.repository.ggreport.GGReportGrpByFrIdRepo;
import com.ats.webapi.repository.ggreport.GGReportGrpByItemIdRepo;
import com.ats.webapi.repository.ggreport.GGreportGrpByDateMonthRepo;
import com.ats.webapi.repository.ggreport.PendingGrnGvnItemWiseRepo;
import com.ats.webapi.repository.ggreport.PendingItemGrnGvnRepo;

import ch.qos.logback.classic.pattern.DateConverter;

@RestController
public class GrnGvnReportController {

	@Autowired
	GGreportGrpByDateMonthRepo gGreportGrpByDateMonthRepo;// r3,r4

	@Autowired
	GGReportGrpByFrIdRepo gGReportGrpByFrIdRepo;// r2

	@Autowired
	GGReportByDateRepo gGReportByDateRepo;// r1

	@Autowired
	GGReportGrpByItemIdRepo gGReportGrpByItemIdRepo;

	// r1
	// report 1
	@RequestMapping(value = { "/getgGReportByDate" }, method = RequestMethod.POST)
	public @ResponseBody List<GGReportByDate> getgGReportByDate(@RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate, @RequestParam("isGrn") List<String> isGrn,
			@RequestParam("frIdList") List<String> frIdList) {

		// System.err.println("Parameter received fromDate: " + fromDate + "toDate : " +
		// toDate + "frIdList : " + frIdList + "isGrn : " + isGrn);

		List<GGReportByDate> grpByDateList = new ArrayList<GGReportByDate>();
		try {

			if (!frIdList.contains("0")) {
				// System.err.println(" frIdList: It is Not zero ");
				grpByDateList = gGReportByDateRepo.getGrnGvnReportByDateSelFr(fromDate, toDate, frIdList, isGrn);

			} else {
				// System.err.println(" frIdList: It is zero ");

				grpByDateList = gGReportByDateRepo.getGrnGvnReportByDateAllFr(fromDate, toDate, isGrn);

			}
		} catch (Exception e) {

			// System.err.println("Exce in /GrnGvnReportController : /getgGReportByDate
			// msg-" + e.getMessage() + " trace-" + e.getStackTrace().toString());
			e.printStackTrace();
			e.getCause();
		}

		return grpByDateList;
	}

	// r1

	// sumit sir query Report 2
	@RequestMapping(value = { "/gGReportGrpByFrId" }, method = RequestMethod.POST)
	public @ResponseBody List<GGReportGrpByFrId> gGReportGrpByFrId(@RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate, @RequestParam("isGrn") List<String> isGrn,
			@RequestParam("frIdList") List<String> frIdList) {

		// System.err.println("Parameter received fromDate: " + fromDate + "toDate : " +
		// toDate + "frIdList : " + frIdList + "isGrn : " + isGrn);

		List<GGReportGrpByFrId> grpByFrIdList = null;

		try {
			if (!frIdList.contains("0")) {
				// System.out.println("fr Id List doesn't contain zero ");
				grpByFrIdList = gGReportGrpByFrIdRepo.getGGReportGrpByFrIdSelFr(fromDate, toDate, isGrn, frIdList);
			} else {
				// System.out.println("fr id list is zero : get For All Fr");
				grpByFrIdList = gGReportGrpByFrIdRepo.getGGReportGrpByFrIdSelFrAllFr(fromDate, toDate, isGrn);
			}

		} catch (Exception e) {

			// System.err.println("Exce in /GrnGvnReportController : /gGReportGrpByFrId" +
			// e.getMessage());
			e.printStackTrace();
		}

		return grpByFrIdList;
	}

	// report 3
	@RequestMapping(value = { "/getGGReportGrpByDate" }, method = RequestMethod.POST)
	public @ResponseBody List<GGReportGrpByMonthDate> getGGReportGrpByDate(@RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate, @RequestParam("isGrn") List<String> isGrn,
			@RequestParam("frIdList") List<String> frIdList) {

		// System.err.println("Parameter received fromDate: " + fromDate + "toDate : " +
		// toDate + "frIdList : " + frIdList + "isGrn : " + isGrn);

		List<GGReportGrpByMonthDate> grpByDateList = null;
		try {
			if (!frIdList.contains("0")) {
				grpByDateList = gGreportGrpByDateMonthRepo.getGrnGvnReportByDateSelFrGroupByDate(fromDate, toDate,
						isGrn, frIdList);
			} else {

				grpByDateList = gGreportGrpByDateMonthRepo.getGrnGvnReportByDateAllFrAllGGGroupByDate(fromDate, toDate,
						isGrn);

			}
		} catch (Exception e) {

			// System.err.println("Exce in /GrnGvnReportController : /getGGReportGrpByDate"
			// + e.getMessage());
			e.printStackTrace();
		}

		return grpByDateList;
	}

	// report 4
	@RequestMapping(value = { "/getGGReportGrpByMonth" }, method = RequestMethod.POST)
	public @ResponseBody List<GGReportGrpByMonthDate> getGGReportGrpByMonth(@RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate, @RequestParam("isGrn") List<String> isGrn,
			@RequestParam("frIdList") List<String> frIdList) {
		// System.err.println("Parameter received fromDate: " + fromDate + "toDate : " +
		// toDate + "frIdList : " + frIdList + "isGrn : " + isGrn);

		List<GGReportGrpByMonthDate> grpByDateList = null;
		try {
			if (!frIdList.contains("0")) {
				grpByDateList = gGreportGrpByDateMonthRepo.getGrnGvnReportByDateSelFrGroupByMonth(fromDate, toDate,
						frIdList, isGrn);
			} else {

				grpByDateList = gGreportGrpByDateMonthRepo.getGrnGvnReportByDateAllFrAllGGGroupByMonth(fromDate, toDate,
						isGrn);

			}
		} catch (Exception e) {

			// System.err.println("Exce in /GrnGvnReportController : /getGGReportGrpByMonth"
			// + e.getMessage());
			e.printStackTrace();
		}

		return grpByDateList;
	}

	@RequestMapping(value = { "/gGReportGrpByItemId" }, method = RequestMethod.POST)
	public @ResponseBody List<GGReportGrpByItemId> gGReportGrpByItemId(@RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate, @RequestParam("isGrn") List<String> isGrn,
			@RequestParam("frIdList") List<String> frIdList, @RequestParam("catIdList") List<String> catIdList,
			@RequestParam("subCatId") List<String> subCatId) {

		// System.err.println("Parameter received fromDate: " + fromDate + "toDate : " +
		// toDate + "frIdList : " + frIdList + "isGrn : " +
		// isGrn+"catIdList"+catIdList);

		List<GGReportGrpByItemId> grpByFrIdList = null;

		try {
			if (!frIdList.contains("-1") && !catIdList.contains("-1") && subCatId.contains("-1")) {
				// System.out.println("fr Id List doesn't contain zero ");
				grpByFrIdList = gGReportGrpByItemIdRepo.getGGReportGrpByFrIdSelFr(fromDate, toDate, isGrn, frIdList,
						catIdList);
			} else if (!frIdList.contains("-1") && catIdList.contains("-1") && subCatId.contains("-1")) {
				grpByFrIdList = gGReportGrpByItemIdRepo.getGGReportGrpByItemId(fromDate, toDate, isGrn, frIdList);
			}

			else if (frIdList.contains("-1") && !catIdList.contains("-1") && subCatId.contains("-1")) {
				grpByFrIdList = gGReportGrpByItemIdRepo.getGGReportGrpByItemIdSelFr(fromDate, toDate, isGrn, catIdList);
			} else if (!frIdList.contains("-1") && !catIdList.contains("-1") && !subCatId.contains("-1")) {
				grpByFrIdList = gGReportGrpByItemIdRepo.getGGReportGrpBySubCatId(frIdList, fromDate, toDate, isGrn,
						catIdList, subCatId);
			} else {
				// System.out.println("fr id list is zero : get For All Fr");
				grpByFrIdList = gGReportGrpByItemIdRepo.getGGReportGrpByFrIdSelFrAllFr(fromDate, toDate, isGrn);
			}

		} catch (Exception e) {

			// System.err.println("Exce in /GrnGvnReportController : /gGReportGrpByFrId" +
			// e.getMessage());
			e.printStackTrace();
		}

		return grpByFrIdList;
	}

	@Autowired
	PendingItemGrnGvnRepo pndGrnGvnRepo;

	@RequestMapping(value = { "/getPendingGrnGvnItemReport" }, method = RequestMethod.POST)
	public @ResponseBody List<PendingItemGrnGvn> getPendingGrnGvnItemReport(@RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate, @RequestParam("isGrn") List<String> isGrn,
			@RequestParam("frId") int frId, @RequestParam("isCrnGen") List<String> isCrnGen,
			@RequestParam("status") int status) {

		List<PendingItemGrnGvn> grnGvn = new ArrayList<PendingItemGrnGvn>();
		try {
			grnGvn = pndGrnGvnRepo.getPendingItemGrnGvn(fromDate, toDate, frId, isGrn, isCrnGen, status);

		} catch (Exception e) {

			System.err.println("Exce in /GrnGvnReportController : /gGReportGrpByFrId" + e.getMessage());
			e.printStackTrace();
		}

		return grnGvn;
	}

	@Autowired
	PendingGrnGvnItemWiseRepo pndItmGrnGvn;

	@RequestMapping(value = { "/getAcPendingItemGrnGvnReport" }, method = RequestMethod.POST)
	public @ResponseBody List<PendingGrnGvnItemWise> getAcPendingItemGrnGvnReport(
			@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate,
			@RequestParam("isGrn") List<String> isGrn, @RequestParam("frIdList") List<String> frIdList,
			@RequestParam("grnStatus") int grnStatus, @RequestParam("aprvBy") int aprvBy) {

		System.err.println(
				frIdList + " / " + fromDate + " / " + toDate + " / " + grnStatus + " / " + aprvBy + " / " + isGrn);
		List<PendingGrnGvnItemWise> grnGvn = new ArrayList<PendingGrnGvnItemWise>();
		try {
			if (aprvBy == 1)
				grnGvn = pndItmGrnGvn.getAcPendingGrnGvnItemsAprv(fromDate, isGrn, toDate, grnStatus, frIdList);
			else
				grnGvn = pndItmGrnGvn.getAcPendingGrnGvnItems(fromDate, isGrn, toDate, grnStatus, frIdList);

			System.err.println("grnGvn--------------" + grnGvn);

		} catch (Exception e) {

			System.err.println("Exce in /GrnGvnReportController : /getAcPendingItemGrnGvnReport" + e.getMessage());
			e.printStackTrace();
		}

		return grnGvn;
	}

	// Add New Grn Product wise report
	// Sachin 15-02-2021
	/*
	 * SELECT UUID() as uid, SUM(t_credit_note_details.grn_gvn_qty) as
	 * grn_gvn_qty,t_credit_note_details.item_id, t_credit_note_header.fr_id
	 * ,m_item.item_name FROM t_credit_note_header,t_credit_note_details,m_item
	 * WHERE t_credit_note_header.crn_id=t_credit_note_details.crn_id and
	 * m_item.id=t_credit_note_details.item_id and t_credit_note_header.crn_date
	 * BETWEEN '2020-01-01' and '2020-12-31' and t_credit_note_details.item_id=5 and
	 * t_credit_note_header.is_grn IN(0,1,2) GROUP by t_credit_note_header.fr_id,
	 * t_credit_note_details.item_id
	 */

	@Autowired
	GGProdWiseReportRepo ggProdwiseReportRepo;

	@RequestMapping(value = { "/getGGProdWiseQtyReport" }, method = RequestMethod.POST)
	public @ResponseBody List<GGProdWiseReport> getGGItemwiseReport(@RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate, @RequestParam("isGrn") List<String> isGrn,
			@RequestParam("frIdList") List<String> frIdList, @RequestParam("itemIdList") List<String> itemIdList) {
		
		DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		System.err.println(frIdList + " / " + fromDate + " / " + toDate + " /" + isGrn);
		List<GGProdWiseReport> grnGvnItemQtyList = new ArrayList<GGProdWiseReport>();
		try {
			grnGvnItemQtyList = ggProdwiseReportRepo.getGGItemwiseReport(fromDate, toDate, frIdList, isGrn, itemIdList);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return grnGvnItemQtyList;

	}
	
	@Autowired CreditNoteGrnGvnItemWiseRepo crnGrnGvnRepo;
	@RequestMapping(value = { "/getCrnItemGrnGvnReport" }, method = RequestMethod.POST)
	public @ResponseBody List<CreditNoteGrnGvnItemWise> getCrnItemGrnGvnReport(@RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate, @RequestParam("isGrn") List<String> isGrn, @RequestParam("frId") int frId) {

		
		List<CreditNoteGrnGvnItemWise> grnGvn = new ArrayList<CreditNoteGrnGvnItemWise>();
		try {
			
			grnGvn = crnGrnGvnRepo.getCrnGrnGvnItems(fromDate, isGrn, toDate, frId);
			
			//System.err.println("grnGvn--------------"+grnGvn);
			
		} catch (Exception e) {

			System.err.println("Exce in /GrnGvnReportController : /getCrnItemGrnGvnReport" +e.getMessage());			
			e.printStackTrace();
		}

		return grnGvn;
	}

}
