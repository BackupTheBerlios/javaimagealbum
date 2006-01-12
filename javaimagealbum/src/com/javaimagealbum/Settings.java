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

/**
 * Persistent Settings for Java Image Album.
 *
 * @author  mroth
 * @author  Mirko Actis
 */
public final class Settings 
    extends PersistentSettings 
{
    /** The unique application identifier */
    private static final String APPLICATION_ID = "javaimagealbum";
    
    /** The store name for the properties */
    private static final String STORE_NAME = "settings";
    
    /** The one and only Settings instance for this app. */
    private static Settings theInstance = null;
    
    /** Creates new Settings */
    private Settings() {
        super( APPLICATION_ID, STORE_NAME );
    }
    
    /** 
     * Initializes Settings object.  This should be called at
     * program initialization.  
     * 
     * @param IOException Thrown if settings file exists, but 
     *   could not be retrieved.
     */
    public static void init() 
        throws IOException
    {
        if( theInstance != null ) {
            throw new RuntimeException( 
              "An attempt was made to initialize Settings twice!" );
        }
        
        Settings.theInstance = new Settings();
        Settings.theInstance.refresh();
    }
    
    /** 
     * Retrieves the instance of the Settings object.
     */
    public static Settings getInstance() {
        return Settings.theInstance;
    }
    
    /**
     * Ignores the possible IOException, and prints an error message to
     * the console.
     */
    public void setProperty( String key, String value ) {
        try {
            super.setProperty( key, value );
        }
        catch( IOException e ) {
            System.err.println( "IOException persisting property " + key + 
                " to " + value + ".");
            e.printStackTrace();
        }
    }

    /**
     * Returns the description to be placed in the properties file.
     * This implementation returns a default value, but subclasses
     * can override this value.
     */
    protected String getDescription() {
        return "Java Image Album Settings";
    }
    
}
