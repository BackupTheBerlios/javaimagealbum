/*
 Copyright (C) 2001, Brent Bryan
 
 Authors:
 Brent Bryan     (brent@whitties.org)
 Brendan McMahan (mcmahahb@whitman.edu)
 
 For questions, comments, and the latest version:
 http://jigs.sourceforge.net/
 
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

import java.util.HashMap;

public class ExifConstants {
    public ExifConstants() {
        tagNames = new HashMap();
        tagNames.put(new Integer(0x010e), "Image Description           ");
        tagNames.put(new Integer(0x010f), "Manufacture Make            ");
        tagNames.put(new Integer(0x0110), "Manufacture Model           ");
        tagNames.put(new Integer(0x0112), "Camera Orientation          ");
        tagNames.put(new Integer(0x011a), "X Resolution                ");
        tagNames.put(new Integer(0x011b), "Y Resolution                ");
        tagNames.put(new Integer(0x011c), "Planar Configuration        ");
        tagNames.put(new Integer(0x0128), "Resolution Unit             ");
        tagNames.put(new Integer(0x0131), "Firmware Version            ");
        tagNames.put(new Integer(0x0132), "Last Modified               ");
        tagNames.put(new Integer(0x013e), "White Point                 ");
        tagNames.put(new Integer(0x013f), "Primary Chromaticities      ");
        tagNames.put(new Integer(0x0211), "YCbCr Coefficients          ");
        tagNames.put(new Integer(0x0212), "YCbCr SubSampling           ");
        tagNames.put(new Integer(0x0213), "YCbCr Positioning           ");
        tagNames.put(new Integer(0x0214), "Black/White Reference       ");
        tagNames.put(new Integer(0x8298), "Copyright                   ");
        tagNames.put(new Integer(0x8769), "Exif Offset                 ");

        tagNames.put(new Integer(0x829a), "Exposure Time               ");
        tagNames.put(new Integer(0x829d), "F Number                    ");
        tagNames.put(new Integer(0x8822), "Exposure Program            ");
        tagNames.put(new Integer(0x8827), "ISO Rating                  ");
        tagNames.put(new Integer(0x9000), "EXIF Version                ");
        tagNames.put(new Integer(0x9003), "Creation Date               ");
        tagNames.put(new Integer(0x9004), "Digitized Date              ");
        tagNames.put(new Integer(0x9101), "Components Configuration    ");
        tagNames.put(new Integer(0x9102), "Compressed Bits Per Pixel   ");
        tagNames.put(new Integer(0x9201), "Shutter Speed Value         ");
        tagNames.put(new Integer(0x9202), "Aperture Value              ");
        tagNames.put(new Integer(0x9203), "Brightness Value            ");
        tagNames.put(new Integer(0x9204), "Exposure Bias Value         ");
        tagNames.put(new Integer(0x9205), "Max Aperture Value          ");
        tagNames.put(new Integer(0x9206), "Subject Distance            ");
        tagNames.put(new Integer(0x9207), "Metering Mode               ");
        tagNames.put(new Integer(0x9208), "Light Source                ");
        tagNames.put(new Integer(0x9209), "Flash                       ");
        tagNames.put(new Integer(0x920a), "Focal Length                ");
        tagNames.put(new Integer(0x927c), "Maker Note                  ");
        tagNames.put(new Integer(0x9286), "User Comments               ");
        tagNames.put(new Integer(0x9290), "Sub Second Time             ");
        tagNames.put(new Integer(0x9291), "Sub Second Creation Date    ");
        tagNames.put(new Integer(0x9292), "Sub Second Digitized Date   ");
        tagNames.put(new Integer(0xa000), "FlashPix Version            ");
        tagNames.put(new Integer(0xa001), "Color Space                 ");
        tagNames.put(new Integer(0xa002), "Image Width                 ");
        tagNames.put(new Integer(0xa003), "Image Height                ");
        tagNames.put(new Integer(0xa004), "Related Sound File          ");
        tagNames.put(new Integer(0xa005), "EXIF Interoperability Offset");
        tagNames.put(new Integer(0xa20e), "Focal Plane X Resolution    ");
        tagNames.put(new Integer(0xa20f), "Focal Plane Y Resolution    ");
        tagNames.put(new Integer(0xa210), "Focal Plane Resolution Unit ");
        tagNames.put(new Integer(0xa215), "Exposure Index              ");
        tagNames.put(new Integer(0xa217), "Sensing Method              ");
        tagNames.put(new Integer(0xa300), "File Source                 ");
        tagNames.put(new Integer(0xa301), "Scene Type                  ");
        tagNames.put(new Integer(0xa302), "CFA Pattern                 ");

        tagNames.put(new Integer(0x0001), "Interoperability Index      ");
        tagNames.put(new Integer(0x0002), "Interoperability Version    ");
        tagNames.put(new Integer(0x1000), "Related Image File Format   ");
        tagNames.put(new Integer(0x1001), "Related Image Width         ");
        tagNames.put(new Integer(0x1002), "Related Image Length        ");

        tagNames.put(new Integer(0x0100), "Thumbnail Width             ");
        tagNames.put(new Integer(0x0101), "Thumbnail Length            ");
        tagNames.put(new Integer(0x0102), "Thumbnail Bits Per Sample   ");
        tagNames.put(new Integer(0x0103), "Thumbnail Compression       ");
        tagNames.put(new Integer(0x0106),
                "Thumbnail Photometric Interpretation");
        tagNames.put(new Integer(0x0111), "Thumbnail Strip Offsets     ");
        tagNames.put(new Integer(0x0115), "Thumbnail Samples Per Pixel ");
        tagNames.put(new Integer(0x0116), "Thumbnail Rows Per Strip    ");
        tagNames.put(new Integer(0x0117), "Thumbnail Strip Byte Count  ");
        tagNames.put(new Integer(0x0201), "Thumbnail JPEG Offset       ");
        tagNames.put(new Integer(0x0202), "Thumbnail JPEG Length       ");
    }

    public static String getName(int i) {
        return (String) tagNames.get(new Integer(i));
    }

    public static String[] dataFormatNames = { "Unsigned Byte    ",
            "ASCII String     ", "Unsigned Short   ", "Unsigned Long    ",
            "Unsigned Rational", "Signed Byte      ", "Undefined        ",
            "Signed Short     ", "Signed Long      ", "Signed Rational  ",
            "Single Float     ", "Double Float     " };

    public static int[] dataBytesPerComponent = { 1, 1, 2, 4, 8, 1, 1, 2, 4, 8,
            4, 8 };

    private static HashMap tagNames;

    // Tags used by IFD0 (main image)
    public static int IMAGE_DESCRIPTION = 0x010e;

    public static int CAMERA_TYPE = 0x010e;

    public static int CAMERA_MAKE = 0x010f;

    public static int CAMERA_MODEL = 0x0110;

    public static int IMAGE_ORIENTATION = 0x0112;

    public static int X_RESOLUTION = 0x011a;

    public static int Y_RESOLUTION = 0x011b;

    public static int RESOLUTION_UNIT = 0x0128;

    public static int FIRMWARE_VERSION = 0x131;

    public static int MODIFIED_DATE = 0x0132;

    public static int WHITE_POINT = 0x013e;

    public static int PRIMARY_CHROMATICITIES = 0x013f;

    public static int YCBCR_COEFFICIENTS = 0x0211;

    public static int YCBCR_POSITIONING = 0x0213;

    public static int REFERENCE_BLACK_WHITE = 0x0214;

    public static int COPYRIGHT = 0x8298;

    // Tags used by EXIF SubIFD
    public static int SHUTTER_SPEED = 0x829a;

    public static int F_NUMBER = 0x829d;

    public static int EXPOSURE_PROGRAM = 0x8822;

    public static int ISO_RATING = 0x8827;

    public static int EXIF_VERSION = 0x9000;

    public static int CREATION_DATE = 0x9003;

    public static int DIGITIZED_DATE = 0x9004;

    public static int COMPONENT_CONFIG = 0x9101;

    public static int COMPRESSED_BITS_PER_PIXEL = 0x9102;

    public static int LOG_SHUTTER_SPEED = 0x9201;

    public static int LOG_APERTURE_VALUE = 0x9202;

    public static int BRIGHTNESS_VALUE = 0x9203;

    public static int EXPOSURE_BIAS = 0x9204;

    public static int LOG_MAX_APERTURE = 0x9205;

    public static int SUBJECT_DISTANCE = 0x9206;

    public static int METERING_MODE = 0x9207;

    public static int LIGHT_SOURCE = 0x9208;

    public static int FLASH = 0x9209;

    public static int FOCAL_LENGTH = 0x920a;

    public static int MAKER_NOTE = 0x927c;

    public static int USER_COMMENT = 0x9286;

    public static int SUBSEC_MODIFIED_TIME = 0x9290;

    public static int SUBSEC_CREATION_TIME = 0x9291;

    public static int SUBSEC_DIGITIZED_TIME = 0x9292;

    public static int FLASH_PIX_VERSION = 0xa000;

    public static int COLOR_SPACE = 0xa001;

    public static int IMAGE_WIDTH = 0xa002;

    public static int IMAGE_HEIGHT = 0xa003;

    public static int ASSOCIATED_SOUND_NAME = 0xa004;

    public static int FOCAL_PLANE_X_RESOLUTION = 0xa20e;

    public static int FOCAL_PLANE_Y_RESOLUTION = 0xa20f;

    public static int FOCAL_PLANE_RESOLUTION_UNIT = 0xa210;

    public static int EXPOSURE_INDEX = 0xa215;

    public static int SENSING_METHOD = 0xa217;

    public static int FILE_SOURCE = 0xa300;

    public static int SCENE_TYPE = 0xa301;

    public static int CFA_PATTER = 0xa302;

    // Tags used by Interoperability IFD
    public static int INTEROPERABILITY_INDEX = 0x0001;

    public static int INTEROPERABILITY_VERSION = 0x0002;

    public static int RELATED_IMAGE_FILE_FORMAT = 0x1000;

    public static int RELATED_IMAGE_WIDTH = 0x1001;

    public static int RELATED_IMAGE_HEIGHT = 0x1002;

    // Tags used by IFD1 (thumbnail Image)
    public static int THUMBNAIL_WIDTH = 0x0100;

    public static int THUMBNAIL_HEIGHT = 0x0101;

    public static int BITS_PER_SAMPLE = 0x0102;

    public static int COMPRESSION = 0x0103;

    public static int PHOTOMETRIC_INTERPRETATION = 0x0106;

    public static int STRIP_OFFSETS = 0x0111;

    public static int SAMPLES_PER_PIXEL = 0x0115;

    public static int ROWS_PER_STRIP = 0x0116;

    public static int STRIP_BYTE_COUNTS = 0x0117;

    public static int THUMBNAIL_X_RESOLUTION = 0x011a;

    public static int THUMBNAIL_Y_RESOLUTION = 0x011b;

    public static int PLANAR_CONFIGURATION = 0x011c;

    public static int THUMBNAIL_RESOLUTION_UNIT = 0x0128;

    public static int THUMBNAIL_YCBCR_COEFFICIENTS = 0x0211;

    public static int THUMBNAIL_YCBCR_SUBSAMPLING = 0x0212;

    public static int THUMBNAIL_YCBCR_POSITIONING = 0x0213;

    public static int THUMBNAIL_REFERENCE_BLACK_WHITE = 0x0214;

    public static int THUMBNAIL_IMAGE = 0x010001;
}
