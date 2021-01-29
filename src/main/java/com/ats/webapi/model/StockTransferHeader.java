package com.ats.webapi.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="t_stock_transfer_header")
public class StockTransferHeader {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int transferId;
	
	private int fromFrId;
	
	private int toFrId;
	
	private String makerDate;
	
	private String makerDatetime;
	
	private float taxableAmt;
	
	private float tax;
	
	private int rateTotal;
	
	private int status;
	
	private int delStatus;
	
	private int additionalCharges;
	
	private float grandTotal;
	
	private int exInt1,exInt2;
	
	private String exVar1,exVar2;
	
	@Transient
	List<StockTransferDetail> StockTransferdetailList;
	

	public int getTransferId() {
		return transferId;
	}

	public void setTransferId(int transferId) {
		this.transferId = transferId;
	}

	public int getFromFrId() {
		return fromFrId;
	}

	public void setFromFrId(int fromFrId) {
		this.fromFrId = fromFrId;
	}

	public int getToFrId() {
		return toFrId;
	}

	public void setToFrId(int toFrId) {
		this.toFrId = toFrId;
	}

	public String getMakerDate() {
		return makerDate;
	}

	public void setMakerDate(String makerDate) {
		this.makerDate = makerDate;
	}


	public float getTaxableAmt() {
		return taxableAmt;
	}

	public void setTaxableAmt(float taxableAmt) {
		this.taxableAmt = taxableAmt;
	}

	public float getTax() {
		return tax;
	}

	public void setTax(float tax) {
		this.tax = tax;
	}

	public int getRateTotal() {
		return rateTotal;
	}

	public void setRateTotal(int rateTotal) {
		this.rateTotal = rateTotal;
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

	public int getAdditionalCharges() {
		return additionalCharges;
	}

	public void setAdditionalCharges(int additionalCharges) {
		this.additionalCharges = additionalCharges;
	}

	public float getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(float grandTotal) {
		this.grandTotal = grandTotal;
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

	public List<StockTransferDetail> getStockTransferdetailList() {
		return StockTransferdetailList;
	}

	public void setStockTransferdetailList(List<StockTransferDetail> stockTransferdetailList) {
		StockTransferdetailList = stockTransferdetailList;
	}

	public String getMakerDatetime() {
		return makerDatetime;
	}

	public void setMakerDatetime(String makerDatetime) {
		this.makerDatetime = makerDatetime;
	}

	@Override
	public String toString() {
		return "StockTransferHeader [transferId=" + transferId + ", fromFrId=" + fromFrId + ", toFrId=" + toFrId
				+ ", makerDate=" + makerDate + ", makerDatetime=" + makerDatetime + ", taxableAmt=" + taxableAmt
				+ ", tax=" + tax + ", rateTotal=" + rateTotal + ", status=" + status + ", delStatus=" + delStatus
				+ ", additionalCharges=" + additionalCharges + ", grandTotal=" + grandTotal + ", exInt1=" + exInt1
				+ ", exInt2=" + exInt2 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + ", StockTransferdetailList="
				+ StockTransferdetailList + "]";
	}


	
	
	
	
	
	
	
	

}
