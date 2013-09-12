package com.thewiseguys.app.home;

/**
 * Version 0.0.1 - Last Modified 6/09/13.
 * Created by Chris Krause on 6/09/13.
 * TheWiseguys - com.thewiseguys.app.home
 */
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.thewiseguys.android.SystemFunctions;
import com.thewiseguys.android.UserFunctions;
import com.thewiseguys.android.db.sqlite.DBHandler;
import com.thewiseguys.app.R;
import com.thewiseguys.android.ui.animation.LiveButton;
import com.thewiseguys.android.ui.navdrawer.AbstractNavDrawerActivity;
import com.thewiseguys.android.ui.navdrawer.NavDrawerSelectItemRunnable;
import com.thewiseguys.app.YourApplication;
import com.thewiseguys.app.login.Login;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

public class MainFragment extends Fragment {

    private UpdateUserTask mAuthTask = null;

    private static String KEY_SUCCESS = "success";
    private static String KEY_ONLINE = "online";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuthTask = new UpdateUserTask();
        mAuthTask.execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View mainView = inflater
                .inflate(R.layout.main_fragment, container, false);

        return mainView;
    }

    private class UpdateUserTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String responseString = null;
            if (SystemFunctions.isInternetConnected(getActivity(), SystemFunctions.getServerURL())){
                UserFunctions userFunction = new UserFunctions();
                JSONObject json = userFunction.updateOnline(getActivity().getApplicationContext());
                try {
                    if (json.getString(KEY_SUCCESS) != null) {
                        String res = json.getString(KEY_SUCCESS);
                        if(Integer.parseInt(res) == 1){

                            responseString = "1";
                        return responseString;
                    }
                        responseString = "0";
                        return responseString;
                    }
                    responseString = "0";
                    return responseString;
                } catch (JSONException e) {
                    responseString = "0";
                    return responseString;
                }
            } else {
                responseString = "2";
                return responseString;
            }
        }


        @Override
        protected void onPostExecute(String result) {
            mAuthTask = null;
            UserFunctions userFunction = new UserFunctions();
            if (result == "1") {
                ((YourApplication) getActivity().getApplication()).inject(MainFragment.this);
            } else if (result == "2") {
                userFunction.logoutUser(getActivity().getApplicationContext());
                Intent login = new Intent(getActivity().getApplicationContext(), Login.class);
                login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(login);
                // Closing dashboard screen
            } else {
                //error
                Log.d("Error", "hahaha");
            }
        }

    }
}
