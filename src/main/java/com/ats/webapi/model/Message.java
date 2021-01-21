package com.ats.webapi.model;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "t_message")

public class Message implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="msg_id")
	private int msgId;
	
	//@JsonFormat(pattern = "dd-MM-yyyy")
	//
	//@Temporal(TemporalType.DATE)
	//@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	@Column(name="msg_frdt")
	private Date msgFrdt;
	
	//@JsonFormat(pattern = "dd-MM-yyyy")
	//@Temporal(TemporalType.DATE)
	
	@Column(name="msg_todt")
	private Date msgTodt;
	
	@Column(name="msg_image")
	private String msgImage;
	@Column(name="msg_header")
	private String msgHeader;
	@Column(name="msg_details")
	private String msgDetails;
	@Column(name="is_active")
	private int isActive;
	@Column(name="del_status")
	private int delStatus;
	
	@Column(name="maker_datetime")
	private String makerDatetime;
	
	@Column(name="aplicable_fr")
	private String applicableFr;
	
	@Column(name="ex_int1")
	private int exInt1;
	
	@Column(name="ex_int2")
	private int exInt2;
	
	@Column(name="ex_var1")
	private String exVar1;
	
	@Column(name="ex_var2")
	private String exVar2;
	
	
	
	
	
	
	public String getMakerDatetime() {
		return makerDatetime;
	}

	public void setMakerDatetime(String makerDatetime) {
		this.makerDatetime = makerDatetime;
	}

	public String getApplicableFr() {
		return applicableFr;
	}

	public void setApplicableFr(String applicableFr) {
		this.applicableFr = applicableFr;
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

	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public Date getMsgFrdt() {
		return msgFrdt;
	}

	public void setMsgFrdt(Date msgFrdt) {
		this.msgFrdt = msgFrdt;
	}
	
	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public Date getMsgTodt() {
		return msgTodt;
	}

	public void setMsgTodt(Date msgTodt) {
		this.msgTodt = msgTodt;
	}

	public String getMsgImage() {
		return msgImage;
	}

	public void setMsgImage(String msgImage) {
		this.msgImage = msgImage;
	}

	public String getMsgHeader() {
		return msgHeader;
	}

	public void setMsgHeader(String msgHeader) {
		this.msgHeader = msgHeader;
	}

	public String getMsgDetails() {
		return msgDetails;
	}

	public void setMsgDetails(String msgDetails) {
		this.msgDetails = msgDetails;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	public int getMsgId() {
		return msgId;
	}

	public void setMsgId(int msgId) {
		this.msgId = msgId;
	}

	@Override
	public String toString() {
		return "Message [msgId=" + msgId + ", msgFrdt=" + msgFrdt + ", msgTodt=" + msgTodt + ", msgImage=" + msgImage
				+ ", msgHeader=" + msgHeader + ", msgDetails=" + msgDetails + ", isActive=" + isActive + ", delStatus="
				+ delStatus + ", makerDatetime=" + makerDatetime + ", applicableFr=" + applicableFr + ", exInt1="
				+ exInt1 + ", exInt2=" + exInt2 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + "]";
	}



	
	
	
}
