package com.gatebot.app.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.databinding.ObservableArrayList
import com.gatebot.app.R
import com.gatebot.app.component.module.BaseSpinnerBinding
import com.gatebot.app.data.remote.model.unit.Units
import com.gatebot.app.data.remote.model.vehicle.RegisterVehicle
import com.gatebot.app.data.remote.model.vehicle.VehicleData
import com.google.android.material.bottomsheet.BottomSheetDialog




class BottomAlertRegister(private val context: Context, private val vehicleData: VehicleData,
                          private val mUnitsList: ObservableArrayList<Units>,
                          val callBack: (data: RegisterVehicle) -> Unit) {

    private val mBottomSheetDialog = BottomSheetDialog(context)
    private val unit = ArrayList<String>()
    private val unitIds = ArrayList<Int>()
    private var unitName = ""
    private var unitId = 0

    init {
        showOkCancelDialog()
    }


    @SuppressLint("InflateParams")
    fun showOkCancelDialog() {
        val sheetView = LayoutInflater.from(context).inflate(R.layout.dialog_register, null)

        val titleTv = sheetView.findViewById(R.id.title_tv) as TextView
        val okActionTv = sheetView.findViewById(R.id.ok_action_tv) as TextView
        val cancelActionTv = sheetView.findViewById(R.id.cancel_action_tv) as TextView
        val mobileNoEd = sheetView.findViewById(R.id.mobile_no_ed) as EditText
        val unitNameSpr = sheetView.findViewById(R.id.unit_spinner) as Spinner
        val vehicleNoEd = sheetView.findViewById(R.id.vehicle_no_ed) as EditText
        vehicleNoEd.setText(vehicleData.licensePlateNumber)

        okActionTv.setOnClickListener {
            val mobileNo = mobileNoEd.text?.toString()?.trim()
            val vehicleNo = vehicleNoEd.text?.toString()?.trim()
            when {
                vehicleNo.isNullOrEmpty() ->  showToast("Vehicle No should not be empty")
                unitId == 0 -> showToast("Please select Unit")
                //mobileNo.isNullOrEmpty() -> mobileNo = null
                (mobileNo?.length ?: 0) > 0 &&
                        (mobileNo?.length ?: 0) < 10 -> showToast("Please enter valid mobile no")
                else -> {
                    val data = RegisterVehicle(
                        licensePlateNumber = vehicleNo,
                        unitName = unitName,
                        unitId = unitId,
                        mobileNo = if (mobileNo.isNullOrEmpty()) "" else mobileNo,
                        vehicleLogId = vehicleData.logId
                    )
                    callBack(data)
                    dismiss()
                }
            }
        }

        cancelActionTv.setOnClickListener {
            dismiss()
        }


        val baseSpnner = BaseSpinnerBinding()
        baseSpnner.setAdapter(unitNameSpr, R.layout.item_dropdown, R.id.drop_down_tv,
            R.layout.dropdown_tv_layout, R.id.item_drop_down_tv,"", getUnitList(),
            0, "")

        unitNameSpr.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val name = unit[position]
                val uId = unitIds[position]
                unitName = name
                unitId = uId
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }

        mBottomSheetDialog.setContentView(sheetView)
        mBottomSheetDialog.show()
    }

    private fun getUnitList(): ArrayList<String> {
        unit.clear()
        unitIds.clear()
        for (un in mUnitsList){
            val label = un.label
            val value = un.value
            if (!label.isNullOrEmpty() && value != null) {
                unit.add(label)
                unitIds.add(value)
            }
        }
        return unit
    }

    private fun showToast(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
    }


    fun dismiss() {
        mBottomSheetDialog.dismiss()
    }
}