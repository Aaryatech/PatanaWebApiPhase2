package com.ats.webapi.model.bill;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="t_pendingbill_header")
public class PendingBillHeader {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	@Column(name="bill_id")
	private int billId;
	
	@Column(name="fr_id")
	private int frId;
	
	@Column(name="process_date")
	private Date processDate;
	
	@Column(name="taxable_amt")
	private float taxableAmt;
	
	@Column(name="tax_amt")
	private float taxAmt;
	
	@Column(name="grand_total")
	private float grandTotal;
	
	@Column(name="remark")
	private String remark;
	
	@Column(name="payment_mode")
	private int paymentMode;
	
	@Column(name="status")
	private int status;
	
	@Column(name="del_status")
	private int delStatus;
	
	@Column(name="ex_int1")
	private int exInt1;
	
	@Column(name="ex_int2")
	private int exInt2;
	
	@Column(name="ex_var1")
	private String exVar1;
	
	@Column(name="ex_var2")
	private String exVar2;
	
	@Column(name="insert_datetime")
	private String insertDatetime;
	
	@Transient List<PendingBillDetail> billDetailList;

	public int getBillId() {
		return billId;
	}

	public void setBillId(int billId) {
		this.billId = billId;
	}

	public int getFrId() {
		return frId;
	}

	public void setFrId(int frId) {
		this.frId = frId;
	}
	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public Date getProcessDate() {
		return processDate;
	}
	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "yyyy-MM-dd")
	public void setProcessDate(Date processDate) {
		this.processDate = processDate;
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

	public float getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(float grandTotal) {
		this.grandTotal = grandTotal;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(int paymentMode) {
		this.paymentMode = paymentMode;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
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

	public List<PendingBillDetail> getBillDetailList() {
		return billDetailList;
	}

	public void setBillDetailList(List<PendingBillDetail> billDetailList) {
		this.billDetailList = billDetailList;
	}

	public String getInsertDatetime() {
		return insertDatetime;
	}

	public void setInsertDatetime(String insertDatetime) {
		this.insertDatetime = insertDatetime;
	}

	@Override
	public String toString() {
		return "PendingBillHeader [billId=" + billId + ", frId=" + frId + ", processDate=" + processDate
				+ ", taxableAmt=" + taxableAmt + ", taxAmt=" + taxAmt + ", grandTotal=" + grandTotal + ", remark="
				+ remark + ", paymentMode=" + paymentMode + ", status=" + status + ", delStatus=" + delStatus
				+ ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2
				+ ", insertDatetime=" + insertDatetime + ", billDetailList=" + billDetailList + "]";
	}
	
}
