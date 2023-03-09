package com.gatebot.app.component.implementaion

import android.widget.TextView
import androidx.databinding.BindingAdapter

interface IReadMoreBinding {

    @BindingAdapter("readMoreDescription")
    fun setImageFromUrlList(readMore: TextView?, readMoreDescription: String?)

}