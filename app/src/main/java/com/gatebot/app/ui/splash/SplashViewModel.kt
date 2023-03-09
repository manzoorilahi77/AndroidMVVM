package com.gatebot.app.ui.splash

import android.app.Application
import android.view.View
import com.gatebot.app.base.BaseNavigator
import com.gatebot.app.base.BaseViewModel

class SplashViewModel(application: Application) : BaseViewModel<BaseNavigator>(application) {

    fun onClickAction(view: View?) {
        getNavigator().onClickView(view)
    }
}