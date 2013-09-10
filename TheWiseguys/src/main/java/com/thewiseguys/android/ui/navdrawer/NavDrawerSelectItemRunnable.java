package com.thewiseguys.android.ui.navdrawer;

/**
 * Version 0.0.1 - Last Modified 6/09/13.
 * Created by Chris Krause on 6/09/13.
 * TheWiseguys - com.thewiseguys.android.ui.navdrawer
 */
public class NavDrawerSelectItemRunnable implements Runnable {

    private AbstractNavDrawerActivity activity ;

    private int itemId ;

    public NavDrawerSelectItemRunnable(AbstractNavDrawerActivity act, int itemId) {
        this.activity = act ;
        this.itemId = itemId ;
    }

    @Override
    public void run() {
        activity.selectItem(itemId);
    }
}

