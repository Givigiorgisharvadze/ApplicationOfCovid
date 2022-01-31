package com.example.testfinaluri

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide

class SecondFragment : Fragment(R.layout.second_fragment) {

    private lateinit var imageView: ImageView

    private lateinit var secondImageView : ImageView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageView = view.findViewById(R.id.imageView)

        secondImageView = view.findViewById(R.id.secondImageView)

        view.findViewById<TextView>(R.id.textView).text = SecondFragmentArgs.fromBundle(requireArguments()).userTitle

        view.findViewById<TextView>(R.id.model).text = "Model: " + SecondFragmentArgs.fromBundle(requireArguments()).userModel

        view.findViewById<TextView>(R.id.year).text = "Release year: " + SecondFragmentArgs.fromBundle(requireArguments()).userYear

        view.findViewById<TextView>(R.id.mileage).text = "Mileage: " + SecondFragmentArgs.fromBundle(requireArguments()).userMileage

        view.findViewById<TextView>(R.id.bodyType).text = "Body Type: " + SecondFragmentArgs.fromBundle(requireArguments()).userBodyType

        view.findViewById<TextView>(R.id.engineType).text = "Engine Type: " + SecondFragmentArgs.fromBundle(requireArguments()).userEngineType

        view.findViewById<TextView>(R.id.engineVol).text = "Engine Vol: " + SecondFragmentArgs.fromBundle(requireArguments()).userEngineVol

        view.findViewById<TextView>(R.id.Driving).text = "Drive: " + SecondFragmentArgs.fromBundle(requireArguments()).userDrive

        view.findViewById<TextView>(R.id.exteriorColor).text = "Exterior Color: " + SecondFragmentArgs.fromBundle(requireArguments()).userColor

        val urlImage = SecondFragmentArgs.fromBundle(requireArguments()).imageUrl

        val secondUrlImage = SecondFragmentArgs.fromBundle(requireArguments()).secondUrlImage

        Glide.with(this@SecondFragment)
            .load(urlImage)
            .centerCrop()
            .into(imageView)

        Glide.with((this@SecondFragment))
            .load(secondUrlImage)
            .centerCrop()
            .into(secondImageView)

    }

}