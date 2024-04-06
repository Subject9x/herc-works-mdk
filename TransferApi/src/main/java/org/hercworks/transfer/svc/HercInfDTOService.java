package org.hercworks.transfer.svc;

import org.hercworks.core.data.file.dat.shell.HercInf;
import org.hercworks.transfer.dto.file.shell.HercInfDTO;

public interface HercInfDTOService {
	
	public HercInfDTO convertToDTO(HercInf source);
	
	public HercInf fromDTO(HercInfDTO source);
}
