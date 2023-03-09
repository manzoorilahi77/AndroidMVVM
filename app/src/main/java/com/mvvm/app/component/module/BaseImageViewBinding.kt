package com.mvvm.app.component.module

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.mvvm.app.R
import com.mvvm.app.component.implementaion.IImageViewBinding


class BaseImageViewBinding : IImageViewBinding {

    override fun setImageFromDrawable(imageView: ImageView, fileName: String?) {
        val place = getImgFromDrawable(imageView.context, "placeholder_logo")
        val option: RequestOptions = RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .centerCrop()
            .placeholder(place)
            .fitCenter()
        if (!fileName.isNullOrEmpty()) {
            val id: Int = getImgFromDrawable(imageView.context, fileName)
            Glide.with(imageView.context)
                .load(if (id > 0) id else place)
                .apply(option)
                .into(imageView)
        }
    }

    override fun setImageFromUrl(imageView: ImageView, filePath: String?) {
        val option: RequestOptions = RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .placeholder(getImgFromDrawable(imageView.context, "placeholder_logo"))
            .fitCenter()

        Glide.with(imageView.context)
            .load(filePath)
            .apply(option)
            .into(imageView)
    }

    override fun setImageFromUrlList(imageView: ImageView, filePath: ArrayList<String>?) {
        val option: RequestOptions = RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .placeholder(getImgFromDrawable(imageView.context, "placeholder_logo"))
            .fitCenter()

        Glide.with(imageView.context)
            .load(filePath?.get(0) ?: "")
            .apply(option)
            .into(imageView)
    }

    override fun setItemBackground(relativeLayout: RelativeLayout, backGround: Int?) {
        val context = relativeLayout.context
        val bg = backGround ?: R.drawable.vehicle_item_bg_1

        relativeLayout.background  = getIntDrawable(context, bg)
    }

    fun getImgFromDrawable(mContext: Context, filename: String): Int {
        var placeholder = getResourceIdentifier(mContext, filename)
        return placeholder
    }

    private fun getResourceIdentifier(mContext: Context, filename: String): Int {
        val res = mContext.resources
        return res.getIdentifier(filename, "drawable", mContext.packageName)
    }

    override fun goneUnless(view: View, str: String?) {
        /*view.visibility = if (str != null) {
            View.VISIBLE
        } else {
            View.INVISIBLE
        }*/
    }

    fun getIntDrawable(mContext: Context, colorInt: Int): Drawable? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mContext.resources.getDrawable(colorInt, null)
        } else {
            mContext.resources.getDrawable(colorInt)
        }
    }
}