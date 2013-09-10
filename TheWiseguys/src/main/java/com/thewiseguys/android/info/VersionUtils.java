package com.thewiseguys.android.info;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;

/**
 * Version 0.0.1 - Last Modified 6/09/13.
 * <p/>
 * Created by Chris Krause on 6/09/13.
 * <p/>
 * TheWiseguys - com.thewiseguys.android.info
 */

public class VersionUtils {

    public static int getVersionCode( Context context ) {
        PackageInfo manager= null;
        try {
            manager = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0);
        } catch (NameNotFoundException e) {
            throw new RuntimeException(e);
        }
        return manager.versionCode;
    }

    public static String getVersionName( Context context ) {
        PackageInfo manager= null;
        try {
            manager = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0);
        } catch (NameNotFoundException e) {
            throw new RuntimeException(e);
        }
        return manager.versionName;
    }
}
