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
 * Object to keep track of the source of the pictures.
 *
 * @author  Mark Roth
 */
public class PhotoSource {

    /** The list of photos found so far */
    private ArrayList photoList = new ArrayList();
    
    /** The source directory */
    private File sourceDir;
    
    /** Creates new PhotoSource */
    public PhotoSource() {
    }
    
    /**
     * Clears the list of photos thus far
     */
    private void clearPhotos() {
        photoList.clear();
    }
    
    /**
     * Clears the photo list, and scans the given directory for photos 
     * and adds them to the list of current photos.  If the directoriy
     * given is the same as the current directory, nothing is changed.
     */
    public void scanDirectory( File dir ) {
        if( (dir == null) && (this.sourceDir != null) ) {
            clearPhotos();
            this.sourceDir = null;
        }
        else if( (dir != null) && !dir.equals( this.sourceDir ) ) {
            clearPhotos();
            this.sourceDir = dir;

            String[] files = dir.list();
            // could happen if we have no access to this directory.
            if( files == null ) files = new String[0];
            Arrays.sort( files );
            for( int i = 0; i < files.length; i++ ) {
                File file = new File( dir, files[i] );
                String name = file.getName();
                String nameLower = name.toLowerCase();
                if( nameLower.endsWith( ".jpg" ) || 
                    nameLower.endsWith( ".jpeg" ) ||
                    nameLower.endsWith( ".gif" ) ||
                    nameLower.endsWith( ".png" ) ) 
                {
                    OutputPhoto ophoto = 
                        new OutputPhoto( file, 
                            convertToOutputFilename( file.getName() ), 
                            true );
                    photoList.remove( ophoto );
                    photoList.add( ophoto );
                }
            }
        }
    }
    
    /**
     * Retrieves a mutable array list of pictures for this source.  Each
     * element in the ArrayList is an OutputPhoto object.
     */
    public ArrayList getPhotos() {
        return photoList;
    }
    
    /**
     * Retrieves a new array list of all selected photos for this source.
     * Each element in the ArrayList is an OutputPhoto element.
     */
    public ArrayList getSelectedPhotos() {
        ArrayList result = new ArrayList();
        
        for( int i = 0; i < photoList.size(); i++ ) {
            OutputPhoto outPhoto = (OutputPhoto)photoList.get( i );
            if( outPhoto.isSelected() ) {
                result.add( outPhoto );
            }
        }
        
        return result;
    }
    
    /**
     * Returns a count of the number of photos we are outputting
     */
    public int getOutputPhotoCount() {
        int count = 0;
        for( int i = 0; i < photoList.size(); i++ ) {
            OutputPhoto ophoto = (OutputPhoto)photoList.get( i );
            if( ophoto.isSelected() ) count++;
        }
        return count;
    }
    
    /**
     * Retrieves the source directory, containing the photos.
     */
    public File getSourceDir() {
        return sourceDir;
    }
    
    /**
     * Returns an acceptable output filename, given an input
     * file.  Converts to lowercase and replaces all spaces with
     * underscores.
     */
    private String convertToOutputFilename( String filename ) {
        return filename.toLowerCase().replace( ' ', '_' );
    }

}
