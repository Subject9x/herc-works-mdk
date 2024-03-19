# herc-works-mdk
yet another reverse-engineering effort for Earthsiege 2 and maybe Earthsiege 1. The main focus is data editing to try modding game values.

## ideal features:
+ edit herc stats.
+ edit item stats.
+ adjust and edit player campaign resources.
+ edit mission files (somewhat unlikely at the moment.)
+ edit or modify game content.

## goals
+ understand all the Earthsiege data formats as best I can.

## Status
Not even Alpha, currently I'm mapping 3Space 2.0 data files and writing transformers for the byte-data.

## Modding Content
Starsiege Community has discovered that 'updated' or 'modded' game files (as seen in places like `SHELL0` or `SIMVOL0`) can be *replaced without having to compile new vol files!*

Simply create root-level copies of the directories seen in the shell files.
1. example: `GAM/HERCS.DAT` placed in `<ES2 root>/GAM/`
2. this will cause the game to load the `HERCS.DAT` copy in the external folder.