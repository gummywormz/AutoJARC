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
import com.github.gummywormz.AutoJARC.User.Project;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Parses / writes to the autojarc.project file
 * @author Paul Alves
 */
public class ProjectParser {

    private static final String[] header ={
            "#This lists all projects so AutoJARC doesn't have to go through and verify each individual folder at start every time. Syntax is as follows:",
            "#[PROJECT_START] indicates the start of a project",
            "#PACKAGENAME= package name / extension directory name",
            "#APPNAME= application name (from the last part of the package name or from values/strings.xml",
            "#HASEXTENSIONDIR= <0 or 1> If an extension has been generated yet",
            "#APKNAME= name of the apk file (so we don't have to scan the /bin folder for it every time)",
            "#APKHASH= hash of the apk file to check for changes (so we don't needlessly copy the same file each time)",
            "#[PROJECT_END] indicates the end of a project"};

    /**
     * Writes a given ArrayList of projects to autojarc.projects
     * @param p The list of projects to write (this will overwrite the file)
     * @throws IOException
     */
    public static void writeProjects(ArrayList<Project> p) throws IOException{
        String dir = System.getProperty("user.dir") + File.separator + "autojarc.projects";
        BufferedWriter w = new BufferedWriter(new FileWriter(dir));
        for(String s : header){
            w.write(s + "\n");
        }
        for(Project proj : p){
            String ext;
            if(proj.hasExtensionDirectory()){ext = "1";}
            else{ext = "0";}
            String write = "[PROJECT_START]\nPACKAGENAME=" + proj.getPackageName()
                + "\nAPPNAME=" + proj.getAppName() + "\nHASEXTENSIONDIR=" + ext + "\nAPKNAME=" + proj.getApkName()
                + "\nAPKHASH=" + proj.getHash() + "\n[PROJECT_END]\n";
            w.write(write);
        }
        w.close();

    }
    
    /**
     * Removes ignored entries from a project list
     * @param projectList The list of projects to remove ignored entries from
     * @param ignoreList The ignorelist object to compare to
     * @return A project list without ignored entries
     */
    public static ArrayList<Project> removeIgnored(ArrayList<Project> projectList, IgnoreList ignoreList)
    {
    for(int i = 0; i < projectList.size(); i++){
            Project p = projectList.get(i);
            if(ignoreList.isIgnored(p.getAppName())){
                projectList.remove(i);
            }
        }
    return projectList;
    }

    /**
     * Gets an arraylist of projects from autojarc.projects
     * @return Arraylist of projects
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static ArrayList<Project> getProjects() throws FileNotFoundException, IOException{
        ArrayList<Project> p = new ArrayList<>();
        String dir = System.getProperty("user.dir") + File.separator + "autojarc.projects";
        BufferedReader r = new BufferedReader(new FileReader(dir));
        String ln;
        String pkg = null;
        String hash = null;
        String apkName = null;
        String appName = null;
        boolean extDir = false;
        while((ln = r.readLine())!= null){
            if(!ln.startsWith("#") || !ln.isEmpty()){

                if(ln.startsWith("PACKAGENAME=")){
                    pkg = ln.split("=")[1];
                }

                if(ln.startsWith("APPNAME=")){
                    appName = ln.split("=")[1];
                }

                if(ln.startsWith("HASEXTENSIONDIR=")){
                    String comp = ln.split("=")[1];
                    extDir = comp.equals("1");
                }

                if(ln.startsWith("APKNAME=")){
                    apkName = ln.split("=")[1];
                }

                if(ln.startsWith("APKHASH=")){
                    hash = ln.split("=")[1];
                }    

                if(ln.equals("[PROJECT_END]")){
                    p.add(new Project(pkg,appName,hash,apkName,extDir));
                    pkg = null;
                    hash = null;
                    apkName = null;
                    appName = null;
                    extDir = false;
                }

            }
        }
        r.close();
        return p;
    }

}
