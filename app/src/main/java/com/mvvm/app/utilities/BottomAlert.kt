package com.mvvm.app.utilities

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.mvvm.app.R

class BottomAlert(private val context: Context) {

    private var dialogListener: OnAlertClickListener? = null
    private val mBottomSheetDialog = BottomSheetDialog(context)
    private val autoDismissDuration: Long = 5000


    @SuppressLint("InflateParams")
    fun showOkCancelDialog(msg: String) {
        val sheetView = LayoutInflater.from(context).inflate(R.layout.alert_bottom_layout, null)
        val rootLayoutRl = sheetView.findViewById(R.id.root_layout_rl) as RelativeLayout
        val titleTv = sheetView.findViewById(R.id.title_tv) as TextView
        val okActionTv = sheetView.findViewById(R.id.ok_action_tv) as TextView
        val cancelActionTv = sheetView.findViewById(R.id.cancel_action_tv) as TextView

        titleTv.text = msg

        okActionTv.setOnClickListener {
            if (dialogListener != null) {
                dialogListener!!.onPositive()
            }
            dismiss()
        }

        cancelActionTv.setOnClickListener {
            if (dialogListener != null) {
                dialogListener!!.onNegative()
            }
        }
        mBottomSheetDialog.setContentView(sheetView)
        mBottomSheetDialog.show()
    }

    @SuppressLint("InflateParams")
    fun showOkButton(msg: String) {
        val sheetView = LayoutInflater.from(context).inflate(R.layout.alert_bottom_layout, null)
        val titleTv = sheetView.findViewById(R.id.title_tv) as TextView
        val okActionTv = sheetView.findViewById(R.id.ok_action_tv) as TextView
        val cancelActionTv = sheetView.findViewById(R.id.cancel_action_tv) as TextView
      //  val cancelActionview = sheetView.findViewById(R.id.cancel_view) as View


        titleTv.text = msg
        cancelActionTv.visibility = View.GONE
     //   cancelActionview.visibility = View.GONE
        okActionTv.setOnClickListener {
            if (dialogListener != null) {
                dialogListener!!.onPositive()
            }
            dismiss()
        }

        mBottomSheetDialog.setContentView(sheetView)
        mBottomSheetDialog.show()
    }

    @SuppressLint("InflateParams")
    fun showOnlyTextDialog(msg: String, isAutoDismiss: Boolean) {
        val sheetView = LayoutInflater.from(context).inflate(R.layout.alert_bottom_layout, null)
        val titleTv = sheetView.findViewById(R.id.title_tv) as TextView
        val actionContainer = sheetView.findViewById(R.id.action_container) as RelativeLayout
        titleTv.text = msg
        actionContainer.visibility = View.GONE

        mBottomSheetDialog.setContentView(sheetView)
        mBottomSheetDialog.show()

        if (isAutoDismiss){
            autoDismiss()
        }
    }

    private fun autoDismiss() {
        val handler = Handler()
        handler.postDelayed({
            dismiss()
        }, autoDismissDuration)
    }

    fun dismiss() {
        mBottomSheetDialog.dismiss()
    }

    fun setOnAlertClickListener(listener: OnAlertClickListener) {
        dialogListener = listener
    }


    interface OnAlertClickListener {
        fun onPositive()
        fun onNegative()
    }
}