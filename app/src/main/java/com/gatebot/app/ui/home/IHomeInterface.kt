package com.gatebot.app.ui.home

interface IHomeInterface {
    fun setScreenTitle(title: String?)
    fun clearBackStack(clearAll: Boolean)
    fun onRefreshBottomNav(isShow: Boolean)
    fun onSinglePopBackStack()
}