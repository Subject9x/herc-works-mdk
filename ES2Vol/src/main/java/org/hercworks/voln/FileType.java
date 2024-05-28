package org.hercworks.voln;

/**
 * VOL folders are just the file EXT as a folder name,
 * very handy.
 */
public enum FileType{
	BND("bnd"),
	COL("col"),
	CYC("cyc"),
	DAT("dat"),
	DB0("db0"),
	DB1("db1"),
	DB2("db2"),
	DBA("dba"),
	DBM("dbm"),
	DCI("dci"),
	DEM("dem"),
	DFN("dfn"),
	DGS("dgs"),
	DMG("dmg"),
	DPL("dpl"),
	DTS("dts"),
	ED0("ed0"),
	ED1("ed1"),
	ED2("ed2"),
	ED3("ed3"),
	EDG("edg"),
	ENG("eng"),
	FM("fm"),
	FRE("fre"),
	GAM("gam"),
	GAU("gau"),
	GER("ger"),
	GL("gl"),
	HB0("hb0"),
	HB1("hb1"),
	HB2("hb2"),
	HBA("hba"),
	HD0("hd0"),
	HD1("hd1"),
	HD2("hd2"),
	HD3("hd3"),
	HFN("hfn"),
	HMI("hmi"),
	HMX("hmx"),
	MSN("msn"),
	NAM("nam"),
	OFS("ofs"),
	PDG("pdg"),
	RMP("rmp"),
	SAV("sav"),
	SNC("snc"),
	SOS("sos"),
	STR("str"),
	TTM("ttm"),
	VUE("vue"),
	VOL("vol"),
	WLD("wld"),
	WAV("wav");
	
	private String val;
	
	private FileType(String v) {
		this.val = v;
	}
	
	public String val() {
		return this.val;
	}
	
	public static FileType typeFromVal(String v) {
		for(FileType t : FileType.values()) {
			if(t.val().toUpperCase().equals(v.toUpperCase())) {
				return t;
			}
		}
		return null;
	}
}