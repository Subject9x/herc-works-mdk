package org.hercworks.core.data.file.gau;

import java.awt.Dimension;
import java.awt.Point;

public class HWeaponPanelItem extends WidgetBase{
	
	public HWeaponPanelItem() {}
	
	public HWeaponPanelItem(Point org, Dimension size) {
		setOrigin(org);
		setSize(size);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		if(gethWidgetId() != null) {
			sb.append(gethWidgetId().name());			
		}
		else {
			sb.append(getClass().getSimpleName());
		}
		sb.append(" ");
		sb.append("[origin=(" + getOrigin().x +","+getOrigin().y + "), size=(" + getSize().width +"," +getSize().height + ")]");
		
		return sb.toString();
	}
	
}
