package com.thewiseguys.app.home;

/**
 * Version 0.0.1 - Last Modified 6/09/13.
 * Created by Chris Krause on 6/09/13.
 * TheWiseguys - com.thewiseguys.app.home
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.thewiseguys.app.R;
import com.thewiseguys.android.ui.animation.LiveButton;
import com.thewiseguys.android.ui.navdrawer.AbstractNavDrawerActivity;
import com.thewiseguys.android.ui.navdrawer.NavDrawerSelectItemRunnable;
import com.thewiseguys.app.YourApplication;

import javax.inject.Inject;

public class MainFragment extends Fragment {

    @Inject
    LiveButton liveButton ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((YourApplication) getActivity().getApplication()).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View mainView = inflater
                .inflate(R.layout.main_fragment, container, false);

        return mainView;
    }
}
