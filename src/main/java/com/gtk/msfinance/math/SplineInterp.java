package com.gtk.msfinance.math;

import org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator;
import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;

public class SplineInterp {
    static public void test() {

        try {

            double [] yRaw = new double [] {
                    43124470448.0,
                    47209839358.0,
                    34161140501.0,
                    21229846866.0,
                    20334485855.0,
                    14675472924.0,
                    14673334280.0,
                    11630051224.0,
                    10049.0,
                    8004.0,
                    6391.0,
                    5389.0,
                    4322.0,
                    2303.0,
                    2618.0,
                    3026.0,
                    950.0,
                    86.0
            };

            double [] yDouble = new double [yRaw.length];
            for(int i = 0; i < yRaw.length; i++)
                yDouble[i] = yRaw[yRaw.length - 1 - i];
            double [] xDouble = new double [yRaw.length];
            for(int i = 0; i < yRaw.length; i++)
                xDouble[i] = i;

            AkimaSplineInterpolator asi = new AkimaSplineInterpolator();
            PolynomialSplineFunction psf = asi.interpolate(xDouble, yDouble);

            for (PolynomialFunction pf : psf.getPolynomials()) {
                System.out.println(pf.polynomialDerivative());
            }
            return;
        } catch(Exception e) {

        }
    }
}
