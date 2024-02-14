package com.mech.works.util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

import com.mech.works.data.file.dyn.DynamixPalette;

public class PaletteViewPanel extends JComponent{

	private DynamixPalette dpl;
	
	public PaletteViewPanel(PaletteViewer parent) {
		this.dpl = parent.getPalette();
		setPreferredSize(parent.getMaximumSize());
		setMaximumSize(parent.getMaximumSize());
		setBackground(Color.black);
	}
	
	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		if(getDPL() != null && !getDPL().getColors().isEmpty()) {
			int x = 0;
			int y =0;
			for(Color c : getDPL().getColors()) {
				
				g2d.setColor(c);
				g2d.fillRect(x, y, 64, 64);			
				
				x += 64;
				if(x == 1024) {
					x = 0;
					y += 64;
				}
			}
		}
		g.dispose();
	}
	
	public DynamixPalette getDPL() {
		return this.dpl;
	}
}
