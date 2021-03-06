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
import java.util.Iterator;
import java.util.Map.Entry;

public class ExifHashMap extends HashMap {
    
    /** Default serial version */
    private static final long serialVersionUID = 1L;
    
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
        final Rational rat = (Rational) getObject(ExifConstants.SHUTTER_SPEED);
        if (rat == null) {
            return "";
        }
        return rat.getFraction();
    }
    
    public String getFStop() {
        final Rational rat = (Rational) getObject(ExifConstants.F_NUMBER);
        if (rat == null) {
            return "";
        }
        return "" + rat.getDoubleValue();
    }
    
    public String getExposureProgram() {
        final int programNumber = getInt(ExifConstants.EXPOSURE_PROGRAM);
        String out = "";
        switch (programNumber) {
            case 1:
                out = "Manual Mode";
            case 2:
                out = "Automatic Mode";
            case 3:
                out = "Aperture Priority";
            case 4:
                out = "Shutter Priority";
            case 5:
                out = "Night Mode";
            case 6:
                out = "Action Mode";
            case 7:
                out = "Portrait Mode";
            case 8:
                out = "Landscape Mode";
            default:
                out = "";
        }
        return (out);
    }
    
    public String getISO() {
        String out = "";
        if (getInt(ExifConstants.ISO_RATING) > 0) {
            out += getInt(ExifConstants.ISO_RATING);
        }
        return out ;
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
        final int flash = getInt(ExifConstants.FLASH);
        String out = "";
        switch (flash) {
            case 0:
                out = "No Flash";
            case 5:
                out = "Camera Flash";
            case 6:
                out = "Camera Flash, no Strobe Bounce Detected";
            case 7:
                out = "Camera Flash, Strobe Bounce Detected";
            default :
                out = "";
        }
        return (out);
    }
    
    public String getLightSource() {
        int light = getInt(ExifConstants.LIGHT_SOURCE);
        String out = "";
        switch (light) {
            case 0:
                out = "Unknown";
            case 1:
                out = "Daylight";
            case 2:
                out = "Fluorescent";
            case 3:
                out = "Tungsten";
            case 10:
                out = "Flash";
            case 17:
                out = "Standard Light A";
            case 18:
                out = "Standard Light B";
            case 19:
                out = "Standard Light C";
            case 20:
                out = "D55";
            case 21:
                out = "D65";
            case 22:
                out = "D75";
            case 255:
                out = "Other";
            default :
                out = "";
        }
        return (out);
    }
    
    public String getDate( final int dateType ) {
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
        if (dateParts.size() != 6) {
            return dateTime;
        }
        
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

    public String toString() {
    	StringBuffer buf = new StringBuffer();
    	buf.append("{\n");

    	Iterator i = entrySet().iterator();
            boolean hasNext = i.hasNext();
            while (hasNext) {
    	    Entry e = (Entry) (i.next());
                Object key = e.getKey();
                Object value = e.getValue();
                buf.append((key == this ?  "(this Map)" : key) + "=" + 
                           (value == this ? "(this Map)": value));

                hasNext = i.hasNext();
                if (hasNext)
                    buf.append(",\n");
            }

    	buf.append("\n}");
    	return buf.toString();
        }
}
