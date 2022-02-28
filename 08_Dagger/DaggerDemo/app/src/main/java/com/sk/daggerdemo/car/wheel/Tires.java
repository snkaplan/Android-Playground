package com.sk.daggerdemo.car.wheel;

import android.util.Log;

// we don't have this class so we can't annotate it with @Inject
public class Tires {
    private static final String TAG = "Car";
    public void inflate(){
        Log.d(TAG, "Tires inflated");
    }
}
