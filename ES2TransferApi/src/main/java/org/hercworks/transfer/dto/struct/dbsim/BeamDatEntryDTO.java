package org.hercworks.transfer.dto.struct.dbsim;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("")
public class BeamDatEntryDTO {

	@JsonProperty(value = "id", index = 0, required = true)
	private int id;
	
	@JsonProperty(value = "width", index = 1, required = true)
	private int width;

	@JsonProperty(value = "color_id", index = 2, required = true)
	private int colorId;
	
	@JsonProperty(value = "dbaFrameNum", index = 3, defaultValue = "0", required = true)
	private int dbaFrameNum;
	
	public BeamDatEntryDTO() {}

	public int getId() {
		return id;
	}

	public int getWidth() {
		return width;
	}

	public int getColorId() {
		return colorId;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setColorId(int colorId) {
		this.colorId = colorId;
	}

	public int getDbaFrameNum() {
		return dbaFrameNum;
	}

	public void setDbaFrameNum(int dbaFrameNum) {
		this.dbaFrameNum = dbaFrameNum;
	}
}
