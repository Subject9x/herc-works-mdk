package org.hercworks.core.data.file.gau;

import java.awt.Dimension;
import java.awt.Point;

public abstract class WidgetBase {

	private HWidgetId hWidgetId;
	private Point origin;
	private Dimension size;
	private WidgetBase[] components;

	public Point getOrigin() {
		return origin;
	}

	public void setOrigin(Point origin) {
		this.origin = origin;
	}

	public Dimension getSize() {
		return size;
	}

	public void setSize(Dimension size) {
		this.size = size;
	}

	public WidgetBase[] getComponents() {
		return components;
	}

	public void setComponents(WidgetBase[] components) {
		this.components = components;
	}

	public HWidgetId gethWidgetId() {
		return hWidgetId;
	}

	public void sethWidgetId(HWidgetId hWidgetId) {
		this.hWidgetId = hWidgetId;
	}
}
