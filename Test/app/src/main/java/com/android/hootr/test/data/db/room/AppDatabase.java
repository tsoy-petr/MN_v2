package com.android.hootr.test.data.db.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.android.hootr.test.data.db.model.OrderEntity;
import com.android.hootr.test.data.db.model.OrderGoodsEntity;
import com.android.hootr.test.data.db.room.order.OrdersDAO;

@Database(entities = {OrderEntity.class, OrderGoodsEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract OrdersDAO ordersDAO();
}
