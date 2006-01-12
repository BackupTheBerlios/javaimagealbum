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
 * Common interface that all panels in this application implement.
 * This helps the GUI know when to enable the "Next>>" button.
 * 
 * @author  Mark Roth
 */
public interface WizardPanel {
    /**
     * Returns true if all required data was filled in for this panel.
     */
    public boolean isSatisfied();
    
    /**
     * Called when the panel is shown to the user
     */
    public void showPanel();
    
    /**
     * Called when the panel is hidden from the user
     *
     * @param forwardDirection True if the panel is being hidden because
     *     we are moving forward.  This allows the wizard panel to do
     *     last minute validation.
     * @exception CannotChangePanelException Thrown if, during last
     *     checks, we cannot change to the next panel.
     */
    public void hidePanel( boolean forwardDirection ) 
        throws CannotChangePanelException;
}
