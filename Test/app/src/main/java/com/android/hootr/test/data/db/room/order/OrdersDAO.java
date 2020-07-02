package com.android.hootr.test.data.db.room.order;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.android.hootr.test.data.db.model.OrderEntity;
import com.android.hootr.test.data.db.model.OrderGoodsEntity;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public abstract class OrdersDAO {

    @Transaction
    public void saveOrederFull(List<OrderEntity> orderEntityList,
                               List<OrderGoodsEntity> orderGoodsEntitiesList) {
        saveOrder(orderEntityList);
        saveOrderGoods(orderGoodsEntitiesList);
    }

    @Insert(onConflict = REPLACE)
    public abstract void saveOrder(List<OrderEntity> orders);

    @Query("select * from orders ORDER BY date ASC")
    public abstract Flowable<List<OrderEntity>> getOrders();

    @Query("select * from orders where uuid = :uuid")
    public abstract Single<OrderEntity> getOrder(String uuid);

    @Insert(onConflict = REPLACE)
    public abstract void saveOrderGoods(List<OrderGoodsEntity> orderGoods);

}
