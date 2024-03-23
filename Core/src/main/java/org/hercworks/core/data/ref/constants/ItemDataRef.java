package org.hercworks.core.data.ref.constants;

import at.favre.lib.bytes.Bytes;

/**
 * Reference data for weapons
 */
public class ItemDataRef {

    private Bytes id = Bytes.allocate(2);
    private Bytes id2 = Bytes.allocate(2);
    private Bytes rangeHex = Bytes.allocate(2);
    
    private String name = "";
    private int idInt = 0;
    private String uiRange = "";

    public ItemDataRef(Bytes id, Bytes range, String name, String uiRange){
        this.id = id;
        this.id2 = id;
        this.rangeHex = range;
        this.name = name;
        this.uiRange = uiRange;
    }

    public Bytes getId() {
		return id;
	}

	public void setId(Bytes id) {
		this.id = id;
	}

	public Bytes getId2() {
		return id2;
	}
	
	public void setId2(Bytes id2) {
		this.id2 = id2;
	}

	public Bytes getRangeHex() {
		return rangeHex;
	}

	public void setRangeHex(Bytes rangeHex) {
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
