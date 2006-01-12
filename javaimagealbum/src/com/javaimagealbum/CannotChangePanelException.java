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

/**
 * Thrown when an attempt is made to change the current panel
 * but it cannot be done.
 *
 * @author  Mark Roth
 */
public class CannotChangePanelException extends Exception {

    /**
     * Creates new <code>CannotChangePanelException</code> without 
     * detail message.
     */
    public CannotChangePanelException() {
    }


    /**
     * Constructs an <code>CannotChangePanelException</code> with the 
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public CannotChangePanelException(String msg) {
        super(msg);
    }
}

