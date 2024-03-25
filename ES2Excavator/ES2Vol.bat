@echo off
echo enter VOL file name.
echo IF Path, make it relative to where this jar is.
set /p file= file=
java -jar ES2Unvol-0.0.1.jar %file%
pause