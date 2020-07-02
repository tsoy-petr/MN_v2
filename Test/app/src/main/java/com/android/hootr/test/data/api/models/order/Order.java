package com.android.hootr.test.data.api.models.order;

import java.util.List;

public class Order {

    public String uuid;
    public String bc;
    public String number;
    public int date;

    public List<OrderGood> goods;
}
