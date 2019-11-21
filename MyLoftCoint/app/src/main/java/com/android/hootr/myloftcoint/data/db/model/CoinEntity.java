package com.android.hootr.myloftcoint.data.db.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Coin")
public class CoinEntity {

    @PrimaryKey
    public int id;
    public String name;
    public String symbol;
    public String slug;
    public int rank;
    public long updated;

}
