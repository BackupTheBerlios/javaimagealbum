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
     * @param args the command line arguments \n -v : show version information \n -h : show help
     */
    public static void main(String args[]) {
        // Read command line parameter
        try {
            if (args.length > 0) {
                for(int i = 0;i < args.length;i++) {
                    if (args[i].equalsIgnoreCase("-v")) {
                        System.out.println( Constants.APP_NAME + " " + res.getString("VERSION")+" " + JavaImageAlbumFrame.class.getPackage().getImplementationVersion() );
                        System.out.println( "For more information see: ");
                        System.out.println( "Web Site: " + Constants.SITE_URL );
                        System.out.println( "Mailing List: " + Constants.MAIL_LIST );
                        System.out.println( "Hosting Site: " + Constants.HOSTING_URL );
                    } else if (args[i].equalsIgnoreCase("-h")) {
                        System.out.println("Help");
                        System.out.println( "For more information see: ");
                        System.out.println( "Web Site: " + Constants.SITE_URL );
                        System.out.println( "Mailing List: " + Constants.MAIL_LIST );
                        System.out.println( "Hosting Site: " + Constants.HOSTING_URL );
                    } else  {
                        System.out.println("Invalid command line parameter: "+args[i]);
                        System.out.println("Use: [-h] for Help, [-v] for Version");
                    }
                }
            } else {
                startApp();
            }
        } catch (Exception e) {
            System.err.println("Unhandled Exception: "+e);
            e.printStackTrace();
        }
    }
    
    private static void startApp() {
        // Initialize settings support:
        try {
            Settings.init();
        } catch( IOException e ) {
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
        } catch (Exception e) {
            JOptionPane.showMessageDialog( null,
                    res.getString("MessageDialog2P1") + lookAndFeel +
                    ".\n" +
                    res.getString("MessageDialog2P2"),
                    "Warning", JOptionPane.ERROR_MESSAGE );
            e.printStackTrace();
        }
        
        // Start main frame:
        JavaImageAlbumFrame frame = new JavaImageAlbumFrame();
        
        // Center the frame
        frame.setLocationRelativeTo( null );
        
        // Show it!
        frame.show();
    }
    
}
