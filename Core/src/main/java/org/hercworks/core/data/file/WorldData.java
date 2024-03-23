package org.hercworks.core.data.file;

import java.nio.ByteOrder;

import org.hercworks.voln.DataFile;

import at.favre.lib.bytes.Bytes;


/**
 * FILE
 * 	/SIMVOL0/WLD/ worldX.wld
 */
public class WorldData extends DataFile{

	public static Bytes header = Bytes.from("0200D000").byteOrder(ByteOrder.BIG_ENDIAN);

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

}
