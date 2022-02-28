package com.sk.daggerdemo.di;

import com.sk.daggerdemo.car.Car;
import com.sk.daggerdemo.MainActivity;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

// Component is an injector
@Singleton
@Component(modules = {WheelsModule.class, PetrolEngineModule.class})
public interface CarComponent {

    Car getCar();

    void inject(MainActivity mainActivity);

    @Component.Builder
    interface Builder {
        // Builder checks the type of variable so it sets same value all the integers but giving @Named annotation solves problem.
        // Same @Named annotation should be use the data class(In PetrolEngine class)
        @BindsInstance
        Builder horsePower(@Named("horse power") int horsePower);

        @BindsInstance
        Builder engineCapacity(@Named("engine capacity") int engineCapacity);

        CarComponent build();
    }
}

