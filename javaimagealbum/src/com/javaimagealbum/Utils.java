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
 * Generic, common, reusable utility methods.
 *
 * @author  Mark Roth
 */
public class Utils extends Object {

    /** 
     * Private constructor disallows instantiation of this 
     * static utility class. 
     */
    private Utils() {
    }
    
    /**
     * Copies a source file to a destination directory.  If
     * the destination file already exists, it is overwritten.
     */
    public static void copyFile( File source, File dest ) 
        throws IOException
    {
        if( !source.equals( dest ) ) {
	    InputStream in = new FileInputStream( source );
	    OutputStream out = new FileOutputStream( dest );
	    byte[] buffer = new byte[4096];
	    
	    int bytesRead;
	    while( (bytesRead = in.read( buffer )) != -1 ) {
		out.write( buffer, 0, bytesRead );
	    }
	    
	    out.close();
	    in.close();
	}
    }

}
