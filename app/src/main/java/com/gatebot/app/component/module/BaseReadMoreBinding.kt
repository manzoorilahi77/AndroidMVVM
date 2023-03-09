package com.gatebot.app.component.module

import android.view.View
import android.widget.TextView
import com.gatebot.app.component.implementaion.IReadMoreBinding

class BaseReadMoreBinding : IReadMoreBinding {

    override fun setImageFromUrlList(readMore: TextView?, readMoreDescription: String?) {
        readMore?.visibility = if (readMoreDescription.isNullOrEmpty()) {
            View.GONE
        } else if ((readMoreDescription.length ?: 0) >= 160) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }
}