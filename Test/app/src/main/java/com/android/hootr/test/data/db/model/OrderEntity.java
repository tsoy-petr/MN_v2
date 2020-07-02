package com.android.hootr.test.data.db.model;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "orders"
        ,
        indices = {
                @Index(value = "uuid", unique = true)})
public class OrderEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;


    public String uuid;

    //    @ColumnInfo(index = true)
    public String bc;

    public String number;
    public long date;
}
