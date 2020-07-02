package com.android.hootr.oneactivitymanyfragments;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class RxBus {

    private static RxBus mInstance;

    public static RxBus getInstance() {
        if (mInstance == null) {
            mInstance = new RxBus();
        }
        return mInstance;
    }

    private RxBus() {
    }

    private PublishSubject<Integer> publisher = PublishSubject.create();

    void publish(Integer event) {
        publisher.onNext(event);
    }

    // Listen should return an Observable
    Observable<Integer> listen() {
        return publisher;
    }

}
