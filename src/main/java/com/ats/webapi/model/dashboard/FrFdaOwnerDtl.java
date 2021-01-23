package com.ats.webapi.model.dashboard;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class FrFdaOwnerDtl {
	@Id
	private int frId;
	private String frName;
	private String frCode;
	private Date fdaExpiryDate;
	private Date frAgreementDate;
	private Date ownerBirthDate;
	private int currFdaDateDiff;
	private int currAgrmntDateDiff;
	
	
	public int getFrId() {
		return frId;
	}
	public void setFrId(int frId) {
		this.frId = frId;
	}
	public String getFrName() {
		return frName;
	}
	public void setFrName(String frName) {
		this.frName = frName;
	}
	@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public String getFrCode() {
		return frCode;
	}
	public void setFrCode(String frCode) {
		this.frCode = frCode;
	}
	@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public Date getFdaExpiryDate() {
		return fdaExpiryDate;
	}
	public void setFdaExpiryDate(Date fdaExpiryDate) {
		this.fdaExpiryDate = fdaExpiryDate;
	}
	@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public Date getFrAgreementDate() {
		return frAgreementDate;
	}
	public void setFrAgreementDate(Date frAgreementDate) {
		this.frAgreementDate = frAgreementDate;
	}
	@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public Date getOwnerBirthDate() {
		return ownerBirthDate;
	}
	public void setOwnerBirthDate(Date ownerBirthDate) {
		this.ownerBirthDate = ownerBirthDate;
	}
	public int getCurrFdaDateDiff() {
		return currFdaDateDiff;
	}
	public void setCurrFdaDateDiff(int currFdaDateDiff) {
		this.currFdaDateDiff = currFdaDateDiff;
	}
	public int getCurrAgrmntDateDiff() {
		return currAgrmntDateDiff;
	}
	public void setCurrAgrmntDateDiff(int currAgrmntDateDiff) {
		this.currAgrmntDateDiff = currAgrmntDateDiff;
	}
	@Override
	public String toString() {
		return "FrFdaOwnerDtl [frId=" + frId + ", frName=" + frName + ", frCode=" + frCode + ", fdaExpiryDate="
				+ fdaExpiryDate + ", frAgreementDate=" + frAgreementDate + ", ownerBirthDate=" + ownerBirthDate
				+ ", currFdaDateDiff=" + currFdaDateDiff + ", currAgrmntDateDiff=" + currAgrmntDateDiff + "]";
	}
	
}
