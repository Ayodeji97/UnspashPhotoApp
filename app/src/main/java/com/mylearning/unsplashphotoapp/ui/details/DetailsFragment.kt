package com.mylearning.unsplashphotoapp.ui.details

import android.content.Intent
import android.graphics.Point
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.mylearning.unsplashphotoapp.R
import com.mylearning.unsplashphotoapp.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment(R.layout.fragment_details) {

    private val args by navArgs<DetailsFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentDetailsBinding.bind(view)

        binding.apply {
            val photo = args.photo

            Glide.with(this@DetailsFragment)
                .load(photo.urls.full)
                .error(R.drawable.ic_error)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBar.isVisible = false
                        textViewAuthor.isVisible = true
                        textViewDescription.isVisible = photo.description != null

                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBar.isVisible = false
                        return false
                    }

                })
                .into(imageView)


            textViewDescription.text = photo.description

            // creating a link to unSplash with attribute url
            val uri = Uri.parse(photo.user.atrributionUrl)

            val intent = Intent(Intent.ACTION_VIEW, uri)

            textViewAuthor.apply {
                text = "Photo by ${photo.user.name} on Unsplash"

                // We want the text to be clickable
                setOnClickListener {
                    context.startActivity(intent)
                }
                paint.isUnderlineText = true
            }

        }
    }


}