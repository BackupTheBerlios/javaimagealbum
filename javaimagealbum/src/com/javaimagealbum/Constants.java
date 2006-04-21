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
import java.util.Locale;

/**
 * Various constants for Web Photo Publisher
 *
 * @author  Mark Roth
 * @author  Mirko Actis Grosso
 */
public final class Constants {

    /**
     * Private constructor, as this is a Utility class
     */
    private Constants() {
    }
    
    // Application Name
    public static final String APP_NAME = "Java Image Album";

    public static final String SITE_URL = "http://javaimagealbum.berlios.de/";

    // Settings Constants:
    
    /** Set to true if we are skipping the intro panel, or false if not */
    public static final String LICENSE_AGREED = "agree_to_license_agreement";
    public static final String SKIP_INTRO = "skip_intro";
    public static final String DEFAULT_INPUT_DIR = "input_dir";
    public static final String DEFAULT_OUTPUT_DIR = "output_dir";
    public static final String LOOK_AND_FEEL = "look_and_feel";
    public static final String AUTO_PREVIEW = "auto_preview";
    public static final String ALBUM_TITLE = "album_title";
    public static final String ALBUM_DESCRIPTION = "album_description";
    public static final String OUTPUT_COLUMNS = "output_columns";
    public static final String THUMBNAILS_PER_PAGE = "thumbnails_per_page";
    public static final String RESIZE_ALL = "resize_all";
    public static final String RESIZE_ALL_WIDTH = "resize_all_width";
    public static final String RESIZE_ALL_HEIGHT = "resize_all_height";
    public static final String RESIZE_PORTRAITS = "resize_portraits";
    public static final String RESIZE_PORTRAITS_WIDTH = 
        "resize_portraits_width";
    public static final String RESIZE_PORTRAITS_HEIGHT = 
        "resize_portraits_height";
    public static final String PUBLISH_FULL_SIZE =
        "publish_full_size";
    public static final String STORE_CAPTIONS = "store_captions";
    public static final String LINK_TO_ALBUM_INDEX = "link_to_album_index";
    public static final String DESCRIPTION_IN_EMPTY_PAGE = "description_in_empty_page";
    public static final String SHOW_EXIF = "show_exif";
    public static final String OUTPUT_LANGUAGE = "output_language";
    public static final String PHOTO_POSITION = "photo_position";
    public static final String PHOTO_POSITION_LEFT = "left";
    public static final String PHOTO_POSITION_CENTER = "center";
    public static final String PHOTO_POSITION_RIGHT = "right";
    public static final String CAPTION_POSITION = "caption_position";
    public static final String CAPTION_POSITION_BELOW = "below";
    public static final String CAPTION_POSITION_ABOVE = "above";
    public static final String CAPTION_ALIGN = "caption_align";
    public static final String CAPTION_ALIGN_LEFT = "left";
    public static final String CAPTION_ALIGN_CENTER = "center";
    public static final String CAPTION_ALIGN_RIGHT = "right";
    
    // Miscellaneous Constants:
    public static final int THUMBNAIL_WIDTH = 160;
    public static final int THUMBNAIL_HEIGHT = 120;
    public static final Dimension THUMBNAIL_DIMENSION = 
        new Dimension( THUMBNAIL_WIDTH, THUMBNAIL_HEIGHT );
    public static final String UNLIMITED_THUMBNAILS = "unlimited";
    
    // Localization constant:
    public static final Locale[] listLocaleForOutput = new Locale[] { Locale.ITALIAN, Locale.ENGLISH, new Locale("ru") };
    //public static final Locale[] listLocaleForOutput = new Locale[] { Locale.ITALIAN, Locale.ENGLISH };
    //new javax.swing.DefaultComboBoxModel(new Locale[] { Locale.ITALIAN, Locale.ENGLISH })    
}
