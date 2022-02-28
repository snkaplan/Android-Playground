package com.sk.daggerdemo.car.wheel;

// we don't have this class so we can't annotate it with @Inject
public class Wheels {
    private Rims rims;
    private Tires tires;

    public Wheels(Rims rims, Tires tires) {
        this.rims = rims;
        this.tires = tires;
    }
}
