package com.mech.works.util;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import com.mech.works.data.file.dyn.DynamixPalette;

public class PaletteViewer extends JFrame{

	private DynamixPalette dpl;
	
	public PaletteViewer(DynamixPalette dpl) {
		super();
		this.dpl = dpl;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(1680, 1050));
		setMaximumSize(getPreferredSize());
		setAlwaysOnTop(true);
		setLayout(new BorderLayout());
	}
	
    
    public DynamixPalette getPalette() {
    	return this.dpl;
    }
}
