package com.ats.webapi.model.section;

public class SectionMenu {
	
	private int menuId;
	
	private String menuTitle;

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

	@Override
	public String toString() {
		return "SectionMenu [menuId=" + menuId + ", menuTitle=" + menuTitle + "]";
	}
	
	
	

}
