package com.mech.works.vol.data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import com.mech.works.data.file.VolEntry;
import com.mech.works.data.ref.files.DataFile;

import at.favre.lib.bytes.Bytes;

/**
 * Library representation of a Dynamix ThreeSpace vol ('.vol') file.
 * use builder to generate one
 */

public class Voln extends DataFile{

	public static int fileListHeaderLen = 18;
	
	private String destPath;
	private ExeUse exeUse;
	private int dirCount;
	private int dirSize;
	private Set<VolEntry> filesSet = new LinkedHashSet<VolEntry>();
	private HashMap<FileType, Set<DataFile>> directory = new HashMap<Voln.FileType, Set<DataFile>>();
	private Set<DataFile> looseFiles = new HashSet<DataFile>();
	private int listCount;
	private int listSize;
	
	public Voln() {}
	
	private Voln(VolnBuilder b) {
		setDestPath(b.getDestPath());
		setRawBytes(b.getRawBytes());
		setGameDirPath(b.getGameDirPath());
		setFileName(b.getFileName());
		setExeUse1(b.getExeUse());
		setDirCount(b.getDirCount());
		setDirSize(b.getDirSize());
	}

	public Set<DataFile> getDirectoryByString(String name){
		
		if(directory != null && !directory.isEmpty()) {
			if(directory.get(Voln.FileType.typeFromVal(name)) == null) {
				return null;
			}
			return directory.get(Voln.FileType.typeFromVal(name));
		}
		return null;
	}
	
	public static class VolnBuilder{
		
		private String fileName;
		private String gameDirPath;
		private String destPath;
		private byte[] rawBytes;
		private ExeUse exeUse;
		private int dirCount;
		private int dirSize;
			
		public VolnBuilder() {}
		
		public Voln build() {
			
			return new Voln(this);
		}

		public VolnBuilder setFileName(String fileName) {
			this.fileName = fileName;
			return this;
		}

		public VolnBuilder setGameDirPath(String gameDirPath) {
			this.gameDirPath = gameDirPath;
			return this;
		}

		public VolnBuilder setDestPath(String destPath) {
			this.destPath = destPath;
			return this;
		}

		public VolnBuilder setRawBytes(byte[] rawBytes) {
			this.rawBytes = rawBytes;
			return this;
		}
		

		public VolnBuilder setExeUse1(ExeUse exeUse) {
			this.exeUse = exeUse;
			return this;
		}

		public VolnBuilder setDirCount(int dirCount) {
			this.dirCount = dirCount;
			return this;
		}
		
		public VolnBuilder setDirSize(int dirSize) {
			this.dirSize = dirSize;
			return this;
		}
		
		private String getFileName() {
			return fileName;
		}

		private String getGameDirPath() {
			return gameDirPath;
		}

		private String getDestPath() {
			return destPath;
		}
		
		private byte[] getRawBytes() {
			return rawBytes;
		}

		public ExeUse getExeUse() {
			return exeUse;
		}

		public int getDirCount() {
			return dirCount;
		}

		public int getDirSize() {
			return dirSize;
		}
	}
	
	
	public String getDestPath() {
		return destPath;
	}

	public void setDestPath(String destPath) {
		this.destPath = destPath;
	}	
	
	
	/**
	 * Pre-computed byte 
	 */
	public static enum ByteHeader{
		/**
		 * pulled from Earthsiege 2 Reverse Engineered google-site, credit UNKNOWN atm (I can't find any contact)
			lang0       56 4F 4C 4E 00 01 00 00 05 03 0F 00 len: D484
			shell0      56 4F 4C 4E 00 01 00 00 05 06 1E 00 len: 86B052
			shell1      56 4F 4C 4E 00 01 00 00 0A 01 05 00 len: 10EE1
			shlsound    56 4F 4C 4E 00 01 00 00 05 01 05 00 len: 206B68
			simlang     56 4F 4C 4E 01 00 00 00 05 02 0A 00 len: A1F1
			simalert    56 4F 4C 4E 01 00 00 00 05 07 23 00 len: B6B10
			simpatch    56 4F 4C 4E 01 00 00 00 0A 29 CB 00 len: 11AF5
			simsound    56 4F 4C 4E 01 00 00 00 05 03 0F 00 len: 22F692
			simvoicg    56 4F 4C 4E 01 00 00 00 05 01 0A 00 len: 64DCAE
			simvoice    56 4F 4C 4E 01 00 00 00 05 01 0A 00 len: 6B7567
			simvoicf    56 4F 4C 4E 01 00 00 00 05 01 0A 00 len: 6ECD7F
			simvol0     56 4F 4C 4E 01 00 00 00 05 27 C1 00 len: 1D63B29
			zones       56 4F 4C 4E 01 01 00 00 05 03 0F 00 len: 208969
			
			The first four bytes ("VOLN" in ASCII) indicate a ES volume file (magic bytes).

        	The 5th byte is apparently 0x01 if the file is used in the simulator and 0x00 if used in the shell.

        	The 6th byte is apparently 0x01 if the file is used in the shell and 0x00 if used in the simulator

        	Two bytes are ignored and always zero.

        	The 9th byte is some kind of counter (currently unknown)

        	The 10th byte is the directory count (how many directories are in the volume)

        	The 11th and 12th byte determine how many bytes the directory list has (little-endian). simvol0 and simpatch are the leaders in the directory count.
		 */
		VOLN("564F4C4E"),
		LANG0("564F4C4E0001000005030F00"),
		SHELL0("564F4C4E0001000005061E00"),
		SHELL1("564F4C4E000100000A010500"),
		SHLSOUND("564F4C4E0001000005010500"),
		SIMLANG("564F4C4E0100000005020A00"),
		SIMALERT("564F4C4E0100000005072300"),
		SIMPATCH("564F4C4E010000000A29CB00"),
		SIMSOUND("564F4C4E0100000005030F00"),
		SIMVOICG("564F4C4E0100000005010A00"),
		SIMVOICE("564F4C4E0100000005010A00"),
		SIMVOICF("564F4C4E0100000005010A00"),
		SIMVOL0("564F4C4E010000000527C100"),
		ZONES("564F4C4E0101000005030F00");
		
		private Bytes data;
		
		private ByteHeader(String rawHex) {
			this.data = Bytes.parseHex(rawHex);
		}
		
		public Bytes bytes(){
			return data;
		}
	}
	
	public static enum ExeUse{
		
		DBSIM(0),
		VSHELL(1);
		
		private int exeType;
		
		private ExeUse(int type) {
			this.exeType = type;
		}
		
		private int val() {
			return this.exeType;
		}
	}
	
	/**
	 * VOL folders are just the file EXT as a folder name,
	 * very handy.
	 */
	public static enum FileType{
		BND("bnd"),
		COL("col"),
		CYC("cyc"),
		DAT("dat"),
		DB0("db0"),
		DB1("db1"),
		DB2("db2"),
		DBA("dba"),
		DBM("dbm"),
		DCI("dci"),
		DFN("dfn"),
		DGS("dgs"),
		DMG("dmg"),
		DPL("dpl"),
		DTS("dts"),
		ED0("ed0"),
		ED1("ed1"),
		ED2("ed2"),
		ED3("ed3"),
		EDG("edg"),
		FM("fm"),
		GAM("gam"),
		GAU("gau"),
		GL("gl"),
		HB0("hb0"),
		HB1("hb1"),
		HB2("hb2"),
		HBA("hba"),
		HD0("hd0"),
		HD1("hd1"),
		HD2("hd2"),
		HD3("hd3"),
		HFN("hfn"),
		MSN("msn"),
		NAM("nam"),
		OFS("ofs"),
		PDG("pdg"),
		RMP("rmp"),
		SNC("snc"),
		SOS("sos"),
		STR("str"),
		VUE("vue"),
		WLD("wld");
		
		private String val;
		
		private FileType(String v) {
			this.val = v;
		}
		
		public String val() {
			return this.val;
		}
		
		public static Voln.FileType typeFromVal(String v) {
			for(Voln.FileType t : Voln.FileType.values()) {
				if(t.val().toUpperCase().equals(v.toUpperCase())) {
					return t;
				}
			}
			return null;
		}
	}
	

	public ExeUse getExeUse1() {
		return exeUse;
	}

	public void setExeUse1(ExeUse exeUse) {
		this.exeUse = exeUse;
	}


	public int getDirCount() {
		return dirCount;
	}

	public void setDirCount(int dirCount) {
		this.dirCount = dirCount;
	}

	public int getDirSize() {
		return dirSize;
	}

	public void setDirSize(int dirSize) {
		this.dirSize = dirSize;
	}

	public ExeUse getExeUse() {
		return exeUse;
	}

	public void setExeUse(ExeUse exeUse) {
		this.exeUse = exeUse;
	}

	public HashMap<FileType, Set<DataFile>> getDirectory() {
		return directory;
	}

	public void setDirectory(HashMap<FileType, Set<DataFile>> directory) {
		this.directory = directory;
	}

	public int getListCount() {
		return listCount;
	}

	public void setListCount(int listCount) {
		this.listCount = listCount;
	}

	public int getListSize() {
		return listSize;
	}

	public void setListSize(int listSize) {
		this.listSize = listSize;
	}

	public static int getFileListHeaderLen() {
		return fileListHeaderLen;
	}

	public static void setFileListHeaderLen(int fileListHeaderLen) {
		Voln.fileListHeaderLen = fileListHeaderLen;
	}

	public Set<DataFile> getLooseFiles() {
		return looseFiles;
	}

	public void setLooseFiles(Set<DataFile> looseFiles) {
		this.looseFiles = looseFiles;
	}

	public Set<VolEntry> getFilesSet() {
		return filesSet;
	}

	public void setFilesSet(Set<VolEntry> filesSet) {
		this.filesSet = filesSet;
	}
	
}
