package com.thewiseguys.app;

/**
 * Version 0.0.1 - Last Modified 6/09/13.
 * Created by Chris Krause on 6/09/13.
 * TheWiseguys - com.thewiseguys.app
 */
import android.content.pm.PackageManager.NameNotFoundException;

import com.thewiseguys.android.MCXApplication;
import com.thewiseguys.android.db.sqlite.SQLiteDatabaseFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import dagger.ObjectGraph;

public class YourApplication extends MCXApplication {

    /**
     *
     */
    private static final Logger log = LoggerFactory.getLogger(YourApplication.class);

    @Override
    public void buildDaggerModules(List<Object> modules) {
        modules.add(new YourAppModule());
    }

    @Override
    public void onObjectGraphCreated(ObjectGraph objectGraph) {
        super.onObjectGraphCreated(objectGraph);
        log.debug("onObjectGraphCreated");
        SQLiteDatabaseFactory sqliteDbFactory = objectGraph.get(SQLiteDatabaseFactory.class);
        try {
            sqliteDbFactory.init(this, true, true);
        } catch (NameNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}