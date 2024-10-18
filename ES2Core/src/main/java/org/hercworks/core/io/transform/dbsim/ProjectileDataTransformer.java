package org.hercworks.core.io.transform.dbsim;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.hercworks.core.data.file.dat.sim.ProjectileData;
import org.hercworks.core.data.file.dat.sim.ProjectileData.Projectile;
import org.hercworks.core.data.struct.ProjectileType;
import org.hercworks.core.io.transform.ThreeSpaceByteTransformer;
import org.hercworks.voln.DataFile;
import org.hercworks.voln.FileType;

public class ProjectileDataTransformer extends ThreeSpaceByteTransformer {
	
	@Override
	public DataFile bytesToObject(byte[] inputArray) throws ClassCastException {
		if(inputArray == null || inputArray.length <= 0) {
			return null;
		}
		setBytes(inputArray);
		
		ProjectileData data = new ProjectileData();
		data.setExt(FileType.DAT);
		data.setDir(FileType.DAT);
		data.setRawBytes(inputArray);
		
		data.setTotal(indexShortLE());
		data.setData(new Projectile[data.getTotal()]);
		
		for(int p=0; p < data.getTotal(); p++) {
			Projectile proj = data.newProjectile();
			
			proj.setType(ProjectileType.forId(indexShortLE()));
			proj.setMissileId(indexShortLE());
			proj.setDamageShield(indexShortLE());
			proj.setDamageArmor(indexShortLE());
			proj.setUnk2_val(indexShortLE());
			proj.setSpeed(indexShortLE());
			
			proj.getImpactFXShield()[0] = indexShortLE();
			proj.getImpactFXShield()[1] = indexShortLE();
			proj.getImpactFXShield()[2] = indexShortLE();
			proj.getImpactFXShield()[3] = indexShortLE();
			
			//this order is fairly wonky and probably requires more research.
			proj.getImpactFXGround()[0] = indexShortLE();
			proj.getImpactFXGround()[1] = indexShortLE();
			proj.getImpactFXGround()[2] = indexShortLE();
			proj.getImpactFXGround()[3] = indexShortLE();
			
			proj.getImpactFXArmor()[0] = indexShortLE();
			proj.getImpactFXArmor()[1] = indexShortLE();
			proj.getImpactFXArmor()[2] = indexShortLE();
			proj.getImpactFXArmor()[3] = indexShortLE();
			
			data.getData()[p] = proj;
		}
		return data;
	}

	@Override
	public byte[] objectToBytes(DataFile source) throws ClassCastException, IOException {

		ProjectileData data = (ProjectileData)source;
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		out.write(writeShortLE(data.getTotal()));
		
		for(int p=0; p < data.getData().length; p++) {
			Projectile proj = data.getData()[p];
			
			out.write(writeShortLE(proj.getType().val()));
			out.write(writeShortLE(proj.getMissileId()));
			out.write(writeShortLE(proj.getDamageShield()));
			out.write(writeShortLE(proj.getDamageArmor()));
			out.write(writeShortLE(proj.getUnk2_val()));
			out.write(writeShortLE(proj.getSpeed()));
			
			out.write(writeShortLE(proj.getImpactFXShield()[0]));
			out.write(writeShortLE(proj.getImpactFXShield()[1]));
			out.write(writeShortLE(proj.getImpactFXShield()[2]));
			out.write(writeShortLE(proj.getImpactFXShield()[3]));
			
			//this order is fairly wonky and probably requires more research.
			out.write(writeShortLE(proj.getImpactFXGround()[0]));
			out.write(writeShortLE(proj.getImpactFXGround()[1]));
			out.write(writeShortLE(proj.getImpactFXGround()[2]));
			out.write(writeShortLE(proj.getImpactFXGround()[3]));
			
			out.write(writeShortLE(proj.getImpactFXArmor()[0]));
			out.write(writeShortLE(proj.getImpactFXArmor()[1]));
			out.write(writeShortLE(proj.getImpactFXArmor()[2]));
			out.write(writeShortLE(proj.getImpactFXArmor()[3]));
			
		}
		return out.toByteArray();
	}
}
