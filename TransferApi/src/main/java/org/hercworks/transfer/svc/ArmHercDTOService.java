package org.hercworks.transfer.svc;

import org.hercworks.core.data.file.dat.shell.ArmHerc;
import org.hercworks.transfer.dto.shell.ArmHercDTO;

public interface ArmHercDTOService {

	public ArmHercDTO convertToDTO(ArmHerc source);
	
	public ArmHerc fromDTO(ArmHercDTO source);
	
}
