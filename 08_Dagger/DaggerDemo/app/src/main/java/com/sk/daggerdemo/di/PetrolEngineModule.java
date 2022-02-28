package com.sk.daggerdemo.di;

import com.sk.daggerdemo.car.engine.Engine;
import com.sk.daggerdemo.car.engine.PetrolEngine;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class PetrolEngineModule {
    @Binds //Makes same operations with @Provide annotation
    // Returns PetrolEngine object
    abstract Engine bindEngine(PetrolEngine engine);
}
