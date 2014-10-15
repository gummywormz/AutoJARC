/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.gummywormz.AutoJARC.Background;

/**
 * Gives a reason for the success or failure of the verification of a project directory
 * @author Paul Alves
 */
public class VerifierFlag {
    
    /**
     * The verification failed because no APK was found. It was not generated or it was a library project.
     */
    public static final int FAILURE_NO_APK_FOUND = 0;
    
    /**
     * The verification failed because there was no project in the folder
     */
    public static final int FAILURE_NO_PROJECT_FOUND = 1;
    
    /**
     * The verification failed because the project was missing one or more key elements (strings.xml or the package name etc)
     */
    public static final int FAILURE_INVALID_OR_CORRUPTED_PROJECT = 2;
    
    /**
     *  The verification failed because of an IO error.
     */
    public static final int FAILURE_IO_ERROR = 3;
    
    /**
     * Verification succeeded
     */
    public static final int SUCCESS = 4;
    
    
    private final boolean didSucceed;
    private final int why;
    
    /**
     * Constructs a new flag 
     * @param success Whether the verification failed or not (True for success. false for failure
     * 
     * @param reason Reason for success or failure
     */
    public VerifierFlag(boolean success, int reason){
    didSucceed = success;
    why = reason;
    }
    
    /**
     * Gets whether the verification succeeded
     * @return True if it succeeded, false if it failed
     */
    public boolean getSuccess(){
    return didSucceed;
    }
    
    /**
     * Returns the reason for failure as an integer
     * @return the reason for failure as an integer
     */
    public int getReason(){
    return why;
    }
    
    /**
     * Returns the reason for failure as the variable name.
     * @return the reason for failure as the variable name.
     */
    public String getReasonAsString(){
    if(why == 0){return "FAILURE_NO_APK_FOUND";}
    if(why == 1){return "FAILURE_NO_PROJECT_FOUND";}
    if(why == 2){return "FAILURE_INVALID_OR_CORRUPTED_PROJECT";}
    if(why == 3){return "FAILURE_IO_ERROR";}
    if(why == 4){return "SUCCESS";}
    return null;
    }
    
     /**
     * Returns the reason for failure as a displayable message to the user.
     * @return the reason for failure as a displayable message to the user.
     */
    public String getReasonAsMessage(){
    if(why == 0){return "No APK was found. This is either a library project or it was not generated";}
    if(why == 1){return "There is no android project in this folder";}
    if(why == 2){return "A key component of the project was not found. Please try rebuilding and / or cleaning.";}
    if(why == 3){return "Unable to access one or more elements of the project. Please check that you have appropriate permissions to access the files.";}
    if(why == 4){return "Verification succeeded.";}
    return null;
    }
    
}
