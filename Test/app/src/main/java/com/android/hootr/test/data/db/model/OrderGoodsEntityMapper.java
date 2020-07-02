package com.android.hootr.test.data.db.model;

import com.android.hootr.test.data.api.models.order.OrderGood;

import java.util.ArrayList;
import java.util.List;

public class OrderGoodsEntityMapper {

    public List<OrderGoodsEntity> orderGoodsEntitiesMap(List<OrderGood> orderGoods) {

        List<OrderGoodsEntity> entities = new ArrayList<>();

        for (OrderGood orderGood :
                orderGoods) {
            entities.add(mapOrederGood(orderGood));
        }

        return entities;
    }

    private OrderGoodsEntity mapOrederGood(OrderGood orderGood) {

        OrderGoodsEntity entity = new OrderGoodsEntity();
        entity.orderUuid = orderGood.orderUuid;
        entity.goodName = orderGood.goodName;
        entity.goodUuid = orderGood.goodUuid;
        entity.actualQuantity = orderGood.actualQuantity;
        entity.plannedAmount = orderGood.plannedAmount;
        entity.bc1 = orderGood.bc1;
        entity.bc2 = orderGood.bc2;

        return entity;
    }

}
