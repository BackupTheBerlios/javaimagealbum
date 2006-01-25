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
import java.awt.*;
import javax.swing.*;

import com.javaimagealbum.resources.ResourceFactory;

/**
 * Manages the photo publishing operation
 *
 * @author  Mark Roth
 * @author  Mirko Actis
 */
public class PublishManager extends Observable {
    static ResourceBundle res = ResourceFactory.getBundle();
    
    // Debug flag to display generation timings
    private static final boolean DISPLAY_TIMINGS = false;

    // The object to keep track of where the photos are coming from
    private PhotoSource photoSource;
    
    // The directory to send output to
    private File outputDirectory;
    
    // Generation parameters:
    private String albumTitle = "Photos";
    private String albumDescription = null;
    
    // Non-null when page generation is in progress.
    private Thread generationThread = null;
    private int generationProgress = 0;
    private String generationMessage = "";
    private boolean stopGeneration = false;
    private boolean generationSuccessful = false;
    private boolean generating = false;
    private String errorMessage = null;
    private boolean resizeAll = false;
    private int resizeAllWidth = 640;
    private int resizeAllHeight = 480;
    private boolean resizePortraits = false;
    private int resizePortraitsWidth = 480;
    private int resizePortraitsHeight = 640;
    private boolean storeCaptions = false;
    private boolean publishFullSize = false;
//    private boolean generationComplete = false;
    private int outputColumns = 4;
    private String thumbnailsPerPage = Constants.UNLIMITED_THUMBNAILS;
    private boolean linkToAlbumIndex = false;
    private boolean descriptionInEmptyPage = false;
    private String photoPosition = Constants.PHOTO_POSITION_LEFT;
    private String captionPosition = Constants.CAPTION_POSITION_BELOW;
    private String captionAlign = Constants.CAPTION_ALIGN_LEFT;
    private boolean showExif = false;
    private Locale outputLanguage = res.getLocale();
    
    // Keep track of what data is unsaved so we can warn the user
    // if they click Cancel.
    // Set to true if the user changed one or more captions.
    private boolean unsavedCaptions = false;
    // Set to true if the program thinks the user has the intent to 
    // generate more photos but hasn't yet.
    private boolean unsavedPhotos = false;
    // Set to true if photos were ever successfully generated.  If so,
    // then unsavedPhotos will never be set to true again.
    private boolean everGeneratedPhotos = false;
    
    /** Creates new PublishManager */
    public PublishManager() {
        photoSource = new PhotoSource();
        
        // Set default input and output directories:
        Settings settings = Settings.getInstance();
        String inputDirString = settings.getProperty( 
            Constants.DEFAULT_INPUT_DIR, 
            System.getProperty( "user.home" ) );
        String outputDirString = settings.getProperty( 
            Constants.DEFAULT_OUTPUT_DIR,
            inputDirString + "/publish" );
        photoSource.scanDirectory( new File( inputDirString ) );
        setOutputDirectory( new File( outputDirString ) );
        
        loadOutputSettings();
    }
    
    private void loadOutputSettings() {
        Settings settings = Settings.getInstance();
        setAlbumTitle( settings.getProperty( Constants.ALBUM_TITLE, 
            "Photos" ) );
        setAlbumDescription( settings.getProperty( Constants.ALBUM_DESCRIPTION, 
        "" ) );
        setOutputColumns( Integer.parseInt(
            settings.getProperty( Constants.OUTPUT_COLUMNS, "4" ) ) );
        setThumbnailsPerPage( settings.getProperty( 
            Constants.THUMBNAILS_PER_PAGE, "unlimited" ) );
        setResizeAll( settings.getProperty( Constants.RESIZE_ALL, 
            "true" ).toLowerCase().equals( "true" ) );
        setResizeAllWidth( Integer.parseInt( 
            settings.getProperty( Constants.RESIZE_ALL_WIDTH, "640" ) ) );
        setResizeAllHeight( Integer.parseInt(
            settings.getProperty( Constants.RESIZE_ALL_HEIGHT, "480" ) ) );
        setResizePortraits( settings.getProperty( Constants.RESIZE_PORTRAITS,
            "true" ).toLowerCase().equals( "true" ) );
        setResizePortraitsWidth( Integer.parseInt(
            settings.getProperty( Constants.RESIZE_PORTRAITS_WIDTH, 
            "480" ) ) );
        setResizePortraitsHeight( Integer.parseInt(
            settings.getProperty( Constants.RESIZE_PORTRAITS_HEIGHT, 
            "640" ) ) );
        setPublishFullSize( settings.getProperty( Constants.PUBLISH_FULL_SIZE,
            "false" ).toLowerCase().equals( "true" ) );
        setStoreCaptions( settings.getProperty( Constants.STORE_CAPTIONS,
            "true" ).toLowerCase().equals( "true" ) );
        setLinkToAlbumIndex( settings.getProperty( 
            Constants.LINK_TO_ALBUM_INDEX, "true" ).toLowerCase().equals( 
            "true" ) );
        setDescriptionInEmptyPage( settings.getProperty( 
                Constants.DESCRIPTION_IN_EMPTY_PAGE, "true" ).toLowerCase().equals( 
                "true" ) );
        setShowExif( settings.getProperty( 
                Constants.LINK_TO_ALBUM_INDEX, "true" ).toLowerCase().equals( 
                "true" ) );
//        setOutputLanguage( settings.getProperty( 
//                Constants.OUTPUT_LANGUAGE, res.getLocale().toString() ) );
        setCaptionPosition( settings.getProperty(
            Constants.CAPTION_POSITION, Constants.CAPTION_POSITION_BELOW ) );
        setCaptionAlign( settings.getProperty(
                Constants.CAPTION_ALIGN, Constants.CAPTION_ALIGN_LEFT ) );
        setPhotoPosition( settings.getProperty(
            Constants.PHOTO_POSITION, Constants.PHOTO_POSITION_LEFT ) );
    }
    
    public void persistOutputSettings() {
        Settings settings = Settings.getInstance();
        settings.setProperty( Constants.ALBUM_TITLE, 
            getAlbumTitle() );
        settings.setProperty( Constants.ALBUM_DESCRIPTION, 
            getAlbumDescription() );
        settings.setProperty( Constants.OUTPUT_COLUMNS,
            "" + getOutputColumns() );
        settings.setProperty( Constants.THUMBNAILS_PER_PAGE,
            getThumbnailsPerPage() );
        settings.setProperty( Constants.RESIZE_ALL, 
            "" + getResizeAll() );
        settings.setProperty( Constants.RESIZE_ALL_WIDTH, 
            "" + getResizeAllWidth() );
        settings.setProperty( Constants.RESIZE_ALL_HEIGHT,
            "" + getResizeAllHeight() );
        settings.setProperty( Constants.RESIZE_PORTRAITS,
            "" + getResizePortraits() );
        settings.setProperty( Constants.RESIZE_PORTRAITS_WIDTH,
            "" + getResizePortraitsWidth() );
        settings.setProperty( Constants.RESIZE_PORTRAITS_HEIGHT,
            "" + getResizePortraitsHeight() );
        settings.setProperty( Constants.PUBLISH_FULL_SIZE,
            "" + getPublishFullSize() );
        settings.setProperty( Constants.STORE_CAPTIONS,
            "" + getStoreCaptions() );
        settings.setProperty( Constants.LINK_TO_ALBUM_INDEX, 
            "" + getLinkToAlbumIndex() );
        settings.setProperty( Constants.DESCRIPTION_IN_EMPTY_PAGE, 
                "" + getDescriptionInEmptyPage() );
        settings.setProperty( Constants.SHOW_EXIF, 
                "" + getShowExif() );
        settings.setProperty( Constants.OUTPUT_LANGUAGE, 
                "" + getOutputLanguage() );        
        settings.setProperty( Constants.PHOTO_POSITION, 
            getPhotoPosition() );
        settings.setProperty( Constants.CAPTION_POSITION,
            getCaptionPosition() );
        settings.setProperty( Constants.CAPTION_ALIGN,
            getCaptionAlign() );
    }
    
    /**
     * Set to true if one or more captions changed
     */
    public void setUnsavedCaptions( boolean unsavedCaptions ) {
        this.unsavedCaptions = unsavedCaptions;
    }
    
    /**
     * Returns true if one or more captions changed
     */
    public boolean isUnsavedCaptions() {
        return this.unsavedCaptions;
    }
    
    /**
     * Set to true if the program believes the user has the intent
     * to generate photos.  Used to warn the user later, if accidentally
     * trying to exit.
     */
    public void setUnsavedPhotos( boolean unsavedPhotos ) {
        this.unsavedPhotos = unsavedPhotos;
    }
    
    /**
     * Returns true if there are unsaved photos and the user never
     * generated photos before.
     */
    public boolean isUnsavedPhotos() {
        return !this.everGeneratedPhotos && this.unsavedPhotos;
    }
    
    public PhotoSource getPhotoSource() {
        return photoSource;
    }
    
    public void setOutputDirectory( File dir ) {
        this.outputDirectory = dir;
        Settings.getInstance().setProperty( 
            Constants.DEFAULT_OUTPUT_DIR,
            dir.getAbsolutePath() );
    }
    
    public File getOutputDirectory() {
        return this.outputDirectory;
    }
    
    public void setAlbumTitle( String title ) {
        this.albumTitle = title;
    }
    
    public String getAlbumTitle() {
        return this.albumTitle;
    }
    
    public void setAlbumDescription( String description ) {
        this.albumDescription = description; 
    }

    public String getAlbumDescription() {
        return this.albumDescription;
    }
    
    public void setOutputColumns( int outputColumns ) {
        this.outputColumns = outputColumns;
    }
    
    public int getOutputColumns() {
        return this.outputColumns;
    }
    
    public void setThumbnailsPerPage( String thumbnailsPerPage ) {
        this.thumbnailsPerPage = thumbnailsPerPage;
    }
    
    public String getThumbnailsPerPage() {
        return this.thumbnailsPerPage;
    }
    
    public void setResizeAll( boolean resizeAll ) {
        this.resizeAll = resizeAll;
    }
    
    public boolean getResizeAll() {
        return this.resizeAll;
    }
    
    public void setResizeAllWidth( int resizeAllWidth ) {
        this.resizeAllWidth = resizeAllWidth;
    }
    
    public int getResizeAllWidth() {
        return this.resizeAllWidth;
    }
    
    public void setResizeAllHeight( int resizeAllHeight ) {
        this.resizeAllHeight = resizeAllHeight;
    }
    
    public int getResizeAllHeight() {
        return this.resizeAllHeight;
    }
    
    public void setResizePortraits( boolean resizePortraits ) {
        this.resizePortraits = resizePortraits;
    }
    
    public boolean getResizePortraits() {
        return this.resizePortraits;
    }
    
    public void setResizePortraitsWidth( 
        int resizePortraitsWidth ) 
    {
        this.resizePortraitsWidth = resizePortraitsWidth;
    }
    
    public int getResizePortraitsWidth() {
        return this.resizePortraitsWidth;
    }
    
    public void setResizePortraitsHeight( int resizePortraitsHeight ) {
        this.resizePortraitsHeight = resizePortraitsHeight;
    }

    public int getResizePortraitsHeight() {
        return this.resizePortraitsHeight;
    }
    
    public void setPublishFullSize( boolean publishFullSize ) {
        this.publishFullSize = publishFullSize;
    }
    
    public boolean getPublishFullSize() {
        return this.publishFullSize;
    }
    
    public void setStoreCaptions( boolean storeCaptions ) {
        this.storeCaptions = storeCaptions;
    }
    
    public boolean getStoreCaptions() {
        return this.storeCaptions;
    }
    
    public void setLinkToAlbumIndex( boolean linkToAlbumIndex ) {
        this.linkToAlbumIndex = linkToAlbumIndex;
    }
    
    public boolean getLinkToAlbumIndex() {
        return this.linkToAlbumIndex;
    }
    
    public void setDescriptionInEmptyPage( boolean descriptionInEmptyPage ) {
        this.descriptionInEmptyPage = descriptionInEmptyPage;
    }
    
    public boolean getDescriptionInEmptyPage() {
        return this.descriptionInEmptyPage;
    }
    
    public void setShowExif( boolean showExif ) {
        this.showExif = showExif;
    }
    
    public boolean getShowExif() {
        return this.showExif;
    }
    
    public void setOutputLanguage( Locale outputLanguage ) {
        this.outputLanguage = outputLanguage;
    }
    
    public Locale getOutputLanguage() {
        return this.outputLanguage;
    }

    public java.lang.String getPhotoPosition() {
        return photoPosition;
    }
    
    public void setPhotoPosition(java.lang.String photoPosition) {
        this.photoPosition = photoPosition;
    }
    
    public java.lang.String getCaptionPosition() {
        return captionPosition;
    }
    
    public java.lang.String getCaptionAlign() {
        return captionAlign;
    }
    
    public void setCaptionPosition(java.lang.String captionPosition) {
        this.captionPosition = captionPosition;
    }
    
    public void setCaptionAlign(java.lang.String captionAlign) {
        this.captionAlign = captionAlign;
    }
    
    /**
     * Builds a summary of the user's selections in HTML.
     */
    public String buildSummary() {
        int photoCount = photoSource.getOutputPhotoCount();
        String sourceDir = photoSource.getSourceDir().getAbsolutePath();
        String destDir = outputDirectory.getAbsolutePath();
        
        String thumbnailSummary;
        String sizeSummary;
        String captionsSummary;
        String linkSummary;
        String exifSummary;
        String detailSummary;
        String outputLanguageSummary;
        
        if( resizeAll ) {
            sizeSummary = 
                "  <li>" + (resizePortraits ? "All landscape" : "All") + 
                " photos will be resized to " + 
                resizeAllWidth + " by " + resizeAllHeight +
                ".</li>\n";
            if( resizePortraits ) {
                sizeSummary += 
                    "  <li>All portrait photos will be resized to " +
                    resizePortraitsWidth + " by " + resizePortraitsHeight +
                    ".</li>\n";
            }
                
            if( publishFullSize ) {
                sizeSummary += 
                    "  <li>Original-sized images will be " +
                    "published as well.</li>\n";
            }
        
        }
        else {
            sizeSummary = 
                "  <li>All photos will be kept at their original size</li>\n";
        }
        
        if( storeCaptions ) {
            captionsSummary =
                "  <li>Captions will be saved as .txt files in " +
                "the " + sourceDir + " folder.</li>\n";
        }
        else {
            captionsSummary = "";
        }

        // Construct thumbnail pages summary:
        int numPages = 1;
        if( getThumbnailsPerPage().equals( Constants.UNLIMITED_THUMBNAILS ) ) {
            numPages = 1;
        }
        else {
            int perPage = Integer.parseInt( getThumbnailsPerPage() );
            numPages = Math.max( 1, 1 + (photoCount - 1) / perPage );
        }

        if( numPages == 1 ) {
            thumbnailSummary = 
                "  <li>An album index page will be generated, with ";
        }
        else {
            thumbnailSummary = 
                "  <li>There will be " + numPages + 
                " album index pages, with ";
        }
        thumbnailSummary += 
            Math.min( photoCount, getOutputColumns() ) + 
            " column" + 
            (((photoCount != 1) && (getOutputColumns() != 1)) ? "s" : "") + 
            " of thumbnails.</li>\n";
            
        // Create link summary:
        linkSummary = "";
        if( linkToAlbumIndex ) {
            linkSummary = "  <li>A link will be generated to the parent " +
                "Album Index folder</li>\n";
        }

        // Create Exif summary:
        exifSummary = "";
        if( showExif ) {
            exifSummary = "  <li>Exif information will be printed</li>\n";
        }

        // Create Exif summary:
        outputLanguageSummary = "  <li>Output language is "+outputLanguage+"</li>\n";

        // Create detail page summary:
        detailSummary = "  <li>Detail photos will be " + 
            getPhotoPosition() + " aligned.</li>\n";
        detailSummary += "  <li>Detail captions will appear " +
            getCaptionPosition() + " the photo aligned " +
            getCaptionAlign() + ".</li>\n";
        
        return
            "<html><font face=\"SansSerif,Arial,Helvetica\">\n" +
            "An HTML photo album will be created as follows:\n" +
            "<ul>\n" +
            "  <li>There will be a total of " + photoCount + 
                " photo" + ((photoCount != 1) ? "s" : "") + 
                " published.</li>\n" +
            thumbnailSummary + 
            "  <li>The photos are from the " + sourceDir + 
                " folder.</li>\n" +
            "  <li>The album will be created in the " + destDir + 
                " folder.</li>\n" +
            sizeSummary + 
            detailSummary + 
            captionsSummary + 
            linkSummary +
            exifSummary +
            outputLanguageSummary +
            "</font></html>";
    }
    
    /**
     * Generates photos based on the current settings.  
     * Generation begins on a separate Thread.  Call
     * stopGeneration to stop and reset page generation, or
     * call getGenerationProgress to return the percentage
     * done.  Observe this class to get progress notifications.
     * Call getGenerationMessage to get a message indicating
     * what is currently happening.
     */
    public void startGeneration() {
        errorMessage = null;
        generationSuccessful = false;
        
        if( generationThread != null ) {
            stopGeneration( "Aborted." );
        }
        
        // First, create the directory if it doesn't exist.
        getOutputDirectory().mkdirs();
        
        generationThread = new Thread() {
            public void run() {
                // Construct plan:
                Vector stages = new Vector();
                if( storeCaptions ) {
                    stages.addElement( 
                        new OutputCaptionsStage( PublishManager.this ) );
                }
                stages.addElement( 
                    new OutputPhotosStage( PublishManager.this ) );
                stages.addElement(
                    new OutputHTMLStage( PublishManager.this ) );
                
                // Assign percentages for each task, based on weights:
                int totalWeights = 0;
                
                // Total up the weights:
                for( int i = 0; i < stages.size(); i++ ) {
                    OutputStage stage = (OutputStage)stages.elementAt( i );
                    int weight = stage.getTimeComplexity();
                    totalWeights += weight;
                }
                
                if( totalWeights != 0 ) {
                    // Assign percentages:
                    int base = 0;
                    for( int i = 0; i < stages.size(); i++ ) {
                        OutputStage stage = (OutputStage)stages.elementAt( i );
                        int weight = stage.getTimeComplexity();
                        int total = weight * 100 / totalWeights;
                        stage.setPercentTotal( total );
                        stage.setPercentBase( base );
                        base += total;
                    }

                    generating = true;
                    long total = System.currentTimeMillis();
                    for( int i = 0; !stopGeneration && (i < stages.size()); 
                        i++ ) 
                    {
                        long timer = System.currentTimeMillis();
                        OutputStage stage = (OutputStage)stages.elementAt( i );
                        stage.generate();
                        if( DISPLAY_TIMINGS ) {
                            System.out.println( stage.getClass().getName() + 
                            ": " + (System.currentTimeMillis() - timer) + 
                            " ms" );
                        }
                    }
                    if( DISPLAY_TIMINGS ) {
                        System.out.println( "TOTAL: " + 
                            (System.currentTimeMillis() - total) + " ms" );
                    }
                }
                
                generating = false;
                if( stopGeneration ) {
                    setGenerationMessage( "Aborted." );
                    new Thread() {
                        public void run() {
                            if( errorMessage == null ) {
                                errorMessage = "";
                            }
                            Toolkit.getDefaultToolkit().beep();
                            JOptionPane.showMessageDialog( null, 
                                "Your photos were not published for the " +
                                "following reason:\n\n " + errorMessage, 
                                "Could not publish photos", 
                                JOptionPane.ERROR_MESSAGE );
                        }
                    }.start();
                }
                else{
                    generationMessage = "Done!";
                    // Photos are secure.
                    unsavedPhotos = false;
                    everGeneratedPhotos = true;
                }
                
                generationProgress = 0;
                generationSuccessful = !stopGeneration;
                stopGeneration = false;
                setChanged();
                notifyObservers();
                synchronized( generationThread ) {
                    generationThread.notifyAll();
                }
                generationThread = null;
            }
        };
        
        generationThread.start();
    }
    
    public void stopGeneration( String errorMessage ) {
        this.errorMessage = errorMessage;
        stopGeneration = true;
        if( Thread.currentThread() != generationThread ) {
            while( generationThread != null ) {
                synchronized( generationThread ) {
                    try {
                        generationThread.wait();
                    }
                    catch( InterruptedException e ) {
                        // ignore.
                    }
                }
            }
        }
    }
    
    public boolean isStopGeneration() {
        return this.stopGeneration;
    }
    
    /** Returns 0 to 100 */
    public int getGenerationProgress() {
        return generationProgress;
    }
    
    public boolean isGenerationComplete() {
        return generationSuccessful;
    }
    
    public boolean isGenerating() {
        return generating;
    }
    
    public String getGenerationMessage() {
        return generationMessage;
    }
    
    public void setGenerationMessage( String message ) {
        generationMessage = message;
        setChanged();
        notifyObservers();
    }
    
    public void setGenerationProgress( int progress ) {
        generationProgress = progress;
        setChanged();
        notifyObservers();
    }
    
    /**
     * Save captions now - don't wait until generation time.
     */
    public void saveCaptions() {
        OutputCaptionsStage stage = new OutputCaptionsStage( this );
        stage.generate();
    }
    
}
