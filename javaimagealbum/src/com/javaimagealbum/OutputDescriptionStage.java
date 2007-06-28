/*
 * <license>
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 * 
 * The Original Code is Java Image Album.
 * 
 * The Initial Developer of the Original Code is Mirko Actis Grosso.  Portions 
 * created by Mirko Actis Grosso are Copyright (C) 2006 Mirko Actis Grosso.  
 * All Rights Reserved.
 * 
 * Contributor(s) listed below.
 * </license>
 */

package com.javaimagealbum;

import java.io.*;

/**
 * Saves captions in source folder.
 *
 * @author  Mirko Actis Grosso
 */
public class OutputDescriptionStage extends OutputStage {

    /** Current progress */
    private int progress = 0;
    
    /** Creates new OutputCaptionsStage */
    public OutputDescriptionStage( PublishManager publishManager ) {
        super( publishManager );
    }

    /**
     * Performs generation for this stage
     */
    public void generate() {
        setGenerationMessage( "Storing Title and Description ..." );
        
        try {
            // Save description and title
            File captionFile = new File( publishManager.getPhotoSource().getSourceDir(),
                    "title.txt" );
            String caption = publishManager.getAlbumTitle();
            updateCaption( captionFile, caption );

            captionFile = new File( publishManager.getPhotoSource().getSourceDir(),
                    "description.txt" );
            caption = publishManager.getAlbumDescription();
            updateCaption( captionFile, caption );

            // All data are secure.
            publishManager.setUnsavedDescriptions( false );
        }
        catch( IOException e ) {
            error( "Could not write Title or Description file: " + e.getMessage() );
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
     * Load Title.
     */
    public static String loadTitle( File sourceDir) {
        String title = OutputPhoto.guessCaption( new File ( sourceDir, "title.txt" ) );
        return title;
    }

    /**
     * Load Description.
     */
    public static String loadDescription( File sourceDir) {
        String description = OutputPhoto.guessCaption( new File ( sourceDir, "description.txt" ) );
        return description;
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
