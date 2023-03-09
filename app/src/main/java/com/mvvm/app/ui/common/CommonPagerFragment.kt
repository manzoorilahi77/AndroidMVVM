package com.mvvm.app.ui.common

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.mvvm.app.R
import com.mvvm.app.databinding.AlphabetsPagerItemBinding
import com.mvvm.app.databinding.FragmentCommonPagerBinding
import com.mvvm.app.BR
import com.mvvm.app.base.BaseFragment
import com.mvvm.app.base.BaseNavigator
import com.mvvm.app.base.adapter.BaseRecyclerViewAdapter
import com.mvvm.app.base.adapter.OnDataBindCallback
import com.mvvm.app.data.remote.model.learnings.Alphabet
import com.mvvm.app.ui.home.IHomeInterface

class CommonPagerFragment : BaseFragment<FragmentCommonPagerBinding, CommonPagerVM>(),
    BaseNavigator {
    private lateinit var mAlphabetsAdapter: BaseRecyclerViewAdapter<Alphabet, AlphabetsPagerItemBinding>
    private lateinit var iHomeInterface: IHomeInterface
    private lateinit var mContext: Context

    override val bindingVariable: Int
        get() = BR.mCommonPagerVM
    override val layoutId: Int
        get() = R.layout.fragment_common_pager
    override val viewModel: CommonPagerVM
        get() = ViewModelProvider(this).get(CommonPagerVM::class.java)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            mContext = context
            iHomeInterface = context as IHomeInterface
        } catch (e: ClassCastException) {
            throw ClassCastException("${e.message}")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = this.arguments
        if (bundle != null) {
            //screenKEey = bundle.getString(Constants.NAV_KEY, "")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        viewModel.setNavigator(this)

        initAlphabets()
        observeData()

        return viewDataBinding?.root
    }

    private fun observeData() {
        viewModel.mAlphabetLiveData.observeEvent(this, Observer{
            if (it != null) {
                updateAlphabets()
            }
        })
    }

    private fun initAlphabets() {
        mAlphabetsAdapter = BaseRecyclerViewAdapter(R.layout.alphabets_pager_item, BR.mAlphabet,
            ArrayList(), object : OnDataBindCallback<AlphabetsPagerItemBinding> {
                override fun onItemClick(view: View, position: Int, v: AlphabetsPagerItemBinding) {
                    /*when(view.id) {
                        R.id.activate_device -> {
                            setActivityResult(Constants.NAV_KEY_CHECKIN)
                        }
                    }*/
                }

                override fun onDataBind(v: AlphabetsPagerItemBinding, onClickListener: View.OnClickListener) {

                }

            })

        val pager = viewDataBinding?.pager
        pager?.adapter = mAlphabetsAdapter
        initData()
        updateAlphabets()

        pager?.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                viewModel.setSelectedArea(position)
                updateAlphabets()
            }

            override fun onPageScrollStateChanged(state: Int) {

            }

        })
    }

    private fun updateAlphabets() {
        val pager = viewDataBinding?.pager
        val position = viewModel.mAlphabetPosition.get()
        pager?.setCurrentItem(position, true)
        val alphabets = viewModel.mLearningsResponse.get()?.alphabets
        if (!alphabets.isNullOrEmpty()) {
            viewModel.prevActive.set(position != 0)
            viewModel.nextActive.set(position != (alphabets.size - 1))
        }
    }

    private fun initData() {
        val alphabets = viewModel.mLearningsResponse.get()?.alphabets
        if (!alphabets.isNullOrEmpty()) {
            viewModel.mAlphabets.clear()
            viewModel.mAlphabets.addAll(alphabets)
            mAlphabetsAdapter.clearAndAddDataSet(alphabets)
        }
    }

    override fun onClickView(v: View?) {
        when(v?.id) {
            R.id.prev -> {
                if (viewModel.prevActive.get()) {
                    viewModel.updateSelection(false)
                }
            }
            R.id.next -> {
                if (viewModel.nextActive.get()) {
                    viewModel.updateSelection(true)
                }
            }
            R.id.play -> {}
        }
    }

    override fun goTo(clazz: Class<*>, mExtras: Bundle?) {

    }
}