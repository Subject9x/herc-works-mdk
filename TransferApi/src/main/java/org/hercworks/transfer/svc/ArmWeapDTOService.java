package org.hercworks.transfer.svc;

import org.hercworks.core.data.file.dat.shell.ArmWeap;
import org.hercworks.transfer.dto.file.shell.ArmWeapDTO;

public interface ArmWeapDTOService {

	public ArmWeapDTO convertToDTO(ArmWeap source);
	
	public ArmWeap fromDTO(ArmWeapDTO source);
	
}