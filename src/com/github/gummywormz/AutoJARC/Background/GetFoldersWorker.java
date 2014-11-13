package com.github.gummywormz.AutoJARC.Background;

import com.github.gummywormz.AutoJARC.APKUtils.ExtensionGenerator;
import com.github.gummywormz.AutoJARC.UI.AutoJARCUI;
import com.github.gummywormz.AutoJARC.User.Project;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;
import net.erdfelt.android.apk.AndroidApk;

/**
 * Gets the available projects in the workspace directory using a swing worker
 * 
 * @author Paul Alves
 */
public class GetFoldersWorker extends javax.swing.SwingWorker<ArrayList<Project>, String>
{

    private JTextArea console = null;
    private AutoJARCUI gui;
    
    public GetFoldersWorker(AutoJARCUI win)
    {
     gui = win;
    }

    @Override
    protected ArrayList<Project> doInBackground() throws Exception {
        publish("Started scanning projects");
        File dir = new File(AutoJARCUI.getWorkspace());
        String[] subDirs = dir.list();
        ArrayList<Project> projs = new ArrayList<>();
        for(String f : subDirs){
            File test = new File(dir.getAbsolutePath() + ExtensionGenerator.sep + f);
            DirectoryVerifier d = new DirectoryVerifier(test);
            VerifierFlag v = d.verify();
            if( test.isDirectory() && v.getSuccess()){
                String apk = test.getAbsolutePath()+ ExtensionGenerator.sep + "bin" + ExtensionGenerator.sep + d.getApkName();
                String pkg = new AndroidApk(new File(apk)).getPackageName();
                String hash = GetHash.getHash(new File(apk));
                boolean hasExt = new File(AutoJARCUI.getExtensionDir() + ExtensionGenerator.sep + pkg).exists();
                Project p = new Project(pkg,test.getName(),hash,d.getApkName(),hasExt);
                projs.add(p);
                publish("Added " + test.getAbsolutePath());
            }else{
                publish("Not adding " + test.getAbsolutePath() + " because: " + v.getReasonAsMessage());
            }
        }
        return projs;
    }

    @Override
    protected void process(List< String> chunks){
        console = gui.getConsole();
        for (final String string : chunks) {
            console.append(string + "\n");
        }
    }
    
    @Override
    protected void done()
    {
        try {
            gui.pushProjects(this.get());
        } catch (InterruptedException | ExecutionException ex) {
            AutoJARCUI.throwError("Scan Failed because: " + ex.getLocalizedMessage());
        }
    }
    }

