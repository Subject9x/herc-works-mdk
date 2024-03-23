package org.hercworks.core.data.file.dyn;

import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;

import org.hercworks.core.data.struct.ColorBytes;
import org.hercworks.voln.DataFile;

import at.favre.lib.bytes.Bytes;

/**
 * FILE
 * 		VOL file, .DPL
 * 		contains palette colors per-game object, some real optimization right there.
 */
public class DynamixPalette extends DataFile {

	public static Bytes header = Bytes.from("0F002800", StandardCharsets.UTF_8);
	
	private int colorCount;
	
	private int scalar;	//no mapping to binary data, used to account for incredibly dark colors in most palettes.
						//game binary probably scales values up too and this was a byte-saving measure.
	
	private int paletteSizeByte;
	private Bytes rawIndexBytes;
	private LinkedHashMap<Integer, ColorBytes> colors;
	
	public DynamixPalette() {
		super();
		scalar = 1;
		colors = new LinkedHashMap<Integer, ColorBytes>();
	}

	public ColorBytes colorAt(int idx) {
		return getColors().get(idx);
	}
	
	/**
	 * 
	 * @return
	 */
	public int[] toIntColorMap() {
		
		int[] cmap = new int[256];
		int i = 255;
		for(int shade : getColors().keySet()) {
			cmap[shade] =  getColors().get(shade).getColor().getRGB();
//			i--;
		}
//		int grayIncr = 256/(256-i);
//		
//		int gray = grayIncr*8;
//		for(; i< 256; i++) {
//            cmap[i] = (gray<<16)|(gray<<8)|gray;
//            gray += grayIncr;
//		}
		
		return cmap;
	}
	
	public byte[] toByteArray() {
		Bytes index = Bytes.allocate(0);
		
		for(int shade : getColors().keySet()) {
			index = index.append(Bytes.from(getColors().get(shade).getArray(), 0, 3).array());
		}
		return index.array();
	}
	
	public int getColorCount() {
		return colorCount;
	}

	public void setColorCount(int colorCount) {
		this.colorCount = colorCount;
	}

	public LinkedHashMap<Integer, ColorBytes> getColors() {
		return colors;
	}

	public void setColors(LinkedHashMap<Integer, ColorBytes> colors) {
		this.colors = colors;
	}

	public Bytes getRawIndexBytes() {
		return rawIndexBytes;
	}

	public void setRawIndexBytes(Bytes rawIndexBytes) {
		this.rawIndexBytes = rawIndexBytes;
	}

	public int getPaletteSizeByte() {
		return paletteSizeByte;
	}

	public void setPaletteSizeByte(int paletteSizeByte) {
		this.paletteSizeByte = paletteSizeByte;
	}

	public int getScalar() {
		return scalar;
	}

	public void setScalar(int scalar) {
		this.scalar = scalar;
	}
}
