package com.thewiseguys.app.settings;

/**
 * Version 0.0.1 - Last Modified 6/09/13.
 * Created by Chris Krause on 6/09/13.
 * TheWiseguys - com.thewiseguys.app.settings
 */
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;

import com.thewiseguys.app.R;

public class MyPreferences extends Activity {

    public static int PREFERENCES_RESULTCODE = 100 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= 11) {
            addL11PreferencesFromResources();
        } else {
            addLegacyPreferencesFromResource();
        }

    }

    //@SuppressLint("NewApi")
    public void addLegacyPreferencesFromResource() {
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new PrefsFragment()).commit();
    }

    public void addL11PreferencesFromResources() {
        getFragmentManager().beginTransaction()
        		.replace(android.R.id.content, new PrefsFragment()).commit();
    }
}

