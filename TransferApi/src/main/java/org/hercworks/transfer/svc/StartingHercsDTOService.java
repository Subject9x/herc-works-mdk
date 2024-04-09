package org.hercworks.transfer.svc;

import org.hercworks.core.data.file.dat.shell.Hercs;
import org.hercworks.transfer.dto.file.shell.StartHercsDTO;

public interface StartingHercsDTOService {
	public StartHercsDTO convertToDTO(Hercs source);
	
	public Hercs fromDTO(StartHercsDTO source);
}
