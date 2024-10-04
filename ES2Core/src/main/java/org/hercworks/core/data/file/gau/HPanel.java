package org.hercworks.core.data.file.gau;

import java.awt.Dimension;
import java.awt.Point;
import java.util.Arrays;

public class HPanel extends WidgetBase {

	private Point panelOffset;
	

	public HPanel() {}
	
	public HPanel(Point org, Dimension size, Point ofs) {
		setOrigin(org);
		setSize(size);
		setPanelOffset(ofs);
	}

	public Point getPanelOffset() {
		return panelOffset;
	}

	public void setPanelOffset(Point panelOffset) {
		this.panelOffset = panelOffset;
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
		
		sb.append("[origin=(" + getOrigin().x +","+getOrigin().y + "), size=(" + getSize().width +"," +getSize().height +"), "
							+"panelOffset=(" + panelOffset.x +","+panelOffset.y + ")");
		
		if(getComponents() != null && getComponents().length > 0) {
			sb.append(", \n component={ \n");
			Arrays.asList(getComponents()).forEach((l)->{sb.append(l.toString()+"\n");});
			sb.append("}");
		}
		sb.append("]");
		
		return sb.toString();
	}
	
	
}
