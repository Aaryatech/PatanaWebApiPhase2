package com.ats.webapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_stock_transfer_detail")
public class StockTransferDetail {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int transferDetailId;
	
	private int transferId;
	
	private int itemId;
	
	private int qty;
	
	private float itemMrp;
	
	private float itemRate;
	
	private float itemTax;
	
	private float cgst;
	
	private float igst;
	
	private float sgst;
	
	private float cgstAmt;
	
	private float igstAmt;
	
	private float sgstAmt;
	
	
	private int discount;
	
	private int extraCharges;
	
	private float grandTotal;
	
	private float taxAmt;
	
	private int exInt1,exInt2;
	
	private float exFloat1,exFloat2;
	
	private String exVar1,exVar2;

	public int getTransferDetailId() {
		return transferDetailId;
	}

	public void setTransferDetailId(int transferDetailId) {
		this.transferDetailId = transferDetailId;
	}

	public int getTransferId() {
		return transferId;
	}

	public void setTransferId(int transferId) {
		this.transferId = transferId;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}



	public float getItemRate() {
		return itemRate;
	}

	public void setItemRate(float itemRate) {
		this.itemRate = itemRate;
	}

	public float getItemTax() {
		return itemTax;
	}

	public void setItemTax(float itemTax) {
		this.itemTax = itemTax;
	}

	public float getCgst() {
		return cgst;
	}

	public void setCgst(float cgst) {
		this.cgst = cgst;
	}

	public float getIgst() {
		return igst;
	}

	public void setIgst(float igst) {
		this.igst = igst;
	}

	public float getSgst() {
		return sgst;
	}

	public void setSgst(float sgst) {
		this.sgst = sgst;
	}

	public float getCgstAmt() {
		return cgstAmt;
	}

	public void setCgstAmt(float cgstAmt) {
		this.cgstAmt = cgstAmt;
	}

	public float getIgstAmt() {
		return igstAmt;
	}

	public void setIgstAmt(float igstAmt) {
		this.igstAmt = igstAmt;
	}

	public float getSgstAmt() {
		return sgstAmt;
	}

	public void setSgstAmt(float sgstAmt) {
		this.sgstAmt = sgstAmt;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public int getExtraCharges() {
		return extraCharges;
	}

	public void setExtraCharges(int extraCharges) {
		this.extraCharges = extraCharges;
	}

	public float getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(float grandTotal) {
		this.grandTotal = grandTotal;
	}

	public float getTaxAmt() {
		return taxAmt;
	}

	public void setTaxAmt(float taxAmt) {
		this.taxAmt = taxAmt;
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

	public float getExFloat1() {
		return exFloat1;
	}

	public void setExFloat1(float exFloat1) {
		this.exFloat1 = exFloat1;
	}

	public float getExFloat2() {
		return exFloat2;
	}

	public void setExFloat2(float exFloat2) {
		this.exFloat2 = exFloat2;
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

	public float getItemMrp() {
		return itemMrp;
	}

	public void setItemMrp(float itemMrp) {
		this.itemMrp = itemMrp;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	@Override
	public String toString() {
		return "StockTransferDetail [transferDetailId=" + transferDetailId + ", transferId=" + transferId + ", itemId="
				+ itemId + ", qty=" + qty + ", itemMrp=" + itemMrp + ", itemRate=" + itemRate + ", itemTax=" + itemTax
				+ ", cgst=" + cgst + ", igst=" + igst + ", sgst=" + sgst + ", cgstAmt=" + cgstAmt + ", igstAmt="
				+ igstAmt + ", sgstAmt=" + sgstAmt + ", discount=" + discount + ", extraCharges=" + extraCharges
				+ ", grandTotal=" + grandTotal + ", taxAmt=" + taxAmt + ", exInt1=" + exInt1 + ", exInt2=" + exInt2
				+ ", exFloat1=" + exFloat1 + ", exFloat2=" + exFloat2 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2
				+ "]";
	}



	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
