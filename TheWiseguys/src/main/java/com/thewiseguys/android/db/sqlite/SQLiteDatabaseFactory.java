package com.thewiseguys.android.db.sqlite;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.sqlite.SQLiteDatabase;

import com.thewiseguys.android.info.VersionUtils;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Version 0.0.1 - Last Modified 6/09/13.
 * <p/>
 * Created by Chris Krause on 6/09/13.
 * <p/>
 * TheWiseguys - com.thewiseguys.android.db.sqlite
 */
@Singleton
public class SQLiteDatabaseFactory {

    /**
     * SQLite Open Helper
     */
    private SQLiteDatabaseHelper helper;

    /**
     * SQLite Database
     */
    private SQLiteDatabase database ;

    @Inject
    public SQLiteDatabaseFactory() {

    }

    /**
     * @throws NameNotFoundException
     */
    public void init(Context context, boolean writable, boolean enableFK) throws NameNotFoundException {
        this.helper = new SQLiteDatabaseHelper(context, "database", null,
                VersionUtils.getVersionCode(context));
        // open and close database to init database (creation/upgrade)
        if ( writable ) {
            this.database = this.helper.getWritableDatabase();
            if ( enableFK ) {
                this.database.execSQL("PRAGMA foreign_keys = ON");
            }
        }
        else {
            this.database = this.helper.getReadableDatabase();
        }
    }

    /**
     * @return
     */
    public SQLiteDatabase getDatabase() {
        return this.database;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        if ( this.database != null && this.database.isOpen()) {
            this.database.close();
        }
    }

    /**
     *
     */
    public void closeDatabase() {
        this.database.close();
    }
}
