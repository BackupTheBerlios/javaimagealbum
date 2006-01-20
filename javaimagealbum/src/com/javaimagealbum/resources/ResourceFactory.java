/* ==========================================================================
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 * 
 * The Original Code is Java Image Album.
 * 
 * The Initial Developer of the Original Code is Mirko Actis Grosso.  Portions 
 * created by Mirko Actis Grosso are Copyright (C) 2006 Mirko Actis Grosso.  
 * All Rights Reserved.
 * 
 * Contributor(s) listed below.
 * 
 */
package com.javaimagealbum.resources;

import java.util.*;


/**
 * Static class used to get ResourceBundles for the Java Image Album project.
 *
 * @author  Mirko
 * @version
 */
public class ResourceFactory {
    private static ResourceBundle textBundle = ResourceBundle.getBundle("com.javaimagealbum.resources.Resource");
    private static ResourceBundle mnemonicBundle =
        ResourceBundle.getBundle("com.javaimagealbum.resources.MnemonicResource");

    private ResourceFactory() {}

    public static ResourceBundle getBundle() {
        return textBundle;
    }

    public static ResourceBundle getBundle(Locale locale) {
        return ResourceBundle.getBundle("com.javaimagealbum.resources.Resource", locale);
    }

    public static ResourceBundle getMnemonicBundle() {
        return mnemonicBundle;
    }
}
