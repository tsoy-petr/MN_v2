package com.android.hootr.test.data.db.model;

import com.android.hootr.test.data.api.models.order.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderEntityMapper {

    public List<OrderEntity> maoOrders(List<Order> orders) {

        List<OrderEntity> entities = new ArrayList<>();

        for (Order order:
             orders) {
            entities.add(mapOrder(order));
        }

        return entities;

    }

    private OrderEntity mapOrder(Order order) {
        OrderEntity entity = new OrderEntity();
        entity.uuid = order.uuid;
        entity.namber = order.number;
        entity.date = order.date;
        entity.bc = order.bc;

        return entity;
    }
}
