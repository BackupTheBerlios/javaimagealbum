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
        tagNames.put(new Integer(IMAGE_DESCRIPTION), "Image Description           ");
        tagNames.put(new Integer(CAMERA_MAKE), "Manufacture Make            ");
        tagNames.put(new Integer(CAMERA_MODEL), "Manufacture Model           ");
        tagNames.put(new Integer(IMAGE_ORIENTATION), "Camera Orientation          ");
        tagNames.put(new Integer(X_RESOLUTION), "X Resolution                ");
        tagNames.put(new Integer(Y_RESOLUTION), "Y Resolution                ");
        tagNames.put(new Integer(PLANAR_CONFIGURATION), "Planar Configuration        ");
        tagNames.put(new Integer(RESOLUTION_UNIT), "Resolution Unit             ");
        tagNames.put(new Integer(FIRMWARE_VERSION), "Firmware Version            ");
        tagNames.put(new Integer(MODIFIED_DATE), "Last Modified               ");
        tagNames.put(new Integer(WHITE_POINT), "White Point                 ");
        tagNames.put(new Integer(PRIMARY_CHROMATICITIES), "Primary Chromaticities      ");
        tagNames.put(new Integer(YCBCR_COEFFICIENTS), "YCbCr Coefficients          ");
        tagNames.put(new Integer(THUMBNAIL_YCBCR_SUBSAMPLING), "YCbCr SubSampling           ");
        tagNames.put(new Integer(YCBCR_POSITIONING), "YCbCr Positioning           ");
        tagNames.put(new Integer(REFERENCE_BLACK_WHITE), "Black/White Reference       ");
        tagNames.put(new Integer(COPYRIGHT), "Copyright                   ");
        tagNames.put(new Integer(0x8769), "Exif Offset                 ");

        tagNames.put(new Integer(SHUTTER_SPEED), "Exposure Time               ");
        tagNames.put(new Integer(F_NUMBER), "F Number                    ");
        tagNames.put(new Integer(EXPOSURE_PROGRAM), "Exposure Program            ");
        tagNames.put(new Integer(ISO_RATING), "ISO Rating                  ");
        tagNames.put(new Integer(EXIF_VERSION), "EXIF Version                ");
        tagNames.put(new Integer(CREATION_DATE), "Creation Date               ");
        tagNames.put(new Integer(DIGITIZED_DATE), "Digitized Date              ");
        tagNames.put(new Integer(COMPONENT_CONFIG), "Components Configuration    ");
        tagNames.put(new Integer(COMPRESSED_BITS_PER_PIXEL), "Compressed Bits Per Pixel   ");
        tagNames.put(new Integer(LOG_SHUTTER_SPEED), "Shutter Speed Value         ");
        tagNames.put(new Integer(LOG_APERTURE_VALUE), "Aperture Value              ");
        tagNames.put(new Integer(BRIGHTNESS_VALUE), "Brightness Value            ");
        tagNames.put(new Integer(EXPOSURE_BIAS), "Exposure Bias Value         ");
        tagNames.put(new Integer(LOG_MAX_APERTURE), "Max Aperture Value          ");
        tagNames.put(new Integer(SUBJECT_DISTANCE), "Subject Distance            ");
        tagNames.put(new Integer(METERING_MODE), "Metering Mode               ");
        tagNames.put(new Integer(LIGHT_SOURCE), "Light Source                ");
        tagNames.put(new Integer(FLASH), "Flash                       ");
        tagNames.put(new Integer(FOCAL_LENGTH), "Focal Length                ");
        tagNames.put(new Integer(MAKER_NOTE), "Maker Note                  ");
        tagNames.put(new Integer(USER_COMMENT), "User Comments               ");
        tagNames.put(new Integer(SUBSEC_MODIFIED_TIME), "Sub Second Time             ");
        tagNames.put(new Integer(SUBSEC_CREATION_TIME), "Sub Second Creation Date    ");
        tagNames.put(new Integer(SUBSEC_DIGITIZED_TIME), "Sub Second Digitized Date   ");
        tagNames.put(new Integer(FLASH_PIX_VERSION), "FlashPix Version            ");
        tagNames.put(new Integer(COLOR_SPACE), "Color Space                 ");
        tagNames.put(new Integer(IMAGE_WIDTH), "Image Width                 ");
        tagNames.put(new Integer(IMAGE_HEIGHT), "Image Height                ");
        tagNames.put(new Integer(ASSOCIATED_SOUND_NAME), "Related Sound File          ");
        tagNames.put(new Integer(0xa005), "EXIF Interoperability Offset");
        tagNames.put(new Integer(FOCAL_PLANE_X_RESOLUTION), "Focal Plane X Resolution    ");
        tagNames.put(new Integer(FOCAL_PLANE_Y_RESOLUTION), "Focal Plane Y Resolution    ");
        tagNames.put(new Integer(FOCAL_PLANE_RESOLUTION_UNIT), "Focal Plane Resolution Unit ");
        tagNames.put(new Integer(EXPOSURE_INDEX), "Exposure Index              ");
        tagNames.put(new Integer(SENSING_METHOD), "Sensing Method              ");
        tagNames.put(new Integer(FILE_SOURCE), "File Source                 ");
        tagNames.put(new Integer(SCENE_TYPE), "Scene Type                  ");
        tagNames.put(new Integer(CFA_PATTER), "CFA Pattern                 ");

        tagNames.put(new Integer(INTEROPERABILITY_INDEX), "Interoperability Index      ");
        tagNames.put(new Integer(INTEROPERABILITY_VERSION), "Interoperability Version    ");
        tagNames.put(new Integer(RELATED_IMAGE_FILE_FORMAT), "Related Image File Format   ");
        tagNames.put(new Integer(RELATED_IMAGE_WIDTH), "Related Image Width         ");
        tagNames.put(new Integer(RELATED_IMAGE_HEIGHT), "Related Image Length        ");

        tagNames.put(new Integer(THUMBNAIL_WIDTH), "Thumbnail Width             ");
        tagNames.put(new Integer(THUMBNAIL_HEIGHT), "Thumbnail Length            ");
        tagNames.put(new Integer(BITS_PER_SAMPLE), "Thumbnail Bits Per Sample   ");
        tagNames.put(new Integer(COMPRESSION), "Thumbnail Compression       ");
        tagNames.put(new Integer(PHOTOMETRIC_INTERPRETATION),
                "Thumbnail Photometric Interpretation");
        tagNames.put(new Integer(STRIP_OFFSETS), "Thumbnail Strip Offsets     ");
        tagNames.put(new Integer(SAMPLES_PER_PIXEL), "Thumbnail Samples Per Pixel ");
        tagNames.put(new Integer(ROWS_PER_STRIP), "Thumbnail Rows Per Strip    ");
        tagNames.put(new Integer(STRIP_BYTE_COUNTS), "Thumbnail Strip Byte Count  ");
        tagNames.put(new Integer(0x0201), "Thumbnail JPEG Offset       ");
        tagNames.put(new Integer(0x0202), "Thumbnail JPEG Length       ");
    }

    public static String getName( final int i ) {
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
    public static final int IMAGE_DESCRIPTION = 0x010e;

    public static final int CAMERA_TYPE = 0x010e;

    public static final int CAMERA_MAKE = 0x010f;

    public static final int CAMERA_MODEL = 0x0110;

    public static final int IMAGE_ORIENTATION = 0x0112;

    public static final int X_RESOLUTION = 0x011a;

    public static final int Y_RESOLUTION = 0x011b;

    public static final int RESOLUTION_UNIT = 0x0128;

    public static final int FIRMWARE_VERSION = 0x131;

    public static final int MODIFIED_DATE = 0x0132;

    public static final int WHITE_POINT = 0x013e;

    public static final int PRIMARY_CHROMATICITIES = 0x013f;

    public static final int YCBCR_COEFFICIENTS = 0x0211;

    public static final int YCBCR_POSITIONING = 0x0213;

    public static final int REFERENCE_BLACK_WHITE = 0x0214;

    public static final int COPYRIGHT = 0x8298;

    // Tags used by EXIF SubIFD
    public static final int SHUTTER_SPEED = 0x829a;

    public static final int F_NUMBER = 0x829d;

    public static final int EXPOSURE_PROGRAM = 0x8822;

    public static final int ISO_RATING = 0x8827;

    public static final int EXIF_VERSION = 0x9000;

    public static final int CREATION_DATE = 0x9003;

    public static final int DIGITIZED_DATE = 0x9004;

    public static final int COMPONENT_CONFIG = 0x9101;

    public static final int COMPRESSED_BITS_PER_PIXEL = 0x9102;

    public static final int LOG_SHUTTER_SPEED = 0x9201;

    public static final int LOG_APERTURE_VALUE = 0x9202;

    public static final int BRIGHTNESS_VALUE = 0x9203;

    public static final int EXPOSURE_BIAS = 0x9204;

    public static final int LOG_MAX_APERTURE = 0x9205;

    public static final int SUBJECT_DISTANCE = 0x9206;

    public static final int METERING_MODE = 0x9207;

    public static final int LIGHT_SOURCE = 0x9208;

    public static final int FLASH = 0x9209;

    public static final int FOCAL_LENGTH = 0x920a;

    public static final int MAKER_NOTE = 0x927c;

    public static final int USER_COMMENT = 0x9286;

    public static final int SUBSEC_MODIFIED_TIME = 0x9290;

    public static final int SUBSEC_CREATION_TIME = 0x9291;

    public static final int SUBSEC_DIGITIZED_TIME = 0x9292;

    public static final int FLASH_PIX_VERSION = 0xa000;

    public static final int COLOR_SPACE = 0xa001;

    public static final int IMAGE_WIDTH = 0xa002;

    public static final int IMAGE_HEIGHT = 0xa003;

    public static final int ASSOCIATED_SOUND_NAME = 0xa004;

    public static final int FOCAL_PLANE_X_RESOLUTION = 0xa20e;

    public static final int FOCAL_PLANE_Y_RESOLUTION = 0xa20f;

    public static final int FOCAL_PLANE_RESOLUTION_UNIT = 0xa210;

    public static final int EXPOSURE_INDEX = 0xa215;

    public static final int SENSING_METHOD = 0xa217;

    public static final int FILE_SOURCE = 0xa300;

    public static final int SCENE_TYPE = 0xa301;

    public static final int CFA_PATTER = 0xa302;

    // Tags used by Interoperability IFD
    public static final int INTEROPERABILITY_INDEX = 0x0001;

    public static final int INTEROPERABILITY_VERSION = 0x0002;

    public static final int RELATED_IMAGE_FILE_FORMAT = 0x1000;

    public static final int RELATED_IMAGE_WIDTH = 0x1001;

    public static final int RELATED_IMAGE_HEIGHT = 0x1002;

    // Tags used by IFD1 (thumbnail Image)
    public static final int THUMBNAIL_WIDTH = 0x0100;

    public static final int THUMBNAIL_HEIGHT = 0x0101;

    public static final int BITS_PER_SAMPLE = 0x0102;

    public static final int COMPRESSION = 0x0103;

    public static final int PHOTOMETRIC_INTERPRETATION = 0x0106;

    public static final int STRIP_OFFSETS = 0x0111;

    public static final int SAMPLES_PER_PIXEL = 0x0115;

    public static final int ROWS_PER_STRIP = 0x0116;

    public static final int STRIP_BYTE_COUNTS = 0x0117;

    public static final int THUMBNAIL_X_RESOLUTION = 0x011a;

    public static final int THUMBNAIL_Y_RESOLUTION = 0x011b;

    public static final int PLANAR_CONFIGURATION = 0x011c;

    public static final int THUMBNAIL_RESOLUTION_UNIT = 0x0128;

    public static final int THUMBNAIL_YCBCR_COEFFICIENTS = 0x0211;

    public static final int THUMBNAIL_YCBCR_SUBSAMPLING = 0x0212;

    public static final int THUMBNAIL_YCBCR_POSITIONING = 0x0213;

    public static final int THUMBNAIL_REFERENCE_BLACK_WHITE = 0x0214;

    public static final int THUMBNAIL_IMAGE = 0x010001;
}
