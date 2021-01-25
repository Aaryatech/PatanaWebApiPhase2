package com.ats.webapi.model.bill;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_pendingbill_detail")
public class PendingBillDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	@Column(name="bill_detail_id")
	private int billDetailId;
	
	@Column(name="bill_id")
	private int billId;
	
	@Column(name="taxable_amt")
	private float taxableAmt;
	
	@Column(name="tax_amt")
	private float taxAmt;
	
	@Column(name="total_amt")
	private float totalAmt;
	
	@Column(name="invoice_no_crn_no")
	private String invoiceNoCrnNo;
		
	@Column(name="bil_no_crn_id")
	private int bilNoCrnId;
	
	@Column(name="type")
	private int type;
	
	@Column(name="del_status")
	private int delStatus;
	
	@Column(name="insert_datetime")
	private String insertDatetime;
	
	@Column(name="ex_int1")
	private int exInt1;
	
	@Column(name="ex_int2")
	private int exInt2;
	
	@Column(name="ex_var1")
	private String exVar1;
	
	@Column(name="ex_var2")
	private String exVar2;

	public int getBillDetailId() {
		return billDetailId;
	}

	public void setBillDetailId(int billDetailId) {
		this.billDetailId = billDetailId;
	}

	public int getBillId() {
		return billId;
	}

	public void setBillId(int billId) {
		this.billId = billId;
	}

	public float getTaxableAmt() {
		return taxableAmt;
	}

	public void setTaxableAmt(float taxableAmt) {
		this.taxableAmt = taxableAmt;
	}

	public float getTaxAmt() {
		return taxAmt;
	}

	public void setTaxAmt(float taxAmt) {
		this.taxAmt = taxAmt;
	}

	public float getTotalAmt() {
		return totalAmt;
	}
	
	public String getInvoiceNoCrnNo() {
		return invoiceNoCrnNo;
	}

	public void setInvoiceNoCrnNo(String invoiceNoCrnNo) {
		this.invoiceNoCrnNo = invoiceNoCrnNo;
	}

	public int getBilNoCrnId() {
		return bilNoCrnId;
	}

	public void setBilNoCrnId(int bilNoCrnId) {
		this.bilNoCrnId = bilNoCrnId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setTotalAmt(float totalAmt) {
		this.totalAmt = totalAmt;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	public String getInsertDatetime() {
		return insertDatetime;
	}

	public void setInsertDatetime(String insertDatetime) {
		this.insertDatetime = insertDatetime;
	}

	public int getExInt1() {
		return exInt1;
	}

	public void setExInt1(int exInt1) {
		this.exInt1 = exInt1;
	}

	public int getExInt2() {
		return exInt2;
	}

	public void setExInt2(int exInt2) {
		this.exInt2 = exInt2;
	}

	public String getExVar1() {
		return exVar1;
	}

	public void setExVar1(String exVar1) {
		this.exVar1 = exVar1;
	}

	public String getExVar2() {
		return exVar2;
	}

	public void setExVar2(String exVar2) {
		this.exVar2 = exVar2;
	}

	@Override
	public String toString() {
		return "PendingBillDetail [billDetailId=" + billDetailId + ", billId=" + billId + ", taxableAmt=" + taxableAmt
				+ ", taxAmt=" + taxAmt + ", totalAmt=" + totalAmt + ", invoiceNoCrnNo=" + invoiceNoCrnNo
				+ ", bilNoCrnId=" + bilNoCrnId + ", type=" + type + ", delStatus=" + delStatus + ", insertDatetime="
				+ insertDatetime + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exVar1=" + exVar1 + ", exVar2="
				+ exVar2 + "]";
	}

}
