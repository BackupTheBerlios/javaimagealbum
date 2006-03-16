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
 * Mirko Actis Grosso
 * </license>
 */

package com.javaimagealbum;

import java.text.*;
import java.io.*;
import java.util.*;

import com.javaimagealbum.resources.ResourceFactory;

/**
 * Generates all HTML output files.
 *
 * @author  mroth
 * @author  Mirko Actis
 */
public class OutputHTMLStage extends OutputStage {
    static ResourceBundle resOutput;

    /** Current progress */
    int progress = 0;
    
    /** Creates new OutputHTMLStage */
    public OutputHTMLStage( PublishManager publishManager ) {
        super( publishManager );
    }

    /**
     * Performs generation for this stage
     */
    public void generate() {
        setGenerationMessage( "Generating HTML..." );
        
        resOutput = ResourceFactory.getBundle( publishManager.getOutputLanguage() );

        ArrayList photos = publishManager.getPhotoSource().getSelectedPhotos();
        int numPhotos = photos.size();
        
        // Determine thumbnail generation parameters:
        int numPages = 1;
        int perPage;
        if( publishManager.getThumbnailsPerPage().equals( 
            Constants.UNLIMITED_THUMBNAILS ) ) 
        {
            perPage = numPhotos;
            numPages = 1;
        }
        else {
            perPage = Integer.parseInt( 
                publishManager.getThumbnailsPerPage() );
            numPages = Math.max( 1, 1 + (numPhotos - 1) / perPage );
        }
        
//        File outputDirectory = publishManager.getOutputDirectory();
        try {
            for( int i = 0; !isStopGeneration() && (i < numPhotos); i++ ) {
                progress = i * 100 / Math.max(numPhotos + 1, 1);
                updateProgress();
                setGenerationMessage( "Generating HTML (" + (i+1) + 
                    "/" + photos.size() + ")..." );
                OutputPhoto outPhoto = (OutputPhoto)photos.get( i );
                outPhoto.setDetailHTMLFilename( 
                    generateDetailPage( i, i / perPage ) );
            }
            
            // Construct description pages:
            if (publishManager.getDescriptionInEmptyPage()) {
            	generateDescriptionPage();
            }

            // Construct thumbnail pages:
            progress = numPhotos * 100 / Math.max(numPhotos + 1, 1);
            updateProgress();
            setGenerationMessage( "Generating Index HTML Pages..." );
            
            for( int i = 0; i < numPages; i++ ) {
                generateThumbnailPage( i, perPage, numPages );
            }
        }
        catch( IOException e ) {
            error( "Could not generate HTML page: " + e.getMessage() );
        }
    }

    /**
     * Performs generation for this stage
     */
    public void generateDescriptionPage()
    throws IOException
    {
        if (publishManager.getAlbumDescription().length() > 0) {
	        SimpleDateFormat sdf = new SimpleDateFormat( resOutput.getString("DATE_FORMAT") );
	        String todaysDate = sdf.format( new Date() );
	        
	        String indexFilename = "indexDEscription";
	        indexFilename += ".html";
	
	        File outputDirectory = publishManager.getOutputDirectory();
	        File destFile = new File( outputDirectory, indexFilename );
	        PrintWriter out = new PrintWriter( new FileWriter( destFile ) );
	        out.print( 
	            "<html>\n" +
	            "  <head>\n" +
	            "    <title>" + publishManager.getAlbumTitle() );
	        out.print(
	            "</title>\n" +
	            "  </head>\n" +
	            "  <body bgcolor=\"#ffffff\">\n" );
            out.print( "<table border=\"0\" cellpadding=\"5\"><tr>\n" );
	        if( publishManager.getLinkToAlbumIndex() ) {
	            out.print(
	                "    <td><a href=\"../\">&lt; "+resOutput.getString("BACK_ALBUM_INDEX")+"</a></td>\n");
	        }
            out.print( "<td><a href=\"index.html\">"+resOutput.getString("BACK_ALBUM")+"</a></td>\n" );
            out.print( "</tr></table>\n" );

	        out.print( 
	            "    <div align=\"Center\">\n" +
	            "      <h1>" + publishManager.getAlbumTitle() + "</h1>\n" );
	        out.print( 
	            "    <div align=\"Left\">\n" +
	            "      " + publishManager.getAlbumDescription() + "\n" +
	            "    </div>\n" +
	            "    <br/>");
	
            out.print( 
                    "      </table>\n" +
                    "    </div>\n" );
            printFooter( out, todaysDate );
            out.print( 
                "  </body>\n" +
                "</html>" );

	        out.close();
        }
    }
    
    public void generateThumbnailPage( int index, int perPage, int numPages )
        throws IOException
    {
        ArrayList photos = publishManager.getPhotoSource().getSelectedPhotos();
        
        int numPhotos = photos.size();
        int beginIndex = index * perPage;
        int endIndex = (index + 1) * perPage - 1;
        if( endIndex > (numPhotos-1) ) {
            endIndex = numPhotos - 1;
        }
        int onThisPage = endIndex - beginIndex + 1;
        int numCols = publishManager.getOutputColumns();
        int numRows = (onThisPage-1) / numCols + 1; 
        
        if( numRows == 1 ) {
            // If there is only one row of photos, 
            // only have as many columns as there are photos on this page.
            numCols = onThisPage;
        }
        
        SimpleDateFormat sdf = new SimpleDateFormat( resOutput.getString("DATE_FORMAT") );
        String todaysDate = sdf.format( new Date() );
        
        String indexFilename = "index";
        if( index > 0 ) indexFilename += index;
        indexFilename += ".html";

        File outputDirectory = publishManager.getOutputDirectory();
        File destFile = new File( outputDirectory, indexFilename );
        PrintWriter out = new PrintWriter( new FileWriter( destFile ) );
        out.print( 
            "<html>\n" +
            "  <head>\n" +
            "    <title>" + publishManager.getAlbumTitle() );
        if( numPages > 1 ) {
            out.print( " - Page " + (index + 1) + " of " + numPages );
        }
        out.print(
            "</title>\n" +
            "  </head>\n" +
            "  <body bgcolor=\"#ffffff\">\n" );
        if( publishManager.getLinkToAlbumIndex() ) {
            out.print(
                "    <a href=\"../\">&lt; "+resOutput.getString("BACK_ALBUM_INDEX")+"</a><br/>\n");
        }
        out.print( 
            "    <div align=\"Center\">\n" +
            "      <h1>" + publishManager.getAlbumTitle() + "</h1>\n" );
        if (publishManager.getAlbumDescription().length() > 0) {
        	if (publishManager.getDescriptionInEmptyPage()) {
                    // Create link to Description Page
	            out.print(
		                "    <div align=\"Center\">\n" +
	            		"       <a href=\"indexDescription.html\">"+resOutput.getString("ALBUM_DESCRIPTION")+"</a>\n" + 
		                "    </div>\n" +
		                "    <br/>");
        	} else {
                    // Add Album Description
	            out.print( 
	                "    <div align=\"Left\">\n" +
	                "      " + publishManager.getAlbumDescription() + "\n" +
	                "    </div>\n" +
	                "    <br/>");
        	}
        }
        if( numPages > 1 ) {
            out.print(
                "      <hr>\n" +
                "      "+resOutput.getString("PAGE")+" " + (index + 1) + " "+resOutput.getString("OF")+" " + numPages + " -- \n" );
            // Output meta-index:
            out.print( resOutput.getString("INDEX")+": [ " );
            for( int i = 0; i < numPages; i++ ) {
                int bi = i * perPage;
                int ei = (i + 1) * perPage - 1;
                if( ei > (numPhotos-1) ) {
                    ei = numPhotos - 1;
                }
                String pageName = "index";
                if( i > 0 ) pageName += i;
                pageName += ".html";
                if( i != index ) out.print( "<a href=\"" + pageName + "\">" );
                out.print( "" + (bi+1) + "-" + (ei+1) );
                if( i != index ) out.print( "</a>" );
                if( (i < (numPages-1) ) ) {
                    out.print( " | " );
                }
            }
            out.print( 
                " ]\n" + 
                "<hr>\n" );
        }
        out.print(
            "      <table cellpadding=\"2\" cellspacing=\"0\" " +
              "border=\"1\" width=\"100%\" align=\"Center\">\n" );

        for( int row = 0; !isStopGeneration() && (row < numRows); row++ ) {
            out.print( 
                "        <tr valign=\"Top\">\n" );
            for( int col = 0; !isStopGeneration() && (col < numCols); 
                col++ ) 
            {
                int i = beginIndex + row * numCols + col;
                if( i >= numPhotos ) break;
                OutputPhoto outPhoto = (OutputPhoto)photos.get( i );
//                File sourceFile = outPhoto.getSource();
                String thumbnail = outPhoto.getOutThumbnailName();
//                String filename = outPhoto.getOutImageName();
                String caption = outPhoto.getCaption();
                String detailFilename = outPhoto.getDetailHTMLFilename();

                out.print( 
                    "          <td valign=\"Top\" width=\"" + 
                               (100/numCols) + "%\">\n" +
                    "            <div align=\"Center\">\n" + 
                    "              <table cellpadding=\"0\" " +
                                   "cellspacing=\"0\" border=\"0\">\n" + 
                    "                <tr height=\"" + 
                                     (Constants.THUMBNAIL_HEIGHT+6) + 
                                     "\">\n" +
                    "                  <td height=\"" + 
                                       (Constants.THUMBNAIL_HEIGHT+6) + 
                                       "\">\n" +
                    "                    <center>\n" +
                    "                      <a href=\"" + detailFilename + 
                                           "\">" +
                                           "<img src=\"" + thumbnail + 
                                           "\" alt=\"" + caption + 
                                           "\"></a>\n" + 
                    "                    </center>\n" + 
                    "                  </td>\n" + 
                    "                </tr>\n" +
                    "                <tr><td><center>" + 
                                     caption + "</center></td></tr>\n"+
                    "              </table>\n" + 
                    "            </div>\n" +
                    "          </td>\n" );
            }

            // Take care of empty columns for last row:
            if( (index == (numPages-1)) && (row == (numRows - 1)) ) {
                int emptyCols = numCols - 
                    ( ( ( numPhotos - 1 ) % numCols ) + 1 );
                for( int col = 0; col < emptyCols; col++ ) {
                    out.print(
                        "        <td valign=\"Top\" width=\"" + 
                                 (100/numCols) + "%\"><br/>\n" +
                        "        </td>\n" );
                }
            }

            out.print( 
                "        </tr>\n" );
        }

        out.print( 
            "      </table>\n" +
            "    </div>\n" );
        printFooter( out, todaysDate );
        out.print( 
            "  </body>\n" +
            "</html>" );

        out.close();
    }
    
    private void printFooter ( PrintWriter out, String todaysDate ) {
//        out.print( 
//                "      </table>\n" +
//                "    </div>\n" +
//                "    <hr align=\"Left\" width=\"100%\" size=\"2\" noshade>\n" +
//                "      <i>"+resOutput.getString("PUBLISHED")+": " + todaysDate + "\n" +
//                "      <div align=\"right\"><small>"+resOutput.getString("GENERATED_BY")+"</i><br/>\n" +
//                "      <a href=\""+Constants.SITE_URL+"\"" +
//                    "\">"+Constants.APP_NAME+"</a>" +
//                    "</small></div>\n" +
//                "  </body>\n" +
//                "</html>" );
        
        out.print( 
                "    <hr align=\"Left\" width=\"100%\" size=\"2\" noshade>\n" +
                "      <i>"+resOutput.getString("PUBLISHED")+": " + todaysDate + "\n" +
                "      <div align=\"right\"><small>"+resOutput.getString("GENERATED_BY")+"</i><br/>\n" +
                "      <a href=\""+Constants.SITE_URL+"\"" +
                    "\">"+Constants.APP_NAME+"</a>" +
                    "</small></div>\n");
    }
    

    private String generateDetailPage( int index, int indexPageNum )
        throws IOException
    {
        ArrayList photos = publishManager.getPhotoSource().getSelectedPhotos();
        int numPhotos = photos.size();
        OutputPhoto outPhoto = (OutputPhoto)photos.get( index );
        String imageFilename = outPhoto.getFilename();
        String pageFilename = imageFilename + ".html";
        String caption = outPhoto.getCaption();
        String next = null;
        String prev = null;
        
        if( index > 0 ) {
            OutputPhoto prevPhoto = (OutputPhoto)photos.get( index - 1 );
            prev = prevPhoto.getFilename() + ".html";
        }
        if( index < (numPhotos-1) ) {
            OutputPhoto nextPhoto = (OutputPhoto)photos.get( index + 1 );
            next = nextPhoto.getFilename() + ".html";
        }
        
        File outputDirectory = publishManager.getOutputDirectory();
        File outFile = new File( outputDirectory, pageFilename );
        PrintWriter out = new PrintWriter( new FileWriter( outFile ) );
        
        out.print( 
            "<html>\n" +
            "  <head>\n" +
            "    <title>" + publishManager.getAlbumTitle() + 
            " - "+resOutput.getString("PHOTO")+": " + imageFilename + "</title>\n" +
            "  </head>\n" +
            "  <body bgcolor=\"#ffffff\">\n" );
        
        out.print( "<table border=\"0\" cellpadding=\"5\"><tr>\n" );
        
        String indexPage = "index";
        if( indexPageNum > 0 ) indexPage += indexPageNum;
        indexPage += ".html";
        out.print( "<td><a href=\"" + indexPage + "\">"+resOutput.getString("BACK_ALBUM")+"</a></td>\n" );
        
        if( prev != null ) {
            out.print( 
                "<td width=\"180\"><div align=\"center\"><a href=\"" + prev + 
                "\">&lt;&lt; "+resOutput.getString("PREVIOUS")+" &lt;&lt;</a></div></td>\n" );
        }
        else {
            out.print( "<td width=\"180\"><div align=\"center\">" +
                "<font color=\"#999999\">&lt;&lt; "+resOutput.getString("PREVIOUS")+" &lt;&lt;</font>" +
                "</div></td>\n" );
        }
        
        if( next != null ) {
            out.print( 
                "<td width=\"180\"><a href=\"" + next + 
                "\">&gt;&gt; "+resOutput.getString("NEXT1")+" &gt;&gt;</a></td>\n" );
        }
        else {
            out.print(
                "<td width=\"180\">" +
                "<font color=\"#999999\">&gt;&gt; "+resOutput.getString("NEXT1")+" &gt;&gt;" +
                "</font></td>\n" );
        }
        
        out.print(
            "<td>[ "+resOutput.getString("PHOTO")+" " + (index+1) + " "+resOutput.getString("OF")+" " + numPhotos + " ]</td>\n" );
        
        out.print( "</tr></table>\n" );
        
        // Generate HTML for image:
        String outImageFilename = outPhoto.getOutImageName();
        if( publishManager.getCaptionPosition().equals( 
            Constants.CAPTION_POSITION_ABOVE ) ) 
        {
            out.print(getCaption(publishManager, caption));        
            out.println( "<BR>\n" );
        }
        out.print( "<div align=\"" + 
            publishManager.getPhotoPosition() + "\">" );
        if( outPhoto.getOutputResized() ) {
            // If we output a resized version of this photo, prepend
            // the filename with "resized-" and check to see if we
            // have a full-sized version as well.
            if( publishManager.getPublishFullSize() ) {
                // link to full-sized image as well.
                out.print( 
                    "<a href=\"" + imageFilename + "\">" +
                    "<img src=\"resized-" + outImageFilename + 
                        "\" alt=\""+resOutput.getString("CLICK_ZOOM")+"\">" +
                    "</a>" );
            }
            else {
                out.print( 
                    "<img src=\"resized-" + outImageFilename + "\">" );
            }
        }
        else {
            out.print( "<img src=\"" + outImageFilename + "\">" );
        }
        
        out.println( "</div>\n" );
        
        if( publishManager.getCaptionPosition().equals( 
            Constants.CAPTION_POSITION_BELOW ) ) 
        {
            out.println( "<BR>\n" );
            out.print(getCaption(publishManager, caption));        
        }
        
        if( publishManager.getShowExif() ) {
            printExifInformation ( outPhoto, out );
        }
            
        out.print( 
            "    <div align=\"right\"><small><i>"+resOutput.getString("GENERATED_BY")+"</i><br/>\n" +
            "      <a href=\""+Constants.SITE_URL+"\"" +
            "\">"+Constants.APP_NAME+"</a>" +
                "</small></div>\n" +
            "  </body>\n" +
            "</html>\n" );
        
        out.close();
        
        return pageFilename;
    }
    
    private String getCaption (PublishManager publishManager, String caption) {
        String out = null;
        if (publishManager.getCaptionAlign().equals(Constants.CAPTION_ALIGN_CENTER)) {
            out = "<div align=\"Center\"><br/>\n" + 
            "    " + caption + "<br/></div>\n";
        }
        if (publishManager.getCaptionAlign().equals(Constants.CAPTION_ALIGN_LEFT)) {
            out = "<div align=\"Left\"><br/>\n" + 
            "    " + caption + "<br/></div>\n";
            }
        if (publishManager.getCaptionAlign().equals(Constants.CAPTION_ALIGN_RIGHT)) {
            out = "<div align=\"Right\"><br/>\n" + 
            "    " + caption + "<br/></div>\n";
            }
        return out;

    }
    
    private void printExifInformation ( OutputPhoto outPhoto, PrintWriter out ) {
        out.println( "<hr/>\n" );
        if ( outPhoto.isExif() ) {
            out.print(
                    "<table cellpadding=\"2\" cellspacing=\"0\" " +
                      "border=\"0\" width=\"60%\" align=\"Left\">\n" );
            out.print( "      <tr><td>Creation Date</td>\n" );
            out.print( "      <td>"+outPhoto.getCreationDate()+"</td></tr>\n" );
            out.print( "      <tr><td>Digitized Date</td>\n" );
            out.print( "      <td>"+outPhoto.getDigitizedDate()+"</td></tr>\n" );
            out.print( "      <tr><td>Modified Date</td>\n" );
            out.print( "      <td>"+outPhoto.getModifiedDate()+"</td></tr>\n" );
            out.print( "      <tr><td>Camera Type</td>\n" );
            out.print( "      <td>"+outPhoto.getCameraType()+"</td></tr>\n" );
            out.print( "      <tr><td>Camera Model</td>\n" );
            out.print( "      <td>"+outPhoto.getCameraModel()+"</td></tr>\n" );
            out.print( "      <tr><td>Camera Make</td>\n" );
            out.print( "      <td>"+outPhoto.getCameraMake()+"</td></tr>\n" );
            out.print( "      <tr><td>FStop</td>\n" );
            out.print( "      <td>"+outPhoto.getFStop()+"</td></tr>\n" );
            out.print( "      <tr><td>Exposure Program</td>\n" );
            out.print( "      <td>"+outPhoto.getExposureProgram()+"</td></tr>\n" );
            out.print( "      <tr><td>Light Source</td>\n" );
            out.print( "      <td>"+outPhoto.getLightSource()+"</td></tr>\n" );
            out.print( "      <tr><td>ISO</td>\n" );
            out.print( "      <td>"+outPhoto.getISO()+"</td></tr>\n" );
            out.print( "      <tr><td>Shutter Speed</td>\n" );
            out.print( "      <td>"+outPhoto.getShutterSpeed()+"</td></tr>\n" );
            out.print( "      <tr><td>Flash</td>\n" );
            out.print( "      <td>"+outPhoto.getFlash()+"</td></tr>\n" );
            out.print( "      <tr><td>Image Height</td>\n" );
            out.print( "      <td>"+outPhoto.getImageHeight()+"</td>\n" );
            out.print( "      <tr><td>Image Width</td>\n" );
            out.print( "      <td>"+outPhoto.getImageWidth()+"</td></tr>\n" );
            out.print( "      <tr><td>Log info</td>\n" );
            out.print( "      <td>"+outPhoto.getAllExif()+"</td></tr>\n" );
            out.print( "</table>\n" );
        } else {
            out.println( "No EXIF information in this picture.\n" );
        }
//        out.println( "<hr/>\n" );
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
        return TIME_COMPLEXITY_FAST;
    }
    
}
