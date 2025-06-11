package org.hercworks.core.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.hercworks.core.data.file.StringBinaryFile;
import org.hercworks.core.io.transform.common.BinStringFileTransformer;
import org.junit.Test;

public class TestTransformers {

	@Test
	public void testBIN() {
		
		BinStringFileTransformer binTrs = new BinStringFileTransformer();
		File file = new File("e:/es2_os/dev/earthsiege2/unpack/lang0/eng/ESNAMES.bin");
		
		StringBinaryFile strbin = null;
		try {
			FileInputStream fizz = new FileInputStream(file);
			
			byte[] b = fizz.readAllBytes();
			fizz.close();
			
			strbin = (StringBinaryFile)binTrs.bytesToObject(b);
			
			
			File es = new File("e:/es2_os/dev/earthsiege2/eng/ESNAMES.bin");
			
			byte[] out = binTrs.objectToBytes(strbin);
			
			FileOutputStream fout = new FileOutputStream(es);
			fout.write(out);
			fout.close();
			
			
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace(System.out);
		} 
		catch (IOException e) {
			e.printStackTrace(System.out);
		}
		catch (Exception e) {
			e.printStackTrace(System.out);
		}
		
	}
	
}
