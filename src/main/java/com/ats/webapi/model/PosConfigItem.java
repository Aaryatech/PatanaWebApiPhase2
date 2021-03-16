package com.ats.webapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="pos_config_item")
public class PosConfigItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@Column(name="config_name")
	private String configName;
	
	@Column(name="item_ids")
	private String itemIds;
	
	@Column(name="ext_int1")
	private int extInt1;
	
	@Column(name="ext_int2")
	private int extInt2;
		
	@Column(name="ext_var1")
	private String extVar1;
	
	@Column(name="ext_var2")
	private String extVar2;
	
	@Column(name="del_status")
	private int delStatus;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getConfigName() {
		return configName;
	}

	public void setConfigName(String configName) {
		this.configName = configName;
	}

	public String getItemIds() {
		return itemIds;
	}

	public void setItemIds(String itemIds) {
		this.itemIds = itemIds;
	}

	public int getExtInt1() {
		return extInt1;
	}

	public void setExtInt1(int extInt1) {
		this.extInt1 = extInt1;
	}

	public int getExtInt2() {
		return extInt2;
	}

	public void setExtInt2(int extInt2) {
		this.extInt2 = extInt2;
	}

	public String getExtVar1() {
		return extVar1;
	}

	public void setExtVar1(String extVar1) {
		this.extVar1 = extVar1;
	}

	public String getExtVar2() {
		return extVar2;
	}

	public void setExtVar2(String extVar2) {
		this.extVar2 = extVar2;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	@Override
	public String toString() {
		return "PosConfigItem [id=" + id + ", configName=" + configName + ", itemIds=" + itemIds + ", extInt1="
				+ extInt1 + ", extInt2=" + extInt2 + ", extVar1=" + extVar1 + ", extVar2=" + extVar2 + ", delStatus="
				+ delStatus + "]";
	}
	
	
	
}
