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

package com.github.gummywormz.AutoJARC.Background;

import com.github.gummywormz.AutoJARC.User.IgnoreList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Gets and writes to autojarc.ignore
 * @author Paul Alves
 */
public class IgnoreParser {

    /**
     * Gets the IgnoreList from autojarc.ignore
     * @return The IgnoreList object based on autojarc.ignore
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static IgnoreList getIgnoreList() throws FileNotFoundException, IOException
    {
        String dir = System.getProperty("user.dir") + File.separator + "autojarc.ignore";
        BufferedReader b = new BufferedReader(new FileReader(dir));
        IgnoreList i = new IgnoreList();
        String ln;
        while((ln = b.readLine())!=null){
            if(!ln.startsWith("#")){
                i.addDirectory(ln);
            }
        }
        b.close();
        return i;
    }

    /**
     * Adds a directory to the ignorelist.
     * @param pDir The directory name to add (folder name only)
     * @throws IOException
     */
    public static void appendIgnoreList(String pDir) throws IOException{
        String dir = System.getProperty("user.dir") + File.separator + "autojarc.ignore";
        BufferedWriter b = new BufferedWriter(new FileWriter(dir,true));
        b.write(pDir + "\n");
        b.close();
    }

}
