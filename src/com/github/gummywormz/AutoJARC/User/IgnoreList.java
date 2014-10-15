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

import java.util.ArrayList;

/**
 * Represents AutoJarc.ignore (Ignore List)
 * @author Paul Alves
 */
public class IgnoreList {

    private final ArrayList<String> dirs;

    /**
     * Constructs a blank ignore list
     */
    public IgnoreList(){
        dirs = new ArrayList<>();
    }

    /**
     * Constructs an IgnoreList from an ArrayList
     * @param i
     */
    public IgnoreList(ArrayList<String> i){
        dirs = new ArrayList<>(i);
    }

    /**
     * Returns the entire IgnoreList
     * @return the entire IgnoreList
     */
    public ArrayList<String> getList(){
        return dirs;
    }

    /**
     * Adds a directory to this IgnoreList
     * @param dir The directory to ignore (as just the folder name, not the complete path)
     */
    public void addDirectory(String dir){
        dirs.add(dir);
    }

    /**
     * Determines if the directory is ignored
     * @param dir The directory name (not the full path, only the name in the workspace folder)
     * @return True if ignored, false if not.
     */
    public boolean isIgnored(String dir){
        for(String s : dirs){
            if(s.equals(dir)){return true;}
        }
        return false;
    }

}
