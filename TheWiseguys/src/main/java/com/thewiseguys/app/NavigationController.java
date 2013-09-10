package com.thewiseguys.app;

/**
 * Version 0.0.1 - Last Modified 6/09/13.
 * Created by Chris Krause on 6/09/13.
 * TheWiseguys - com.thewiseguys.app
 */
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;

import com.thewiseguys.android.UserFunctions;
import com.thewiseguys.android.ui.changelog.ChangeLogHelper;
import com.thewiseguys.android.ui.changelog.EulaChangeLogChainHelper;
import com.thewiseguys.android.ui.eula.EulaHelper;
import com.thewiseguys.android.ui.fragment.dialog.ConfirmDialog;
import com.thewiseguys.android.ui.navdrawer.AbstractNavDrawerActivity;
import com.thewiseguys.app.home.MainFragment;
import com.thewiseguys.app.settings.MyPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class NavigationController {
    UserFunctions userFunctions;
    public static final String HOME_FRAGMENT_TAG = "home";

    @Inject
    public NavigationController() {

    }

    public void goHomeFragment( AbstractNavDrawerActivity activity) {
        activity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, new MainFragment(), HOME_FRAGMENT_TAG).commit();
        activity.setTitleWithDrawerTitle();
    }

    public void confirmEulaAndShowChangeLog(FragmentActivity activity) {
        EulaChangeLogChainHelper chain = new EulaChangeLogChainHelper(R.string.eula_title, R.string.eula_accept,
                R.string.eula_refuse, R.string.changelog_whatsnew_title, R.string.changelog_close, R.xml.changelog, activity);
        chain.show();
    }

    public void confirmEula(FragmentActivity activity) {
        EulaHelper eulaHelper = new EulaHelper(activity);
        eulaHelper.showAcceptRefuse(R.string.eula_title, R.string.eula_accept,
                R.string.eula_refuse);
    }

    public void showEula(FragmentActivity activity) {
        EulaHelper eulaHelper = new EulaHelper(activity);
        eulaHelper.show(R.string.eula_title, R.string.eula_close);
    }

    public void showWhatsNew( FragmentActivity activity ) {
        ChangeLogHelper changeLogHelper = new ChangeLogHelper();
        changeLogHelper.showWhatsNew(R.string.changelog_title, R.string.changelog_close, R.xml.changelog, activity);
    }

    public void showChangeLog( FragmentActivity activity ) {
        ChangeLogHelper changeLogHelper = new ChangeLogHelper();
        changeLogHelper.showFullChangeLog(R.string.changelog_title, R.string.changelog_close, R.xml.changelog, activity);
    }

    public void showExitDialog(final FragmentActivity activity) {
        ConfirmDialog newFragment = ConfirmDialog.newInstance(
                R.string.confirm_quit, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        userFunctions = new UserFunctions();
                        userFunctions.logoutUser(activity);
                        activity.finish();
                    }
                }, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.cancel();
                    }
                }
        );
        newFragment.show(activity.getSupportFragmentManager(), "dialog");
    }

    public void showSettings(FragmentActivity activity) {
        activity.startActivityForResult(new Intent(activity,
                MyPreferences.class), MyPreferences.PREFERENCES_RESULTCODE);
    }
}

