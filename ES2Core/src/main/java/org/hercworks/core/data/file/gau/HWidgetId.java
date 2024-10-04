package org.hercworks.core.data.file.gau;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum HWidgetId {

	ROOTPANEL(0, "root"),
	WEAPON_ITEM_1(1, "weapon_1"),
	WEAPON_ITEM_2(2, "weapon_2"),
	WEAPON_ITEM_3(3, "weapon_3"),
	WEAPON_ITEM_4(4, "weapon_4"),
	WEAPON_ITEM_5(5, "weapon_5"),
	WEAPON_ITEM_6(6, "weapon_6"),
	WEAPON_ITEM_7(7, "weapon_7"),
	WEAPON_ITEM_8(8, "weapon_8"),
	WEAPON_ITEM_9(9, "weapon_9"),
	WEAPON_ITEM_10(10, "weapon_10"),
	LINKCHAIN_PANEL(11, "chainLink_panel"),
	WPN_CHAIN_BTN(12, "wpn_chain_button"),
	WPN_LINK_BTN(13, "wpn_link_button"),
	AUT_TRACK_BTN(14, "auto_track_button");
	
	private int id;
	private String label;
	private static final Map<Integer, HWidgetId> ENUM_MAP;
	
	private HWidgetId(int id, String label) {
		this.id = id;
		this.label = label;
	}

	static {
	    Map<Integer,HWidgetId> map = new HashMap<Integer, HWidgetId>();
	    for (HWidgetId instance : HWidgetId.values()) {
	        map.put(instance.getId(),instance);
	    }
	    ENUM_MAP = Collections.unmodifiableMap(map);
	}
	
    public static HWidgetId fromId(int id) {
        return ENUM_MAP.get(id);
    }
    
    public static int idFromLabel(String label) {
    	for(HWidgetId hid : ENUM_MAP.values()) {
    		if(hid.getLabel().toLowerCase().equals(label.toLowerCase())) {
    			return hid.getId();
    		}
    	}
    	return -1;
    }
	
	public int getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
}
