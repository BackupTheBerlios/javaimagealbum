package com.javaimagealbum.util;

import com.javaimagealbum.resources.*;

import java.net.*;
import java.util.*;
import javax.swing.*;


/**
 * A simple, static class to display a URL in the system browser.
 *
 * Under Unix, the system browser is hard-coded to be 'netscape'.
 * Netscape must be in your PATH for this to work. This has been
 * tested with the following platforms: AIX, HP-UX and Solaris.
 *
 * Under Windows, this will bring up the default browser under windows,
 * usually either Netscape or Microsoft IE. The default browser is
 * determined by the OS. This has been tested under Windows 95/98/NT.
 *
 * Examples:
 * BrowserControl.displayURL("http://www.javaworld.com")
 * BrowserControl.displayURL("file://c:\\docs\\index.html")
 * BrowserContorl.displayURL("file:///user/joe/index.html");
 *
 * Note - you must include the url type -- either "http://" or
 * "file://".
 */
public class BrowserControl {
    static ResourceBundle res = ResourceFactory.getBundle();
//    static String[] validPrimaryDomains =
//    {
//        ".com", ".net", ".org", ".us", ".info", ".biz", ".ws", ".tv", ".cc", ".de", ".jp", ".be", ".at", ".uk", ".nz",
//        ".co"
//    };

    //No instance class available.
    private BrowserControl() {}

    /**
     * Display a file in the system browser. If you want to display a
     * file, you must include the absolute path name.
     *
     * @param url the file's url (the url must start with either "http://" or
     * "file://").
     */
    public static void displayURL(String url) {
        try {
            BrowserLauncher.openURL(url);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, res.getString("Unable_to_launch_browser_") + url,
                res.getString("Error_Launching_Browser"), JOptionPane.ERROR_MESSAGE);
        }

        //launch(url);
//        return;
    }

    /**
     * Is it a URL
     */
    public static boolean isURL(String siteURL) {
        try {
            new URL(siteURL);
        } catch (java.net.MalformedURLException exc) {
            //attempt to match primaryDomain
//            if (siteURL.indexOf("@") > 0) {
//                return false;
//            }

//            for (int i = 0; i < validPrimaryDomains.length; i++) {
//                if (siteURL.indexOf(validPrimaryDomains[i]) > 0) {
//                    return true;
//                }
//            }

            return false;
        }

        return true;
    }

    //    private static void launch(String url) {
    //        boolean windows = isWindowsPlatform();
    //        String cmd = null;
    //        Process p = null;
    //
    //        try {
    //            if (windows) {
    //                // cmd = 'rundll32 url.dll,FileProtocolHandler http://...'
    //                cmd = WIN_PATH + " " + WIN_FLAG + " " + url;
    //
    //                p = Runtime.getRuntime().exec(cmd);
    //            } else {
    //                // Under Unix, Netscape has to be running for the "-remote"
    //                // command to work. So, we try sending the command and
    //                // check for an exit value. If the exit command is 0,
    //                // it worked, otherwise we need to start the browser.
    //                // cmd = 'netscape -remote openURL(http://www.javaworld.com)'
    //                cmd = UNIX_PATH + " " + UNIX_FLAG + "(" + url + ")";
    //
    //                p = Runtime.getRuntime().exec(cmd);
    //
    //                try {
    //                    // wait for exit code -- if it's 0, command worked,
    //                    // otherwise we need to start the browser up.
    //                    int exitCode = p.waitFor();
    //
    //                    if (exitCode != 0) {
    //                        // Command failed, start up the browser
    //                        // cmd = 'netscape http://www.javaworld.com'
    //                        cmd = UNIX_PATH + " " + url;
    //                        p = Runtime.getRuntime().exec(cmd);
    //                    }
    //                } catch (InterruptedException x) {
    //                    System.err.println("Error bringing up browser, cmd='" + cmd + "'");
    //                    System.err.println("Caught: " + x);
    //                }
    //            }
    //        } catch (IOException x) {
    //            // couldn't exec browser
    //            System.err.println("Could not invoke browser, command=" + cmd);
    //            System.err.println("Caught: " + x);
    //        }
    //    }

    /**
     * Try to determine whether this application is running under Windows
     * or some other platform by examing the "os.name" property.
     *
     * @return true if this application is running under a Windows OS
     */
    public static boolean isWindowsPlatform() {
        String os = System.getProperty("os.name");

        if ((os != null) && os.startsWith(WIN_ID)) {
            return true;
        }
        return false;
    }

    /**
     * Simple example.
     */
    public static void main(String[] args) {
        displayURL("http://sdm.sourceforge.net");
    }

    // Used to identify the windows platform.
    private static final String WIN_ID = "Windows";

    // The default system browser under windows.
//    private static final String WIN_PATH = "rundll32";

    // The flag to display a url.
//    private static final String WIN_FLAG = "url.dll,FileProtocolHandler";

    // The default browser under unix.
//    private static final String UNIX_PATH = "netscape";

    // The flag to display a url.
//    private static final String UNIX_FLAG = "-remote openURL";
}