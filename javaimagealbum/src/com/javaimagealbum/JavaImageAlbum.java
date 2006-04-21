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

import javax.swing.*;

import com.javaimagealbum.resources.*;

import java.io.*;
import java.util.ResourceBundle;

/**
 * Main entry point for Java Image Album.
 *
 * @author  mroth
 * @author  Mirko Actis Grosso
*/
public class JavaImageAlbum {
    static ResourceBundle res = ResourceFactory.getBundle();

    /**
    * @param args the command line arguments
    */
    public static void main (String args[]) {
        // Initialize settings support:
        try {
            Settings.init();
        }
        catch( IOException e ) {
            JOptionPane.showMessageDialog( null, 
                    res.getString("MessageDialog1")+
                    "Reason: " + e.getMessage(),
                    "Warning", JOptionPane.WARNING_MESSAGE );
            e.printStackTrace();
        }
        
        // Set look and feel to platform default.
        String lookAndFeel = Settings.getInstance().getProperty(
            Constants.LOOK_AND_FEEL, 
            UIManager.getSystemLookAndFeelClassName() );
        try {
            UIManager.setLookAndFeel( lookAndFeel );
        } 
        catch (Exception e) { 
            JOptionPane.showMessageDialog( null,
                    res.getString("MessageDialog2P1") + lookAndFeel +
                    ".\n" +
                    res.getString("MessageDialog2P2"),
                    "Warning", JOptionPane.ERROR_MESSAGE );
            e.printStackTrace();
        }
        
        // Start main frame:
        JavaImageAlbumFrame frame = new JavaImageAlbumFrame ();
        
        // Center the frame
        frame.setLocationRelativeTo( null );
        
        // Show it!
        frame.show();
    }

}
