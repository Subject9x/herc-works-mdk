package org.hercworks.transfer.dto.file;

public abstract class TransferObject {
	//meta class for generics.
	
	
	//utilities for clean presentation
	public short floatStringToFixedShort(String strFloat) {
		float f = Float.valueOf(strFloat);
		f = f * 100f;
		return (short)f;
	}
	
	public String fixedShortToFloatString(short val) {
		float f = (float)val;
		
		return String.valueOf((f/100f));	
	}
}
