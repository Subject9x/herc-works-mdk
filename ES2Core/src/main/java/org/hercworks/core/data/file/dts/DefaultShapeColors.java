package org.hercworks.core.data.file.dts;

import org.hercworks.core.data.file.dts.poly.TSShadedPoly;

/**
 * I can't figure out the pattern of {@link TSPoly} and {@link TSShadedPoly} surface info
 * choosing the right color in-engine. The colors are usually on a paletted index subject to
 * lighting values that affect the palette index up or down....
 * 
 * so I'm just going to hard code approximations based on found values.
 */
public enum DefaultShapeColors {
	
	ERROR((short)-1, new double[] {0.0, 2.0, 3.0}),
	BLACK_0((short)0, new double[] {0.0, 0.0, 0.0}),
	BROWN_2((short)2, new double[] {0.40, 0.23, 0.15}),
	GREEN_4((short)4,  new double[] {0.29, 0.28, 0.188}),
	GREEN_5((short)5,  new double[] {0.28, 0.31, 0.28}),
	GRAY_8((short)8,  new double[] {0.83, 0.83, 0.83}),
	GRAY_9((short)9,  new double[] {0.9, 0.9, 0.9}),
	UNK_10((short)10,  new double[] {0.2, 0.5, 2.0}),
	GRAY_12((short)12,  new double[] {0.75, 0.75, 0.75}),
	UNK_18((short)18, new double[] {0.5, 0.5, 1.0}),
	UNK_99((short)99,  new double[] {1.0, 2.0, 0.0}),
	UNK_103((short)103,  new double[] {0.0, 2.0, 0.0}),
	GRAY_200((short)200, new double[] {0.14, 0.14, 0.14});
	
	
	private short num;
	private double[] val;
	
	private DefaultShapeColors(short num, double[] val ) {
		this.num = num;
		this.val = val;
	}
	
	public double[] rgb() {
		return this.val;
	}
	
	public static DefaultShapeColors color(short num) {
		for(DefaultShapeColors dsc : DefaultShapeColors.values()) {
			if(dsc.num == num) {
				return dsc;
			}
		}
		System.out.println("UNKNOWN COLOR: " + num);
		return ERROR;
	}
}
