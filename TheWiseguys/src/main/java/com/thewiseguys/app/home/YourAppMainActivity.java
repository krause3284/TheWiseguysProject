package com.thewiseguys.app.home;

/**
 * Version 0.0.1 - Last Modified 6/09/13.
 * Created by Chris Krause on 6/09/13.
 * TheWiseguys - com.thewiseguys.app.home
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.thewiseguys.app.R;
import com.thewiseguys.android.ui.navdrawer.AbstractNavDrawerActivity;
import com.thewiseguys.android.ui.navdrawer.NavDrawerActivityConfiguration;
import com.thewiseguys.android.ui.navdrawer.NavDrawerAdapter;
import com.thewiseguys.android.ui.navdrawer.NavDrawerItem;
import com.thewiseguys.android.ui.navdrawer.NavMenuItem;
import com.thewiseguys.android.ui.navdrawer.NavMenuSection;
import com.thewiseguys.app.NavigationController;
import com.thewiseguys.android.UserFunctions;
import com.thewiseguys.app.YourApplication;
import com.thewiseguys.app.login.Login;
import com.thewiseguys.app.settings.MyPreferences;

import javax.inject.Inject;

public class YourAppMainActivity extends AbstractNavDrawerActivity {
    UserFunctions userFunctions;

    @Inject
    NavigationController navController;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userFunctions = new UserFunctions();
        if(userFunctions.isUserLoggedIn(getApplicationContext())){
            ((YourApplication) getApplication()).inject(this);

            if (savedInstanceState == null) {
                this.navController.goHomeFragment(this);
                this.navController.confirmEulaAndShowChangeLog(this);
            }
        }else{
            // user is not logged in show login screen
            Intent login = new Intent(getApplicationContext(), Login.class);
            login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(login);
            // Closing dashboard screen
            finish();
        }
    }

    @Override
    protected NavDrawerActivityConfiguration getNavDrawerConfiguration() {
        NavDrawerItem[] menu = new NavDrawerItem[] {
                NavMenuSection.create(100, "General"),
                NavMenuItem.create(101, "Announcements", "ic_home_w",false, false, this),
                NavMenuSection.create(200, "Other"),
                NavMenuItem.create(201, "Settings", "navdrawer_settings",
                        false, false, this),
                NavMenuItem.create(204, "ChangeLog", "navdrawer_changelog", false, false, this),
                NavMenuItem.create(206, "Quit", "navdrawer_quit", false, false, this) };

        NavDrawerActivityConfiguration navDrawerActivityConfiguration = new NavDrawerActivityConfiguration();
        navDrawerActivityConfiguration.setMainLayout(R.layout.main);
        navDrawerActivityConfiguration.setDrawerLayoutId(R.id.drawer_layout);
        navDrawerActivityConfiguration.setLeftDrawerId(R.id.left_drawer);
        navDrawerActivityConfiguration.setNavItems(menu);
        navDrawerActivityConfiguration
                .setDrawerShadow(R.drawable.drawer_shadow);
        navDrawerActivityConfiguration.setDrawerOpenDesc(R.string.drawer_open);
        navDrawerActivityConfiguration
                .setDrawerCloseDesc(R.string.drawer_close);
        navDrawerActivityConfiguration.setBaseAdapter(new NavDrawerAdapter(
                this, R.layout.navdrawer_item, menu));
        navDrawerActivityConfiguration.setDrawerIcon(R.drawable.ic_drawer);
        return navDrawerActivityConfiguration;
    }

    @Override
    protected void onNavItemSelected(int id) {
        switch (id) {
            case 101:
                this.navController.goHomeFragment(this);
                break;
            case 102:
                //getSupportFragmentManager().beginTransaction()
                        //.replace(R.id.content_frame, new FriendMainFragment())
                       // .commit();
                break;

            case 201:
                this.navController.showSettings(this);
                break;
            case 204:
                this.navController.showChangeLog(this);
                break;
            case 205:
                this.navController.showEula(this);
                break;
            case 206:
                this.navController.showExitDialog(this);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == MyPreferences.PREFERENCES_RESULTCODE) {
            Toast.makeText(this, "Back from preferences", Toast.LENGTH_SHORT)
                    .show();
        }
    }


    @Override
    public void onBackPressed() {


        FragmentManager fm = getSupportFragmentManager();
        Fragment currentFragment = fm.findFragmentById(R.id.content_frame);
        if (currentFragment != null && currentFragment.getChildFragmentManager().getBackStackEntryCount() > 0) {
            // Get the fragment fragment manager - and pop the backstack
            currentFragment.getChildFragmentManager().popBackStack();
        }
        // Else, nothing in the direct fragment back stack
        else {
            if ( !NavigationController.HOME_FRAGMENT_TAG.equals(currentFragment.getTag())) {
                this.navController.goHomeFragment(this);
            }
            else {
                this.navController.showExitDialog(this);
            }
        }
    }
}
