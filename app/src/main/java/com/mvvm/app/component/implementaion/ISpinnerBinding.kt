package com.mvvm.app.component.implementaion

import androidx.databinding.BindingAdapter
import android.widget.Spinner

interface ISpinnerBinding{

    @BindingAdapter("customEnable")
    fun setEnable(spinner: Spinner,enable:Boolean)

    @BindingAdapter("customItemLayoutRes","customItemTextViewId",
            "customItemDropDownLayoutRes","customItemDropDownTextViewId",
            "customHint","dataItems","customSelectedItemPosition","setSprScreen", requireAll = false)
    fun setAdapter(spinner:Spinner, customLayout:Int, customViewId:Int,
                   customDropDownLayout:Int,customDropDownTextViewId:Int,
                   customHint:String?, dataItems:ArrayList<String>?,
                   customSelectedItemPosition:Int?, screen: String?)
}