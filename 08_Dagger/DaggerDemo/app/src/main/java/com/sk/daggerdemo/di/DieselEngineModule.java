package com.sk.daggerdemo.di;

import com.sk.daggerdemo.car.engine.DieselEngine;
import com.sk.daggerdemo.car.engine.Engine;

import dagger.Module;
import dagger.Provides;


@Module
public class DieselEngineModule {
    private int horsePower;

    public DieselEngineModule(int horsePower) {
        this.horsePower = horsePower;
    }

    @Provides
    int provideHorsePower(){
        return horsePower;
    }

    @Provides
    Engine provideEngine(DieselEngine engine){
        return engine;
    }
}

