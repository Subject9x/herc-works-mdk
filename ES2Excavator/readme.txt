Earthsiege 2 Vol file unpacker.
5/17/2024
v0.1.8

Requirements:
-------------------------------------------------------------------------------------------
	+ You will need to have the Java runtime installed on the target machine.
	+ extract jar to your <Earthsiege2 install root>.
		+ jar will use this as its root directory.
Outcome:
-------------------------------------------------------------------------------------------
	+ console prompt will show output.
	+ unless specified, all files unpacked to <ES2 install>/UNPACK/

Runtime Options
-------------------------------------------------------------------------------------------
	-h = Display help options
	-x = Set log output to debug mode.
	-p = Must provide at least 1 DPL file - will export DBA and DBM to colorized copies.
	-s = All non-VOL files will be exported to the input file's source directory.


File Options
-------------------------------------------------------------------------------------------
	+ .VOL - unpacks vols to /UNPACK/<vol name>/
	+ .DBM - requires a .DPL file as a second file, unpacks DBM to .png.
	+ .DBA - unpack internal .DBM files.
		+ IF provided .DPL, will ALSO export colorized .DBMs as .png
	+ .FM - flight model data from /SIMVOL0/FM/
	+ .GL - GunLayout data from /SIMVOL0/GL/
	+ .DMG - Damage Def file /SIMVOL0/DMG/
	+ .DAT - see below

Export to JSON
-------------------------------------------------------------------------------------------
	+ SHELL0/GAM/ - WEAPONS.DAT, ARM_WEAP.DAT, HERCS.DAT, HERC_INF.DAT, ARM_<herc name>.DAT
		+ these can be converted to JSON plaintext files.
		
	+ SIMVOL0/dat/<herc_name>.DAT
		+ output DBSIM herc data for editing and re-import.
		+ Razor, Spider, Skimmer are not support YET, these don't confirm to the 216-size herc data file that other hercs do.

	+ after making your edits, feed the JSON file back into the cmd line.
		+ This will generate a corresponding Game File that has your new changes.
		+ place new file in <es2 root>/GAM/
			+ run ES2.exe
	