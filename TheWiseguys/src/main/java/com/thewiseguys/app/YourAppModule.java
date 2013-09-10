package com.thewiseguys.app;

/**
 * Version 0.0.1 - Last Modified 6/09/13.
 * Created by Chris Krause on 6/09/13.
 * TheWiseguys - com.thewiseguys.app
 */
import com.thewiseguys.android.MCXModule;
import com.thewiseguys.app.home.MainFragment;
import com.thewiseguys.app.home.YourAppMainActivity;
import com.thewiseguys.app.settings.MyPreferences;

import dagger.Module;

@Module(
        injects = {
                YourAppMainActivity.class,
                MainFragment.class,
                MyPreferences.class
        },
        includes = {
                MCXModule.class
        },
        overrides=true
)
public class YourAppModule {

}
