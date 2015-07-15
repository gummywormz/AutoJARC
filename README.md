AutoJARC
========

**NOTE:** This project is deprecated in favor of [apkizer](https://github.com/gummywormz/apkizer) and [JARC-APK](https://github.com/gummywormz/JARC-APK)

Auto-JARC is an automatic ARC extension builder for use with ADT / Eclipse based on my previous [JARC-APK application.](https://github.com/gummywormz/JARC-APK)

Why not use a build script?
---------------------------
All of the building in Eclipse (haven't tried Android Studio yet) is automatic. ANT is not even part of the default install on that version of Eclipse.
ANT files can only be generated via command line. Gradle support also does not appear to exist either. Instead of fooling around with the command line, Auto-JARC is totally automatic.

Status
------
Completed!

Credits
------
This project is licensed under a MIT license. Please see license.txt for more info.

This project uses Joakime's APK Parser Library which is available [here](https://github.com/joakime/android-apk-parser)

License info for the library is available in License-android-apk-parser.txt.

Special thanks to Google for the initial Android Shim and [Vlad Filippov](https://github.com/vladikoff) for the Chrome Browser port.
