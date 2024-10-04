package org.hercworks.core.data.file.gau;

import java.awt.Point;

public class HButtonBasic extends WidgetBase{

	private Point labelOfs;
	
	public HButtonBasic() {}
	
	public HButtonBasic(Point origin, Point labelOfs) {
		setOrigin(origin);
		this.labelOfs = labelOfs;
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
		sb.append(" [origin=(" + getOrigin().x +","+getOrigin().y + "), labelOfs=("+getLabelOfs().x+","+getLabelOfs().y+")]");		
		
		return sb.toString();
	}

	public Point getLabelOfs() {
		return labelOfs;
	}
	
	public void setLabelOfs(Point labelOfs) {
		this.labelOfs = labelOfs;
	}
}
