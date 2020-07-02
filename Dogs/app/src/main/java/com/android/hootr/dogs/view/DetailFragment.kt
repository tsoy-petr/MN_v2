package com.android.hootr.dogs.view


import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.palette.graphics.Palette
import com.android.hootr.dogs.R
import com.android.hootr.dogs.databinding.FragmentDetailBinding
import com.android.hootr.dogs.model.DogPallet
import com.android.hootr.dogs.viewmodel.DetailViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition

/**
 * A simple [Fragment] subclass.
 */
class DetailFragment : Fragment() {

    private lateinit var viewModel: DetailViewModel
    private var dogUuid = 0

    private lateinit var dataBinding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        return dataBinding.root
//        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            dogUuid = DetailFragmentArgs.fromBundle(it).dogUuid

        }

        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        viewModel.fetch(dogUuid)


        observeViewModel()


    }

    private fun observeViewModel() {

        viewModel.dogLiveData.observe(this, Observer { dog ->
            dog?.let {
                //                dogName.text = it.dogBreed
//                dogPurpose.text = it.breedFor
//                dogTemperment.text = it.temperament
//                dogLifeSpan.text = it.lifeSpan
//                context?.let {
//                    dogImage.loadImage(dog.imageUrl, getProgressDrawable(it))
//                }

                dataBinding.dog = dog

                dog.imageUrl?.let {
                    setupBackgroundColor(it)
                }
            }
        })

    }

    private fun setupBackgroundColor(url: String) {
        Glide.with(this)
            .asBitmap()
            .load(url)
            .into(object : CustomTarget<Bitmap>() {
                override fun onLoadCleared(placeholder: Drawable?) {


                }

                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {

                    Palette.from(resource)
                        .generate { palette ->
                            val intColor = palette?.lightMutedSwatch?.rgb ?: 0
                            val myPalette = DogPallet(intColor)
                            dataBinding.pallet = myPalette
                        }

                }

            })
    }


}
