package org.hercworks.core.data.file.gau;

import java.awt.Dimension;
import java.awt.Point;

/**
 * Observed:
 * 	+ Unlabeled.
 *  + Horizontal
 * 	+ will generate meter ticks from origin to maximum
 * 
 */
public class HMeter extends WidgetBase{

	
	public HMeter() {}
	
	public HMeter(Point origin, Point extent) {
		setOrigin(getOrigin());
		setSize(new Dimension(extent.x - origin.x, extent.y - origin.y));
	}
	
}
