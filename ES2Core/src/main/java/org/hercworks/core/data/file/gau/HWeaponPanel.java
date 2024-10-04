package org.hercworks.core.data.file.gau;

import java.awt.Dimension;
import java.awt.Point;
import java.util.Arrays;

public class HWeaponPanel extends WidgetBase {

	private int activeTotal;
	
	public HWeaponPanel() {}
	
	public HWeaponPanel(Point origin, Dimension size) {
		setOrigin(origin);
		setSize(size);
		setComponents(new WidgetBase[10]);
	}
	
	public HWeaponPanel(Point origin, Dimension size, int activeTotal) {
		setOrigin(origin);
		setSize(size);
		this.activeTotal = activeTotal;
		setComponents(new WidgetBase[10]);
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
		
		sb.append("[activeTotal="+getActiveTotal()+"");
		
		if(getComponents() != null && getComponents().length > 0) {
			sb.append(", \n component={ \n");
			Arrays.asList(getComponents()).forEach((l)->{sb.append(l.toString()+"\n");});
			sb.append("}");
		}
		sb.append("]");
		
		return sb.toString();
	}
	
	public int getActiveTotal() {
		return activeTotal;
	}

	public void setActiveTotal(int activeTotal) {
		this.activeTotal = activeTotal;
	}
}
