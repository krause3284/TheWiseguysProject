package com.thewiseguys.android;

import android.app.Activity;
import android.app.Application;
import android.content.ContentProvider;
import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import dagger.ObjectGraph;

/**
 * Version 0.0.1 - Last Modified 6/09/13.
 *
 * Created by Chris Krause on 6/09/13.
 *
 * TheWiseguys - com.thewiseguys.android
 */
public class MCXApplication extends Application {

    public static final String LOG_TAG = "MCX";

    private ObjectGraph objectGraph;

    public void inject(Activity activity) {
        getObjectGraph().inject(activity);
    }

    public void inject(ContentProvider contentProvider) {
        getObjectGraph().inject(contentProvider) ;
    }

    public void inject(Fragment fragment) {
        getObjectGraph().inject(fragment);
    }

    public synchronized ObjectGraph getObjectGraph() {
        if (objectGraph == null) {
            List<Object> modules = new ArrayList<Object>();
            buildDaggerModules(modules);
            this.objectGraph = ObjectGraph.create(modules.toArray());
            onObjectGraphCreated(this.objectGraph);
        }
        return objectGraph;
    }

    public void buildDaggerModules( List<Object> modules) {
        modules.add(new MCXModule());
    }

    public void onObjectGraphCreated( ObjectGraph objectGraph) {

    }
}

