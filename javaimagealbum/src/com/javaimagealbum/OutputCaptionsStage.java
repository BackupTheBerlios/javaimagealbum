/*
 * <license>
 * The contents of this file are subject to the Mozilla Public License 
 * Version 1.1 (the "License"); you may not use this file except in 
 * compliance with the License.  You may obtain a copy of the License 
 * at http://www.mozilla.org/MPL/
 * 
 * Software distributed under the License is distributed on an 
 * "AS IS" basis, WITHOUT WARRANTY OF ANY KIND, either express or 
 * implied. See the License for the specific language governing rights 
 * and limitations under the License.
 * 
 * The Original Code is Web Photo Publisher.
 * 
 * The Initial Developer of the Original Code is Mark Roth.  Portions 
 * created by Mark Roth are Copyright (C) 2003 Mark Roth.  
 * All Rights Reserved.
 * 
 * Contributor(s) listed below.
 * </license>
 */

package com.javaimagealbum;

import java.io.*;
import java.util.*;

/**
 * Saves captions in source folder.
 *
 * @author  mroth
 */
public class OutputCaptionsStage extends OutputStage {

    /** Current progress */
    private int progress = 0;
    
    /** Creates new OutputCaptionsStage */
    public OutputCaptionsStage( PublishManager publishManager ) {
        super( publishManager );
    }

    /**
     * Performs generation for this stage
     */
    public void generate() {
        setGenerationMessage( "Storing Captions..." );
        
        // Get all photos, not just selected photos:
        ArrayList photos = publishManager.getPhotoSource().getPhotos();
        
        int numPhotos = photos.size();

        try {
            // Save description and title
            for( int index = 0; !isStopGeneration() && (index < numPhotos); 
                index++ ) 
            {
                progress = index * 100 / Math.max(numPhotos, 1);
                updateProgress();
                setGenerationMessage( "Storing Captions (" + (index+1) + 
                    "/" + numPhotos + ")..." );
                OutputPhoto outPhoto = (OutputPhoto)photos.get( index );
                File sourceFile = outPhoto.getSource();
                File captionFile = new File( sourceFile.getParentFile(), 
                    sourceFile.getName() + ".txt" );
                String caption = outPhoto.getCaption();
                updateCaption( captionFile, caption );
            }
            
            // All captions are secure.
            publishManager.setUnsavedCaptions( false );
        }
        catch( IOException e ) {
            error( "Could not write caption file: " + e.getMessage() );
        }
    }

    /**
     * If the caption is not blank, checks the existing caption file (
     * if it exists) to see if it differs.  If the file does not exist,
     * it is written.  If the file exists, and the caption differs, 
     * the file is rewritten with the new caption.  If it does not 
     * differ, the file is not updated.
     * <p>
     * If the caption is blank, if a caption file does not exist it is
     * not created.  If a caption file exists and the caption is blank
     * it is not rewritten.  If the caption file exists and the
     * caption is not blank, the file is rewritten with a blank caption.
     */
    private void updateCaption( File captionFile, String caption )
        throws IOException 
    {
        boolean isBlankCaption = caption.trim().equals( "" );
        
        if( !isBlankCaption ) {
            if( captionFile.exists() ) {
                String currentCaption = 
                    OutputPhoto.readCaption( captionFile );
                if( !currentCaption.equals( caption ) ) {
                    // Captions are not equal.  Update with new caption.
                    OutputPhoto.writeCaption( captionFile, caption );
                }
            }
            else {
                OutputPhoto.writeCaption( captionFile, caption );
            }
        }
        else {
            // Blank caption.
            if( captionFile.exists() ) {
                String currentCaption = 
                    OutputPhoto.readCaption( captionFile );
                if( !currentCaption.trim().equals( "" ) ) {
                    OutputPhoto.writeCaption( captionFile, caption );
                }
            }
        }
    }
    
    /**
     * Returns current progress, on a scale from 0 to 100
     */
    public int getProgress() {
        return this.progress;
    }
    
    /**
     * Returns the time complexity of this task.  This makes the progress
     * meter more accurate.
     */
    public int getTimeComplexity() {
        return TIME_COMPLEXITY_VERY_FAST;
    }
    
}
