package dev.tigrao.github.sdu.card.ui.support

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

internal fun ImageView.loadImage(url: String) {
    Glide.with(this)
        .load(url)
        .apply(RequestOptions().circleCrop())
        .into(this)
}
