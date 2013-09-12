package com.thewiseguys.android;

/**
 * Version 0.0.1 - Last Modified 7/09/13.
 * Created by Chris Krause on 7/09/13.
 * TheWiseguys - com.thewiseguys.app
 */
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.thewiseguys.android.db.sqlite.DBHandler;
import com.thewiseguys.android.json.JSONParser;
import com.thewiseguys.app.R;

public class UserFunctions {
    private static String serverUrl = SystemFunctions.getServerURL();

    private JSONParser jsonParser;
    // http://192.168.56.101
    // Testing in localhost using wamp or xampp
    // use http://10.0.2.2/ to connect to your localhost ie http://localhost/
    private static String loginURL = serverUrl+"login.php";
    private static String userURL = serverUrl+"user_functions.php";
    private static String registerURL = serverUrl+"register.php";

    private static String login_tag = "login";
    private static String register_tag = "register";

    private String userEmail;

    // constructor
    public UserFunctions(){
           jsonParser = new JSONParser();
    }

    /**
     * function make Login Request
     * @param email
     * @param password
     * */
    public JSONObject loginUser(String email, String password){
        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag", login_tag));
        params.add(new BasicNameValuePair("email", email));
        params.add(new BasicNameValuePair("password", password));
        JSONObject json = jsonParser.getJSONFromUrl(loginURL, params);
        // return json
        // Log.e("JSON", json.toString());
        return json;
    }

    /**
     * function make Login Request
     * @param name
     * @param email
     * @param password
     * */
    public JSONObject registerUser(String name, String email, String password){
        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag", register_tag));
        params.add(new BasicNameValuePair("name", name));
        params.add(new BasicNameValuePair("email", email));
        params.add(new BasicNameValuePair("password", password));

        // getting JSON Object
        JSONObject json = jsonParser.getJSONFromUrl(registerURL, params);
        // return json
        return json;
    }

    public JSONObject updateOnline(Context context){
        // Building Parameters
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);

        String userEmail = prefs.getString("user_email","");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag", "updateonline"));
        params.add(new BasicNameValuePair("email", userEmail));

        // getting JSON Object
        String updateURL = serverUrl+Integer.toString(R.string.url_update_user);
        Log.d("User-Functions", updateURL);
        JSONObject json = jsonParser.getJSONFromUrl(userURL, params);
        // return json
        return json;
    }

    /**
     * Function get Login status
     * */
    public boolean isUserLoggedIn(Context context){
        DBHandler db = new DBHandler(context);
        int count = db.getRowCount();
        if(count > 0){
            // user logged in
            return true;
        }
        return false;
    }

    /**
     * Function to logout user
     * Reset Database
     * */
    public boolean logoutUser(Context context){
        DBHandler db = new DBHandler(context);
        db.resetTables();
        return true;
    }

}
