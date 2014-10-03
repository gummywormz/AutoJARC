/*
 * The MIT License
 *
 * Copyright 2014 homecomputer.
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

import com.github.gummywormz.AutoJARC.JARC_APK.ExtensionGenerator;
import java.io.File;

/**
 * Verifies that a project directory is valid.
 * @author Paul Alves
 */
public class DirectoryVerifier {
 
    private File f;
    
    /**
     * Creates a new directory verifier for this file
     * @param pFile The file to verify
     */
    public DirectoryVerifier(File pFile){
    f = pFile;
    }
    
    public VerifierFlag verify(){
    if(!f.isDirectory()){return new VerifierFlag(false,VerifierFlag.FAILURE_INVALID_OR_CORRUPTED_PROJECT);}   
    File bin = new File(f.getAbsolutePath() + ExtensionGenerator.sep + "bin");
    if(!bin.exists()){return new VerifierFlag(false,VerifierFlag.FAILURE_INVALID_OR_CORRUPTED_PROJECT);}
    String[] apk = bin.list();
    boolean found = false;
    for(String a : apk){
        
        if(a.endsWith(".apk")){
        found = true; 
        break;
        
    }
    }
    if(!found){
    return new VerifierFlag(false,VerifierFlag.FAILURE_NO_APK_FOUND);
    }
    return new VerifierFlag(true,VerifierFlag.SUCCESS);
    }
    
}
