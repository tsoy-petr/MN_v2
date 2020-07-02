package com.android.hootr.tsd.screens.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.android.hootr.tsd.data.model.BTData

class BTAdapter(context: Context, val textViewResourceId: Int, val btData: MutableList<BTData>) :
    ArrayAdapter<BTData>(context, textViewResourceId, btData) {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var view =
            if (convertView == null) View.inflate(context, textViewResourceId, null)
            else convertView

        (view as TextView).text = btData[position].name

        return view

    }


    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
//        return super.getDropDownView(position, convertView, parent)
        var view =
            if (convertView == null) View.inflate(context, textViewResourceId, null)
            else convertView

        (view as TextView).text = btData[position].name

        return view
    }
}