package com.ats.webapi.model.grngvnreport;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "m_grn_gvn_status")
public class GrnGvnStatus {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	@Column(name="grn_gvn_status_id")
	private int grnGvnStatuId;
	
	@Column(name="grn_gvn_status")
	private String grnGvnStatus;
	
	@Column(name="ex_int1")
	private int exInt1;
	
	@Column(name="ex_int2")
	private int exInt2;
	
	@Column(name="ex_var1")
	private String exVar1;
	
	@Column(name="ex_var2")
	private String exVar2;

	public int getGrnGvnStatuId() {
		return grnGvnStatuId;
	}

	public void setGrnGvnStatuId(int grnGvnStatuId) {
		this.grnGvnStatuId = grnGvnStatuId;
	}

	public String getGrnGvnStatus() {
		return grnGvnStatus;
	}

	public void setGrnGvnStatus(String grnGvnStatus) {
		this.grnGvnStatus = grnGvnStatus;
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
		return "GrnGvnStatus [grnGvnStatuId=" + grnGvnStatuId + ", grnGvnStatus=" + grnGvnStatus + ", exInt1=" + exInt1
				+ ", exInt2=" + exInt2 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + "]";
	}
	
}
