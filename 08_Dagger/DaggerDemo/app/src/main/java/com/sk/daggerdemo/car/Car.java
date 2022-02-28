package com.sk.daggerdemo.car;

import android.util.Log;

import com.sk.daggerdemo.car.engine.Engine;
import com.sk.daggerdemo.car.wheel.Wheels;

import javax.inject.Inject;

public class Car {
    private static final String TAG = "Car";
    // Field injection handled on main activity so this field will be automatically injected by dagger.
    @Inject
    Engine engine; // Field should be public because dagger can not inject private variables
    private Wheels wheels;
    private Driver driver;
    // Inject annotation means dagger injects car object by using this constructor.
    @Inject // Constructor injection
    public Car(Driver driver, Wheels wheels) {
        this.driver = driver;
        this.wheels = wheels;
    }

    // Method injection
    // Method should be public because dagger can not inject private methods
    @Inject
    public void enableRemote(Remote remote){
        remote.setListener(this);
    }

    public void drive() {
        engine.start();
        Log.d(TAG, driver + " drives " + this);
    }


}
