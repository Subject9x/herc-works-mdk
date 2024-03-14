package com.mech.works.data.struct.vshell.hercs;

import com.mech.works.data.file.dyn.DynamixBitmapArray;

/**
 * 	Data struct observed throughout VSHELL
 *  
 */
public class UiImageDBA {
	
	private DynamixBitmapArray dba;
	private int originX;
	private int originY;
	
	private int frameId;
	private RFlag flag;
	
	public UiImageDBA() {}
	
	public DynamixBitmapArray getDba() {
		return dba;
	}

	public int getOriginX() {
		return originX;
	}

	public int getOriginY() {
		return originY;
	}
	
	public int getFrameId() {
		return frameId;
	}

	public RFlag getFlags() {
		return flag;
	}

	public void setDba(DynamixBitmapArray dba) {
		this.dba = dba;
	}
	
	public void setOriginX(int originX) {
		this.originX = originX;
	}

	public void setOriginY(int originY) {
		this.originY = originY;
	}

	public void setFrameId(int frameId) {
		this.frameId = frameId;
	}

	public void setFlags(RFlag flag) {
		this.flag = flag;
	}
	
	public static enum RFlag{
		
		NORMAL((byte)0),
		FLIP_XY((byte)1),
		FLIP_X((byte)2),
		FLIP_Y((byte)3);
		
		private byte flag;
		
		private RFlag(byte fl) {
			this.flag = fl;	
		}
		
		public byte val() {
			return this.flag;
		}
	}
}
