package org.hercworks.transfer.svc;

import org.hercworks.transfer.dto.file.TransferObject;
import org.hercworks.voln.DataFile;

public interface GeneralDTOService {

	public TransferObject convertToDTO(DataFile source);
	
	public DataFile fromDTO(TransferObject source);
	
}
