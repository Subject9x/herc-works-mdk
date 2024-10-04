package org.hercworks.transfer.dto.struct.dbsim;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("")
public class BeamDatEntryDTO {

	@JsonProperty(value = "id", index = 0)
	private int id;
	
	@JsonProperty(value = "width", index = 1)
	private int width;

	@JsonProperty(value = "color_id", index = 2)
	private int colorId;
	
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
}
