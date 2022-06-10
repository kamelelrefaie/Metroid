package com.example.metroid.ui.view.settings_cycle

import android.app.Dialog
import android.content.Context
import android.media.Image
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.ImageView
import android.widget.Toast
import com.example.metroid.R
import com.example.metroid.databinding.DialogRateUsBinding

class RatingBarDialog(context: Context, content: String, subject: String) : Dialog(context) {
    var userRate = 3f
     lateinit var dialogRateUsBinding: DialogRateUsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dialogRateUsBinding = DialogRateUsBinding.inflate(layoutInflater)
        setContentView(dialogRateUsBinding.root)

        dialogRateUsBinding.rb.setOnRatingBarChangeListener { ratingBar, rating, b ->
            if (rating <= 1) {
                dialogRateUsBinding.ivRating.setImageResource(R.drawable.one_star)
            } else if (rating <= 2) {
                dialogRateUsBinding.ivRating.setImageResource(R.drawable.two_star)

            } else if (rating <= 3) {
                dialogRateUsBinding.ivRating.setImageResource(R.drawable.three_star)

            } else if (rating <= 4) {
                dialogRateUsBinding.ivRating.setImageResource(R.drawable.four_star)

            } else if (rating <= 5) {
                dialogRateUsBinding.ivRating.setImageResource(R.drawable.five_star)
            }
            animateImage(ratingImage = dialogRateUsBinding.ivRating)
            userRate = rating
        }
    }

    private fun animateImage(ratingImage: ImageView) {
        var scaleAnimation = ScaleAnimation(
            0f, 1f, 0f, 1f,
            Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f
        )
        scaleAnimation.fillAfter = true
        scaleAnimation.duration = 200
        dialogRateUsBinding.ivRating.startAnimation(scaleAnimation)
    }
}