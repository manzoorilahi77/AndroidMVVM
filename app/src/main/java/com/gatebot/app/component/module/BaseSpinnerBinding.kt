package com.gatebot.app.component.module

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import com.gatebot.app.component.implementaion.ISpinnerBinding
import java.lang.reflect.Method


open class BaseSpinnerBinding : ISpinnerBinding {


    override fun setAdapter(spinner: Spinner, customLayout: Int, customViewId: Int,
                            customDropDownLayout: Int, customDropDownTextViewId: Int,
                            customHint: String?, dataItems: ArrayList<String>?,
                            customSelectedItemPosition: Int?, screen: String?) {
        if (dataItems != null) {

            if (!customHint.isNullOrEmpty() && !dataItems.contains(customHint)) {
                dataItems.add(0, customHint)
            }

            val inflater = LayoutInflater.from(spinner.context)
            spinner.adapter =
                object : ArrayAdapter<String>(spinner.context, customLayout, dataItems) {
                    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                        val view = super.getView(position, convertView, parent)
                        val textView = view.findViewById<TextView>(customViewId)
                        textView.text = dataItems[position]
                        val isSelectedItem = if (customHint != null) {
                            position == 0
                        } else {
                            false
                        }
                        return textView
                    }

                    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
                        super.getView(position, convertView, parent)
                        val view = inflater.inflate(customDropDownLayout, parent, false)
                        val textView = view.findViewById<TextView>(customDropDownTextViewId)
                        textView.text = dataItems[position]

                        view.setOnClickListener{
                            spinner.setSelection(position)
                            hideSpinnerDropDown(spinner)
                        }
                        return view
                    }

                    fun hideSpinnerDropDown(spinner: Spinner?) {
                        try {
                            val method: Method = Spinner::class.java.getDeclaredMethod("onDetachedFromWindow")
                            method.setAccessible(true)
                            method.invoke(spinner)
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }

            if (customSelectedItemPosition != null && spinner.selectedItemPosition != customSelectedItemPosition) {
                spinner.isSelected = false
                spinner.setSelection(customSelectedItemPosition)
            }
        }
    }

    override fun setEnable(spinner: Spinner, enable: Boolean) {
        spinner.isEnabled = enable
    }

}