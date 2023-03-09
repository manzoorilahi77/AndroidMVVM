package com.mvvm.app.ui.common

import android.app.Application
import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import com.mvvm.app.base.BaseNavigator
import com.mvvm.app.base.BaseViewModel
import com.mvvm.app.data.remote.model.base.BaseResponse
import com.mvvm.app.data.remote.model.learnings.Alphabet
import com.mvvm.app.data.remote.model.learnings.LearningsResponse
import com.mvvm.app.utilities.CommonUtils
import com.mvvm.app.utilities.SingleLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CommonPagerVM(application: Application) : BaseViewModel<BaseNavigator>(application) {

    var mLearningsResponse = ObservableField<LearningsResponse>()
    var mAlphabets = ArrayList<Alphabet>()
    var prevActive = ObservableBoolean(false)
    var nextActive = ObservableBoolean(false)
    var mAlphabetPosition = ObservableInt(0)
    var mAlphabetLiveData = SingleLiveData<Int>()

    init {
        initLearnings()
    }

    fun onClickAction(view: View?) {
        getNavigator().onClickView(view)
    }

    fun initLearnings() {
        val json = CommonUtils.getHomeTileFile(resource)
        val turnsType = object : TypeToken<BaseResponse<LearningsResponse>>() {}.type
        val learnings = Gson().fromJson<BaseResponse<LearningsResponse>>(json, turnsType)
        mLearningsResponse.set(learnings.responseData)
    }

    fun setSelectedArea(position: Int) {
        mAlphabetPosition.set(position)
    }

    fun updateSelection(isPlus: Boolean) {
        val position = mAlphabetPosition.get()
        val size = mAlphabets.size
        val index = if (isPlus) {
            if(position < (size - 1)) position + 1 else position
        } else {
            if(position != 0) position - 1 else position
        }
        setSelectedArea(index)
        mAlphabetLiveData.value = index
    }
}