# herc-works-mdk
yet another reverse-engineering effort for Earthsiege 2 and maybe Earthsiege 1. The main focus is data editing to try modding game values. The project has several modules for organization and study.

## Modules
### ES2Vol
Entire project for handling the `.vol` game file archive format. Specifically using the 3Space 2 / ES2 version of this file schema.
Dynamix `VOL` files changed constantly, even between game releases. I can't guarantee this code works immediately on other Dynamix games
from the era, even if they are using the same engine. 

This module can load/export/create VOL files, and importantly can return a vol file in-memory as a set of java objects that other
applications can leverage.

### Core
+ Contains class-representation of every single file and file-type encountered in the ES2 binaries.
+ Byte-to-Object transformer API to allow interoperability with ES2 engine, and other languages.
+ Data-to-JSON API for user-friendly presentation.

### ES2Excavator
CLI tool for accessing specific files, and converting them out into workable formats.
+ DBA - unpack DynamixBitmapArrays to separate DBM files.
+ DBM - export DBA to colorized png when provided a DPL file.
+ DPL - exports to a png image of colors in a grid.

### HercWorksUI
Will start later.

#### ideal features:
+ edit herc stats.
+ edit item stats.
+ adjust and edit player campaign resources.
+ edit mission files (somewhat unlikely at the moment.)
+ edit or modify game content.

# Status
+ Prototyping and pathfinding phase.
+I will generate workable tools as I can but no timeline, no projected release date.

# Modding Content
Starsiege Community has discovered that 'updated' or 'modded' game files (as seen in places like `SHELL0` or `SIMVOL0`) can be *replaced without having to compile new vol files!*

Simply create root-level copies of the directories seen in the shell files.
1. example: `GAM/HERCS.DAT` placed in `<ES2 root>/GAM/`
2. this will cause the game to load the `HERCS.DAT` copy in the external folder.