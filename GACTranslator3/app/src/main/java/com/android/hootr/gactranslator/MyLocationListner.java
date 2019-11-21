package com.android.hootr.gactranslator;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import java.lang.ref.WeakReference;

public class MyLocationListner implements LocationListener, LifecycleObserver {

    private WeakReference<Context> context;
    private WeakReference<Lifecycle> lifecycle;

    public MyLocationListner(Context context, Lifecycle lifecycle) {

        this.context = new WeakReference<>(context);
        this.lifecycle = new WeakReference<>(lifecycle);

    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @SuppressLint("MissingPermission")
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void connect() {

        LocationManager manager =
                (LocationManager) context.get().getSystemService(Context.LOCATION_SERVICE);

        if (lifecycle.get().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            manager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                    5_000,
                    0,
                    this);
        }

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void desconnect() {

        LocationManager manager =
                (LocationManager) context.get().getSystemService(Context.LOCATION_SERVICE);
        manager.removeUpdates(this);

    }
}
