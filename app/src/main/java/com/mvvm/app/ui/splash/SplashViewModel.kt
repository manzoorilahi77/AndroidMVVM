package com.mvvm.app.ui.splash

import android.app.Application
import android.view.View
import com.mvvm.app.base.BaseNavigator
import com.mvvm.app.base.BaseViewModel

class SplashViewModel(application: Application) : BaseViewModel<BaseNavigator>(application) {

    fun onClickAction(view: View?) {
        getNavigator().onClickView(view)
    }
}