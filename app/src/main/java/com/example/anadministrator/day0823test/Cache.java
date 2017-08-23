package com.example.anadministrator.day0823test;

import android.content.Context;
import android.os.Environment;

/**
 * Created by 张祺钒
 * on2017/8/23.
 */

public class Cache {
    public static String getDiskCachePath(Context context) {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()) {
            return context.getExternalCacheDir().getPath();
        } else {
            return context.getCacheDir().getPath();
        }
    }
}
