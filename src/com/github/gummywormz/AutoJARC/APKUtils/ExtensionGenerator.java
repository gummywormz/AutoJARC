/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.gummywormz.AutoJARC.APKUtils;
import com.github.gummywormz.AutoJARC.UI.AutoJARCUI;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Makes the extension
 * @author Paul Alves
 */
public class ExtensionGenerator {

    private final String appName;
    private final String pkg;
    private final File apkPath;

    /**
     * Path separator
     */
    public static final String sep = File.separator;
    /**
     * Creates a new extension generator object
     * @param pkgName The name of the package
     * @param apk Path to the APK file
     * @param app Application name 
     */
    public ExtensionGenerator(String pkgName, File apk, String app){
        pkg = pkgName;
        apkPath = apk;
        appName = app;
    }

    /**
     * Generates the extension folder
     * @param path The path to create the file
     */
    public void generate(File path){
        //gets the resource files.
        InputStream locales = ExtensionGenerator.class.getClassLoader().getResourceAsStream("com/github/gummywormz/AutoJARC/res/_locales/en/messages.json");
        InputStream htmlPage = ExtensionGenerator.class.getClassLoader().getResourceAsStream("com/github/gummywormz/AutoJARC/res/app_main.html");
        InputStream icon = ExtensionGenerator.class.getClassLoader().getResourceAsStream("com/github/gummywormz/AutoJARC/res/icon.png");
        InputStream manifest = ExtensionGenerator.class.getClassLoader().getResourceAsStream("com/github/gummywormz/AutoJARC/res/manifest.json");

        String absPath = path.getAbsolutePath(); // get the full path to write
        try{
            //write directories needed
            File baseDir = new File(absPath + sep +  pkg);
            baseDir.mkdirs();
            if(!baseDir.exists()){throw new java.io.IOException();}
            File loc = new File(baseDir.getAbsolutePath() + sep + "_locales" + sep + "en");
            loc.mkdirs();
            if(!loc.exists()){throw new java.io.IOException();}
            File vend = new File(baseDir.getAbsolutePath() + sep + "vendor" + sep + "chromium" + sep + "crx");
            vend.mkdirs();
            if(!vend.exists()){throw new java.io.IOException();}
            //begin writing the internal resources
            OutputStream out = new FileOutputStream(loc.getAbsolutePath() + sep + "messages.json");
            byte[] buf = new byte[1024];
            int len;
            while((len=locales.read(buf))>0){
                out.write(buf,0,len);
            }
            out.close();
            out = new FileOutputStream(baseDir.getAbsolutePath() + sep + "app_main.html");
            buf = new byte[1024];
            len = 0;
            while((len=htmlPage.read(buf))>0){
                out.write(buf,0,len);
            }
            out.close();
            out = new FileOutputStream(baseDir.getAbsolutePath() + sep + "icon.png");
            buf = new byte[1024];
            len = 0;
            while((len=icon.read(buf))>0){
                out.write(buf,0,len);
            }
            out.close();
            out = new FileOutputStream(baseDir.getAbsolutePath() + sep + "manifest.json");
            buf = new byte[1024];
            len = 0;
            while((len=manifest.read(buf))>0){
                out.write(buf,0,len);
            }
            out.close();
            //now, edit the manifest as needed. first, get the lines and store them in a list
            List<String> lines = Files.readAllLines(Paths.get(baseDir.getAbsolutePath() + sep + "manifest.json"), StandardCharsets.UTF_8);
            String[] rLines = new String[lines.size()];
            //manually convert to array for *much* easier replacing
            for(int i = 0; i < lines.size(); i++){
                rLines[i] = lines.get(i);
            }
            //read the array and replace away!
            for(int j = 0; j < rLines.length; j++){
                String line = rLines[j];
                //replace the apk file name
                if(line.contains("__apkname__")){
                    rLines [j] = line.replace("__apkname__",apkPath.getName());
                }
                //replace name of application pkg where needed
                if(line.contains("__PACKAGE__")){
                    rLines[j] = line.replace("__PACKAGE__",pkg);
                }
                //change app name
                if(line.contains("__MSG_extName__")){
                    rLines[j] = line.replace("__MSG_extName__",appName);
                }

            }

            //write back the file
            BufferedWriter w = new BufferedWriter(new FileWriter(baseDir.getAbsolutePath() + sep + "manifest.json"));

            for(String s : rLines){
                w.write(s +"\n");
            }
            //drop the apk to the vendor/chromium/crx folder
            InputStream apkRead = new FileInputStream(apkPath);
            out = new FileOutputStream(new File(baseDir.getAbsolutePath() + sep + "vendor" + sep + "chromium" + sep + "crx" + sep + apkPath.getName()));

            buf = new byte[1024];
            len = 0;
            while((len=apkRead.read(buf))>0){
                out.write(buf,0,len);
            }
            //we're done bro
            out.close();
            apkRead.close();
            w.close();
            locales.close();
            icon.close();
            htmlPage.close();
            manifest.close();
        }catch(java.io.IOException e){
            AutoJARCUI.throwError("Could not write some files. Make sure you have proper permissions!");
        }
    }
}
