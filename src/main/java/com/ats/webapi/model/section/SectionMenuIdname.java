package com.ats.webapi.model.section;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class SectionMenuIdname {
	@Id
	private String id;
	
	

	private int sectionId;
	
	private String sectionName;
	
	
	private int menuId;
	
	private String menuTitle;

	public int getSectionId() {
		return sectionId;
	}

	public void setSectionId(int sectionId) {
		this.sectionId = sectionId;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public String getMenuTitle() {
		return menuTitle;
	}

	public void setMenuTitle(String menuTitle) {
		this.menuTitle = menuTitle;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "SectionMenuIdname [id=" + id + ", sectionId=" + sectionId + ", sectionName=" + sectionName + ", menuId="
				+ menuId + ", menuTitle=" + menuTitle + "]";
	}



	

	
	
	

}
