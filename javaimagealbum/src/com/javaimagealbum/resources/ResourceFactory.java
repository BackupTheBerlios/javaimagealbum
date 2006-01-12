/* ==========================================================================
 * This code is licensed under:
 * SecureDataManager Software License, Version 1.0
 * - for license details see http://sdm.sourceforge.net/LICENSE.TXT
 *
 * Copyright (c) 2001 by the SecureDataManager Team. All rights reserved.
 * (SecureDataManager Team - see http://sdm.sourceforge.net/team.html)
 */
package com.javaimagealbum.resources;

import java.util.*;


/**
 * Static class used to get ResourceBundles for the jIA project.
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

    public static ResourceBundle getMnemonicBundle() {
        return mnemonicBundle;
    }
}
