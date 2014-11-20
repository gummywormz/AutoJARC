/*
 * The MIT License
 *
 * Copyright 2014 Paul Alves.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package io.github.gummywormz.AutoJARC.User;

/**
 * Represents a project in AutoJARC.projects
 * @author Paul Alves
 */
public class Project {
    private String pkg;
    private String name;
    private String apkHash;
    private boolean extFolder;
    private String apkName;
    //extension directory name is not needed because its the same as the pkg name

    /**
     * Creates a new project object
     * @param pkgName Name of the package / extension folder
     * @param appName Name of the app
     * @param hash Hash of the APK (to check for changes to the file)
     * @param apk Name of the APK file
     * @param established Whether there an extension directory has been established.
     */
    public Project(String pkgName, String appName, String hash, String apk, boolean established){
        pkg = pkgName;
        name = appName;
        apkHash = hash;
        apkName = apk;
        extFolder = established;
    }

    /**
     * Returns the name of this project's APK
     * @return the name of this project's APK (and folder name)
     */
    public String getPackageName(){
        return pkg;
    }

    /**
     * Returns the name of the app
     * @return The name of the app
     */
    public String getAppName(){
        return name;
    }

    /**
     * Gets the MD5 hash of the APK file
     * @return The MD5 hash of the APK file
     */
    public String getHash(){
        return apkHash;
    }

    /**
     * Gets the file name of the apk in the bin folder
     * @return The file name of the project's apk file
     */
    public String getApkName(){
        return apkName;
    }

    /**
     * Returns if the project has an associated extension directory
     * @return True if a directory exists, false otherwise
     */
    public boolean hasExtensionDirectory(){
        return extFolder;
    }

    /**
     * Sets if the project has an extension directory. 
     * @param set True if the project has an extension directory, false if not
     */
    public void setExtensionDir(boolean set){
        extFolder = set;
    }

    /**
     * Checks if the hash of the apk file matches the given hash
     * @param h The hash to check
     * @return True if the hashes match, false otherwise
     */
    public boolean verifyHash(String h){
        return h.equals(apkHash);
    }

}
