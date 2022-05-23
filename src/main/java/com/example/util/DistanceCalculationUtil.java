package com.example.util;

import org.apache.lucene.util.SloppyMath;

public class DistanceCalculationUtil {

    public static double calculateDistanceBetweenTwoPoints(double lat1, double long1, double lat2, double long2) {
        return SloppyMath.haversinMeters(lat1, long1, lat2, long2);
    }
}
