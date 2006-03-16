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
 * Mirko Actis
 * </license>
 */

package com.javaimagealbum;

import java.io.*;

import com.javaimagealbum.exif.ExifHashMap;
import com.javaimagealbum.exif.ExifReader;

/**
 * Stores information about a photograph to be output.
 *
 * @author  Mark Roth
 * @author  Mirko Actis
 */
public class OutputPhoto {

    /** The source file for the photograph */
    private File source;
    
    /** The destination file name */
    private String filename;
    
    /** The caption */
    private String caption;
    
    /** True if this photo is selected for publishing, or false if not */
    private boolean selected;

    /** The output filename for the thumbnail of this image */
    private String outThumbnailName;
    
    /** 
     * The output filename for the image of this image 
     * (might be different if file format changed, for example)
     */
    private String outImageName;
    
    /** 
     * Set to true if we wrote a resized version of this file to the
     * output directory.  Set to false if not.
     */
    private boolean outputResized;
    
    /**
     * The html page containing the detail for this photo.
     */
    private String detailHTMLFilename;
    
    /**
     * The HashMap containing the EXIF information for this photo.
     */
    private ExifHashMap exifHashMap;
    
    /** 
     * Set to true if this photo contain EXIF information.
     * Set to false if not.
     */
    private boolean isExif = false;
    
    /** Creates new OutputPhoto */
    public OutputPhoto( File source, String filename, boolean selected ) {
        this.source = source;
        this.filename = filename;
        this.selected = selected;
        
        // Attempt to guess the caption, based on the passed-in information.
        this.caption = OutputPhoto.guessCaption( this.source );
        
        // Attempt to read EXIF information.
        try {
            this.isExif = ExifReader.isExif( this.source );
            if ( this.isExif ) {
                exifHashMap = new ExifHashMap(ExifReader.decode( this.source ));
            }
        } catch (java.lang.ArrayIndexOutOfBoundsException ex) {
            // If error occour during EXIF reading set it to false.
            this.isExif = false;
        }
        
        
    }
    
    public File getSource() {
        return source;
    }
    
    public String getFilename() {
        return filename;
    }
    
    public String getCaption() {
        return caption;
    }
    
    public boolean isSelected() {
        return selected;
    }
    
    public void setDetailHTMLFilename( String detailHTMLFilename ) {
        this.detailHTMLFilename = detailHTMLFilename;
    }
    
    public String getDetailHTMLFilename() {
        return this.detailHTMLFilename;
    }
    
    public void setFilename( String filename ) {
        this.filename = filename;
    }
    
    public void setCaption( String caption ) {
        this.caption = caption;
    }
    
    public void setSelected( boolean selected ) {
        this.selected = selected;
    }
    
    public void setOutputResized( boolean outputResized ) {
        this.outputResized = outputResized;
    }
    
    public boolean getOutputResized() {
        return this.outputResized;
    }

    /* Start EXIF getter */
    public boolean isExif() {
        return this.isExif;
    }
    public String getCreationDate() {
        return this.exifHashMap.getDate(ExifHashMap.CREATION_DATE);
    }

    public String getDigitizedDate() {
        return this.exifHashMap.getDate(ExifHashMap.DIGITIZED_DATE);
    }

    public String getModifiedDate() {
        return this.exifHashMap.getDate(ExifHashMap.MODIFIED_DATE);
    }

    public String getCameraType() {
        return this.exifHashMap.getCameraType();
    }

    public String getCameraMake() {
        return this.exifHashMap.getCameraMake();
    }

    public String getCameraModel() {
        return this.exifHashMap.getCameraModel();
    }

    public String getFStop() {
        return this.exifHashMap.getFStop();
    }

    public String getExposureProgram() {
        return this.exifHashMap.getExposureProgram();
    }
    
    public String getLightSource() {
        return this.exifHashMap.getLightSource();
    }

    public String getISO() {
        return this.exifHashMap.getISO();
    }

    public String getShutterSpeed() {
        return this.exifHashMap.getShutterSpeed();
    }

    public String getFlash() {
        return this.exifHashMap.getFlash();
    }

    public String getImageHeight() {
        return this.exifHashMap.getImageHeight();
    }

    public String getImageWidth() {
        return this.exifHashMap.getImageWidth();
    }

    public String getAllExif() {
        return this.exifHashMap.toString();
    }
    /* End EXIF getter */
    
    public int hashCode() {
        return source.hashCode();
    }
    
    public boolean equals( Object obj ) {
        return (obj instanceof OutputPhoto) && 
            source.equals( ((OutputPhoto)obj).source );
    }

    /** Getter for property outThumbnailName.
     * @return Value of property outThumbnailName.
     */
    public String getOutThumbnailName() {
        return outThumbnailName;
    }
    
    /** Setter for property outThumbnailName.
     * @param outThumbnailName New value of property outThumbnailName.
     */
    public void setOutThumbnailName(String outThumbnailName) {
        this.outThumbnailName = outThumbnailName;
    }
    
    /** Getter for property outImageName.
     * @return Value of property outImageName.
     */
    public String getOutImageName() {
        return outImageName;
    }
    
    /** Setter for property outImageName.
     * @param outImageName New value of property outImageName.
     */
    public void setOutImageName(String outImageName) {
        this.outImageName = outImageName;
    }
    
    /**
     * Attempts to guess the caption for this photo, based on known
     * information.  The following steps are taken (in order):
     * <ul>
     *   <li>A filename.jpg.txt or filename.gif.txt file is searched for, 
     *       in the source directory (whichever is appropriate).
     *       If found, the contents are used as the default caption.</li>
     *   <li>A filename.txt file is searched for, in the source directory.
     *       If found, the contents are used as the default caption.</li>
     *   <li>A blank caption is used.</li>
     * </ul>
     */
    public static String guessCaption( File source ) {
        String caption = null;
        String fname = source.getName();
        if( fname.indexOf( '.' ) != -1 ) {
            int dotIndex = fname.lastIndexOf( '.' );
            String name = fname.substring( 0, dotIndex );
            String ext = fname.substring( dotIndex );
            
            File[] guesses = new File[4];
            File guess = null;
            
            guesses[0] = new File( source.getParentFile(), fname + ".txt" );
            guesses[1] = new File( source.getParentFile(), fname + ".TXT" );
            guesses[2] = new File( source.getParentFile(), name + ".txt" );
            guesses[3] = new File( source.getParentFile(), name + ".TXT" );
            
            for( int i = 0; (guess == null) && (i < guesses.length); i++ ) {
                if( guesses[i].exists() ) {
                    guess = guesses[i];
                }
            }
            
            if( guess != null ) {
                try {
                    caption = OutputPhoto.readCaption( guess );
                }
                catch( FileNotFoundException e ) {
                    // we could not find the caption.  ignore.
                }
                catch( IOException e ) {
                    // we could not find the caption.  ignore.
                }
            }
        }
        
        if( caption == null ) caption = "";
        
        return caption;
    }
    
    public static String readCaption( File captionFile ) 
        throws IOException
    {
        String caption = "";
    
        // At least one guess was right.  Read the caption!
        BufferedReader in = new BufferedReader( 
            new FileReader( captionFile ) );
        String line;
        while( (line = in.readLine()) != null ) {
            caption += line + "\n";
        }
        in.close();
        
        return caption.trim();
    }
    
    public static void writeCaption( File captionFile, String caption )
        throws IOException
    {
        PrintWriter out = new PrintWriter( 
            new FileWriter( captionFile ) );
        out.println( caption );
        out.close();
    }
    
    // Modifiers (not yet implemented):
    
    private void rotateCW() {
        // XXX - to be implemented
    }
    
    private void rotateCCW() {
        // XXX - to be implemented
    }
    
}
