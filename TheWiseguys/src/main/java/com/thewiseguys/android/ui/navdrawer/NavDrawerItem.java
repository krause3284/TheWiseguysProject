package com.thewiseguys.android.ui.navdrawer;

/**
 * Version 0.0.1 - Last Modified 6/09/13.
 * Created by Chris Krause on 6/09/13.
 * TheWiseguys - com.thewiseguys.android.ui.navdrawer
 */
public interface NavDrawerItem {

    public int getId();

    public String getLabel();

    public int getType();

    public boolean isEnabled();

    public boolean updateActionBarTitle();

    public boolean isCheckable();
}
