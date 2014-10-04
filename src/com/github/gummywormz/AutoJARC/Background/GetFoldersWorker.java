package com.github.gummywormz.AutoJARC.Background;


import com.github.gummywormz.AutoJARC.JARC_APK.ExtensionGenerator;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextArea;

/**
 * Gets the available projects in the workspace directory using a swing worker
 * 
 * @author Paul Alves
 */
public class GetFoldersWorker extends javax.swing.SwingWorker<ArrayList<File>, String>
{
    
    private final JTextArea console = null;
    
    @Override
    protected ArrayList<File> doInBackground() throws Exception {
        File dir = new File("workspacedir"); //AutoJARCUI.getConfig().getWorkspace(); (full path btw)
        String[] subDirs = dir.list();
        ArrayList<File> realSubDirs = new ArrayList<>();
        for(String f : subDirs){
            File test = new File(dir.getAbsolutePath() + ExtensionGenerator.sep + f);
            DirectoryVerifier d = new DirectoryVerifier(test);
            VerifierFlag v = d.verify();
            if( test.isDirectory() && v.getSuccess()){
            realSubDirs.add(test);
            }else{
            publish("Not adding " + test.getAbsolutePath() + "because: " + v.getReasonAsMessage());
        }
        }
        return realSubDirs;
    }
    
    @Override
    protected void process(List< String> chunks){
    //console = AutoJARCUI.getConsole();
      for (final String string : chunks) {
      //console.append(string + "\n");
    }
    }
}
