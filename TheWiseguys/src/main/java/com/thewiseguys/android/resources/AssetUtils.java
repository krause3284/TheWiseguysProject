package com.thewiseguys.android.resources;

import android.content.res.AssetManager;

import java.io.IOException;
import java.util.Arrays;

/**
 * Version 0.0.1 - Last Modified 6/09/13.
 * Created by Chris Krause on 6/09/13.
 * TheWiseguys - com.thewiseguys.android.resources
 */
public class AssetUtils {

    /**
     * @param path subpath from the asset directory
     * @param assetManager assetManager
     * @return
     * @throws IOException
     */
    public static boolean exists( String fileName, String path, AssetManager assetManager ) throws IOException  {
        for( String currentFileName : assetManager.list(path)) {
            if ( currentFileName.equals(fileName)) {
                return true ;
            }
        }
        return false ;
    }

    /**
     * @param path
     * @param assetManager
     * @return
     * @throws IOException
     */
    public static String[] list( String path, AssetManager assetManager ) throws IOException {
        String[] files = assetManager.list(path);
        Arrays.sort(files);
        return files ;
    }
}

