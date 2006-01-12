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

import java.awt.Dimension;
import java.io.*;
import java.util.*;
import java.awt.image.*;

// XXX - Change from querying PublishManager to passing in settings
// in constructor.

/**
 * Stage in which photos and thumbnails are copied or generated.
 *
 * @author  mroth
 */
public class OutputPhotosStage extends OutputStage {

    /** Current progress */
    private int progress = 0;
    
    /** Creates new OutputPhotosStage */
    public OutputPhotosStage( PublishManager publishManager ) {
        super( publishManager );
    }

    /**
     * Performs generation for this stage
     */
    public void generate() {
        // Determine maximum dimensions of target images so we can
        // subsample the image for faster loading.
        Dimension subsampleLandscape = new Dimension( 
            Constants.THUMBNAIL_DIMENSION );
        Dimension subsamplePortrait = new Dimension( 
            Constants.THUMBNAIL_DIMENSION );
        if( publishManager.getResizeAll() ) {
            int resizeAllWidth = publishManager.getResizeAllWidth();
            int resizeAllHeight = publishManager.getResizeAllHeight();
            int resizePortraitsWidth = 
                publishManager.getResizePortraitsWidth();
            int resizePortraitsHeight = 
                publishManager.getResizePortraitsHeight();

            subsampleLandscape.width = resizeAllWidth;
            subsampleLandscape.height = resizeAllHeight;

            if( publishManager.getResizePortraits() ) {
                subsamplePortrait.width = resizePortraitsWidth;
                subsamplePortrait.height = resizePortraitsHeight;
            }
            else {
                subsamplePortrait = subsampleLandscape;
            }
        }
        
        // Now, generate each image:
        PhotoSource photoSource = publishManager.getPhotoSource();
        setGenerationMessage( "Generating Thumbnails and Output Photos..." );
        ArrayList photos = photoSource.getSelectedPhotos();
        BufferedImage inImage = null;
        for( int i = 0; !isStopGeneration() && (i < photos.size()); i++ ) {
            // Generate thumbnail:
            OutputPhoto outPhoto = (OutputPhoto)photos.get( i );
            progress = i * 100 / Math.max( 1, photos.size() );
            updateProgress();
            setGenerationMessage( "Generating Thumbnail (" + (i+1) + "/" + 
                photos.size() + ")..." );
            
            File sourceFile = outPhoto.getSource();
            try {
                BufferedImage image = 
                    GUIUtils.loadImageFromFile( sourceFile, inImage, 
                    subsampleLandscape, subsamplePortrait );
                generateThumbnail( outPhoto, image );

                // Generate output photo:
                progress += 50 / Math.max( 1, photos.size() );
                updateProgress();
                setGenerationMessage( "Generating Photo Image (" + (i+1) + "/" + 
                    photos.size() + ")..." );
                generateOutputPhoto( outPhoto, image );
            }
            catch( IOException e ) {
                error( "Could not create output photos for '" + 
                    sourceFile.getName() + "'.  " + e.getMessage() );
            }
        }
        
    }

    private void generateThumbnail( OutputPhoto outPhoto, 
        BufferedImage image ) 
    {
        File sourceFile = outPhoto.getSource();
        String outName = "thumb-" + filenameToJPEG( 
            outPhoto.getFilename() );
        outPhoto.setOutThumbnailName( outName );
        File destFile = new File( publishManager.getOutputDirectory(), 
            outName );

        try {
            BufferedImage thumbnailImage = GUIUtils.createThumbnail( 
                image, null,
                Constants.THUMBNAIL_WIDTH, Constants.THUMBNAIL_HEIGHT,
                Constants.THUMBNAIL_WIDTH, Constants.THUMBNAIL_HEIGHT, false );
            GUIUtils.saveImageToFile( destFile, thumbnailImage );
        }
        catch( IOException e ) {
            error( "Could not create thumbnail for '" + 
                sourceFile.getName() + "'.  " + e.getMessage() );
        }
    }
    
    private void generateOutputPhoto( OutputPhoto outPhoto, 
        BufferedImage image ) 
    {
        File outputDirectory = publishManager.getOutputDirectory();
        File sourceFile = outPhoto.getSource();
        String outName = outPhoto.getFilename();
        File origDestFile = new File( outputDirectory, outName );
        try {
            // Set to true if we already copied the full-sized image.
            boolean alreadyCopied = false;
            
            if( publishManager.getPublishFullSize() || 
                !publishManager.getResizeAll() ) 
            {
                // Copy the file if we are publishing all full-sized
                // images, or if we are not resizing
                Utils.copyFile( sourceFile, origDestFile );
                outPhoto.setOutputResized( false );
                alreadyCopied = true;
            }
            
            if( publishManager.getResizeAll() ) {
                outName = filenameToJPEG( outPhoto.getFilename());
                File destFile = new File( outputDirectory, 
                    "resized-" + outName );
                int resizeAllWidth = publishManager.getResizeAllWidth();
                int resizeAllHeight = publishManager.getResizeAllHeight();
                int resizePortraitsWidth = 
                    publishManager.getResizePortraitsWidth();
                int resizePortraitsHeight = 
                    publishManager.getResizePortraitsHeight();
                BufferedImage outImage;
                
                // First, determine if we need to resize this image.
                int imageWidth = image.getWidth();
                int imageHeight = image.getHeight();
                boolean needsResize;
                if( (imageWidth > imageHeight) || 
                    !publishManager.getResizePortraits() ) 
                {
                    // this is a landscape image, or we're resizing all
                    // images to landscape.
                    needsResize = 
                        (imageWidth > resizeAllWidth) ||
                        (imageHeight > resizeAllHeight);
                }
                else {
                    // this is a portrait image and we have a specific
                    // portrait size.
                    needsResize = 
                        (imageWidth > resizePortraitsWidth) ||
                        (imageHeight > resizePortraitsHeight);
                }
                
                if( needsResize ) {
                    if( publishManager.getResizePortraits() ) {
                        outImage = GUIUtils.createThumbnail( 
                            image, null,
                            resizeAllWidth, resizeAllHeight,
                            resizePortraitsWidth, resizePortraitsHeight,
                            false );
                    }
                    else {
                        outImage = GUIUtils.createThumbnail( 
                            image, null,
                            resizeAllWidth, resizeAllHeight,
                            resizeAllWidth, resizeAllHeight,
                            false );
                    }
                    GUIUtils.saveImageToFile( destFile, outImage );
                    outPhoto.setOutputResized( true );
                }
                else {
                    // we don't need to resize this image.  Just
                    // copy the file.
                    if( !alreadyCopied ) {
                        Utils.copyFile( sourceFile, origDestFile );
                    }
                    outName = outPhoto.getFilename();
                    outPhoto.setOutputResized( false );
                }
            }
        }
        catch( IOException e ) {
            error( "Could not copy file '" + sourceFile.getName() +
                "'.  " + e.getMessage() );
        }
        finally {
            outPhoto.setOutImageName( outName );
        }
    }
            
    private String filenameToJPEG( String filename ) {
        String result;
        
        if( !filename.toLowerCase().endsWith( ".jpg" ) ) {
            result = filename + ".jpg";
        }
        else {
            result = filename;
        }
        
        return result;
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
        return TIME_COMPLEXITY_VERY_SLOW;
    }
    
}
