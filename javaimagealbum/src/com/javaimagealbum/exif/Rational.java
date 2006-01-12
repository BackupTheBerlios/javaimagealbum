/*
 Copyright (C) 2001, Brent Bryan
 
 Authors:
 Brent Bryan	(brent@whitties.org)
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

public class Rational {
    public Rational(int n, int d) {
        this.numerator = n;
        this.denominator = d;
    }

    public double getDoubleValue() {
        return (1.0 * numerator) / denominator;
    }

    public String getFraction() {
        return getFraction(numerator, denominator);
    }

    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public void getNumerator(int n) {
        numerator = n;
    }

    public void getDenominator(int d) {
        denominator = d;
    }

    // ------------------------------ Static Methods
    // ------------------------------ //
    public static double getDoubleValue(int n, int d) {
        return (1.0 * n) / d;
    }

    public static String getFraction(int n, int d) {
        int[] numDem = reduceFraction(n, d);
        if (numDem[0] == 0) {
            return "0";
        }
        if (numDem[1] == 1) {
            return "" + numDem[0];
        }
        return "" + numDem[0] + "/" + numDem[1];
    }

    private static int[] reduceFraction(int n, int d) {
        int maxValue = abs((abs(n) > abs(d)) ? n : d);
        for (int i = maxValue; i > 1; i--) {
            if ((n % i == 0) && (d % i == 0)) {
                int[] intArray = { (int) n / i, (int) d / i };
                return intArray;
            }
        }
        int[] intArray = { n, d };
        return intArray;
    }

    private static int abs(int i) {
        return (i < 0) ? -i : i;
    }

    public String toString() {
        return getFraction(numerator, denominator);
    }

    private int numerator;

    private int denominator;
}
