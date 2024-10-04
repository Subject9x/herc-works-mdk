package org.hercworks.core.data.struct.vshell.hercs;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.hercworks.core.data.file.dyn.DynamixBitmapArray;

/**
 * 	Data struct observed throughout VSHELL
 *  
 */
public class UiImageDBA {
	
	private DynamixBitmapArray dba;
	private int originX;
	private int originY;
	
	private short frameId;
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
	
	public short getFrameId() {
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

	public void setFrameId(short frameId) {
		this.frameId = frameId;
	}

	public void setFlags(RFlag flag) {
		this.flag = flag;
	}
	
	public static enum RFlag{
		
		NORMAL((short)0),
		FLIP_XY((short)1),
		FLIP_X((short)2),
		FLIP_Y((short)3);
		
		private short flag;
		private static final Map<Short, RFlag> enum_map;
		
		private RFlag(short fl) {
			this.flag = fl;	
		}
		
		static {
	      	Map<Short,RFlag> map = new HashMap<Short, RFlag>();
	        for (RFlag instance : RFlag.values()) {
	            map.put(instance.val(),instance);
	        }
	        enum_map = Collections.unmodifiableMap(map);
		}
		
		public short val() {
			return this.flag;
		}
		
		public static RFlag get(short v) {
			return enum_map.get(v);
		}
	}
}
