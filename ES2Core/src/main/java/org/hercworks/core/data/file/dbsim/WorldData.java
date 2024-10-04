package org.hercworks.core.data.file.dbsim;

import java.nio.ByteOrder;

import org.hercworks.voln.DataFile;

import at.favre.lib.bytes.Bytes;


/**
 * FILE
 * 	/SIMVOL0/WLD/ worldX.wld
 * 
 * 0- UINT16 - ? - always 2
 * 2- UINT16 - sky palette id
 * 4- UINT16 - sky height on horizon
 * 6- UINT16 - sky start height
 * 8- UINT16 - ? - varies
 * 10- UINT16 - ? - always 1
 * 12- UINT16 - ? - always 1
 * 14- 0x14 Spacer bytes
 * 16- UINT16 - ? - always 233
 * 18- UINT16 - ? - always 1
 * 20- UINT16 - ? - always 47
 * 22- UINT16 - ? - always 223
 * 24- UINT16 - ? - always 6000
 * 26- UINT16 - ? - always 7
 * 28- UINT16 - ? - always 16, other values crash EXCEPT 32
 * 30- Spacer bytes 
 * 32- UINT32 - ? - always 60000
 * 36- UINT32 - ? - always 64400
 * 40- UINT16 - ? 
 * 
 * 
 * 240- UINT16 -
 * 242- UINT16 -
 * 244- UINT16 -
 * 246- UINT16 -
 * 248- UINT16 - 
 * 250- UINT16 - 
 * 252- UINT16 - 
 * 254- UINT16 - ? always 111 or 106 
 * 256- UINT16 - ? always 1 OR 4
 * 258- UINT16 - ? always 86
 * 260- UINT16 - ? always 16
 * 262- UINT16 - ? always 102
 * 264- UINT16 - ? always 1
 * 266- UINT16 - ? always 20
 * 268- UINT16 - ? always 250
 * 270- UINT16 - ? unknown flag 55536
 * 272- UINT16 - ? unknown flag 65535
 * 274- UINT16 - ? unknown flag 55536
 * 276- UINT16 - ? unknown flag 65535
 * 278- String - always "world24\0" possibly a version number
 * 286- String - clouds name, "clouds1", "clouds2" - though I don't ever see clouds rendered, this could be an ES1 holdover
 * 294- String - impact graphic id? "impact1" "impact2"
 * 302- String - ground texture name "urban.tex", "bsnow.tex", "ice.tex", "moon.tex", "volcan.tex" - variable length but also EoF!
 */
public class WorldData extends DataFile{
	
	private short unk0_val2 = (short)2;
	
	private short skyPaletteId = (short)208;
	private short skyHorizonHeight;
	private short skyHorizonStartHeight;
	
	private short unk8_val;
	private short unk10_val = (short)1;
	private short unk12_val = (short)1;
	
//	0x14 Spacer bytes
	
	private short unk16_val = (short)233;
	private short unk18_val = (short)1;
	private short unk20_val = (short)47;
	private short unk22_val = (short)223;
	private short unk24_val = (short)6000;
	private short unk26_val = (short)7;
	private short unk28_val = (short)16;
//	0x14 Spacer bytes
	
	private int unk32_val = 60000;
	private int unk34_val = 64400;
	
	//TODO - finish
	
	private String worldTypeStr;
	private String cloudStr;
	private String impactSt;
	private String texStr;
	
	public WorldData() {}
	
	public WorldData(String fileName, String dirPath) {
		super(fileName, dirPath);
	}

	public String getWorldTypeStr() {
		return worldTypeStr;
	}

	public void setWorldTypeStr(String worldTypeStr) {
		this.worldTypeStr = worldTypeStr;
	}

	public String getCloudStr() {
		return cloudStr;
	}

	public void setCloudStr(String cloudStr) {
		this.cloudStr = cloudStr;
	}

	public String getImpactSt() {
		return impactSt;
	}

	public void setImpactSt(String impactSt) {
		this.impactSt = impactSt;
	}

	public String getTexStr() {
		return texStr;
	}

	public void setTexStr(String texStr) {
		this.texStr = texStr;
	}

	public class Sky{
		private short paletteId;
		private short horizonHeight;
		private short startHeight;
		
	}
	
}
