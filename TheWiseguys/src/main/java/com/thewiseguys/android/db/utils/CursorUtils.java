package com.thewiseguys.android.db.utils;

import android.database.Cursor;

/**
 * Version 0.0.1 - Last Modified 6/09/13.
 *
 * Created by Chris Krause on 6/09/13.
 *
 * TheWiseguys - com.thewiseguys.android.db.utils
 */
public class CursorUtils {

    /**
     * @param columnName
     * @param cursor
     * @return
     */
    public static String getString( String columnName, Cursor cursor ) {
        return cursor.getString(cursor.getColumnIndexOrThrow(columnName));
    }
}
