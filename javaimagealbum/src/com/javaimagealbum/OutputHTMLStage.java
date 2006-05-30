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
 * @author  Mirko Actis Grosso
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
                Constants.UNLIMITED_THUMBNAILS ) ) {
            perPage = numPhotos;
            numPages = 1;
        } else {
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
            
            for( int i = 0 ; i < numPages; i++ ) {
                generateThumbnailPage( i, perPage, numPages );
            }
        } catch( IOException e ) {
            error( "Could not generate HTML page: " + e.getMessage() );
        }
    }
    
    /**
     * Performs generation for this stage
     */
    public void generateDescriptionPage()
    throws IOException {
        if (publishManager.getAlbumDescription().length() > 0) {
            
            String indexFilename = "indexDescription";
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
            printThumbnailFooter( out );
            out.print(
                    "  </body>\n" +
                    "</html>" );
            
            out.close();
        }
    }
    
    /**
     * Generate thumbnail Pages
     */
    public void generateThumbnailPage( int index, int perPage, int numPages )
    throws IOException {
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
            out.print( " - " + resOutput.getString("PAGE") + " " + (index + 1) + " " + resOutput.getString("OF") + " " + numPages );
        }
        out.print(
                "</title>\n" +
                "  </head>\n" +
                "  <body bgcolor=\"#ffffff\">\n" );
        if( publishManager.getLinkToAlbumIndex() ) {
            out.print(
                    "    <a href=\"../\">&lt; "+resOutput.getString("BACK_ALBUM_INDEX")+"</a><br/>\n");
        }
        out.print("<br/>\n");
        
        out.print(
                "    <div align=\"Center\">\n" +
                "      <h1>" + publishManager.getAlbumTitle() + "</h1>\n" );
        
        // Manage description page
        if (publishManager.getAlbumDescription().length() > 0) {
            if (publishManager.getDescriptionInEmptyPage()) {
                // Create link to Description Page
                out.print(
                        "    <div align=\"Left\">\n" +
                        "       <a href=\"indexDescription.html\">"+resOutput.getString("ALBUM_DESCRIPTION")+"</a>\n" +
                        "    </div>\n" +
                        "    <br/>\n");
            } else if (index == 0) {
                // Add Album Description in first Thumbnail page
                out.print(
                        "    <div align=\"Left\">\n" +
                        "      " + publishManager.getAlbumDescription() + "\n" +
                        "    </div>\n" +
                        "    <br/>\n");
            }
        }
        
        if( numPages > 1 ) {
            out.print(
                    "      <hr/>\n" +
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
                if( i == index ) {
                    out.print( "<b>" );
                } else {
                    out.print( "<a href=\"" + pageName + "\">" );
                }
                out.print( "" + (bi+1) + "-" + (ei+1) );
                if( i == index ) {
                    out.print( "</b>" );
                } else {
                    out.print( "</a>" );
                }
                if( (i < (numPages-1) ) ) {
                    out.print( " | " );
                }
            }
            out.print(
                    " ]\n" +
                    "      <hr/>\n" );
        }
        out.print(
                "      <table cellpadding=\"2\" cellspacing=\"0\" " +
                "border=\"1\" width=\"100%\" align=\"Center\">\n" );
        
        for( int row = 0; !isStopGeneration() && (row < numRows); row++ ) {
            out.print(
                    "        <tr valign=\"Top\">\n" );
            for( int col = 0; !isStopGeneration() && (col < numCols);
            col++ ) {
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
                        "\"/></a>\n" +
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
        printThumbnailFooter( out );
        out.print(
                "  </body>\n" +
                "</html>" );
        
        out.close();
    }
    
    /**
     * Print Thunbnail page footer
     */
    private void printThumbnailFooter( PrintWriter out ) {
        SimpleDateFormat sdf = new SimpleDateFormat( resOutput.getString("DATE_FORMAT") );
        String todaysDate = sdf.format( new Date() );

        out.print(
                "    <hr align=\"Left\" width=\"100%\" size=\"2\" noshade>\n" +
                "      <table width=\"100%\"><tr>\n" +
                "      <td align=\"left\"><small><i>"+resOutput.getString("PUBLISHED")+":</i> " + todaysDate + 
                "</small></small></td>\n" +
                "      <td align=\"right\"><small><i>"+resOutput.getString("GENERATED_BY")+"</i>\n" +
                "      <a href=\""+Constants.SITE_URL+"\"/>"+Constants.APP_NAME+"</a><br/>" + 
                "</i>" + resOutput.getString("VERSION") + ":</i> " + Settings.getInstance().getProperty( Constants.LICENSE_AGREED ) +
                "</small></td>\n</tr></table>");
    }
    
    /**
     * Print Detail page footer
     */
    private void printDetailFooter( PrintWriter out ) {
        out.print(
                "      <div align=\"right\"><small>"+resOutput.getString("GENERATED_BY")+"</i>\n" +
                "      <a href=\""+Constants.SITE_URL+"\"/>"+Constants.APP_NAME+"</a><br/>" + 
                resOutput.getString("VERSION") + " " + Settings.getInstance().getProperty( Constants.LICENSE_AGREED ) +
                "</small></div>\n");
    }
    
    /**
     * Generate detail pages
     */
    private String generateDetailPage( int index, int indexPageNum )
    throws IOException {
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
        
        String indexPage = "index";
        if( indexPageNum > 0 ) indexPage += indexPageNum;
        indexPage += ".html";

        // Add Navigation button
        if( publishManager.getNavButtonPosition().equals(
                Constants.NAV_BUTTON_POSITION_ABOVE ) ) {
            out.print( getNavButton(indexPage, prev, next, index, numPhotos) );
        }
        
        // Generate HTML for image:
        String outImageFilename = outPhoto.getOutImageName();
        if( publishManager.getCaptionPosition().equals(
                Constants.CAPTION_POSITION_ABOVE ) ) {
            out.print(getCaption(publishManager, caption));
            out.println( "<br/>\n" );
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
                        "\" alt=\""+resOutput.getString("CLICK_ZOOM")+"\"/>" +
                        "</a>" );
            } else {
                out.print(
                        "<img src=\"resized-" + outImageFilename + "\"/>" );
            }
        } else {
            out.print( "<img src=\"" + outImageFilename + "\"/>" );
        }
        
        out.println( "</div>\n" );
        
        if( publishManager.getCaptionPosition().equals(
                Constants.CAPTION_POSITION_BELOW ) ) {
            out.println( "<br/>\n" );
            out.print( getCaption(publishManager, caption) );
        }
        
        // Add Navigation button
        if( publishManager.getNavButtonPosition().equals(
                Constants.NAV_BUTTON_POSITION_BELOW ) ) {
            out.println( "<br/>\n" );
            out.print( getNavButton(indexPage, prev, next, index, numPhotos) );
        }
        
        out.print( "<hr/>\n" );

        if( publishManager.getShowExif() ) {
            if ( publishManager.getExifInSeparatePage()  ) {
                out.println( generateExifPage( outPhoto ) );
            } else {
                out.println( printExifInformation( outPhoto, "60" ) );
            }
        }
        
        printDetailFooter( out );;
        out.print(
                "  </body>\n" +
                "</html>\n" );

        out.close();
        
        return pageFilename;
    }
    
     private String getNavButton( String indexPage, String prev, String next, int index, int numPhotos ) {
    	 StringBuffer out = new StringBuffer();
		out.append( "<table border=\"0\" cellpadding=\"5\"><tr>\n" );
		
		out.append( "<td><a href=\"" + indexPage + "\">"+resOutput.getString("BACK_ALBUM")+"</a></td>\n" );
		
		if( prev != null ) {
		    out.append( "<td width=\"180\"><div align=\"center\"><a href=\"" + prev +
		            "\">&lt;&lt; "+resOutput.getString("PREVIOUS")+" &lt;&lt;</a></div></td>\n" );
		} else {
		    out.append( "<td width=\"180\"><div align=\"center\">" +
		            "<font color=\"#999999\">&lt;&lt; "+resOutput.getString("PREVIOUS")+" &lt;&lt;</font>" +
		            "</div></td>\n" );
		}
		
		if( next != null ) {
		    out.append( "<td width=\"180\"><a href=\"" + next +
		            "\">&gt;&gt; "+resOutput.getString("NEXT1")+" &gt;&gt;</a></td>\n" );
		} else {
		    out.append( "<td width=\"180\">" +
		            "<font color=\"#999999\">&gt;&gt; "+resOutput.getString("NEXT1")+" &gt;&gt;" +
		            "</font></td>\n" );
		}
		
		out.append( "<td>[ "+resOutput.getString("PHOTO")+" " + (index+1) + " "+resOutput.getString("OF")+" " + numPhotos + " ]</td>\n" );
		out.append( "</tr></table>\n" );
		
		return out.toString();
    }        

    private String getCaption( PublishManager publishManager, String caption ) {
        String out = null;
        if ( ( caption != null ) && ( !caption.equals("")) ) {
            if (publishManager.getCaptionAlign().equals(Constants.CAPTION_ALIGN_CENTER)) {
                out = "<div align=\"Center\"><br/>\n" +
                        "    " + caption + "<br/></div>\n";
            } else if (publishManager.getCaptionAlign().equals(Constants.CAPTION_ALIGN_LEFT)) {
                out = "<div align=\"Left\"><br/>\n" +
                        "    " + caption + "<br/></div>\n";
            } else if (publishManager.getCaptionAlign().equals(Constants.CAPTION_ALIGN_RIGHT)) {
                out = "<div align=\"Right\"><br/>\n" +
                        "    " + caption + "<br/></div>\n";
            }
        } else {
                out = "<br/>\n<br/>\n";
        }
        return out;
        
    }
    
    /**
     * If Exif information should be printed in a separate page, create it otherwise
     * get the Exif in HTML
     */
    private String generateExifPage( OutputPhoto outPhoto ) 
    throws IOException {
        String pageName = outPhoto.getFilename() + "Exif.html";
        
        StringBuffer sbExifInfo = new StringBuffer();
        
        // Create Link to separate page
//        sbExifInfo.append( "<a href=\"" + pageName + "\">"+resOutput.getString("EXIF_INFORMATION")+"</a></td>\n" );
        sbExifInfo.append( "<form>\n" );
        sbExifInfo.append( "      <input type=\"button\" value=\""+ resOutput.getString("EXIF_INFORMATION") +"\" onclick=\"window.open('"+ pageName +"', '"+ pageName +"','width=600,height=500')\">\n" );
        sbExifInfo.append( "</form>\n" );
                
        // Create separate page
        File outputDirectory = publishManager.getOutputDirectory();
        File outFile = new File( outputDirectory, pageName );
        PrintWriter out = new PrintWriter( new FileWriter( outFile ) );
        
        out.print(
                "<html>\n" +
                "  <head>\n" +
                "    <title>" + outPhoto.getFilename() );
        out.print(
                "</title>\n" +
                "  </head>\n" +
                "  <body bgcolor=\"#ffffff\">\n" );
        
        out.print(
                "<h1>" + outPhoto.getFilename() + "</h1>");

        out.print( "<hr/>\n" );

        out.print( printExifInformation( outPhoto, "100" ) );
        
        out.print(
                "  </body>\n" +
                "</html>" );

        out.close();
            
        return sbExifInfo.toString();
    }
    
    /**
     * Print EXIF information
     */
    private String printExifInformation( OutputPhoto outPhoto, String width ) {
        StringBuffer sbExifInfo = new StringBuffer();
        if ( outPhoto.isExif() ) {
            sbExifInfo.append(
                    "<table cellspacing=\"0\" " +
                    "border=\"0\" width=\""+ width +"%\" align=\"Left\">\n" );
            sbExifInfo.append( "      <tr><td>Creation Date</td>\n" );
            sbExifInfo.append( "      <td><b>"+outPhoto.getCreationDate()+"</b></td></tr>\n" );
            sbExifInfo.append( "      <tr><td>Digitized Date</td>\n" );
            sbExifInfo.append( "      <td><b>"+outPhoto.getDigitizedDate()+"</b></td></tr>\n" );
            sbExifInfo.append( "      <tr><td>Modified Date</td>\n" );
            sbExifInfo.append( "      <td><b>"+outPhoto.getModifiedDate()+"</b></td></tr>\n" );
            sbExifInfo.append( "      <tr><td>Camera Type</td>\n" );
            sbExifInfo.append( "      <td><b>"+outPhoto.getCameraType()+"</b></td></tr>\n" );
            sbExifInfo.append( "      <tr><td>Camera Model</td>\n" );
            sbExifInfo.append( "      <td><b>"+outPhoto.getCameraModel()+"</b></td></tr>\n" );
            sbExifInfo.append( "      <tr><td>Camera Make</td>\n" );
            sbExifInfo.append( "      <td><b>"+outPhoto.getCameraMake()+"</b></td></tr>\n" );
            sbExifInfo.append( "      <tr><td>Firmware Version</td>\n" );
            sbExifInfo.append( "      <td><b>"+outPhoto.getFirmwareVersion()+"</b></td></tr>\n" );
            sbExifInfo.append( "      <tr><td>FStop</td>\n" );
            sbExifInfo.append( "      <td><b>"+outPhoto.getFStop()+"</b></td></tr>\n" );
            sbExifInfo.append( "      <tr><td>Exposure Program</td>\n" );
            sbExifInfo.append( "      <td><b>"+outPhoto.getExposureProgram()+"</b></td></tr>\n" );
            sbExifInfo.append( "      <tr><td>Light Source</td>\n" );
            sbExifInfo.append( "      <td><b>"+outPhoto.getLightSource()+"</b></td></tr>\n" );
            sbExifInfo.append( "      <tr><td>ISO</td>\n" );
            sbExifInfo.append( "      <td><b>"+outPhoto.getISO()+"</b></td></tr>\n" );
            sbExifInfo.append( "      <tr><td>Shutter Speed</td>\n" );
            sbExifInfo.append( "      <td><b>"+outPhoto.getShutterSpeed()+"</b></td></tr>\n" );
            sbExifInfo.append( "      <tr><td>Flash</td>\n" );
            sbExifInfo.append( "      <td><b>"+outPhoto.getFlash()+"</b></td></tr>\n" );
            sbExifInfo.append( "      <tr><td>Image Height</td>\n" );
            sbExifInfo.append( "      <td><b>"+outPhoto.getImageHeight()+"</b></td>\n" );
            sbExifInfo.append( "      <tr><td>Image Width</td>\n" );
            sbExifInfo.append( "      <td><b>"+outPhoto.getImageWidth()+"</b></td></tr>\n" );
            sbExifInfo.append( "      <tr><td>Log info</td>\n" );
            sbExifInfo.append( "      <td>"+outPhoto.getAllExif()+"</td></tr>\n" );
            sbExifInfo.append( "</table>\n" );
        } else {
            sbExifInfo.append( "No EXIF information in this picture.\n" );
        }
//        sbExifInfo.append( "<hr/>\n" );
        return sbExifInfo.toString();
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
