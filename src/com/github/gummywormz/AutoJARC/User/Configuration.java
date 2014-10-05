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

package com.github.gummywormz.AutoJARC.User;

import java.io.File;

/**
 * Objective Representation of AutoJarc.conf 
 * @author Paul Alves
 */
public class Configuration {
    //also remember to add MAC OS stuffs (you should probably handle it on the config screen automatically rather than here though....)
    private String workspaceDir;
    private String chromePath;
    private String extensionDir;
    
    /**
     * Creates a new configuration object NOTE: all paths are to be absolute
     * @param ws Directory to the user's workspace
     * @param cp Path to the Chrome executable (including the file itself)
     * @param ed Path to the extension directory
     */
    public Configuration(String ws, String cp,String ed){
    workspaceDir = ws;
    chromePath = cp;
    extensionDir = ed;
    }
    
    /**
     * Sets Chrome's path (including the executable file)
     * @param path Path to Chrome
     */
    public void setChromePath(String path){
    chromePath = path;
    }
    
    /**
     * Sets the workspace directory
     * @param path Workspace directory path
     */
    public void setWorkSpace(String path){
    workspaceDir = path;
    }
    
    /**
     * Sets the extension directory
     * @param path The extension directory to set.
     */
    public void setExtensionDirectory(String path){
    extensionDir = path;
    }
    
    /**
     * Returns this workspace as a String
     * @return this workspace as a String
     */
    public String getWorkSpace(){
    return workspaceDir;
    }
    
    /**
     * Returns the Chrome path as a String
     * @return the Chrome path as a String
     */
    public String getChromePath(){
    return chromePath;
    }
    
    /**
     * Returns the extension directory as a String
     * @return the extension directory as a String
     */
    public String getExtensionDirectory(){
    return extensionDir;
    }
    
    /**
     * Returns the workspace directory as a File
     * @return the workspace directory as a File
     */
    public File getWorkSpaceAsFile(){
    return new File(workspaceDir);
    }
    
    /**
     * Returns the Chrome path as a File
     * @return the Chrome path as a File
     */
    public File getChromePathAsFile(){
    return new File(chromePath);
    }
    
    /**
     * Returns the Extension Directory as a File
     * @return the Extension Directory as a File
     */
    public File getExtensionDirAsFile(){
    return new File(extensionDir);
    }
    
}
