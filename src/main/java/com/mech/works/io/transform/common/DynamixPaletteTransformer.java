package com.mech.works.io.transform.common;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteOrder;

import com.mech.works.data.file.dyn.DynamixPalette;
import com.mech.works.data.ref.files.DataFile;
import com.mech.works.data.struct.ColorBytes;
import com.mech.works.io.transform.ThreeSpaceByteTransformer;
import com.mech.works.vol.data.Voln;

import at.favre.lib.bytes.Bytes;

/**
 * Transforms byete[] data to and from {@linkplain DynamixPalette} game files.
 * 
 */
public class DynamixPaletteTransformer extends ThreeSpaceByteTransformer {

	public DynamixPaletteTransformer() {}
	
	@Override
	public DataFile bytesToObject(byte[] inputArray) throws ClassCastException {
		if(inputArray.length <= 0) {
			//TODO - DynamixPaletteTransformer - Log null 
			return null;
		}
		setBytes(inputArray);
		
		DynamixPalette dpl = new DynamixPalette();
		dpl.setRawBytes(Bytes.from(inputArray).array());	//clone array
		
		dpl.setExt(Voln.FileType.DPL);
		index += 4;	//skip magic header bytes.
		
		dpl.setPaletteSizeByte(indexIntLE());
		dpl.setColorCount(indexIntLE());
		
//		int colorBytes = dpl.getColorCount() * 4;
		
		int colorIdx = 0;
		
		dpl.setScalar(4);
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
		System.out.println("["+ idx +"] Palette Entry RAW:" + Bytes.from(dynamixColor).encodeHex());
		System.out.println("["+ idx +"] Palette Entry LENDIAN RAW:" + Bytes.from(dynamixColor).byteOrder(ByteOrder.LITTLE_ENDIAN).encodeHex());
		
		int ir = Byte.toUnsignedInt(dynamixColor[0]);
		ir = (ir * scalar) > 255 ? 255 : ir * scalar;
		bytes[0] = (byte) ir;
		
		
		int ig = Byte.toUnsignedInt(dynamixColor[1]);
		ig = (ig * scalar) > 255 ? 255 : ig * scalar;
		bytes[1] = (byte) ig;
		
		int ib =  Byte.toUnsignedInt(dynamixColor[2]);
		ib = (ib * scalar) > 255 ? 255 : ib * scalar;
		bytes[2] = (byte) ib;
		
		
		int ia = dynamixColor[3];
		ia = ia == 1 ? 255 : 0;
		bytes[3] = (byte) ia;
		
		System.out.println("		Palette Entry:" + Bytes.from(dynamixColor).encodeHex());

		if(ir == 0 && ig == 0 && ib == 0) {
			//I think this is their alpha color;
			bytes[3] = (byte) 1;
			ia = 128;
		}
		
		ColorBytes rawColor = new ColorBytes(bytes);
		
		rawColor.setColor(new Color(ir, ig, ib, ia));
		
		System.out.println("		index:" + idx);
		System.out.println("		getIntBGR:" + Bytes.from(rawColor.getIntBGR()).encodeHex());
		System.out.println("		getIntRGB:" + Bytes.from(rawColor.getIntRGB()).encodeHex());
		
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
		
		data[0] = Bytes.from(color.getColor().getRed() / scalar).byteOrder(ByteOrder.LITTLE_ENDIAN).toByte();
		
		data[1] = Bytes.from(color.getColor().getBlue() / scalar).byteOrder(ByteOrder.LITTLE_ENDIAN).toByte();
		
		data[2] = Bytes.from(color.getColor().getGreen() / scalar).byteOrder(ByteOrder.LITTLE_ENDIAN).toByte();
		
		if(color.getArray()[3] != 0) {
			data[3] = (byte)0x01;
		}
		
		return data;
	}
	
}
