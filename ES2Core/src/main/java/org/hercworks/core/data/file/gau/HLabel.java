package org.hercworks.core.data.file.gau;

import java.awt.Dimension;
import java.awt.Point;

public class HLabel extends WidgetBase{

	public HLabel() {}
	
	public HLabel(Point origin, Dimension size) {
		setOrigin(origin);
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
		sb.append(" [origin=(" + getOrigin().x+","+getOrigin().y+ "), size=(" + getSize().width+","+getSize().height + ")]");
		return sb.toString();
	}

	
}
