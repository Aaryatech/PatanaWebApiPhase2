package com.ats.webapi.model.section;

import java.util.List;

public class SectionWithMenuList {
	
	private int sectionId;
	
	private String sectionName;
	
	
	private List<SectionMenu> menu=null;


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


	public List<SectionMenu> getMenu() {
		return menu;
	}


	public void setMenu(List<SectionMenu> menu) {
		this.menu = menu;
	}


	@Override
	public String toString() {
		return "SectionWithMenu [sectionId=" + sectionId + ", sectionName=" + sectionName + ", menu=" + menu + "]";
	}
	
	
	
	

}
