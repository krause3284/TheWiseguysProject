package com.thewiseguys.android.resources;

import android.content.Context;
import android.graphics.drawable.Drawable;

/**
 * Version 0.0.1 - Last Modified 6/09/13.
 * Created by Chris Krause on 6/09/13.
 * TheWiseguys - com.thewiseguys.android.resources
 */
public class ResourceUtils {

    public static Drawable getDrawableByName( String name, Context context ) {
        int drawableResource = context.getResources().getIdentifier(
                name,
                "drawable",
                context.getPackageName());
        if ( drawableResource == 0 ) {
            throw new RuntimeException("Can't find drawable with name: " + name );
        }
        return context.getResources().getDrawable(drawableResource);
    }

    public static int getDrawableIdByName( String name, Context context ) {
        int drawableResource = context.getResources().getIdentifier(
                name,
                "drawable",
                context.getPackageName());
        if ( drawableResource == 0 ) {
            throw new RuntimeException("Can't find drawable with name: " + name );
        }
        return drawableResource;
    }
}

