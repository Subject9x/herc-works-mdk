package org.hercworks.core.data.file.dyn;

import java.util.Set;

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
	
	private Set<TSObject> meshes;
	
	//XXX - these are set by game engine code, and thus must be set by any code that would like to link 
	// a texture to the DTS model
	private String textureName;
	private DynamixBitmapArray textureDBA;
	
	public DynamixThreeSpaceModel() {}

	public Set<TSObject> getMeshes() {
		return meshes;
	}

	public void setMeshes(Set<TSObject> meshes) {
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
}
