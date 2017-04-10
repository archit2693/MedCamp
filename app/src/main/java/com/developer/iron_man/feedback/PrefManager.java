package com.developer.iron_man.feedback;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * Created by Ravi on 01/06/15.
 */
@SuppressLint("CommitPrefEdits")
public class PrefManager {
     // Shared Preferences
    SharedPreferences pref;
    SharedPreferences pref1;

    // Editor for Shared preferences
    Editor editor;
    Editor editor1;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

	

    // Shared pref file name
    private static final String PREF_NAME = "AndroidHive";

    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";

    // Email address (make variable public to access from outside)
    public static final String KEY_EMAIL = "email";
    
    public static final String Event = "event";

    public static final String LOGIN = "login";


    // Constructor
    public PrefManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        pref1 = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        
        editor = pref.edit();
        editor1 = pref1.edit();
   
    }


 public void setLang(String product) {


        editor1.putString("Language", product);

        // Storing email in pref


        // commit changes
        editor1.commit();
    }
    public void setVol(String product) {


        editor1.putString("Vol", product);

        // Storing email in pref


        // commit changes
        editor1.commit();
    }






    public String getSerialNo() {
        return pref.getString("serial", null);
    }

    public String getIPSize() {
        return pref.getString("ipSize", null);
    }
    public String getDate() {
        return pref.getString("date", null);
    }

    
    public String getEvent() {
        return pref.getString(Event, null);
    }

    public String getStudentClass() {
        return pref.getString("stud_class", null);
    }
    
    public String getLogin() {
        return pref.getString(LOGIN, null);
    }
    
    public String getName() {
        return pref.getString("name", null);
    }
    
    public String getRoll() {
        return pref.getString("roll", null);
    }
    
    public String getTeacher() {
        return pref.getString("teacher", null);
    }
    public String getLang() {
        return pref.getString("Language", null);
    }
    public String getVol() {
        return pref.getString("Vol", null);
    }

    public String getIP() {
        return pref.getString("IP", null);
    }
    public String getROLL() {
        return pref.getString("ROLL", null);
    }
    public String getNumber() {
        return pref.getString("Number", null);
    }

    public String getFlag() {
        return pref.getString("FLAG", null);
    }



    public String getEmail() {
        return pref.getString(KEY_EMAIL, null);
    }
    public String getPort() {
        return pref.getString("port", null);
    }


    public boolean isLoggedIn() {
        return pref.getBoolean(IS_LOGIN, false);
    }
    public String getBatch() {
        return pref.getString("batch", null);
    }  public String getCenter() {
        return pref.getString("center", null);
    }

    public String getclass() {
        return pref.getString("class", null);
    }

      public String getHosts() {
        return pref.getString("hosts", null);
    }


    public void logout() {
        editor1.clear();
        editor1.commit();
    }

	
}