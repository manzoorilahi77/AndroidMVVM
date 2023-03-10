package com.mvvm.app.ui.home

import android.app.Application
import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.mvvm.app.base.BaseNavigator
import com.mvvm.app.base.BaseViewModel
import com.mvvm.app.data.local.Singleton
import com.mvvm.app.utilities.SingleLiveData

class HomeVM(application: Application) : BaseViewModel<BaseNavigator>(application) {

    val isFullMenuScreensVisible = ObservableBoolean(false)
    val showProgressBar =  SingleLiveData<Boolean>()
    val mLive = ObservableBoolean()
    val mTitle = ObservableField<String>("")
    val content = ObservableField<String>("Alphabets")

    init {
        mLive.set(Singleton.isLive)
    }

    fun onClickAction(view: View?) {
        getNavigator().onClickView(view)
    }
}