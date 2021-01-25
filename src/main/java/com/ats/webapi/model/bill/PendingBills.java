package com.ats.webapi.model.bill;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class PendingBills {
	@Id
	private int billNo;
	private String invoiceNo;
	private Date billDate;
	private int frId;
	private float grandTotal;
	private float taxableAmt;
	private float totalTax;
	private int pendingBill;
	
	public int getBillNo() {
		return billNo;
	}
	public void setBillNo(int billNo) {
		this.billNo = billNo;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public Date getBillDate() {
		return billDate;
	}
	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}
	public int getFrId() {
		return frId;
	}
	public void setFrId(int frId) {
		this.frId = frId;
	}
	public float getGrandTotal() {
		return grandTotal;
	}
	public void setGrandTotal(float grandTotal) {
		this.grandTotal = grandTotal;
	}
	public float getTaxableAmt() {
		return taxableAmt;
	}
	public void setTaxableAmt(float taxableAmt) {
		this.taxableAmt = taxableAmt;
	}
	public float getTotalTax() {
		return totalTax;
	}
	public void setTotalTax(float totalTax) {
		this.totalTax = totalTax;
	}
	public int getPendingBill() {
		return pendingBill;
	}
	public void setPendingBill(int pendingBill) {
		this.pendingBill = pendingBill;
	}
	@Override
	public String toString() {
		return "PendingBills [billNo=" + billNo + ", invoiceNo=" + invoiceNo + ", billDate=" + billDate + ", frId="
				+ frId + ", grandTotal=" + grandTotal + ", taxableAmt=" + taxableAmt + ", totalTax=" + totalTax
				+ ", pendingBill=" + pendingBill + "]";
	}
	
}
