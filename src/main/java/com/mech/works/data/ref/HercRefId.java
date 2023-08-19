package com.mech.works.data.ref;

/**
 * Data convenience class to reference hercs quickly
 */
public class HercRefId {

    private byte[] idBytes = new byte[2];
    private byte hardpointCountBytes;
    private byte[] nameStrId = new byte[2];

    private int intId = 0;
    private int hardpointCount = 0;

    private String name;

    public HercRefId(){}

    public HercRefId(byte[] id, byte hardpoints, String name){
        this.idBytes = id;
        this.hardpointCountBytes = hardpoints;
        this.intId = ((id[0] & 0xFF) << 24) | ((id[1] & 0xFF) << 16);
        this.hardpointCount = ((hardpoints & 0xFF) << 24);
        this.nameStrId = this.idBytes;
        this.name = name;
    }

    public byte[] getIdBytes() {
        return idBytes;
    }

    public void setIdBytes(byte[] idBytes) {
        this.idBytes = idBytes;
    }

    public byte getHardpointCountBytes() {
        return hardpointCountBytes;
    }

    public void setHardpointCountBytes(byte hardpointCountBytes) {
        this.hardpointCountBytes = hardpointCountBytes;
    }

    public byte[] getNameStrId() {
        return nameStrId;
    }

    public void setNameStrId(byte[] nameStrId) {
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
