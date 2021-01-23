package com.ats.webapi.model.rejection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
//Akhilesh 2021-01-23
@Entity
@Table(name="t_rejection_detail")
public class RejectionDetail {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int rejDetailId;
	
	private int rejectId;
	
	private int itemId;
	
	private int qty;
	
	private int delStatus;
	
	private int exInt1,exInt2;
	
	private String exVar1,exVar2;

	public int getRejDetailId() {
		return rejDetailId;
	}

	public void setRejDetailId(int rejDetailId) {
		this.rejDetailId = rejDetailId;
	}


	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
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

	public int getRejectId() {
		return rejectId;
	}

	public void setRejectId(int rejectId) {
		this.rejectId = rejectId;
	}

	@Override
	public String toString() {
		return "RejectionDetail [rejDetailId=" + rejDetailId + ", rejectId=" + rejectId + ", itemId=" + itemId
				+ ", qty=" + qty + ", delStatus=" + delStatus + ", exInt1=" + exInt1 + ", exInt2=" + exInt2
				+ ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + "]";
	}

	
	
	
	
	

}
