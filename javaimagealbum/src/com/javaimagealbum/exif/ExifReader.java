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

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import javax.imageio.*;

public class ExifReader {
    // FIX ME -- Type declarations are not complete....
    // Need to finish off all data types

    public static boolean isExif(File f) {
        if (!f.exists())
            return false;
        try {
            BufferedInputStream in = new BufferedInputStream(
                    new FileInputStream(f));
            return isExif(in);
        } catch (FileNotFoundException e) {
        }
        return false;
    }

    private static boolean isExif(InputStream in) {
        if (in == null)
            return false;
        int firstByte = readByte(in);
        int secondByte = readByte(in);
        int marker = 0;

        int bytesRead = 2;
        // Search out the start of the image -- 0xFFD8
        while (((firstByte << 8) | secondByte) != 0xFFD8) {
            firstByte = secondByte;
            secondByte = readByte(in);
            bytesRead++;

            if (bytesRead > 10)
                return false;
        }

        do {
            // read marker; if marker is not EXIF (0xFFE1) skip to next marker.
            marker = readBytes(in, 2);
            if (marker == 0xFFE1)
                return true;

            tossBytes(in, readBytes(in, 2) - 2);
        } while ((marker != 0xFFDA) && (marker != 0x0000));

        return false;
    }

    public static HashMap decode(File f) {
        thumbnailOffset = -1;
        thumbnailLength = -1;
        HashMap hashMap = new HashMap();
        ExifConstants exifConstants = new ExifConstants();

        // Error catching for Bad Exif Files Here.
        if (!f.exists())
            return null;

        try {
            BufferedInputStream in = new BufferedInputStream(
                    new FileInputStream(f));
            boolean exif = isExif(in);

            if (!exif)
                return hashMap;

            // Length of EXIF Header
            int dataLength = readBytes(in, 2);

            // Skip Exif followed by 0x0000 -- 6 bytes
            int leftOff = readBytes(in, 6);

            // Read EXIF header to array
            byte[] exifBuffer = new byte[dataLength];
            in.read(exifBuffer, 0, dataLength);
            in.close();

            // Determine byte order of data: 4949 = intel = Right to Left 4d4d =
            // motorola = L to R
            int byteOrder = readBytes(exifBuffer, 0, 2, false);
            boolean intelByteOrder = false;

            if (byteOrder == 0x4949) {
                intelByteOrder = true;
            } else if (byteOrder == 0x4d4d) {
                intelByteOrder = false;
            } else {
                System.out.println("Byte Order Unknown.");
                return hashMap;
            }

            // Toss the 0x002a and 0x00000008 bytes;
            // Start reading data after header 8 bytes
            int nextDirectory = 8;
            do {
                int offset = readImageFileDirectory(exifBuffer, nextDirectory,
                        intelByteOrder, hashMap);
                nextDirectory = readBytes(exifBuffer, offset, 2, intelByteOrder);
            } while (nextDirectory != 0x00000000);

            // try and extract a thumbnail
            if ((thumbnailOffset > 0) && (thumbnailLength > 0)) {
                ByteArrayInputStream bais = new ByteArrayInputStream(
                        exifBuffer, thumbnailOffset, thumbnailLength);

                hashMap.put(new Integer(ExifConstants.THUMBNAIL_IMAGE), ImageIO
                        .read(bais));
            }
        } catch (IOException e) {
            System.out.println(e);
            return hashMap;
        }
        return hashMap;
    }

    public static BufferedImage getThumbnail(File f) {
        thumbnailOffset = -1;
        thumbnailLength = -1;
        ExifConstants exifConstants = new ExifConstants();

        // Error catching for Bad Exif Files Here.
        if (!f.exists())
            return null;

        try {
            BufferedInputStream in = new BufferedInputStream(
                    new FileInputStream(f));
            boolean exif = isExif(in);

            if (!exif)
                return null;

            // Length of EXIF Header
            int dataLength = readBytes(in, 2);

            // Skip Exif followed by 0x0000 -- 6 bytes
            int leftOff = readBytes(in, 6);

            // Read EXIF header to array
            byte[] exifBuffer = new byte[dataLength];
            in.read(exifBuffer, 0, dataLength);
            in.close();

            // Determine byte order of data: 4949 = intel = Right to Left 4d4d =
            // motorola = L to R
            int byteOrder = readBytes(exifBuffer, 0, 2, false);
            boolean intelByteOrder = false;

            if (byteOrder == 0x4949) {
                intelByteOrder = true;
            } else if (byteOrder == 0x4d4d) {
                intelByteOrder = false;
            } else {
                System.out.println("Byte Order Unknown.");
                return null;
            }

            // Toss the 0x002a and 0x00000008 bytes;
            // Start reading data after header 8 bytes
            int nextDirectory = 8;
            do {
                int offset = readDirectoryForThumb(exifBuffer, nextDirectory,
                        intelByteOrder);
                nextDirectory = readBytes(exifBuffer, offset, 2, intelByteOrder);
            } while (nextDirectory != 0x00000000);

            // try and extract a thumbnail
            if ((thumbnailOffset > 0) && (thumbnailLength > 0)) {

                ByteArrayInputStream bais = new ByteArrayInputStream(
                        exifBuffer, thumbnailOffset, thumbnailLength);

                return ImageIO.read(bais);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return null;
    }

    public static boolean hasThumbnail(File f) {
        thumbnailOffset = -1;
        thumbnailLength = -1;
        ExifConstants exifConstants = new ExifConstants();

        // Error catching for Bad Exif Files Here.
        if (!f.exists())
            return false;

        try {
            BufferedInputStream in = new BufferedInputStream(
                    new FileInputStream(f));
            boolean exif = isExif(in);

            if (!exif)
                return false;

            // Length of EXIF Header
            int dataLength = readBytes(in, 2);

            // Skip Exif followed by 0x0000 -- 6 bytes
            int leftOff = readBytes(in, 6);

            // Read EXIF header to array
            byte[] exifBuffer = new byte[dataLength];
            in.read(exifBuffer, 0, dataLength);
            in.close();

            // Determine byte order of data: 4949 = intel = Right to Left 4d4d =
            // motorola = L to R
            int byteOrder = readBytes(exifBuffer, 0, 2, false);
            boolean intelByteOrder = false;

            if (byteOrder == 0x4949) {
                intelByteOrder = true;
            } else if (byteOrder == 0x4d4d) {
                intelByteOrder = false;
            } else {
                System.out.println("Byte Order Unknown.");
                return false;
            }

            // Toss the 0x002a and 0x00000008 bytes;
            // Start reading data after header 8 bytes
            int nextDirectory = 8;
            do {
                int offset = readDirectoryForThumb(exifBuffer, nextDirectory,
                        intelByteOrder);
                nextDirectory = readBytes(exifBuffer, offset, 2, intelByteOrder);
            } while (nextDirectory != 0x00000000);

            // try and extract a thumbnail
            if ((thumbnailOffset > 0) && (thumbnailLength > 0)) {
                return true;
            }

        } catch (IOException e) {
            System.out.println(e);
        }
        return false;
    }

    // ------------------------------ Thumbnail Utilities
    // ------------------------------
    private static int readDirectoryForThumb(byte[] exifBuffer, int offset,
            boolean intelByteOrder) {
        int numEntries = readBytes(exifBuffer, offset, 2, intelByteOrder);
        offset += 2;

        // Read out Image Directory Data Entries
        for (int i = 0; i < numEntries; i++) {
            readImageForThumb(exifBuffer, offset, intelByteOrder);
            offset += 12;
        }
        return offset;
    }

    private static void readImageForThumb(byte[] exifBuffer, int offset,
            boolean intelByteOrder) {
        int tagNumber = readBytes(exifBuffer, offset, 2, intelByteOrder);
        String tagName = ExifConstants.getName(tagNumber);
        int dataType = readBytes(exifBuffer, offset + 2, 2, intelByteOrder);
        int numComponents = readBytes(exifBuffer, offset + 4, 2, intelByteOrder);
        int numBytes = numComponents
                * ExifConstants.dataBytesPerComponent[dataType - 1];

        if (tagNumber == 0x0201) {
            thumbnailOffset = readBytes(exifBuffer, offset + 8, numBytes,
                    intelByteOrder);
        } else if (tagNumber == 0x0202) {
            thumbnailLength = readBytes(exifBuffer, offset + 8, numBytes,
                    intelByteOrder);
        }
        return;
    }

    // ------------------------------ General Utilities
    // ------------------------------
    private static int readImageFileDirectory(byte[] exifBuffer, int offset,
            boolean intelByteOrder, HashMap hashMap) {
        int numEntries = readBytes(exifBuffer, offset, 2, intelByteOrder);
        offset += 2;

        // Read out Image Directory Data Entries
        for (int i = 0; i < numEntries; i++) {
            readImageEntry(exifBuffer, offset, intelByteOrder, hashMap);
            offset += 12;
        }
        return offset;
    }

    private static void readImageEntry(byte[] exifBuffer, int offset,
            boolean intelByteOrder, HashMap hashMap) {
        // FIX ME -- These declarations are not complete.... Need to finish off
        // all data types

        int tagNumber = readBytes(exifBuffer, offset, 2, intelByteOrder);
        String tagName = ExifConstants.getName(tagNumber);
        int dataType = readBytes(exifBuffer, offset + 2, 2, intelByteOrder);
        int numComponents = readBytes(exifBuffer, offset + 4, 2, intelByteOrder);
        int numBytes = numComponents
                * ExifConstants.dataBytesPerComponent[dataType - 1];

        if ((tagNumber == 0x8769) || (tagNumber == 0xa005)) {
            // expand SubIFDs 0x8769 or EXIF Interoperability format data 0xa005
            int subOffset = readBytes(exifBuffer, offset + 8, 4, intelByteOrder);
            readImageFileDirectory(exifBuffer, subOffset, intelByteOrder,
                    hashMap);

        } else {

            // Read Thumbnail info for later processing.
            if (tagNumber == 0x0201) {
                thumbnailOffset = readBytes(exifBuffer, offset + 8, numBytes,
                        intelByteOrder);
                return;
            } else if (tagNumber == 0x0202) {
                thumbnailLength = readBytes(exifBuffer, offset + 8, numBytes,
                        intelByteOrder);
                return;
            }

            if (numBytes > 4) {
                int offsetAddress = readBytes(exifBuffer, offset + 8, 4,
                        intelByteOrder);

                if ((dataType == 1) || (dataType == 3) || (dataType == 4)) {
                    // Unsigned Byte or unsigned long or unsigned short
                    hashMap.put(new Integer(tagNumber), new Integer(
                            readBytes(exifBuffer, offsetAddress, numBytes,
                                    intelByteOrder)));

                } else if (dataType == 2) {
                    // Ascii String
                    String s = readString(exifBuffer, offsetAddress, numBytes);
                    if (!s.trim().equals("")) {
                        hashMap.put(new Integer(tagNumber), s);
                    }
                } else if ((dataType == 5) || (dataType == 10)) {
                    // unsigned or signed rational
                    int ndLength = (int) (numBytes / 2.0);
                    int numerator = readBytes(exifBuffer, offsetAddress,
                            ndLength, intelByteOrder);
                    int denominator = readBytes(exifBuffer, offsetAddress
                            + ndLength, ndLength, intelByteOrder);
                    hashMap.put(new Integer(tagNumber), new Rational(numerator,
                            denominator));

                } else if (dataType == 7) {
                    // undefined

                }

            } else {
                // Read appropriate type out of next 4 bytes!
                if ((dataType == 1) || (dataType == 3) || (dataType == 4)) {
                    // Unsigned Byte or unsigned long or unsigned short
                    hashMap.put(new Integer(tagNumber), new Integer(readBytes(
                            exifBuffer, offset + 8, numBytes, intelByteOrder)));

                } else if (dataType == 2) {
                    // Ascii String
                    String s = readString(exifBuffer, offset + 8, numBytes);
                    if (!s.trim().equals("")) {
                        hashMap.put(new Integer(tagNumber), s);
                    }
                } else if (dataType == 7) {
                    // undefined

                }
            }
        }
        return;
    }

    // ------------------------------ Read Byte Methods
    // ------------------------------ //
    private static int readByte(InputStream in) {
        byte[] tempByte = new byte[1];
        try {
            in.read(tempByte, 0, 1);
            if (tempByte[0] < 0)
                return tempByte[0] + 256;
            else
                return tempByte[0];
        } catch (IOException e) {
            System.out.println(e);
        }
        return tempByte[0];
    }

    private static int readSignByte(InputStream in) {
        byte[] tempByte = new byte[1];
        try {
            in.read(tempByte, 0, 1);
        } catch (IOException e) {
            System.out.println(e);
        }
        return tempByte[0];
    }

    private static int readByte(byte[] data, int offset) {
        int tempByte = data[offset];
        if (tempByte < 0)
            return tempByte + 256;
        else
            return tempByte;
    }

    private static int readBytes(InputStream in, int numBytes) {
        int[] tempBytes = new int[numBytes];
        for (int i = 0; i < numBytes; i++) {
            tempBytes[i] = readByte(in);
        }

        int byteVal = 0;
        for (int i = 0; i < numBytes; i++) {
            byteVal = 256 * byteVal + tempBytes[i];
        }
        return byteVal;
    }

    private static int readBytes(byte[] data, int offset, int numBytes,
            boolean order) {
        int[] tempBytes = new int[numBytes];
        for (int i = 0; i < numBytes; i++) {
            tempBytes[i] = readByte(data, offset++);
        }

        int byteVal = 0;
        if (order) {
            for (int i = numBytes - 1; i >= 0; i--) {
                byteVal = 256 * byteVal + tempBytes[i];
            }
        } else {
            for (int i = 0; i < numBytes; i++) {
                byteVal = 256 * byteVal + tempBytes[i];
            }
        }
        return byteVal;
    }

    private static void tossBytes(InputStream in, int numBytes) {
        byte[] tempByte = new byte[1];

        for (int i = 0; i < numBytes; i++) {
            try {
                in.read(tempByte, 0, 1);
            } catch (IOException e) {
            }
        }
    }

    // ------------------------------ Read Data Types from Byte Array
    // ------------------------------ //
    private static String readString(byte[] exifBuffer, int offset, int length) {
        String result = new String(exifBuffer, offset, length);

        int nullIndex = result.indexOf('\0');
        if (nullIndex >= 0) {
            return result.substring(0, nullIndex);
        } else {
            return result;
        }
    }

    // ------------------------------ Byte Manipulation
    // ------------------------------ //
    private static String toHexString(int n, int length) {
        String s = "00";
        for (int i = 1; i < length; i++) {
            s += "00";
        }
        s += Integer.toHexString(n & 0xffff);
        return s.substring(s.length() - 2 * length, s.length());
    }

    private File imageFile;

    private static int thumbnailOffset;

    private static int thumbnailLength;
}
