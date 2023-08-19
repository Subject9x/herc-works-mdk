package com.mech.works.data.ref;

/**
 * Reference data for weapons
 */
public class WeaponRefId {

    private byte[] id = new byte[2];
    private byte[] id2 = new byte[2];
    private byte[] rangeHex = new byte[2];
    
    private String name = "";
    private int idInt = 0;
    private String uiRange = "";

    public WeaponRefId(byte[] id, byte[] range, String name, String uiRange){
        this.id = id;
        this.id2 = id;
        this.rangeHex = range;
        this.name = name;
        this.uiRange = uiRange;
    }

    public byte[] getId() {
        return id;
    }

    public void setId(byte[] id) {
        this.id = id;
    }

    public byte[] getId2() {
        return id2;
    }

    public void setId2(byte[] id2) {
        this.id2 = id2;
    }

    public byte[] getRangeHex() {
        return rangeHex;
    }

    public void setRangeHex(byte[] rangeHex) {
        this.rangeHex = rangeHex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdInt() {
        return idInt;
    }

    public void setIdInt(int idInt) {
        this.idInt = idInt;
    }

    public String getUiRange() {
        return uiRange;
    }

    public void setUiRange(String uiRange) {
        this.uiRange = uiRange;
    }

    
}
