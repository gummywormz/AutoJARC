AutoJARC
========

Auto-JARC is an automatic ARC extension builder for use with ADT / Eclipse based on my previous [JARC-APK application.](https://github.com/gummywormz/JARC-APK)

Why not use a build script?
---------------------------
All of the building in Eclipse (haven't tried Android Studio yet) is automatic. ANT is not even part of the default install on that version of Eclipse.
ANT files can only be generated via command line. Gradle support also does not appear to exist either. Instead of fooling around with the command line, Auto-JARC is totally automatic.

Status
------
Done: 
* Background directory worker and verifer is done (barring any bugs...).
* ExtensionGenerator has been trimmed down to fit the project better
* Configuration files stuff

TO-DO:
* Add a config file parser / writer
* Add ignore list parser / writer
* Add projects parser / writer
* Add the actual scanner / launcher
