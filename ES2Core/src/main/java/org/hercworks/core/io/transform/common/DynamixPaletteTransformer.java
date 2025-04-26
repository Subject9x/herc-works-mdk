package org.hercworks.core.io.transform.common;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteOrder;

import org.hercworks.core.data.file.dyn.DynamixPalette;
import org.hercworks.core.data.struct.ColorBytes;
import org.hercworks.core.io.transform.ThreeSpaceByteTransformer;
import org.hercworks.voln.DataFile;
import org.hercworks.voln.FileType;

import at.favre.lib.bytes.Bytes;

/**
 * Transforms byete[] data to and from {@linkplain DynamixPalette} game files.
 * 
 */
public class DynamixPaletteTransformer extends ThreeSpaceByteTransformer {

	private int colorScalar = 4;
	
	public DynamixPaletteTransformer() {}
	
	public DynamixPaletteTransformer(int scalar) {
		this.colorScalar = scalar;
	}
	
	@Override
	public DataFile bytesToObject(byte[] inputArray) throws ClassCastException {
		if(inputArray.length <= 0) {
			//TODO - DynamixPaletteTransformer - Log null 
			return null;
		}
		setBytes(inputArray);
		
		resetIndex();
		
		DynamixPalette dpl = new DynamixPalette();
		dpl.setRawBytes(Bytes.from(inputArray).array());	//clone array
		dpl.setExt(FileType.DPL);
		dpl.setDir(FileType.DPL);
		
		index += 4;	//skip magic header bytes.
		
		dpl.setPaletteSizeByte(indexIntLE());
		dpl.setColorCount(indexIntLE());
		
		int colorIdx = 0;
		
		dpl.setScalar(this.colorScalar);
		//DO NOT CHANGE - original "mostly working" 4-byte RGBA value read
		for(int clr = 0; clr < dpl.getColorCount(); clr++) {
			
			dpl.getColors().put(colorIdx, toColorBytes(indexSegmentLE(4), dpl.getScalar(), colorIdx));
			
			colorIdx++;
		}
		return dpl;
	}
	
	@Override
	public byte[] objectToBytes(DataFile source) throws ClassCastException, IOException {
		
		if(source == null) {
			return null;
		}
		
		DynamixPalette dpl = (DynamixPalette)source;
		ByteArrayOutputStream objectBytes = new ByteArrayOutputStream();
		
		objectBytes.write(DynamixPalette.header.array());
		objectBytes.write(writeInt(dpl.getPaletteSizeByte()));
		objectBytes.write(writeInt(dpl.getColorCount()));
		
		for(ColorBytes color : dpl.getColors().values()) {
			objectBytes.write(toDynamixColor(color, dpl.getScalar()));
		}
		
		byte[] data = objectBytes.toByteArray();
		
		return data;
	}

	/***
	 * Convert 4-byte array segment of a .DPL file to {@linkplain ColorBytes} custom color wrapper.
	 * @param dynamixColor
	 * @param scalar
	 * @param idx
	 * @return {@linkplain ColorBytes}
	 */
	private ColorBytes toColorBytes(byte[] dynamixColor, int scalar, int idx) {

		byte[] bytes = new byte[4];
		
		int ir, ig, ib, ia;
		ir = Byte.toUnsignedInt(dynamixColor[0]);
		ig = Byte.toUnsignedInt(dynamixColor[1]);
		ib =  Byte.toUnsignedInt(dynamixColor[2]);
		ia = dynamixColor[3];
		
		ir = (ir * scalar) > 255 ? 255 : ir * scalar;
		ig = (ig * scalar) > 255 ? 255 : ig * scalar;
		ib = (ib * scalar) > 255 ? 255 : ib * scalar;
		ia = ia == 1 ? 255 : 0;
		
		bytes[0] = (byte) ir;
		bytes[1] = (byte) ig;
		bytes[2] = (byte) ib;
		bytes[3] = (byte) ia;
		
		ColorBytes rawColor = new ColorBytes(bytes);
		
		rawColor.setJavaColor(new Color(ir, ig, ib, ia));
		
		return rawColor;
	}
	
	/***
	 * Converts {@linkplain ColorBytes} object to a 4-byte array based on the parent
	 * {@linkplain DynamixPalette} file's scalar value.
	 * @param color
	 * @param scalar
	 * @return byte[]
	 */
	private byte[] toDynamixColor(ColorBytes color, int scalar) {
		
		byte[] data = new byte[4];
		
		data[0] = Bytes.from(color.getJavaColor().getRed() / scalar).byteOrder(ByteOrder.LITTLE_ENDIAN).toByte();
		
		data[1] = Bytes.from(color.getJavaColor().getBlue() / scalar).byteOrder(ByteOrder.LITTLE_ENDIAN).toByte();
		
		data[2] = Bytes.from(color.getJavaColor().getGreen() / scalar).byteOrder(ByteOrder.LITTLE_ENDIAN).toByte();
		
		if(color.getArray()[3] != 0) {
			data[3] = (byte)0x01;
		}
		
		return data;
	}
	
}
