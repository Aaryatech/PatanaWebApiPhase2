package com.ats.webapi.repository.frpurchasereport;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.report.frpurchase.SalesReportDateMonth;

public interface SalesReportDateMonthRepo extends JpaRepository<SalesReportDateMonth, Integer> {

	/*
	 * @Query(value = "select 0 as crn_id," + "uuid() as crn_no," +
	 * ":fromDate as crn_date," + "0 as fr_id," + "m1.date_str as month," +
	 * ":fromDate as bill_date," + "COALESCE(t1.grand_total,0) grand_total," +
	 * "COALESCE(t1.taxable_amt,0) taxable_amt," +
	 * "COALESCE(t1.total_tax,0) total_tax," + "" +
	 * "COALESCE(t2.grand_total,0) as gvn_grand_total," +
	 * "COALESCE(t2.taxable_amt,0) as gvn_taxable_amt," +
	 * " COALESCE(t2.crn_total_tax,0) as gvn_total_tax," + "" +
	 * " COALESCE(t3.grand_total,0) as grn_grand_total," +
	 * " COALESCE(t3.taxable_amt,0) as grn_taxable_amt," +
	 * " COALESCE(t3.crn_total_tax,0) as grn_total_tax" + "" + " FROM" + " (" +
	 * " select " + " DATE_FORMAT(m1, '%M - %Y') as date_str," +
	 * " DATE_FORMAT(m1, '%b') as date_month," +
	 * " DATE_FORMAT(m1, '%Y') as date_year" + "" + " from" + " (" + " select " +
	 * " (:fromDate - INTERVAL DAYOFMONTH(:fromDate)-1 DAY) " +
	 * " +INTERVAL m MONTH as m1" + " from" + " (" +
	 * " select @rownum\\:=@rownum+1 as m from" +
	 * " (select 1 union select 2 union select 3 union select 4) t1," +
	 * " (select 1 union select 2 union select 3 union select 4) t2," +
	 * " (select 1 union select 2 union select 3 union select 4) t3," +
	 * " (select 1 union select 2 union select 3 union select 4) t4," +
	 * " (select @rownum\\:=-1) t0" + " ) d1" + " ) d2 " + " where m1<=:toDate" +
	 * " order by m1" + " )m1 " + " LEFT JOIN " + "(" + "    SELECT" +
	 * "    MONTHNAME(t_bill_header.bill_date) AS MONTH," +
	 * "    DATE_FORMAT(t_bill_header.bill_date, '%b') as date_month," +
	 * "    DATE_FORMAT(t_bill_header.bill_date, '%Y') as date_year," +
	 * "    t_bill_header.bill_no," + "    t_bill_header.bill_date," +
	 * "    t_bill_header.invoice_no," + "    t_bill_header.fr_id," +
	 * "    t_bill_header.fr_code," + "    t_bill_header.tax_applicable," +
	 * "    SUM(t_bill_header.taxable_amt) AS taxable_amt," +
	 * "    SUM(t_bill_header.total_tax) AS total_tax," +
	 * "    SUM(t_bill_header.grand_total) AS grand_total," +
	 * "    SUM(t_bill_header.round_off) AS round_off," +
	 * "    SUM(t_bill_header.sgst_sum) AS sgst_sum," +
	 * "    SUM(t_bill_header.cgst_sum) AS cgst_sum," +
	 * "    SUM(t_bill_header.igst_sum) AS igst_sum," + "    m_franchisee.fr_name,"
	 * + "    m_franchisee.fr_city," + "    m_franchisee.fr_gst_no," +
	 * "    m_franchisee.is_same_state" + " FROM " + "    m_franchisee," +
	 * "    t_bill_header" + " WHERE" +
	 * "    t_bill_header.fr_id = m_franchisee.fr_id AND t_bill_header.fr_id IN(:frIdList) AND t_bill_header.bill_date BETWEEN :fromDate AND :toDate AND t_bill_header.del_status = 0"
	 * + " GROUP BY " + "    month,date_year" +
	 * ") t1 ON m1.date_month=t1.date_month AND m1.date_year=t1.date_year " + "" +
	 * " LEFT JOIN " + "" + " (" + " SELECT" +
	 * "    MONTHNAME(t_credit_note_header.crn_date) AS MONTH," +
	 * "    DATE_FORMAT(t_credit_note_header.crn_date, '%b') as date_month," +
	 * "    DATE_FORMAT(t_credit_note_header.crn_date, '%Y') as date_year," +
	 * "    t_credit_note_header.crn_id," + "    t_credit_note_header.crn_date," +
	 * "    t_credit_note_header.crn_no," + "    m_franchisee.fr_id," + "    SUM(" +
	 * "        t_credit_note_header.crn_taxable_amt" + "    ) AS taxable_amt," +
	 * "    SUM(" + "        t_credit_note_header.crn_total_tax" +
	 * "    ) AS crn_total_tax," + "    SUM(" +
	 * "        t_credit_note_header.crn_grand_total" + "    ) AS grand_total," +
	 * "    SUM(t_credit_note_header.round_off) AS round_off" + " FROM " +
	 * "    m_franchisee," + "    t_credit_note_header" + " WHERE " +
	 * "    t_credit_note_header.is_grn =0 AND t_credit_note_header.fr_id = m_franchisee.fr_id AND t_credit_note_header.fr_id IN(:frIdList) AND t_credit_note_header.crn_date BETWEEN :fromDate AND :toDate"
	 * + " GROUP BY" + "    month" +
	 * " ) t2 ON m1.date_month=t2.date_month AND m1.date_year=t2.date_year" + "" +
	 * " LEFT JOIN" + "" + " (" + " SELECT" +
	 * "    MONTHNAME(t_credit_note_header.crn_date) AS MONTH," +
	 * "    DATE_FORMAT(t_credit_note_header.crn_date, '%b') as date_month," +
	 * "    DATE_FORMAT(t_credit_note_header.crn_date, '%Y') as date_year," +
	 * "    t_credit_note_header.crn_id," + "    t_credit_note_header.crn_date," +
	 * "    t_credit_note_header.crn_no," + "    m_franchisee.fr_id," + "    SUM(" +
	 * "        t_credit_note_header.crn_taxable_amt " + "    ) AS taxable_amt," +
	 * "    SUM(" + "        t_credit_note_header.crn_total_tax " +
	 * "    ) AS crn_total_tax," + "    SUM(" +
	 * "        t_credit_note_header.crn_grand_total " + "    ) AS grand_total," +
	 * "    SUM(t_credit_note_header.round_off) AS round_off " + " FROM " +
	 * "    m_franchisee, " + "    t_credit_note_header " + " WHERE " +
	 * "    t_credit_note_header.is_grn =1 AND t_credit_note_header.fr_id = m_franchisee.fr_id AND t_credit_note_header.fr_id IN(:frIdList) AND t_credit_note_header.crn_date BETWEEN :fromDate AND :toDate"
	 * + " GROUP BY " + "    month " +
	 * ") t3 ON m1.date_month=t3.date_month AND m1.date_year=t3.date_year " + " ",
	 * nativeQuery = true)
	 * 
	 * List<SalesReportDateMonth> getMonthData(@Param("frIdList") List<String>
	 * frIdList,
	 * 
	 * @Param("fromDate") String fromDate, @Param("toDate") String toDate );
	 */
	
	@Query(value = "select 0 as crn_id," + 
			"uuid() as crn_no," + 
			":fromDate as crn_date," + 
			"0 as fr_id," + 
			"m1.date_str as month," + 
			":fromDate as bill_date," + 
			"COALESCE(t1.grand_total,0) grand_total," + 
			"COALESCE(t1.taxable_amt,0) taxable_amt," + 
			"COALESCE(t1.total_tax,0) total_tax," + 
			"" + 
			"COALESCE(t2.grand_total,0) as gvn_grand_total," + 
			"COALESCE(t2.taxable_amt,0) as gvn_taxable_amt," + 
			" COALESCE(t2.crn_total_tax,0) as gvn_total_tax," + 
			"" + 
			" COALESCE(t3.grand_total,0) as grn_grand_total," + 
			" COALESCE(t3.taxable_amt,0) as grn_taxable_amt," + 
			" COALESCE(t3.crn_total_tax,0) as grn_total_tax" + 
			"" + 
			" FROM" + 
			" (" + 
			" select " + 
			" DATE_FORMAT(m1, '%M - %Y') as date_str," + 
			" DATE_FORMAT(m1, '%b') as date_month," + 
			" DATE_FORMAT(m1, '%Y') as date_year" + 
			"" + 
			" from" + 
			" (" + 
			" select " + 
			" (:fromDate - INTERVAL DAYOFMONTH(:fromDate)-1 DAY) " + 
			" +INTERVAL m MONTH as m1" + 
			" from" + 
			" (" + 
			" select @rownum\\:=@rownum+1 as m from" + 
			" (select 1 union select 2 union select 3 union select 4) t1," + 
			" (select 1 union select 2 union select 3 union select 4) t2," + 
			" (select 1 union select 2 union select 3 union select 4) t3," + 
			" (select 1 union select 2 union select 3 union select 4) t4," + 
			" (select @rownum\\:=-1) t0" + 
			" ) d1" + 
			" ) d2 " + 
			" where m1<=:toDate" + 
			" order by m1" + 
			" )m1 " + 
			" LEFT JOIN " + 
			"(" + 
			"    SELECT" + 
			"    MONTHNAME(t_bill_header.bill_date) AS MONTH," + 
			"    DATE_FORMAT(t_bill_header.bill_date, '%b') as date_month," + 
			"    DATE_FORMAT(t_bill_header.bill_date, '%Y') as date_year," + 
			"    t_bill_header.bill_no," + 
			"    t_bill_header.bill_date," + 
			"    t_bill_header.invoice_no," + 
			"    t_bill_header.fr_id," + 
			"    t_bill_header.fr_code," + 
			"    t_bill_header.tax_applicable," + 
			/*
			 * "    SUM(t_bill_header.taxable_amt) AS taxable_amt," +
			 * "    SUM(t_bill_header.total_tax) AS total_tax," +
			 * "    SUM(t_bill_header.grand_total) AS grand_total," +
			 * "    SUM(t_bill_header.round_off) AS round_off," +
			 * "    SUM(t_bill_header.sgst_sum) AS sgst_sum," +
			 * "    SUM(t_bill_header.cgst_sum) AS cgst_sum," +
			 * "    SUM(t_bill_header.igst_sum) AS igst_sum," +
			 */
			" SUM(t_bill_detail.taxable_amt) AS taxable_amt," + 
			"    SUM(t_bill_detail.total_tax) AS total_tax," + 
			"    SUM(t_bill_detail.grand_total) AS grand_total," + 
			"    SUM(t_bill_header.round_off) AS round_off," + 
			"    SUM(t_bill_detail.sgst_rs) AS sgst_sum," + 
			"    SUM(t_bill_detail.cgst_rs) AS cgst_sum," + 
			"    SUM(t_bill_detail.igst_rs) AS igst_sum," + 
			"    m_franchisee.fr_name," + 
			"    m_franchisee.fr_city," + 
			"    m_franchisee.fr_gst_no," + 
			"    m_franchisee.is_same_state" + 
			" FROM " + 
			"    m_franchisee," + 
			"    t_bill_header,t_bill_detail,m_item " + 
			" WHERE " + 
			"    t_bill_header.bill_no=t_bill_detail.bill_no and t_bill_detail.item_id=m_item.id and m_item.item_grp2 IN (:subCatIdList) and "
			+ " t_bill_header.fr_id = m_franchisee.fr_id AND t_bill_header.fr_id IN(:frIdList) AND t_bill_header.bill_date BETWEEN :fromDate AND :toDate AND t_bill_header.del_status = 0" + 
			" GROUP BY " + 
			"    month,date_year" + 
			") t1 ON m1.date_month=t1.date_month AND m1.date_year=t1.date_year " + 
			"" + 
			" LEFT JOIN " + 
			"" + 
			" (" + 
			" SELECT" + 
			"    MONTHNAME(t_credit_note_header.crn_date) AS MONTH," + 
			"    DATE_FORMAT(t_credit_note_header.crn_date, '%b') as date_month," + 
			"    DATE_FORMAT(t_credit_note_header.crn_date, '%Y') as date_year," + 
			"    t_credit_note_header.crn_id," + 
			"    t_credit_note_header.crn_date," + 
			"    t_credit_note_header.crn_no," + 
			"    m_franchisee.fr_id," + 
			"       SUM(          t_credit_note_details.taxable_amt      ) AS taxable_amt, " + 
			"		  SUM(          t_credit_note_details.total_tax      ) AS crn_total_tax, " + 
			"			   SUM(          t_credit_note_details.grn_gvn_amt      ) AS grand_total, " + 
			"		   SUM(t_credit_note_header.round_off) AS round_off "   + 
			" FROM " + 
			"    m_franchisee," + 
			"    t_credit_note_header,t_credit_note_details,m_item" + 
			" WHERE t_credit_note_details.crn_id=t_credit_note_header.crn_id AND " + 
			"  t_credit_note_details.item_id=m_item.id  AND m_item.item_grp2 IN(:subCatIdList) and " + 
			"    t_credit_note_header.is_grn =0 AND t_credit_note_header.fr_id = m_franchisee.fr_id AND t_credit_note_header.fr_id IN(:frIdList) AND t_credit_note_header.crn_date BETWEEN :fromDate AND :toDate" + 
			" GROUP BY" + 
			"    month" + 
			" ) t2 ON m1.date_month=t2.date_month AND m1.date_year=t2.date_year" + 
			"" + 
			" LEFT JOIN" + 
			"" + 
			" (" + 
			" SELECT" + 
			"    MONTHNAME(t_credit_note_header.crn_date) AS MONTH," + 
			"    DATE_FORMAT(t_credit_note_header.crn_date, '%b') as date_month," + 
			"    DATE_FORMAT(t_credit_note_header.crn_date, '%Y') as date_year," + 
			"    t_credit_note_header.crn_id," + 
			"    t_credit_note_header.crn_date," + 
			"    t_credit_note_header.crn_no," + 
			"    m_franchisee.fr_id," + 
			/*
			 * "    SUM(" + "        t_credit_note_header.crn_taxable_amt " +
			 * "    ) AS taxable_amt," + "    SUM(" +
			 * "        t_credit_note_header.crn_total_tax " + "    ) AS crn_total_tax," +
			 * "    SUM(" + "        t_credit_note_header.crn_grand_total " +
			 * "    ) AS grand_total," +
			 * "    SUM(t_credit_note_header.round_off) AS round_off " +
			 */
			 "    SUM(          t_credit_note_details.taxable_amt      ) AS taxable_amt," + 
				"    SUM(          t_credit_note_details.total_tax      ) AS crn_total_tax," + 
				"    SUM(          t_credit_note_details.grn_gvn_amt      ) AS grand_total," + 
				"    SUM(t_credit_note_header.round_off) AS round_off  " + 
			" FROM " + 
			"    m_franchisee, " + 
			"    t_credit_note_header,t_credit_note_details,m_item " + 
			" WHERE  t_credit_note_details.crn_id=t_credit_note_header.crn_id AND  " + 
			"			  t_credit_note_details.item_id=m_item.id  AND m_item.item_grp2 IN(:subCatIdList) and  " + 
			"    t_credit_note_header.is_grn =1 AND t_credit_note_header.fr_id = m_franchisee.fr_id AND t_credit_note_header.fr_id IN(:frIdList) AND t_credit_note_header.crn_date BETWEEN :fromDate AND :toDate" + 
			" GROUP BY " + 
			"    month " + 
			") t3 ON m1.date_month=t3.date_month AND m1.date_year=t3.date_year " + 
			" ", nativeQuery = true)

	List<SalesReportDateMonth> getMonthDataForM_item(@Param("frIdList") List<String> frIdList,
			@Param("fromDate") String fromDate, @Param("toDate") String toDate,
			@Param("subCatIdList") List<String> subCatIdList
	 );
	
	@Query(value = "select 0 as crn_id," + 
			"uuid() as crn_no," + 
			":fromDate as crn_date," + 
			"0 as fr_id," + 
			"m1.date_str as month," + 
			":fromDate as bill_date," + 
			"COALESCE(t1.grand_total,0) grand_total," + 
			"COALESCE(t1.taxable_amt,0) taxable_amt," + 
			"COALESCE(t1.total_tax,0) total_tax," + 
			"" + 
			"COALESCE(t2.grand_total,0) as gvn_grand_total," + 
			"COALESCE(t2.taxable_amt,0) as gvn_taxable_amt," + 
			" COALESCE(t2.crn_total_tax,0) as gvn_total_tax," + 
			"" + 
			" COALESCE(t3.grand_total,0) as grn_grand_total," + 
			" COALESCE(t3.taxable_amt,0) as grn_taxable_amt," + 
			" COALESCE(t3.crn_total_tax,0) as grn_total_tax" + 
			"" + 
			" FROM" + 
			" (" + 
			" select " + 
			" DATE_FORMAT(m1, '%M - %Y') as date_str," + 
			" DATE_FORMAT(m1, '%b') as date_month," + 
			" DATE_FORMAT(m1, '%Y') as date_year" + 
			"" + 
			" from" + 
			" (" + 
			" select " + 
			" (:fromDate - INTERVAL DAYOFMONTH(:fromDate)-1 DAY) " + 
			" +INTERVAL m MONTH as m1" + 
			" from" + 
			" (" + 
			" select @rownum\\:=@rownum+1 as m from" + 
			" (select 1 union select 2 union select 3 union select 4) t1," + 
			" (select 1 union select 2 union select 3 union select 4) t2," + 
			" (select 1 union select 2 union select 3 union select 4) t3," + 
			" (select 1 union select 2 union select 3 union select 4) t4," + 
			" (select @rownum\\:=-1) t0" + 
			" ) d1" + 
			" ) d2 " + 
			" where m1<=:toDate" + 
			" order by m1" + 
			" )m1 " + 
			" LEFT JOIN " + 
			"(" + 
			"    SELECT" + 
			"    MONTHNAME(t_bill_header.bill_date) AS MONTH," + 
			"    DATE_FORMAT(t_bill_header.bill_date, '%b') as date_month," + 
			"    DATE_FORMAT(t_bill_header.bill_date, '%Y') as date_year," + 
			"    t_bill_header.bill_no," + 
			"    t_bill_header.bill_date," + 
			"    t_bill_header.invoice_no," + 
			"    t_bill_header.fr_id," + 
			"    t_bill_header.fr_code," + 
			"    t_bill_header.tax_applicable," + 
			/*
			 * "    SUM(t_bill_header.taxable_amt) AS taxable_amt," +
			 * "    SUM(t_bill_header.total_tax) AS total_tax," +
			 * "    SUM(t_bill_header.grand_total) AS grand_total," +
			 * "    SUM(t_bill_header.round_off) AS round_off," +
			 * "    SUM(t_bill_header.sgst_sum) AS sgst_sum," +
			 * "    SUM(t_bill_header.cgst_sum) AS cgst_sum," +
			 * "    SUM(t_bill_header.igst_sum) AS igst_sum," +
			 */
			" SUM(t_bill_detail.taxable_amt) AS taxable_amt," + 
			"    SUM(t_bill_detail.total_tax) AS total_tax," + 
			"    SUM(t_bill_detail.grand_total) AS grand_total," + 
			"    SUM(t_bill_header.round_off) AS round_off," + 
			"    SUM(t_bill_detail.sgst_rs) AS sgst_sum," + 
			"    SUM(t_bill_detail.cgst_rs) AS cgst_sum," + 
			"    SUM(t_bill_detail.igst_rs) AS igst_sum," + 
			"    m_franchisee.fr_name," + 
			"    m_franchisee.fr_city," + 
			"    m_franchisee.fr_gst_no," + 
			"    m_franchisee.is_same_state" + 
			" FROM " + 
			"    m_franchisee," + 
			"    t_bill_header,t_bill_detail,m_sp_cake " + 
			" WHERE " + 
			"    t_bill_header.bill_no=t_bill_detail.bill_no and t_bill_detail.item_id=m_sp_cake.sp_id and t_bill_detail.cat_id=5 and"
			+ " t_bill_header.fr_id = m_franchisee.fr_id AND t_bill_header.fr_id IN(:frIdList) AND t_bill_header.bill_date BETWEEN :fromDate AND :toDate AND t_bill_header.del_status = 0" + 
			" GROUP BY " + 
			"    month,date_year" + 
			") t1 ON m1.date_month=t1.date_month AND m1.date_year=t1.date_year " + 
			"" + 
			" LEFT JOIN " + 
			"" + 
			" (" + 
			" SELECT" + 
			"    MONTHNAME(t_credit_note_header.crn_date) AS MONTH," + 
			"    DATE_FORMAT(t_credit_note_header.crn_date, '%b') as date_month," + 
			"    DATE_FORMAT(t_credit_note_header.crn_date, '%Y') as date_year," + 
			"    t_credit_note_header.crn_id," + 
			"    t_credit_note_header.crn_date," + 
			"    t_credit_note_header.crn_no," + 
			"    m_franchisee.fr_id," + 
			"       SUM(          t_credit_note_details.taxable_amt      ) AS taxable_amt, " + 
			"		  SUM(          t_credit_note_details.total_tax      ) AS crn_total_tax, " + 
			"			   SUM(          t_credit_note_details.grn_gvn_amt      ) AS grand_total, " + 
			"		   SUM(t_credit_note_header.round_off) AS round_off "   + 
			" FROM " + 
			"    m_franchisee," + 
			"    t_credit_note_header,t_credit_note_details,m_sp_cake" + 
			" WHERE t_credit_note_details.crn_id=t_credit_note_header.crn_id AND " + 
			"  t_credit_note_details.item_id=m_sp_cake.sp_id  AND  t_credit_note_details.cat_id=5 and " + 
			"    t_credit_note_header.is_grn=0 AND t_credit_note_header.fr_id = m_franchisee.fr_id AND t_credit_note_header.fr_id IN(:frIdList) AND t_credit_note_header.crn_date BETWEEN :fromDate AND :toDate" + 
			" GROUP BY" + 
			"    month" + 
			" ) t2 ON m1.date_month=t2.date_month AND m1.date_year=t2.date_year" + 
			"" + 
			" LEFT JOIN" + 
			"" + 
			" (" + 
			" SELECT" + 
			"    MONTHNAME(t_credit_note_header.crn_date) AS MONTH," + 
			"    DATE_FORMAT(t_credit_note_header.crn_date, '%b') as date_month," + 
			"    DATE_FORMAT(t_credit_note_header.crn_date, '%Y') as date_year," + 
			"    t_credit_note_header.crn_id," + 
			"    t_credit_note_header.crn_date," + 
			"    t_credit_note_header.crn_no," + 
			"    m_franchisee.fr_id," + 
			/*
			 * "    SUM(" + "        t_credit_note_header.crn_taxable_amt " +
			 * "    ) AS taxable_amt," + "    SUM(" +
			 * "        t_credit_note_header.crn_total_tax " + "    ) AS crn_total_tax," +
			 * "    SUM(" + "        t_credit_note_header.crn_grand_total " +
			 * "    ) AS grand_total," +
			 * "    SUM(t_credit_note_header.round_off) AS round_off " +
			 */
			 "    SUM(          t_credit_note_details.taxable_amt      ) AS taxable_amt," + 
				"    SUM(          t_credit_note_details.total_tax      ) AS crn_total_tax," + 
				"    SUM(          t_credit_note_details.grn_gvn_amt      ) AS grand_total," + 
				"    SUM(t_credit_note_header.round_off) AS round_off  " + 
			" FROM " + 
			"    m_franchisee, " + 
			"    t_credit_note_header,t_credit_note_details,m_sp_cake " + 
			" WHERE  t_credit_note_details.crn_id=t_credit_note_header.crn_id AND  " + 
			"			  t_credit_note_details.item_id=m_sp_cake.sp_id  AND  t_credit_note_details.cat_id=5 and  " + 
			"    t_credit_note_header.is_grn =1 AND t_credit_note_header.fr_id = m_franchisee.fr_id AND t_credit_note_header.fr_id IN(:frIdList) AND t_credit_note_header.crn_date BETWEEN :fromDate AND :toDate" + 
			" GROUP BY " + 
			"    month " + 
			") t3 ON m1.date_month=t3.date_month AND m1.date_year=t3.date_year " + 
			" ", nativeQuery = true)

	List<SalesReportDateMonth> getMonthDataForCatId5(@Param("frIdList") List<String> frIdList,
			@Param("fromDate") String fromDate, @Param("toDate") String toDate
	 );
	
	
	
	/*
	 * @Query(value = "SELECT 0 as crn_id," + "uuid() as crn_no," +
	 * "m1.date_str as crn_date," + "0 as fr_id," +
	 * "m1.date_str as month," + "m1.date_str as bill_date," +
	 * "COALESCE(t1.grand_total,0) grand_total," +
	 * "COALESCE(t1.taxable_amt,0) taxable_amt," +
	 * "COALESCE(t1.total_tax,0) total_tax," + "" +
	 * "COALESCE(t2.grand_total,0) as gvn_grand_total," +
	 * "COALESCE(t2.taxable_amt,0) as gvn_taxable_amt," +
	 * "COALESCE(t2.crn_total_tax,0) as gvn_total_tax," + "" +
	 * "COALESCE(t3.grand_total,0) as grn_grand_total," +
	 * "COALESCE(t3.taxable_amt,0) as grn_taxable_amt," +
	 * "COALESCE(t3.crn_total_tax,0) as grn_total_tax" + "" + "FROM" +
	 * "(" + "" + "select * from " +
	 * "(select adddate('1970-01-01',t4.i*10000 + t3.i*1000 + t2.i*100 + t1.i*10 + t0.i) date_str from"
	 * +
	 * " (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t0,"
	 * +
	 * " (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t1,"
	 * +
	 * " (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t2,"
	 * +
	 * " (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t3,"
	 * +
	 * " (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t4) v"
	 * + "where date_str between :fromDate AND :toDate" + ")m1" +
	 * "LEFT JOIN " + "(" + "    SELECT" +
	 * "    MONTHNAME(t_bill_header.bill_date) AS MONTH," +
	 * "    DATE_FORMAT(t_bill_header.bill_date, '%b') as date_month," +
	 * "    DATE_FORMAT(t_bill_header.bill_date, '%Y') as date_year," +
	 * "    t_bill_header.bill_no," + "    t_bill_header.bill_date," +
	 * "    t_bill_header.invoice_no," + "    t_bill_header.fr_id," +
	 * "    t_bill_header.fr_code," + "    t_bill_header.tax_applicable," +
	 * "    SUM(t_bill_header.taxable_amt) AS taxable_amt," +
	 * "    SUM(t_bill_header.total_tax) AS total_tax," +
	 * "    SUM(t_bill_header.grand_total) AS grand_total," +
	 * "    SUM(t_bill_header.round_off) AS round_off," +
	 * "    SUM(t_bill_header.sgst_sum) AS sgst_sum," +
	 * "    SUM(t_bill_header.cgst_sum) AS cgst_sum," +
	 * "    SUM(t_bill_header.igst_sum) AS igst_sum," +
	 * "    m_franchisee.fr_name," + "    m_franchisee.fr_city," +
	 * "    m_franchisee.fr_gst_no," + "    m_franchisee.is_same_state" +
	 * "FROM" + "    m_franchisee," + "    t_bill_header" + "WHERE"
	 * +
	 * "    t_bill_header.fr_id = m_franchisee.fr_id AND t_bill_header.fr_id IN(:frIdList) AND t_bill_header.bill_date BETWEEN :fromDate AND :toDate AND t_bill_header.del_status = 0"
	 * + "GROUP BY" + "   t_bill_header.bill_date" +
	 * ") t1 ON m1.date_str=t1.bill_date" + "" + "LEFT JOIN" + "" +
	 * "(" + "SELECT" +
	 * "    MONTHNAME(t_credit_note_header.crn_date) AS MONTH," +
	 * "    DATE_FORMAT(t_credit_note_header.crn_date, '%b') as date_month," +
	 * "    DATE_FORMAT(t_credit_note_header.crn_date, '%Y') as date_year," +
	 * "    t_credit_note_header.crn_id," +
	 * "    t_credit_note_header.crn_date," +
	 * "    t_credit_note_header.crn_no," + "    m_franchisee.fr_id," +
	 * "    SUM(" + "        t_credit_note_header.crn_taxable_amt" +
	 * "    ) AS taxable_amt," + "    SUM(" +
	 * "        t_credit_note_header.crn_total_tax" +
	 * "    ) AS crn_total_tax," + "    SUM(" +
	 * "        t_credit_note_header.crn_grand_total" +
	 * "    ) AS grand_total," +
	 * "    SUM(t_credit_note_header.round_off) AS round_off" + "FROM" +
	 * "    m_franchisee," + "    t_credit_note_header" + "WHERE" +
	 * "    t_credit_note_header.is_grn =0 AND t_credit_note_header.fr_id = m_franchisee.fr_id AND t_credit_note_header.fr_id IN(:frIdList) AND t_credit_note_header.crn_date BETWEEN :fromDate AND :toDate"
	 * + "GROUP BY" + "    t_credit_note_header.crn_date" +
	 * ") t2 ON m1.date_str=t2.crn_date" + "" + "LEFT JOIN" + "" +
	 * "(" + "SELECT" +
	 * "    MONTHNAME(t_credit_note_header.crn_date) AS MONTH," +
	 * "    DATE_FORMAT(t_credit_note_header.crn_date, '%b') as date_month," +
	 * "    DATE_FORMAT(t_credit_note_header.crn_date, '%Y') as date_year," +
	 * "    t_credit_note_header.crn_id," +
	 * "    t_credit_note_header.crn_date," +
	 * "    t_credit_note_header.crn_no," + "    m_franchisee.fr_id," +
	 * "    SUM(" + "        t_credit_note_header.crn_taxable_amt" +
	 * "    ) AS taxable_amt," + "    SUM(" +
	 * "        t_credit_note_header.crn_total_tax" +
	 * "    ) AS crn_total_tax," + "    SUM(" +
	 * "        t_credit_note_header.crn_grand_total" +
	 * "    ) AS grand_total," +
	 * "    SUM(t_credit_note_header.round_off) AS round_off" + "FROM" +
	 * "    m_franchisee," + "    t_credit_note_header" + "WHERE" +
	 * "    t_credit_note_header.is_grn =1 AND t_credit_note_header.fr_id = m_franchisee.fr_id AND t_credit_note_header.fr_id IN(:frIdList) AND t_credit_note_header.crn_date BETWEEN :fromDate AND :toDate"
	 * + "GROUP BY" + "    t_credit_note_header.crn_date" +
	 * ") t3 ON m1.date_str=t3.crn_date" + " ", nativeQuery = true)
	 * 
	 * List<SalesReportDateMonth> getDateWiseData(@Param("frIdList") List<String>
	 * frIdList,
	 * 
	 * @Param("fromDate") String fromDate, @Param("toDate") String toDate );
	 */
	
	
	@Query(value = "SELECT " + 
			"        0 as crn_id, " + 
			"        uuid() as crn_no, " + 
			"        m1.date_str as crn_date, " + 
			"        0 as fr_id, " + 
			"        m1.date_str as month, " + 
			"        m1.date_str as bill_date, " + 
			"        COALESCE(t1.grand_total, " + 
			"        0) grand_total," + 
			"        COALESCE(t1.taxable_amt," + 
			"        0) taxable_amt," + 
			"        COALESCE(t1.total_tax," + 
			"        0) total_tax," + 
			"        COALESCE(t2.grand_total," + 
			"        0) as gvn_grand_total," + 
			"        COALESCE(t2.taxable_amt," + 
			"        0) as gvn_taxable_amt," + 
			"        COALESCE(t2.crn_total_tax," + 
			"        0) as gvn_total_tax," + 
			"        COALESCE(t3.grand_total," + 
			"        0) as grn_grand_total," + 
			"        COALESCE(t3.taxable_amt," + 
			"        0) as grn_taxable_amt," + 
			"        COALESCE(t3.crn_total_tax," + 
			"        0) as grn_total_tax    " + 
			"    FROM" + 
			"        (    select" + 
			"            * " + 
			"        from" + 
			"            (select" + 
			"                adddate('1970-01-01'," + 
			"                t4.i*10000 + t3.i*1000 + t2.i*100 + t1.i*10 + t0.i) date_str " + 
			"            from" + 
			"                (select" + 
			"                    0 i " + 
			"                union" + 
			"                select" + 
			"                    1 " + 
			"                union" + 
			"                select" + 
			"                    2 " + 
			"                union" + 
			"                select" + 
			"                    3 " + 
			"                union" + 
			"                select" + 
			"                    4 " + 
			"                union" + 
			"                select" + 
			"                    5 " + 
			"                union" + 
			"                select" + 
			"                    6 " + 
			"                union" + 
			"                select" + 
			"                    7 " + 
			"                union" + 
			"                select" + 
			"                    8 " + 
			"                union" + 
			"                select" + 
			"                    9" + 
			"            ) t0,   (" + 
			"                select" + 
			"                    0 i " + 
			"                union" + 
			"                select" + 
			"                    1 " + 
			"                union" + 
			"                select" + 
			"                    2 " + 
			"                union" + 
			"                select" + 
			"                    3 " + 
			"                union" + 
			"                select" + 
			"                    4 " + 
			"                union" + 
			"                select" + 
			"                    5 " + 
			"                union" + 
			"                select" + 
			"                    6 " + 
			"                union" + 
			"                select" + 
			"                    7 " + 
			"                union" + 
			"                select" + 
			"                    8 " + 
			"                union" + 
			"                select" + 
			"                    9" + 
			"            ) t1,   (" + 
			"                select" + 
			"                    0 i " + 
			"                union" + 
			"                select" + 
			"                    1 " + 
			"                union" + 
			"                select" + 
			"                    2 " + 
			"                union" + 
			"                select" + 
			"                    3 " + 
			"                union" + 
			"                select" + 
			"                    4 " + 
			"                union" + 
			"                select" + 
			"                    5 " + 
			"                union" + 
			"                select" + 
			"                    6  " + 
			"                union" + 
			"                select" + 
			"                    7 " + 
			"                union" + 
			"                select" + 
			"                    8 " + 
			"                union" + 
			"                select" + 
			"                    9" + 
			"            ) t2,   (" + 
			"                select" + 
			"                    0 i " + 
			"                union" + 
			"                select" + 
			"                    1 " + 
			"                union" + 
			"                select" + 
			"                    2 " + 
			"                union" + 
			"                select" + 
			"                    3 " + 
			"                union" + 
			"                select" + 
			"                    4 " + 
			"                union" + 
			"                select" + 
			"                    5 " + 
			"                union" + 
			"                select" + 
			"                    6 " + 
			"                union" + 
			"                select" + 
			"                    7 " + 
			"                union" + 
			"                select" + 
			"                    8 " + 
			"                union" + 
			"                select" + 
			"                    9" + 
			"            ) t3,   (" + 
			"                select" + 
			"                    0 i " + 
			"                union" + 
			"                select" + 
			"                    1 " + 
			"                union" + 
			"                select" + 
			"                    2 " + 
			"                union" + 
			"                select" + 
			"                    3 " + 
			"                union" + 
			"                select" + 
			"                    4 " + 
			"                union" + 
			"                select" + 
			"                    5 " + 
			"                union" + 
			"                select" + 
			"                    6 " + 
			"                union" + 
			"                select" + 
			"                    7 " + 
			"                union" + 
			"                select" + 
			"                    8 " + 
			"                union" + 
			"                select" + 
			"                    9" + 
			"            ) t4" + 
			"    ) v  " + 
			"where" + 
			"    date_str between :fromDate AND :toDate " + 
			")m1  " + 
			"LEFT JOIN" + 
			"(" + 
			"SELECT" + 
			"    MONTHNAME(t_bill_header.bill_date) AS MONTH," + 
			"    DATE_FORMAT(t_bill_header.bill_date," + 
			"    '%b') as date_month," + 
			"    DATE_FORMAT(t_bill_header.bill_date," + 
			"    '%Y') as date_year," + 
			"    t_bill_header.bill_no," + 
			"    t_bill_header.bill_date," + 
			"    t_bill_header.invoice_no," + 
			"    t_bill_header.fr_id," + 
			"    t_bill_header.fr_code," + 
			"    t_bill_header.tax_applicable," + 
			"    SUM(t_bill_detail.taxable_amt) AS taxable_amt," + 
			"    SUM(t_bill_detail.total_tax) AS total_tax," + 
			"    SUM(t_bill_detail.grand_total) AS grand_total," + 
			"    SUM(t_bill_header.round_off) AS round_off," + 
			"    SUM(t_bill_detail.sgst_rs) AS sgst_sum," + 
			"    SUM(t_bill_detail.cgst_rs) AS cgst_sum," + 
			"    SUM(t_bill_detail.igst_rs) AS igst_sum," + 
			"    m_franchisee.fr_name," + 
			"    m_franchisee.fr_city," + 
			"    m_franchisee.fr_gst_no," + 
			"    m_franchisee.is_same_state  " + 
			"FROM" + 
			"    m_franchisee," + 
			"    t_bill_header,t_bill_detail ,m_item " + 
			"WHERE" + 
			"    t_bill_header.fr_id = m_franchisee.fr_id AND t_bill_detail.bill_no=t_bill_header.bill_no" + 
			"    AND t_bill_header.fr_id IN( " + 
			"       :frIdList" + 
			"    ) " + 
			"    AND t_bill_header.bill_date BETWEEN :fromDate AND :toDate " + 
			"    AND t_bill_header.del_status = 0  AND  m_item.id=t_bill_detail.item_id AND m_item.item_grp2 IN(:subCatIdList)" + 
			"GROUP BY" + 
			"    t_bill_header.bill_date  " + 
			") t1 " + 
			"ON m1.date_str=t1.bill_date    " + 
			"LEFT JOIN" + 
			"(" + 
			"SELECT" + 
			"    MONTHNAME(t_credit_note_header.crn_date) AS MONTH," + 
			"    DATE_FORMAT(t_credit_note_header.crn_date," + 
			"    '%b') as date_month," + 
			"    DATE_FORMAT(t_credit_note_header.crn_date," + 
			"    '%Y') as date_year," + 
			"    t_credit_note_header.crn_id," + 
			"    t_credit_note_header.crn_date," + 
			"    t_credit_note_header.crn_no," + 
			"    m_franchisee.fr_id," + 
			"    SUM(          t_credit_note_details.taxable_amt      ) AS taxable_amt," + 
			"    SUM(          t_credit_note_details.total_tax      ) AS crn_total_tax," + 
			"    SUM(          t_credit_note_details.grn_gvn_amt      ) AS grand_total," + 
			"    SUM(t_credit_note_header.round_off) AS round_off  " + 
			"FROM" + 
			"    m_franchisee," + 
			"    t_credit_note_header,t_credit_note_details,m_item  " + 
			"WHERE" + 
			"    t_credit_note_header.is_grn =0 and t_credit_note_details.crn_id=t_credit_note_header.crn_id AND" + 
			"    t_credit_note_details.item_id=m_item.id  AND m_item.item_grp2 IN(:subCatIdList)" + 
			"    AND t_credit_note_header.fr_id = m_franchisee.fr_id " + 
			"    AND t_credit_note_header.fr_id IN(" + 
			"        :frIdList " + 
			"    ) " + 
			"    AND t_credit_note_header.crn_date BETWEEN :fromDate AND :toDate " + 
			"GROUP BY" + 
			"    t_credit_note_header.crn_date  " + 
			") t2 " + 
			"ON m1.date_str=t2.crn_date    " + 
			"LEFT JOIN" + 
			"(" + 
			"SELECT" + 
			"    MONTHNAME(t_credit_note_header.crn_date) AS MONTH," + 
			"    DATE_FORMAT(t_credit_note_header.crn_date," + 
			"    '%b') as date_month," + 
			"    DATE_FORMAT(t_credit_note_header.crn_date," + 
			"    '%Y') as date_year," + 
			"    t_credit_note_header.crn_id," + 
			"    t_credit_note_header.crn_date," + 
			"    t_credit_note_header.crn_no," + 
			"    m_franchisee.fr_id," + 
			"    SUM(          t_credit_note_details.taxable_amt      ) AS taxable_amt," + 
			"    SUM(          t_credit_note_details.total_tax      ) AS crn_total_tax," + 
			"    SUM(          t_credit_note_details.grn_gvn_amt      ) AS grand_total," + 
			"    SUM(t_credit_note_header.round_off) AS round_off  " + 
			"FROM" + 
			"    m_franchisee," + 
			"    t_credit_note_header,t_credit_note_details,m_item  " + 
			"WHERE" + 
			"    t_credit_note_header.is_grn =1 and t_credit_note_details.crn_id=t_credit_note_header.crn_id AND" + 
			"    t_credit_note_details.item_id=m_item.id  AND m_item.item_grp2 IN(:subCatIdList) " + 
			"    AND t_credit_note_header.fr_id = m_franchisee.fr_id  " + 
			"    AND t_credit_note_header.fr_id IN( " + 
			"       :frIdList " + 
			"    )  " + 
			"    AND t_credit_note_header.crn_date BETWEEN :fromDate AND :toDate " + 
			"GROUP BY" + 
			"    t_credit_note_header.crn_date  " + 
			") t3 " + 
			"ON m1.date_str=t3.crn_date" + 
			" ", nativeQuery = true)

	List<SalesReportDateMonth> getDateWiseDataForMItem(@Param("frIdList") List<String> frIdList,
			@Param("fromDate") String fromDate, @Param("toDate") String toDate,
			@Param("subCatIdList") List<String> subCatIdList
	 ); //for M-item
	
	@Query(value = "SELECT " + 
			"        0 as crn_id, " + 
			"        uuid() as crn_no, " + 
			"        m1.date_str as crn_date, " + 
			"        0 as fr_id, " + 
			"        m1.date_str as month, " + 
			"        m1.date_str as bill_date, " + 
			"        COALESCE(t1.grand_total, " + 
			"        0) grand_total," + 
			"        COALESCE(t1.taxable_amt," + 
			"        0) taxable_amt," + 
			"        COALESCE(t1.total_tax," + 
			"        0) total_tax," + 
			"        COALESCE(t2.grand_total," + 
			"        0) as gvn_grand_total," + 
			"        COALESCE(t2.taxable_amt," + 
			"        0) as gvn_taxable_amt," + 
			"        COALESCE(t2.crn_total_tax," + 
			"        0) as gvn_total_tax," + 
			"        COALESCE(t3.grand_total," + 
			"        0) as grn_grand_total," + 
			"        COALESCE(t3.taxable_amt," + 
			"        0) as grn_taxable_amt," + 
			"        COALESCE(t3.crn_total_tax," + 
			"        0) as grn_total_tax    " + 
			"    FROM" + 
			"        (    select" + 
			"            * " + 
			"        from" + 
			"            (select" + 
			"                adddate('1970-01-01'," + 
			"                t4.i*10000 + t3.i*1000 + t2.i*100 + t1.i*10 + t0.i) date_str " + 
			"            from" + 
			"                (select" + 
			"                    0 i " + 
			"                union" + 
			"                select" + 
			"                    1 " + 
			"                union" + 
			"                select" + 
			"                    2 " + 
			"                union" + 
			"                select" + 
			"                    3 " + 
			"                union" + 
			"                select" + 
			"                    4 " + 
			"                union" + 
			"                select" + 
			"                    5 " + 
			"                union" + 
			"                select" + 
			"                    6 " + 
			"                union" + 
			"                select" + 
			"                    7 " + 
			"                union" + 
			"                select" + 
			"                    8 " + 
			"                union" + 
			"                select" + 
			"                    9" + 
			"            ) t0,   (" + 
			"                select" + 
			"                    0 i " + 
			"                union" + 
			"                select" + 
			"                    1 " + 
			"                union" + 
			"                select" + 
			"                    2 " + 
			"                union" + 
			"                select" + 
			"                    3 " + 
			"                union" + 
			"                select" + 
			"                    4 " + 
			"                union" + 
			"                select" + 
			"                    5 " + 
			"                union" + 
			"                select" + 
			"                    6 " + 
			"                union" + 
			"                select" + 
			"                    7 " + 
			"                union" + 
			"                select" + 
			"                    8 " + 
			"                union" + 
			"                select" + 
			"                    9" + 
			"            ) t1,   (" + 
			"                select" + 
			"                    0 i " + 
			"                union" + 
			"                select" + 
			"                    1 " + 
			"                union" + 
			"                select" + 
			"                    2 " + 
			"                union" + 
			"                select" + 
			"                    3 " + 
			"                union" + 
			"                select" + 
			"                    4 " + 
			"                union" + 
			"                select" + 
			"                    5 " + 
			"                union" + 
			"                select" + 
			"                    6  " + 
			"                union" + 
			"                select" + 
			"                    7 " + 
			"                union" + 
			"                select" + 
			"                    8 " + 
			"                union" + 
			"                select" + 
			"                    9" + 
			"            ) t2,   (" + 
			"                select" + 
			"                    0 i " + 
			"                union" + 
			"                select" + 
			"                    1 " + 
			"                union" + 
			"                select" + 
			"                    2 " + 
			"                union" + 
			"                select" + 
			"                    3 " + 
			"                union" + 
			"                select" + 
			"                    4 " + 
			"                union" + 
			"                select" + 
			"                    5 " + 
			"                union" + 
			"                select" + 
			"                    6 " + 
			"                union" + 
			"                select" + 
			"                    7 " + 
			"                union" + 
			"                select" + 
			"                    8 " + 
			"                union" + 
			"                select" + 
			"                    9" + 
			"            ) t3,   (" + 
			"                select" + 
			"                    0 i " + 
			"                union" + 
			"                select" + 
			"                    1 " + 
			"                union" + 
			"                select" + 
			"                    2 " + 
			"                union" + 
			"                select" + 
			"                    3 " + 
			"                union" + 
			"                select" + 
			"                    4 " + 
			"                union" + 
			"                select" + 
			"                    5 " + 
			"                union" + 
			"                select" + 
			"                    6 " + 
			"                union" + 
			"                select" + 
			"                    7 " + 
			"                union" + 
			"                select" + 
			"                    8 " + 
			"                union" + 
			"                select" + 
			"                    9" + 
			"            ) t4" + 
			"    ) v  " + 
			"where" + 
			"    date_str between :fromDate AND :toDate " + 
			")m1  " + 
			"LEFT JOIN" + 
			"(" + 
			" SELECT" + 
			"    MONTHNAME(t_bill_header.bill_date) AS MONTH," + 
			"    DATE_FORMAT(t_bill_header.bill_date," + 
			"    '%b') as date_month," + 
			"    DATE_FORMAT(t_bill_header.bill_date," + 
			"    '%Y') as date_year," + 
			"    t_bill_header.bill_no," + 
			"    t_bill_header.bill_date," + 
			"    t_bill_header.invoice_no," + 
			"    t_bill_header.fr_id," + 
			"    t_bill_header.fr_code," + 
			"    t_bill_header.tax_applicable," + 
			"    SUM(t_bill_detail.taxable_amt) AS taxable_amt," + 
			"    SUM(t_bill_detail.total_tax) AS total_tax," + 
			"    SUM(t_bill_detail.grand_total) AS grand_total," + 
			"    SUM(t_bill_header.round_off) AS round_off," + 
			"    SUM(t_bill_detail.sgst_rs) AS sgst_sum," + 
			"    SUM(t_bill_detail.cgst_rs) AS cgst_sum," + 
			"    SUM(t_bill_detail.igst_rs) AS igst_sum," + 
			"    m_franchisee.fr_name," + 
			"    m_franchisee.fr_city," + 
			"    m_franchisee.fr_gst_no," + 
			"    m_franchisee.is_same_state  " + 
			" FROM" + 
			"    m_franchisee," + 
			"    t_bill_header,t_bill_detail ,m_sp_cake " + 
			" WHERE" + 
			"    t_bill_header.fr_id = m_franchisee.fr_id AND t_bill_detail.bill_no=t_bill_header.bill_no" + 
			"    AND t_bill_header.fr_id IN( " + 
			"       :frIdList" + 
			"    ) " + 
			"    AND t_bill_header.bill_date BETWEEN :fromDate AND :toDate AND t_bill_detail.cat_id=5 " + 
			"    AND t_bill_header.del_status = 0  AND  m_sp_cake.sp_id=t_bill_detail.item_id" + 
			" GROUP BY " + 
			"    t_bill_header.bill_date  " + 
			") t1 " + 
			" ON m1.date_str=t1.bill_date    " + 
			" LEFT JOIN" + 
			" (" + 
			" SELECT" + 
			"    MONTHNAME(t_credit_note_header.crn_date) AS MONTH," + 
			"    DATE_FORMAT(t_credit_note_header.crn_date," + 
			"    '%b') as date_month," + 
			"    DATE_FORMAT(t_credit_note_header.crn_date," + 
			"    '%Y') as date_year," + 
			"    t_credit_note_header.crn_id," + 
			"    t_credit_note_header.crn_date," + 
			"    t_credit_note_header.crn_no," + 
			"    m_franchisee.fr_id," + 
			"    SUM(          t_credit_note_details.taxable_amt      ) AS taxable_amt," + 
			"    SUM(          t_credit_note_details.total_tax      ) AS crn_total_tax," + 
			"    SUM(          t_credit_note_details.grn_gvn_amt      ) AS grand_total," + 
			"    SUM(t_credit_note_header.round_off) AS round_off  " + 
			" FROM " + 
			"    m_franchisee," + 
			"    t_credit_note_header,t_credit_note_details,m_sp_cake  " + 
			" WHERE " + 
			"    t_credit_note_header.is_grn =0 and t_credit_note_details.crn_id=t_credit_note_header.crn_id AND" + 
			"    t_credit_note_details.item_id=m_sp_cake.sp_id AND t_credit_note_details.cat_id=5 " + 
			"    AND t_credit_note_header.fr_id = m_franchisee.fr_id " + 
			"    AND t_credit_note_header.fr_id IN(" + 
			"        :frIdList " + 
			"    ) " + 
			"    AND t_credit_note_header.crn_date BETWEEN :fromDate AND :toDate " + 
			" GROUP BY " + 
			"    t_credit_note_header.crn_date  " + 
			" ) t2 " + 
			" ON m1.date_str=t2.crn_date    " + 
			" LEFT JOIN" + 
			" (" + 
			" SELECT" + 
			"    MONTHNAME(t_credit_note_header.crn_date) AS MONTH," + 
			"    DATE_FORMAT(t_credit_note_header.crn_date," + 
			"    '%b') as date_month," + 
			"    DATE_FORMAT(t_credit_note_header.crn_date," + 
			"    '%Y') as date_year," + 
			"    t_credit_note_header.crn_id," + 
			"    t_credit_note_header.crn_date," + 
			"    t_credit_note_header.crn_no," + 
			"    m_franchisee.fr_id," + 
			"    SUM(          t_credit_note_details.taxable_amt      ) AS taxable_amt," + 
			"    SUM(          t_credit_note_details.total_tax      ) AS crn_total_tax," + 
			"    SUM(          t_credit_note_details.grn_gvn_amt      ) AS grand_total," + 
			"    SUM(t_credit_note_header.round_off) AS round_off  " + 
			" FROM " + 
			"    m_franchisee," + 
			"    t_credit_note_header,t_credit_note_details,m_sp_cake  " + 
			" WHERE " + 
			"    t_credit_note_header.is_grn =1 and t_credit_note_details.crn_id=t_credit_note_header.crn_id AND" + 
			"    t_credit_note_details.item_id=m_sp_cake.sp_id AND t_credit_note_details.cat_id=5 " + 
			"    AND t_credit_note_header.fr_id = m_franchisee.fr_id  " + 
			"    AND t_credit_note_header.fr_id IN( " + 
			"       :frIdList " + 
			"    )  " + 
			"    AND t_credit_note_header.crn_date BETWEEN :fromDate AND :toDate " + 
			" GROUP BY" + 
			"    t_credit_note_header.crn_date  " + 
			" ) t3 " + 
			" ON m1.date_str=t3.crn_date" + 
			" ", nativeQuery = true)

	List<SalesReportDateMonth> getDateWiseDataCatId5(@Param("frIdList") List<String> frIdList,
			@Param("fromDate") String fromDate, @Param("toDate") String toDate
			
	 ); //for M-item
	
	
}
