package org.hercworks.core.data.file.msn;


/**
 * possibly top-level abstract class for a certain set of observed map objects.
 * Some map objects have a GUID that seems to be counting up.
 */
public abstract class MapObject {

	private short guid;
	
	public short getGUID() {
		return guid;
	}

	public void setGUID(short guid) {
		this.guid = guid;
	}
	
}
