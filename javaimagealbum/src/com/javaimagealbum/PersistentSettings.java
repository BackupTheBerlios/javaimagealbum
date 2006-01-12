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
 * Allows an application to persist application settings from session
 * to session.  Stores a file in the user's home directory, under
 * an application-specific subdirectory.
 *
 * In Windows NT, this is normally 
 * C:\WINNT\Profiles\<Username>\.<appId>\<store>.properties
 * In UNIX, this is normally /home/<user>/.<appId>/<store>.properties.
 *
 * @author  mroth
 */
public class PersistentSettings {

    /** An application-unique identifier */
    private String appId;
    
    /** A name for this store */
    private String storeName;
    
    /** The properties file location. */
    private File propertiesFile;
    
    /** The set of properties */
    private Properties properties;
    
    /**
     * Creates new PersistentSettings.  After creating, refresh()
     * must be called to retrieve the settings.
     *
     * @param appId An application-unique identifier, suitable for a
     *   directory name to store settings under.  The appId will be
     *   prepended with a "." so that the directory appears hidden
     *   under UNIX OS'.
     * @param storeName A properties file to store information to.
     */
    public PersistentSettings( String appId, String storeName ) {
        this.appId = appId;
        this.storeName = storeName;
        
        // Determine where to load/store properties:
        File homeDir = new File( System.getProperty( "user.home" ) );
        File appDir = new File( homeDir, "." + appId );
        this.propertiesFile = new File( appDir, storeName + ".properties" );
        
        // Load current set of properties
        this.properties = new Properties();
    }
    
    /**
     * Sets a property and makes it persistent, immediately.
     *
     * @param key The key to set
     * @param value The value to set the key to.
     * @throws IOException Thrown if the properties could not be persisted.
     *   The setting will remain set, but only in memory.  The next time
     *   a property is set, an attempt to store all properties will be
     *   made again.
     */
    public void setProperty( String key, String value ) 
        throws IOException
    {
        this.properties.setProperty( key, value );
        save();
    }
    
    /**
     * Retrieves a property.  Even if the properties file has changed since
     * the last retrieval of this property, the version of the value at
     * the time of construction, or since the last refresh() call is used.
     * This allows getProperty to be called without worry of an IOException
     * being thrown.
     * 
     * @param key The key to return.
     * @return The value of the given property.
     */
    public String getProperty( String key ) {
        return this.properties.getProperty( key );
    }

    /**
     * Retrieves a property, and if the property does not exist, the given
     * default value is returned.  Even if the properties file has changed 
     * since the last retrieval of this property, the version of the value at
     * the time of construction, or since the last refresh() call is used.
     * This allows getProperty to be called without worry of an IOException
     * being thrown.
     * 
     * @param key The key to return.
     * @param defaultValue The default value to return, if the key doesn't
     *   exist.
     * @return The value of the given property.
     */
    public String getProperty( String key, String defaultValue ) {
        return this.properties.getProperty( key, defaultValue );
    }

    /**
     * Forces a refresh of the properties in this object, by reloading
     * the contents of the properties file in the user's home directory.
     *
     * @throws IOException Thrown if an IO Exception occurs while reading
     *   the properties.  This will not be thrown if the file does not
     *   exist.  However, it will be thrown if the file exists and it
     *   cannot be read.
     */
    public void refresh() 
        throws IOException
    {
        if( this.propertiesFile.exists() ) {
            load();
        }
    }

    /**
     * Attempts to load the properties file into memory.
     * 
     * @throws IOException Thrown if the properties file could not be
     *     persisted.
     */
    private synchronized void load() 
        throws IOException
    {
        FileInputStream in = new FileInputStream( this.propertiesFile );
        this.properties.load( in );
        in.close();
    }
    
    /**
     * Persists the current set of properties.
     *
     * @throws IOException If the properties could not be persisted.
     */
    private synchronized void save() 
        throws IOException 
    {
        // If output directory doesn't exist, create it first.
        File dir = this.propertiesFile.getParentFile();
        if( !dir.exists() ) {
            dir.mkdirs();
        }
        
        // Next, write setting to file:
        FileOutputStream out = new FileOutputStream( this.propertiesFile );
        this.properties.store( out, getDescription() );
        out.close();
    }
    
    /**
     * Returns the description to be placed in the properties file.
     * This implementation returns a default value, but subclasses
     * can override this value.
     */
    protected String getDescription() {
        return "Application Settings";
    }

}
