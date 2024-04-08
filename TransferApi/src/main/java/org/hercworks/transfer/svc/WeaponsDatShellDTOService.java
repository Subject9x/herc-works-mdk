package org.hercworks.transfer.svc;

import org.hercworks.core.data.file.dat.shell.WeaponsDat;
import org.hercworks.transfer.dto.file.shell.WeaponsDatDTO;

public interface WeaponsDatShellDTOService {
	public WeaponsDatDTO convertToDTO(WeaponsDat source);
	
	public WeaponsDat fromDTO(WeaponsDatDTO source);
}
