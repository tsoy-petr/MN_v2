package com.android.hootr.myapplication;

import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Cancellable;

public class ReactivBT implements ObservableOnSubscribe<DataBT>, Cancellable {

    private ObservableEmitter<DataBT> emitter;

    @Override
    public void subscribe(ObservableEmitter<DataBT> emitter) throws Exception {

        this.emitter = emitter;
        this.emitter.setCancellable(this);
    }

    @Override
    public void cancel() throws Exception {

    }

    public void sendEmitt(DataBT data) {
        if (emitter != null) {
            emitter.onNext(data);
        }
    }
}
