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

import java.awt.image.*;
import java.awt.*;
import java.io.*;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.FileImageInputStream;

/**
 * Various GUI Utility methods.
 *
 * @author  mroth
 * @author  Mirko Actis Grosso
 */
public final class GUIUtils {

    /** Utility class - all methods are static. */
    private GUIUtils() {
    }
    
    /**
     * Creates a thumbnail version of the given BufferedImage, and returns
     * it as a BufferedImage.
     *
     * @param inImage The input image.
     * @param outImage The output image.  If specified, and if the image is 
     *   the right size, the buffered image object is reused and returned.  
     *   If null, or the wrong size, a new BufferedImage is created and 
     *   returned instead.
     * @param thumbWidthLandscape The width of the thumbnail if the
     *   image is landscape.
     * @param thumbHeightLandscape The height of the thumbnail if
     *   the image is landscape.
     * @param thumbWidthPortrait The width of the thumbnail if the
     *   image is portait.
     * @param thumbHeightPortrait The height of the thumbnail if the
     *   image is portait.
     * @param forceSize If true, the thumbnail is forced to the 
     *   desired size, even if the image is smaller.  Else, the
     *   image is resized and then "shrinkwrapped."
     */
    public static BufferedImage createThumbnail( 
        BufferedImage inImage, 
        BufferedImage outImage,
        int thumbWidthLandscape, 
        int thumbHeightLandscape,
        int thumbWidthPortrait, 
        int thumbHeightPortrait,
        boolean forceSize ) 
    {
        // Draw image, centered if necessary:
        // Input image: inImage
        // Output image: outImage (Graphics g)
        int imgWidth = inImage.getWidth( null );
        int imgHeight = inImage.getHeight( null );
        int newHeight, newWidth;
        int newX, newY;
        Dimension size;
        if( imgWidth >= imgHeight ) {
            size = new Dimension( thumbWidthLandscape, thumbHeightLandscape );
        }
        else {
            size = new Dimension( thumbWidthPortrait, thumbHeightPortrait );
        }
        
        if( (imgWidth > size.width) || (imgHeight > size.height) ) {
            if( imgWidth > size.width ) {
                // Image is too wide.
                newWidth = size.width;
                newHeight = imgHeight * size.width / imgWidth;
            }
            else {
                newWidth = imgWidth;
                newHeight = imgHeight;
            }
            newX = 0;
            newY = (size.height - newHeight) / 2;

            if( newHeight > size.height ) {
                // Image is too tall
                newWidth = newWidth * size.height / newHeight;
                newHeight = size.height;
                newY = 0;
                newX = (size.width - newWidth) / 2;
            }
        }
        else {
            // If the image is too small for the preview window...
            newWidth = imgWidth;
            newHeight = imgHeight;
            newX = (size.width - newWidth) / 2;
            newY = (size.height - newHeight) / 2;
        }
        
        if( !forceSize ) {
            // Shrinkwrap the image:
            size.width = newWidth;
            size.height = newHeight;
            newX = 0;
            newY = 0;
        }
        
        if( (outImage == null) ||
            (size.width != outImage.getWidth()) ||
            (size.height != outImage.getHeight()) )
        {
            // If outImage is null or the wrong size, create a new one:
            outImage = new BufferedImage(
                size.width, size.height,
                BufferedImage.TYPE_INT_RGB );
        }
        
        Graphics2D g = (Graphics2D)outImage.getGraphics();
        g.setRenderingHint( RenderingHints.KEY_ANTIALIASING, 
            RenderingHints.VALUE_ANTIALIAS_ON );
        g.setRenderingHint( RenderingHints.KEY_COLOR_RENDERING,
            RenderingHints.VALUE_COLOR_RENDER_QUALITY );
        g.setRenderingHint( RenderingHints.KEY_INTERPOLATION,
            RenderingHints.VALUE_INTERPOLATION_BICUBIC );
        g.setRenderingHint( RenderingHints.KEY_RENDERING,
            RenderingHints.VALUE_RENDER_QUALITY );
        
        g.setColor( Color.white );
        g.fillRect( 0, 0, size.width, size.height );
            
        g.drawImage( inImage, newX, newY, newWidth, newHeight, null );
        
        return outImage;
    }

    /**
     * Reads an image from a file.  The JDK 1.4 ImageIO framework is used
     * to load the image, and so JPEG, GIF and PNG are supported by
     * default (and other formats can by plugged in dynamically).
     * <p>
     * Uses subsampling to speed image loading.
     * <p>
     * Note that because subsampling is used the actual image returned 
     * is at least the size passed in, but it could be rounded up.
     * Use createThumbnail after calling this method if you want an
     * exact image size.
     *
     * @param inFile The file to load the image from.
     * @param outImage The BufferedImage to load the file into.  If
     *   null, or if the BufferedImage is unsuitable for the file being
     *   read, a new BufferedImage is created and returned.
     * @param targetSizeLandscape Desired target size for image if the
     *   image is landscape (result will be slightly larger).  Pass null to 
     *   use the full size of the source image.
     * @param targetSizePortrait Desired target size for image if the
     *   image is portrait (result will be slightly larger).  Pass null to 
     *   use the full size of the source image.
     * @return An object containing the image
     * @throws IOException Thrown if the image could not be read from the
     *   file, or if the image is invalid.
     */
    public static BufferedImage loadImageFromFile( File inFile, 
        BufferedImage outImage, Dimension targetSizeLandscape, 
        Dimension targetSizePortrait ) 
        throws IOException
    {
        BufferedImage result = outImage;
        boolean useImageIORead = true;
        FileImageInputStream inStream = new FileImageInputStream( inFile );
        
        int dotIndex = inFile.getName().lastIndexOf( '.' );
        if( dotIndex != -1 ) {
            String suffix = inFile.getName().substring( dotIndex + 1 );
            Iterator readers = ImageIO.getImageReadersBySuffix( suffix );
            if( readers.hasNext() ) {
                ImageReader reader = (ImageReader)readers.next();
                reader.setInput( inStream, true );
                
                // If the provided outImage is suitable, use it.
                ImageReadParam param = reader.getDefaultReadParam();
                int sourceWidth = reader.getWidth( 0 );
                int sourceHeight = reader.getHeight( 0 );
                
                int destWidth, destHeight;

                if( sourceWidth > sourceHeight ) {
                    if( targetSizeLandscape == null ) {
                        destWidth = sourceWidth;
                        destHeight = sourceHeight;
                    }
                    else {
                        destWidth = targetSizeLandscape.width;
                        destHeight = targetSizeLandscape.height;
                    }
                }
                else {
                    if( targetSizePortrait == null ) {
                        destWidth = sourceWidth;
                        destHeight = sourceHeight;
                    }
                    else {
                        destWidth = targetSizePortrait.width;
                        destHeight = targetSizePortrait.height;
                    }
                }
                
                int subsampleX = sourceWidth / destWidth;
                int subsampleY = sourceHeight / destHeight;
                
                if( subsampleX < 1 ) subsampleX = 1;
                if( subsampleY < 1 ) subsampleY = 1;
                
                subsampleX = Math.min( subsampleX, subsampleY );
                subsampleY = subsampleX;
                
                int actualWidth = sourceWidth / subsampleX;
                int actualHeight = sourceHeight / subsampleY;

                //System.out.println( "Subsampling: " + subsampleX + "x" + 
                //    subsampleY + ", Image size: " + 
                //    actualWidth + "x" + actualHeight );

                //param.setSourceRenderSize( 
                //    new Dimension( actualWidth, actualHeight ) );
                param.setSourceSubsampling( subsampleX, subsampleY, 0, 0 );
                
                if( outImage != null ) {
                    
                    if( (outImage.getWidth() == actualWidth) &&
                        (outImage.getHeight() == actualHeight) )
                    {
                        param.setDestination( outImage );
                    }
                    else {
                        outImage = null;
                    }
                }
                
                if( outImage == null ) {
                    // Handling TYPE_INT_RGB seems to be a bit faster than
                    // handling TYPE_3BYTE_BGR with no apparent degradation
                    // (but perhaps at the expense of some extra RAM)
                    outImage = new BufferedImage( actualWidth, actualHeight,
                        BufferedImage.TYPE_INT_RGB );
                    param.setDestination( outImage );
                }
                
                // Read the image
                try {
                    // Read image:
                    result = reader.read( 0, param );
                    useImageIORead = false;
                }
                catch( IOException e ) {
                    // try again using ImageIO.read, below.
                }
                catch( IllegalArgumentException e ) {
                    // this can happen if the source image is monochrome, 
                    // for example.
                    // try again using ImageIO.read, below.
                }
                finally {
                    reader.dispose();
                }
            }
        }
        
        if( useImageIORead ) {
            if( outImage != null ) outImage.flush();
            result = ImageIO.read( inStream );
        }
        
        return result;
    }

    /**
     * Saves an image to a file.  The file must be a JPG.
     *
     * @param outFile The file to save to.
     * @param image The BufferedImage to save to the file.
     */
    public static void saveImageToFile( File outFile, BufferedImage image ) 
        throws IOException
    {
        ImageIO.write( image, "jpg", 
            ImageIO.createImageOutputStream( outFile ) );
    }

}
