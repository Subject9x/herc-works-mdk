package org.hercworks.core.data.ref.constants;

import at.favre.lib.bytes.Bytes;

/**
 * Data convenience class to reference hercs quickly
 */
public class HercDataRef {

    private Bytes idBytes = Bytes.allocate(2);
    
    private Bytes hardpointCountBytes = Bytes.allocate(2);
    private Bytes nameStrId = Bytes.allocate(2);

    private int intId = 0;
    private int hardpointCount = 0;

    private String name;

    public HercDataRef(){}

    public HercDataRef(Bytes id, Bytes hardpoints, String name){
        this.idBytes = id;
        this.hardpointCountBytes = hardpoints;
        
        this.intId = id.toInt();
        this.hardpointCount = hardpoints.toInt();
        this.nameStrId = this.idBytes;
        this.name = name;
    }
    
    public Bytes getIdBytes() {
		return idBytes;
	}

	public void setIdBytes(Bytes idBytes) {
		this.idBytes = idBytes;
	}

	public Bytes getHardpointCountBytes() {
		return hardpointCountBytes;
	}

	public void setHardpointCountBytes(Bytes hardpointCountBytes) {
		this.hardpointCountBytes = hardpointCountBytes;
	}

	public Bytes getNameStrId() {
		return nameStrId;
	}

	public void setNameStrId(Bytes nameStrId) {
		this.nameStrId = nameStrId;
	}

	public int getIntId() {
        return intId;
    }

    public void setIntId(int intId) {
        this.intId = intId;
    }

    public int getHardpointCount() {
        return hardpointCount;
    }

    public void setHardpointCount(int hardpointCount) {
        this.hardpointCount = hardpointCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
    
}
