package com.sk.daggerdemo.car;

import android.util.Log;

import com.sk.daggerdemo.car.Car;

import javax.inject.Inject;

public class Remote {
    private static final String TAG = "Car";
    // Inject annotation means dagger injects Remote object by using this constructor.
    @Inject // Constructor injection
    public Remote() {
    }

    public void setListener(Car car){
        Log.d(TAG, "setListener: Remote Connected");
    }
}
