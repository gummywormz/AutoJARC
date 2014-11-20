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

package io.github.gummywormz.AutoJARC.Misc;

import java.io.File;
import java.net.URI;

/**
 * Makes files with identical names have .equals return true.
 * @author Paul Alves
 */
public class LibraryFile extends java.io.File{

    public LibraryFile(String pathname) {
        super(pathname);
    }

    public LibraryFile(URI uri){
       super(uri);
    }
    
    public LibraryFile(File parent, String child) {
        super(parent,child);
    }
    
    public LibraryFile(String parent, String child) {
        super(parent, child);
    }
    
    @Override
    public boolean equals(Object obj)
    {
        if ((obj != null) && (obj instanceof File)) {
            String n1 = this.getName();
            File f = (File) obj;
            return n1.equals(f.getName());
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 85 * hash + this.getName().hashCode();
        return hash;
    }


    
}
