/*
 Copyright (C) 2001, Brent Bryan

 Authors:
 Brent Bryan     (brent@whitties.org)
 Brendan McMahan (mcmahahb@whitman.edu)

 For questions, comments, and the latest version:
 http://sourceforge.net/projects/swigs/

 This program is free software; you can redistribute it and/or
 modify it under the terms of the GNU General Public License
 as published by the Free Software Foundation; either version 2
 of the License, or (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program; if not, write to the Free Software
 Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.

 */

package com.javaimagealbum.exif;

import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;

public class ExifHashMap extends HashMap {

    // protected static Perl5Util perlUtil = new Perl5Util();
    private String[] monthNames = { "January", "February", "March", "April",
            "May", "June", "July", "August", "September", "October",
            "November", "December" };

    public static final int CREATION_DATE = 0;

    public static final int DIGITIZED_DATE = 1;

    public static final int MODIFIED_DATE = 2;

    public ExifHashMap(HashMap hashMap) {
        super(hashMap);
    }

    public String getCameraMake() {
        return getString(ExifConstants.CAMERA_MAKE);
    }

    public String getCameraModel() {
        return getString(ExifConstants.CAMERA_MODEL);
    }

    public String getCameraType() {
        return getString(ExifConstants.CAMERA_TYPE);
    }

    public String getFirmwareVersion() {
        return getString(ExifConstants.FIRMWARE_VERSION);
    }

    public String getShutterSpeed() {
        Rational rat = (Rational) getObject(ExifConstants.SHUTTER_SPEED);
        if (rat == null)
            return "";
        return rat.getFraction();
    }

    public String getFStop() {
        Rational rat = (Rational) getObject(ExifConstants.F_NUMBER);
        if (rat == null)
            return "";
        return "" + rat.getDoubleValue();
    }

    public String getExposureProgram() {
        int programNumber = getInt(ExifConstants.EXPOSURE_PROGRAM);
        switch (programNumber) {
        case 1:
            return "Manual Mode";
        case 2:
            return "Automatic Mode";
        case 3:
            return "Aperture Priority";
        case 4:
            return "Shutter Priority";
        case 5:
            return "Night Mode";
        case 6:
            return "Action Mode";
        case 7:
            return "Portrait Mode";
        case 8:
            return "Landscape Mode";
        default:
            return "";
        }
    }

    public String getISO() {
        return "" + getInt(ExifConstants.ISO_RATING);
    }

    public String getImageWidth() {
        String out = "";
        if (getInt(ExifConstants.IMAGE_WIDTH) > 0) {
            out += getInt(ExifConstants.IMAGE_WIDTH);
        }
        return out ;
    }

    public String getImageHeight() {
        String out = "";
        if (getInt(ExifConstants.IMAGE_HEIGHT) > 0) {
            out += getInt(ExifConstants.IMAGE_HEIGHT);
        }
        return out ;
    }

    public Image getThumbnail() {
        return (Image) getObject(ExifConstants.THUMBNAIL_IMAGE);
    }

    public String getFlash() {
        int flash = getInt(ExifConstants.FLASH);
        switch (flash) {
        case 0:
            return "No Flash";
        case 5:
            return "Camera Flash";
        case 6:
            return "Camera Flash, no Strobe Bounce Detected";
        case 7:
            return "Camera Flash, Strobe Bounce Detected";
        }
        return "";
    }

    public String getLightSource() {
        int light = getInt(ExifConstants.LIGHT_SOURCE);
        switch (light) {
        case 0:
            return "Unknown";
        case 1:
            return "Daylight";
        case 2:
            return "Fluorescent";
        case 3:
            return "Tungsten";
        case 10:
            return "Flash";
        case 17:
            return "Standard Light A";
        case 18:
            return "Standard Light B";
        case 19:
            return "Standard Light C";
        case 20:
            return "D55";
        case 21:
            return "D65";
        case 22:
            return "D75";
        case 255:
            return "Other";
        }
        return "";
    }

    public String getDate(int dateType) {
        String dateTime;
        if (dateType == DIGITIZED_DATE) {
            dateTime = getString(ExifConstants.DIGITIZED_DATE);
        } else if (dateType == MODIFIED_DATE) {
            dateTime = getString(ExifConstants.MODIFIED_DATE);
        } else {
            dateTime = getString(ExifConstants.CREATION_DATE);
        }
        if (dateTime.equals(""))
            return "";

        ArrayList dateParts = new ArrayList();
        // perlUtil.split(dateParts, "/[: ]/", dateTime);
        if (dateParts.size() != 6)
            return dateTime;

        int mNum = getAInt(dateParts, 1) - 1;
        String month = "Month " + mNum;
        if (mNum >= 0 && mNum < 12) {
            month = monthNames[mNum];
        } else {
            System.out.println("invalid month " + mNum + " from date "
                    + dateTime);
        }

        int day = getAInt(dateParts, 2);
        int year = getAInt(dateParts, 0);
        return month + " " + day + ", " + year;
    }

    public String getTime(int dateType) {
        String dateTime;
        if (dateType == DIGITIZED_DATE) {
            dateTime = getString(ExifConstants.DIGITIZED_DATE);
        } else if (dateType == MODIFIED_DATE) {
            dateTime = getString(ExifConstants.MODIFIED_DATE);
        } else {
            dateTime = getString(ExifConstants.CREATION_DATE);
        }
        if (dateTime.equals(""))
            return "";

        ArrayList dateParts = new ArrayList();
        // perlUtil.split(dateParts, "/[: ]/", dateTime);
        if (dateParts.size() != 6)
            return dateTime;

        int hour = getAInt(dateParts, 3);
        int minute = getAInt(dateParts, 4);
        int second = getAInt(dateParts, 5);
        return (hour + ":" + minute + ":" + second);
    }

    // ------------------------------ Utilities ------------------------------
    // //
    private int getAInt(ArrayList array, int index) {
        return Integer.parseInt((String) array.get(index));
    }

    // ------------------------------ Return Types
    // ------------------------------//
    private Object getObject(int i) {
        return super.get(new Integer(i));
    }

    public int getInt(int i) {
        Integer obj = (Integer) getObject(i);
        if (obj == null)
            return -1;
        return obj.intValue();
    }

    public Rational getRational(int i) {
        Rational obj = (Rational) getObject(i);
        if (obj == null)
            return new Rational(1, 0);
        return obj;
    }

    public String getString(int i) {
        Object obj = getObject(i);
        if (obj == null)
            return "";
        return (String) obj;
    }
}
