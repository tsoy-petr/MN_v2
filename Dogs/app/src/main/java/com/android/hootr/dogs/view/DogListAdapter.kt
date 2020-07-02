package com.android.hootr.dogs.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.android.hootr.dogs.R
import com.android.hootr.dogs.databinding.ItemDogBinding
import com.android.hootr.dogs.model.DogBreed
import kotlinx.android.synthetic.main.item_dog.view.*

class DogListAdapter(val dogsList: ArrayList<DogBreed>) :
    RecyclerView.Adapter<DogListAdapter.DogViewHolder>(), DogClickListener {

    fun updateDogList(newDogList: List<DogBreed>) {
        dogsList.clear()
        dogsList.addAll(newDogList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {

//        val inflater = LayoutInflater.from(parent.context)
//        val view = inflater.inflate(R.layout.item_dog, parent, false)
//
//        return DogViewHolder(view)

        val inflater = LayoutInflater.from(parent.context)
        val view =
            DataBindingUtil.inflate<ItemDogBinding>(inflater, R.layout.item_dog, parent, false)
        return DogViewHolder(view)

    }

    override fun getItemCount() = dogsList.size


    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {

        holder.view.dog = dogsList[position]
        holder.view.listener = this
//        holder.view.name.text = dogsList[position].dogBreed
//        holder.view.lifeSpan.text = dogsList[position].lifeSpan
//        holder.view.setOnClickListener {
//            val action = ListFragmentDirections.actionDetailFragment()
//            action.dogUuid = dogsList[position].uuid
//            Navigation.findNavController(it).navigate(action)
//        }
//
//        holder.view.imageView.loadImage(dogsList[position].imageUrl, getProgressDrawable(holder.view.imageView.context))
    }


    class DogViewHolder(var view: ItemDogBinding) : RecyclerView.ViewHolder(view.root) {

    }

    override fun dogClicked(v: View) {
        val uuid = v.dogId.text.toString().toInt()
        val action = ListFragmentDirections.actionDetailFragment()
        action.dogUuid = uuid
        Navigation.findNavController(v).navigate(action)
    }

}