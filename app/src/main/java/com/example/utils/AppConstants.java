package com.example.utils;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;


import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AppConstants {



    public static final String KEY_USERNAME= "username";
    public static final String KEY_NOM= "nom";
    public static  final String KEY_PRENOM="prenom" ;
    public static final String KEY_VILLE= "ville";
    public static final String KEY_ADRESSE= "adresse";
    public static final String KEY_EMAIL= "email";
    public static final String KEY_TEL = "tel";
    public static final String KEY_CODE_POSTAL= "codePostal";
    public static final String KEY_ID = "id";
    public static final String KEY_API_KEY = "api_key";
    public  static  final  SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
    public static final String BASE_URL = "http://193.70.3.61/tikiti/" ;





    public static double distance(double lat1, double lon1, double lat2, double lon2, String unit) {
        if ((lat1 == lat2) && (lon1 == lon2)) {
            return 0;
        }
        else {
            double theta = lon1 - lon2;
            double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
            dist = Math.acos(dist);
            dist = Math.toDegrees(dist);
            dist = dist * 60 * 1.1515;
            if (unit.equals("K")) {
                dist = dist * 1.609344;
            } else if (unit.equals( "N")) {
                dist = dist * 0.8684;
            }
            return (dist);
        }
    }

}
