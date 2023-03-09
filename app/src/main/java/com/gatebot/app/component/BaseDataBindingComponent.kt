package com.gatebot.app.component

import androidx.databinding.DataBindingComponent
import com.gatebot.app.component.implementaion.*
import com.gatebot.app.component.module.*

class BaseDataBindingComponent : DataBindingComponent {
    override fun getISpinnerBinding(): ISpinnerBinding {
        return BaseSpinnerBinding()
    }

    override fun getIImageViewBinding(): IImageViewBinding {
        return BaseImageViewBinding()
    }

    override fun getIReadMoreBinding(): IReadMoreBinding {
        return BaseReadMoreBinding()
    }

}