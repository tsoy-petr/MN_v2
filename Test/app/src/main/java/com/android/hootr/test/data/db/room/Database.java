package com.android.hootr.test.data.db.room;

import com.android.hootr.test.data.db.model.OrderEntity;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

public interface Database {


    void saveOrder(List<OrderEntity> orders);

    Flowable<List<OrderEntity>> getOrders();

    Single<OrderEntity> getOrder(String uuid);

}
