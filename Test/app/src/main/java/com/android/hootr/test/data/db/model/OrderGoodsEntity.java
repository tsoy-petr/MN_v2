package com.android.hootr.test.data.db.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "orderGoods",
        foreignKeys = @ForeignKey(entity = OrderEntity.class,
                parentColumns = "uuid",
                childColumns = "orderUuid",
                onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE)
        ,
        indices = {
                @Index(value = "orderUuid"),
                @Index(value = {"orderUuid", "bc1"}),
                @Index(value = {"orderUuid", "bc2"})
        }
)
public class OrderGoodsEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String orderUuid;

    public String goodName;
    public String goodUuid;
    public double plannedAmount;
    public double actualQuantity;
    public String bc1;
    public String bc2;

}
