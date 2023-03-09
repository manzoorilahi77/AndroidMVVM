package com.mvvm.app.component.implementaion

import androidx.databinding.BindingAdapter
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout

interface IImageViewBinding {

    @BindingAdapter("loadImageFromDrawable")
    fun setImageFromDrawable(imageView: ImageView, fileName: String?)

    @BindingAdapter("customImageFromUrl")
    fun setImageFromUrl(imageView: ImageView, filePath: String?)

    @BindingAdapter("customImageFromUrlList")
    fun setImageFromUrlList(imageView: ImageView, filePath: ArrayList<String>?)

    @BindingAdapter("itemBackground")
    fun setItemBackground(relativeLayout: RelativeLayout, backGround: Int?)

    @BindingAdapter("goneUnless")
    fun goneUnless(view: View, str: String?)

}