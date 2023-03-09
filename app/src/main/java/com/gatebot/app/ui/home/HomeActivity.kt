package com.gatebot.app.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.gatebot.app.BR
import com.gatebot.app.R
import com.gatebot.app.base.BaseActivity
import com.gatebot.app.base.BaseNavigator
import com.gatebot.app.data.local.AppPreferences
import com.gatebot.app.databinding.ActivityHomeBinding

class HomeActivity: BaseActivity<ActivityHomeBinding, HomeVM>(), BaseNavigator, IHomeInterface {

    private lateinit var viewModel: HomeVM
    private lateinit var mActivityBinding: ActivityHomeBinding
    private lateinit var appPreferences: AppPreferences
    private var isOnCreate = true

    override fun getBindingVariable(): Int {
        return BR.homeVM
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_home
    }

    override fun getViewModel(): HomeVM {
        viewModel = ViewModelProvider(this)[HomeVM::class.java]
        return viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityBinding = getViewDataBinding()
        viewModel.setNavigator(this)
        appPreferences = AppPreferences()
        isOnCreate = true
        observer()
    }

    private fun observer() {

    }

    override fun onClickView(v: View?) {
        when(v?.id) {
            //R.id.content_tv -> {}
        }

    }

    override fun goTo(clazz: Class<*>, mExtras: Bundle?) {

    }

    private fun startFragment(fragment: Fragment, bundle: Bundle?, tag: String) {
        if (isSameFragmentAttached(tag)) {
            viewModel.mTitle.set("")
            val transaction = supportFragmentManager.beginTransaction()
            fragment.arguments = bundle

            transaction.setCustomAnimations(
                R.anim.anim_enter, R.anim.anim_exit,
                R.anim.anim_pop_enter, R.anim.anim_pop_exit
            )
            transaction.replace(R.id.full_menu_fl, fragment, tag)
            transaction.addToBackStack(tag)
            transaction.commit()
        }
        viewModel.isFullMenuScreensVisible.set(true)
    }

    private fun addFragment(fragment: Fragment, bundle: Bundle?, tag: String) {
        val transaction = supportFragmentManager.beginTransaction()
        fragment.arguments = bundle

        transaction.setCustomAnimations(
            R.anim.anim_enter, R.anim.anim_exit,
            R.anim.anim_pop_enter, R.anim.anim_pop_exit
        )
        transaction.add(R.id.full_menu_fl, fragment, tag)
        transaction.addToBackStack(tag)
        transaction.commit()
        viewModel.isFullMenuScreensVisible.set(true)
    }

    private fun clearBackStack() {
        val fragmentManager = supportFragmentManager
        val stack = fragmentManager.backStackEntryCount
        for (i in 0 until stack) {
            fragmentManager.popBackStack()
        }
        viewModel.isFullMenuScreensVisible.set(false)
    }

    override fun setScreenTitle(title: String?) {

    }

    override fun clearBackStack(clearAll: Boolean) {

    }

    override fun onRefreshBottomNav(isShow: Boolean) {

    }

    override fun onSinglePopBackStack() {
        viewModel.isFullMenuScreensVisible.set(false)
    }

}