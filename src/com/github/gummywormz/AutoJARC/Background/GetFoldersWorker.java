package com.github.gummywormz.AutoJARC.Background;


import com.github.gummywormz.AutoJARC.JARC_APK.ExtensionGenerator;
import com.github.gummywormz.AutoJARC.UI.AutoJARCUI;
import com.github.gummywormz.AutoJARC.User.Project;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
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
            boolean hasExt = new File(AutoJARCUI.getExtensionDir()).exists();
            Project p = new Project(pkg,test.getName(),hash,d.getApkName(),hasExt);
            projs.add(p);
            publish("Added " + test.getAbsolutePath());
            }else{
            publish("Not adding " + test.getAbsolutePath() + "because: " + v.getReasonAsMessage());
        }
        }
        return projs;
    }
    
    @Override
    protected void process(List< String> chunks){
    console = AutoJARCUI.getConsole();
      for (final String string : chunks) {
          console.append(string + "\n");
    }
    }
}
