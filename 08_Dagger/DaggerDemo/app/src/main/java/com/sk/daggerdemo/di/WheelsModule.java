package com.sk.daggerdemo.di;

import com.sk.daggerdemo.car.wheel.Wheels;
import com.sk.daggerdemo.car.wheel.Rims;
import com.sk.daggerdemo.car.wheel.Tires;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class WheelsModule {
    @Provides
    static Rims provideRims() {
        return new Rims();
    }

    @Provides
    static Tires provideTires() {
        Tires tires = new Tires();
        tires.inflate();
        return tires;
    }

    // Dagger automatically finds rims and tires in module class
    @Provides
    static Wheels provideFrameworkWheels(Rims rims, Tires tires) {
        return new Wheels(rims, tires);
    }
}
