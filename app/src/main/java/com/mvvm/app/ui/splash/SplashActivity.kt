package com.mvvm.app.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.mvvm.app.BR
import com.mvvm.app.R
import com.mvvm.app.base.BaseActivity
import com.mvvm.app.base.BaseNavigator
import com.mvvm.app.data.local.AppPreferences
import com.mvvm.app.data.local.Singleton
import com.mvvm.app.databinding.SplashActivityBinding
import com.mvvm.app.ui.home.HomeActivity
import com.mvvm.app.utilities.Constants


class SplashActivity : BaseActivity<SplashActivityBinding, SplashViewModel>(), BaseNavigator {

    private lateinit var viewModel: SplashViewModel
    private lateinit var mActivityBinding: SplashActivityBinding
    private lateinit var appPreferences: AppPreferences

    override fun getBindingVariable(): Int {
        return BR.mSplashViewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.splash_activity
    }

    override fun getViewModel(): SplashViewModel {
        viewModel = ViewModelProvider(this)[SplashViewModel::class.java]
        return viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityBinding = getViewDataBinding()
        viewModel.setNavigator(this)
        appPreferences = AppPreferences()
        Singleton.initPrefData()

        Handler(Looper.getMainLooper()).postDelayed({
            goTo(getNextClass(), null)
        }, 2000)
    }

    private fun getNextClass(): Class<*> {
        val isLogin = appPreferences.getBool(Constants.PREF_IS_LOGIN)
        return if (isLogin) HomeActivity::class.java else HomeActivity::class.java
    }


    override fun onClickView(v: View?) {

    }


    override fun goTo(clazz: Class<*>, mExtras: Bundle?) {
        val intent = Intent(this, clazz)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        overridePendingTransition(R.anim.anim_enter, R.anim.anim_exit)
        finish()
    }
}