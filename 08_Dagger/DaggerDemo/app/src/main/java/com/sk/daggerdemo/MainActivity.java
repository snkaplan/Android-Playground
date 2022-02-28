package com.sk.daggerdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.sk.daggerdemo.car.Car;
import com.sk.daggerdemo.di.CarComponent;
import com.sk.daggerdemo.di.DaggerCarComponent;
import com.sk.daggerdemo.di.DieselEngineModule;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {
    @Inject Car car1, car2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CarComponent component = DaggerCarComponent.builder().horsePower(150).engineCapacity(1400).build();
                //.dieselEngineModule(new DieselEngineModule(20)).build();
        component.inject(this); // For field injection
//        car = component.getCar();
        car1.drive();
        car2.drive();
    }
}