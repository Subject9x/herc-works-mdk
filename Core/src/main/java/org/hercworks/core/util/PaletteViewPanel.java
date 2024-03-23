package org.hercworks.core.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.IndexColorModel;
import java.util.List;

import javax.swing.JComponent;

import org.hercworks.core.data.file.dyn.DynamixBitmap;
import org.hercworks.core.data.file.dyn.DynamixPalette;
import org.hercworks.core.data.struct.ColorBytes;
import org.hercworks.core.io.read.DynFileReader;

import at.favre.lib.bytes.Bytes;

public class PaletteViewPanel extends JComponent{

	private DynamixPalette dpl;
	private DynamixBitmap matchDBM;
	
	private List<Byte> compareImageData;
	
	private BufferedImage paletteBuffer;	//render palette directly to an image to mimic file output-style rendering.
	private int swatW = 64;	//swatch size
	private int swatH = 64;	//swatch size
	
	public PaletteViewPanel(PaletteViewer parent, DynamixBitmap imageData) {
		this.dpl = parent.getPalette();
		this.matchDBM = imageData;
		
		setPreferredSize(parent.getMaximumSize());
		setMaximumSize(parent.getMaximumSize());
		setBackground(Color.black);
		
		initBufferedImage();
	}
	
	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		if(getDPL() != null && !getDPL().getColors().isEmpty()) {

			Font currentFont = g.getFont();
			Font mediumFont = currentFont.deriveFont(currentFont.getSize() * 2F);
			Font smallFont = currentFont.deriveFont(Font.BOLD, currentFont.getSize() * 1.1F);
			
			g2d.setFont(mediumFont);

			g2d.drawImage(paletteBuffer, null, 0, 0);
			int x = 0;
			int y =0;
			for(int shade : getDPL().getColors().keySet()) {
				ColorBytes tone = getDPL().getColors().get(shade);
//				g2d.setColor(getDPL().getColors().get(b).getColor());
//				g2d.fillRect(x, y, 64, 64);	
				
				
				g2d.setColor(Color.PINK);
				
				
				g2d.drawString(Bytes.from(shade).encodeHex().substring(6, 8), x+16, y+24);
				g2d.setFont(smallFont);
				g2d.drawString(Bytes.from(tone.getArray()).encodeHex().substring(0, 6), x+4, y+48);
				
				if(compareImageData != null && !compareImageData.isEmpty()) {
					boolean has = false;
					for(byte imgB : compareImageData) {
						if((int)imgB == shade) {
							has = true;
							break;
						}
					}
					if(has) {
						g2d.setColor(Color.white);
						g2d.fillOval(x+32, y+8, 12, 12);
					}
//					byte adj = (byte)((shade - 22) > 0 ? shade - 22 : 256 + (shade - 22));
//					if(compareImageData.contains(adj)) {
//						g2d.setColor(Color.white);
//						g2d.fillOval(x+32, y+8, 16, 16);
//					}
				}
				
				
				x += 64;
				if(x == 1024) {
					x = 0;
					y += 64;
				}
			}

		}
		g.dispose();
	}
	
	
	private void initBufferedImage() {
		
		
		IndexColorModel colorIndex = new IndexColorModel(8,
				256,
				getDPL().toIntColorMap(),
				0,
				false,
				-1,
				DataBuffer.TYPE_BYTE
			);

//		paletteBuffer = new BufferedImage(1024, 1024, BufferedImage.TYPE_3BYTE_BGR);
		paletteBuffer = new BufferedImage(1024, 1024, BufferedImage.TYPE_BYTE_INDEXED, colorIndex);
				
		int grdW = paletteBuffer.getWidth() / this.swatW - 1;
		int grdH = paletteBuffer.getHeight() / this.swatH - 1;
		
		int row = 0;
		int col = 0;
		for(int shade : getDPL().getColors().keySet()) {
			for(int y=0; y < this.swatH; y++) {
				for(int x=0; x < this.swatW; x++) {
					paletteBuffer.setRGB( 
								x + (col * swatW), 
								y + (row * swatH),
								getDPL().getColors().get(shade).getIntRGB());
				}
			}
			if(col < grdW) {
				col++;
			}
			else {
				col = 0;
				if(row < grdH) {
					row++;	
				}
				
			}
		}
		
		List<Byte> unique = DynFileReader.getDBMUniqueColors(matchDBM);
		compareImageData = DynFileReader.matchUniqueColorToPalette(unique, getDPL());

	}
	
	
	public DynamixPalette getDPL() {
		return this.dpl;
	}
}
