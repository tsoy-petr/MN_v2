package com.android.hootr.myloftcoint.screens.main.rate;

import androidx.recyclerview.widget.DiffUtil;

import com.android.hootr.myloftcoint.data.db.model.CoinEntity;

import java.util.List;

public class CoinDiffCallback extends DiffUtil.Callback {

    private List<CoinEntity> oldList;
    private List<CoinEntity> newList;

    public CoinDiffCallback(List<CoinEntity> oldList, List<CoinEntity> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).id == newList.get(newItemPosition).id;
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).name.equals(
                newList.get(newItemPosition).name
        );
    }
}
