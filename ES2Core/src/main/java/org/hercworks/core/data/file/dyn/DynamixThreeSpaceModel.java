package org.hercworks.core.data.file.dyn;

import java.util.List;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.hercworks.core.data.file.dts.TSObject;
import org.hercworks.voln.DataFile;

/**
 * FILE
 * 		DTS - Dynamix ThreeSpace
 * 	primary 3D model format for ES2 engine.
 * 
 * 
 */
public class DynamixThreeSpaceModel extends DataFile {
	
	private List<TSObject> meshes;
	
	private Vector3D center;
	
	//XXX - these are set by game engine code, and thus must be set by any code that would like to link 
	// a texture to the DTS model
	private String textureName;
	private DynamixBitmapArray textureDBA;
	
	public DynamixThreeSpaceModel() {}

	public List<TSObject> getMeshes() {
		return meshes;
	}

	public void setMeshes(List<TSObject> meshes) {
		this.meshes = meshes;
	}

	public DynamixBitmapArray getTextureDBA() {
		return textureDBA;
	}

	public void setTextureDBA(DynamixBitmapArray textureDBA) {
		this.textureDBA = textureDBA;
	}

	public String getTextureName() {
		return textureName;
	}

	public void setTextureName(String textureName) {
		this.textureName = textureName;
	}
	
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		
		str.append("{\"file\" : \"").append(originNameNoExt()).append("\",\n");
		str.append("\"meshes\" : [\n");
		for(int s=0; s < meshes.size(); s++) {
			str.append(meshes.get(s).toString());
			if(s < meshes.size() - 1) {
				str.append(",\n");
			}
		}
		str.append("]}");
		
		return str.toString();
	}
}
