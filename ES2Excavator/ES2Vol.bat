@echo off
echo enter args and files.
echo IF Path, make it relative to where this jar is.
set /p args= enter args
java -jar ES2UnVol-0.0.1.jar %args%
pause