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

package com.github.gummywormz.AutoJARC.UI;

import com.github.gummywormz.AutoJARC.Background.GetFoldersWorker;
import com.github.gummywormz.AutoJARC.Background.ProjectParser;
import com.github.gummywormz.AutoJARC.JARC_APK.ExtensionGenerator;
import com.github.gummywormz.AutoJARC.User.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

/**
 * Main window of AutoJARC
 * @author Paul Alves
 */
public class AutoJARCUI extends javax.swing.JFrame {

    private final String workDir;
    private ArrayList<Project> projectList;
    private Configuration configuration;
    private static IgnoreList ignoreList;
    private final String sep = ExtensionGenerator.sep;
    private static String workspaceDirS = "";
    private static String extDirS = "";
    
    /**
     * Creates new form AutoJARCUI
     */
    public AutoJARCUI() {
        this.workDir = System.getProperty("user.dir");
        this.projectList = new ArrayList<>();
        initComponents();
        try {
            checkFiles();
        } catch (IOException ex) {
            throwError("An IO error occurred when writing config files. Make sure you have proper permissions to write the needed files."
                    + "\nUnfortunately, AutoJARC will not work without these files being written."
                    + "\nTry getting the config files under the res/ConfigFiles folder on the Github page (see About) and downloading them to the same directory as the JAR file.");
        }
    }
    
    /**
     * Gets the console so it can be written to by swing workers
     * @return The Console text area
     */
    public static JTextArea getConsole(){
    return console;
    }
    
    private void checkFiles() throws FileNotFoundException, IOException{

    File config = new File(workDir + sep + "autojarc.conf");
    File ignore = new File(workDir + sep + "autojarc.ignore");
    File projects = new File(workDir + sep + "autojarc.projects");
    if(!ignore.exists()){
        InputStream ignoreStream = AutoJARCUI.class.getClassLoader().getResourceAsStream("com/github/gummywormz/AutoJARC/res/ConfigFile/autojarc.ignore");
        OutputStream out = new FileOutputStream(ignore.getAbsolutePath());
        byte[] buf = new byte[1024];
        int len;
        while((len=ignoreStream.read(buf))>0){
             out.write(buf,0,len);
             }
        out.close();
        ignoreStream.close();
    }
    if(!projects.exists()){
        InputStream projectsStream = AutoJARCUI.class.getClassLoader().getResourceAsStream("com/github/gummywormz/AutoJARC/res/ConfigFile/autojarc.projects");
        OutputStream out = new FileOutputStream(projects.getAbsolutePath());
        byte[] buf = new byte[1024];
        int len;
        while((len=projectsStream.read(buf))>0){
             out.write(buf,0,len);
             }
        out.close();
        projectsStream.close();
    }else{projectList = new ArrayList<>(ProjectParser.getProjects());repaintTable();}
    if(!config.exists()){
        InputStream configStream = AutoJARCUI.class.getClassLoader().getResourceAsStream("com/github/gummywormz/AutoJARC/res/ConfigFile/autojarc.conf");
        OutputStream out = new FileOutputStream(config.getAbsolutePath());
        byte[] buf = new byte[1024];
        int len;
        while((len=configStream.read(buf))>0){
             out.write(buf,0,len);
             }
        out.close();
        configStream.close();
        configWindow.pack();
        configWindow.setLocationRelativeTo(null);
        configWindow.setVisible(true);
    }else{parseConfig(true);}
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        configWindow = new javax.swing.JFrame();
        jLabel4 = new javax.swing.JLabel();
        wsTextField = new javax.swing.JTextField();
        extDirTextField = new javax.swing.JTextField();
        chromeTextField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        configOK = new javax.swing.JButton();
        workspaceBrowse = new javax.swing.JButton();
        extBrowse = new javax.swing.JButton();
        chromeBrowse = new javax.swing.JButton();
        chromeFC = new javax.swing.JFileChooser();
        extensionFC = new javax.swing.JFileChooser();
        workspaceFC = new javax.swing.JFileChooser();
        errorDia = new javax.swing.JDialog();
        errorText = new javax.swing.JLabel();
        errorBtn = new javax.swing.JButton();
        aboutWindow = new javax.swing.JFrame();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        aboutText = new javax.swing.JTextPane();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        console = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        projectTable = new javax.swing.JTable();
        launchProjectBtn = new javax.swing.JButton();
        ignoreBtn = new javax.swing.JButton();
        scanBtn = new javax.swing.JButton();
        configBtn = new javax.swing.JButton();
        about = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel4.setText("Configuration");

        wsTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wsTextFieldActionPerformed(evt);
            }
        });

        jLabel5.setText("Workspace Directory:");

        jLabel6.setText("Extension Directory:");

        jLabel7.setText("Chrome Executeable:");

        configOK.setText("OK");
        configOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                configOKActionPerformed(evt);
            }
        });

        workspaceBrowse.setText("Browse");
        workspaceBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                workspaceBrowseActionPerformed(evt);
            }
        });

        extBrowse.setText("Browse");
        extBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                extBrowseActionPerformed(evt);
            }
        });

        chromeBrowse.setText("Browse");
        chromeBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chromeBrowseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout configWindowLayout = new javax.swing.GroupLayout(configWindow.getContentPane());
        configWindow.getContentPane().setLayout(configWindowLayout);
        configWindowLayout.setHorizontalGroup(
            configWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(configWindowLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(configWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, configWindowLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(configOK))
                    .addGroup(configWindowLayout.createSequentialGroup()
                        .addGroup(configWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(configWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(wsTextField)
                            .addComponent(extDirTextField, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(chromeTextField, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(configWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chromeBrowse, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(extBrowse, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(workspaceBrowse, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, configWindowLayout.createSequentialGroup()
                .addContainerGap(132, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(118, 118, 118))
        );
        configWindowLayout.setVerticalGroup(
            configWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(configWindowLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addGroup(configWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(wsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(workspaceBrowse))
                .addGap(18, 18, 18)
                .addGroup(configWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(extDirTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(extBrowse))
                .addGap(18, 18, 18)
                .addGroup(configWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chromeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(chromeBrowse))
                .addGap(18, 18, 18)
                .addComponent(configOK)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        chromeFC.setDialogTitle("AutoJARC - Select Chrome Executeable");
        chromeFC.setFileFilter(new FileNameExtensionFilter("Executeable Files", new String[] { "exe","app" }));

        extensionFC.setDialogTitle("AutoJARC - Select Extension Directory");
        extensionFC.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);

        workspaceFC.setDialogTitle("AutoJARC - Select Workspace");
        workspaceFC.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);

        errorDia.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        errorDia.setTitle("AutoJARC - Error!");

        errorText.setText("jLabel8");

        errorBtn.setText("OK");
        errorBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                errorBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout errorDiaLayout = new javax.swing.GroupLayout(errorDia.getContentPane());
        errorDia.getContentPane().setLayout(errorDiaLayout);
        errorDiaLayout.setHorizontalGroup(
            errorDiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(errorDiaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(errorText)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, errorDiaLayout.createSequentialGroup()
                .addContainerGap(339, Short.MAX_VALUE)
                .addComponent(errorBtn)
                .addContainerGap())
        );
        errorDiaLayout.setVerticalGroup(
            errorDiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(errorDiaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(errorText)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(errorBtn)
                .addContainerGap())
        );

        aboutWindow.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        aboutWindow.setTitle("AutoJARC - About");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel9.setText("AutoJARC");

        jLabel10.setText("Automatic Chrome Extension Builder for Android SDK Projects");

        aboutText.setContentType("text/html"); // NOI18N
        aboutText.setText("<html>\r\n  <head>\r\n\r\n  </head>\r\n  <body>\r\n    <p style=\"margin-top: 0\">\r\n      \rBy Paul \"Gummywormz\" Alves\n<br>\n<br>\n      <b><a href=\"https://github.com/gummywormz/AutoJARC\">Project Info / Source</a></b>\n<br>\n<br>\nThis project is licensed under a MIT license. Please see license.txt for more info.\n<br>\n<br>\nThis project uses Joakime's APK Parser Library which is available <b><a href = \"https://github.com/joakime/android-apk-parser\">here.</b></a>\n<br>\nLicense info for the library is available in License-android-apk-parser.txt.\n<br>\n<br>\nAdditional information about this project is available in the supplied README.md\n<br>\n<br>\nSpecial thanks to Google for the initial Android Shim and <a href = \"https://github.com/vladikoff\"><b>Vlad Filippov for the Chrome Browser port.</b></a>\n    </p>\r\n  </body>\r\n</html>\r\n");
        jScrollPane4.setViewportView(aboutText);

        jButton2.setText("OK");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout aboutWindowLayout = new javax.swing.GroupLayout(aboutWindow.getContentPane());
        aboutWindow.getContentPane().setLayout(aboutWindowLayout);
        aboutWindowLayout.setHorizontalGroup(
            aboutWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, aboutWindowLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addGap(290, 290, 290))
            .addGroup(aboutWindowLayout.createSequentialGroup()
                .addGroup(aboutWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(aboutWindowLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2))
                    .addGroup(aboutWindowLayout.createSequentialGroup()
                        .addGap(173, 173, 173)
                        .addComponent(jLabel10)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        aboutWindowLayout.setVerticalGroup(
            aboutWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, aboutWindowLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(aboutWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(aboutWindowLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE))
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("AutoJARC");

        console.setColumns(20);
        console.setRows(5);
        jScrollPane1.setViewportView(console);

        jLabel1.setText("Console:");

        projectTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Project", "Extension Directory"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        projectTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(projectTable);

        launchProjectBtn.setText("Launch");
        launchProjectBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                launchProjectBtnActionPerformed(evt);
            }
        });

        ignoreBtn.setText("Ignore");
        ignoreBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ignoreBtnActionPerformed(evt);
            }
        });

        scanBtn.setText("Scan");
        scanBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scanBtnActionPerformed(evt);
            }
        });

        configBtn.setText("Setup");
        configBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                configBtnActionPerformed(evt);
            }
        });

        about.setText("About");
        about.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setText("Welcome to AutoJARC");

        jLabel3.setText("Automatic Chrome Extension Builder for Android SDK Projects");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 747, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(launchProjectBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(ignoreBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(scanBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(configBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(about, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(309, 309, 309))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(257, 257, 257))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(scanBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(launchProjectBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ignoreBtn)
                        .addGap(8, 8, 8)
                        .addComponent(configBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(about)))
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void errorBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_errorBtnActionPerformed
        errorDia.dispose();
    }//GEN-LAST:event_errorBtnActionPerformed

    private void configBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_configBtnActionPerformed
        try {
            parseConfig(false);
        } catch (FileNotFoundException ex) {
            throwError("Configuration file was not found due to an anomaly in the space time continuum.");
        }
       configWindow.pack();
       configWindow.setLocationRelativeTo(null);
       configWindow.setVisible(true);
    }//GEN-LAST:event_configBtnActionPerformed

    
    private void parseConfig(boolean isLaunch) throws FileNotFoundException{
    BufferedReader b = new BufferedReader(new FileReader(workDir + sep + "autojarc.conf"));
        try {
            String ws = b.readLine().split("=")[1];
            String cp = b.readLine().split("=")[1];
            String ed = b.readLine().split("=")[1];
            b.close();
            if(!isLaunch){
            wsTextField.setText(ws);
            chromeTextField.setText(cp);
            extDirTextField.setText(ed);}
            else{updateConfig(ws,cp,ed);
            workspaceDirS = ws;
            extDirS = ed;}
        } catch (IOException ex) {
            throwError("Could not read the configuration file.");
        }catch(java.lang.ArrayIndexOutOfBoundsException e){
            throwError("Your configuration file is badly formatted. Delete it and restart this program.");
        }
    }
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        aboutWindow.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void aboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutActionPerformed
        aboutWindow.pack();
        aboutWindow.setLocationRelativeTo(null);
        aboutWindow.setVisible(true);
    }//GEN-LAST:event_aboutActionPerformed

    private void workspaceBrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_workspaceBrowseActionPerformed
        int returnVal = workspaceFC.showOpenDialog(AutoJARCUI.this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            wsTextField.setText(workspaceFC.getSelectedFile().getAbsolutePath());
        }
    }//GEN-LAST:event_workspaceBrowseActionPerformed

    private void extBrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_extBrowseActionPerformed
        int returnVal = extensionFC.showOpenDialog(AutoJARCUI.this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            extDirTextField.setText(extensionFC.getSelectedFile().getAbsolutePath());
        }
    }//GEN-LAST:event_extBrowseActionPerformed

    private void chromeBrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chromeBrowseActionPerformed
        int returnVal = chromeFC.showOpenDialog(AutoJARCUI.this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            chromeTextField.setText(chromeFC.getSelectedFile().getAbsolutePath());
        }
    }//GEN-LAST:event_chromeBrowseActionPerformed

    private void configOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_configOKActionPerformed
        String extDir = extDirTextField.getText();
        String wsDir = wsTextField.getText();
        String chromeDir = chromeTextField.getText();
        
        if(extDir.isEmpty() || wsDir.isEmpty() || chromeDir.isEmpty()){throwError("Please fill in all text fields.");return;}
        workspaceDirS = wsDir;
        extDirS = extDir;
        updateConfig(wsDir,chromeDir,extDir);
        try {
            configuration.output();
        } catch (IOException ex) {
           throwError("Could not write configuration file. You can use the program but you will have to enter your configuration on eahc launch.");
        }
        configWindow.dispose();
    }//GEN-LAST:event_configOKActionPerformed

    private void wsTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wsTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_wsTextFieldActionPerformed

    private void scanBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scanBtnActionPerformed
        GetFoldersWorker g = new GetFoldersWorker();
        g.execute();
        try {
            projectList = new ArrayList<>(g.get());
            repaintTable();
        } catch (InterruptedException | ExecutionException ex) {
            throwError("The scan was interrupted or something terrible happened.\n" + ex.getMessage());
        }
        try {
            ProjectParser.writeProjects(projectList);
        } catch (IOException ex) {
            throwError("Could not write back to the projects file. You can still interact with the program, but your list is not saved.");
        }
    }//GEN-LAST:event_scanBtnActionPerformed

    private void ignoreBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ignoreBtnActionPerformed
        int sel = projectTable.getSelectedRow();
        Project dir = projectList.get(sel);
        ignoreList.addDirectory(dir.getPackageName());
        repaintTable();
    }//GEN-LAST:event_ignoreBtnActionPerformed

    private void launchProjectBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_launchProjectBtnActionPerformed
        int row = projectTable.getSelectedRow();
        if(row<0){throwError("Please select a project to launch!");return;}
        Project p = projectList.get(row);
        
    }//GEN-LAST:event_launchProjectBtnActionPerformed

    private void updateConfig(String ws, String cp, String ed){
    configuration = new Configuration(ws,cp,ed);
    }
    
    private void repaintTable(){
    DefaultTableModel tm = new DefaultTableModel(new String[]{"Project","ExtensionDirectory"},projectList.size());
    
    for(Project p : projectList){
        if(!ignoreList.isIgnored(p.getPackageName())){
        String[] rowData = {p.getAppName(),Boolean.toString(p.hasExtensionDirectory())};
        tm.addRow(rowData);}
    }
    projectTable.setModel(tm);
    }
    
    /**
     * Displays an error message with the given text
     * @param text The error text to display
     */
    public static void throwError(String text){
    errorText.setText(text);
    errorDia.pack();
    errorDia.setLocationRelativeTo(null); //center ze window
    errorDia.setVisible(true);
    }
    
    /**
     * Returns the workspace path
     * @return The workspace path
     */
    public static String getWorkspace(){
        return workspaceDirS;
    }
    
    /**
     * Returns the extension directory
     * @return the extensino directory
     */
    public static String getExtensionDir(){
        return extDirS;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AutoJARCUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AutoJARCUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AutoJARCUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AutoJARCUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AutoJARCUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton about;
    private javax.swing.JTextPane aboutText;
    private javax.swing.JFrame aboutWindow;
    private javax.swing.JButton chromeBrowse;
    private javax.swing.JFileChooser chromeFC;
    private javax.swing.JTextField chromeTextField;
    private javax.swing.JButton configBtn;
    private javax.swing.JButton configOK;
    private javax.swing.JFrame configWindow;
    private static javax.swing.JTextArea console;
    private javax.swing.JButton errorBtn;
    private static javax.swing.JDialog errorDia;
    private static javax.swing.JLabel errorText;
    private javax.swing.JButton extBrowse;
    private javax.swing.JTextField extDirTextField;
    private javax.swing.JFileChooser extensionFC;
    private javax.swing.JButton ignoreBtn;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JButton launchProjectBtn;
    private javax.swing.JTable projectTable;
    private javax.swing.JButton scanBtn;
    private javax.swing.JButton workspaceBrowse;
    private javax.swing.JFileChooser workspaceFC;
    private javax.swing.JTextField wsTextField;
    // End of variables declaration//GEN-END:variables
}
