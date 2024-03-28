Earthsiege 2 Vol file unpacker.
3/28/2024

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