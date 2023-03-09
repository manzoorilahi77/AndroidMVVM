package com.mvvm.app.utilities

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Resources
import android.net.Uri
import android.util.Log
import android.widget.Toast
import com.mvvm.app.MApplication
import com.mvvm.app.R
import java.io.IOException
import java.io.InputStream
import java.text.SimpleDateFormat
import java.util.*


object CommonUtils {

    fun startWhatsApp(context: Context?, phnNumber: String?) {
        if (context != null) {
            val url = "https://api.whatsapp.com/send?phone=$phnNumber"
            try {
                val pm = context.packageManager
                pm.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES)
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(url)
                context.startActivity(i)
            } catch (e: PackageManager.NameNotFoundException) {
                Toast.makeText(
                    context,
                    "Whatsapp app not installed in your phone",
                    Toast.LENGTH_SHORT
                ).show()
                e.printStackTrace()
            }
        }
    }

    fun shareViaCommonApp(context: Context?, link: String?) {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_TEXT, link)
        context?.startActivity(Intent.createChooser(shareIntent, "Share via"))
        //context?.startActivityForResult(Intent.createChooser(shareIntent, ""), shareIntentCode)
    }

    fun getTimestamp(time: String?): Long {
        val sdf = SimpleDateFormat("EEE, dd MMM yyyy hh:mm:ss Z", Locale.ENGLISH) // Mon, 04 Jul 2022 10:08:28 GMT
        try {
            if (!time.isNullOrEmpty()) {
                return sdf.parse(time).time
            }
        }  catch (ex: java.lang.Exception) {
            Log.e("formatTimeAgo", ex.toString())
        }
        return 0
    }

    fun getTimeAgo(createdAt: String, dateFormat: String): String {
        // Note : date1 must be in   "yyyy-MM-dd hh:mm:ss"   format
        var conversionTime = ""
        try {

            val sdf = SimpleDateFormat(dateFormat, Locale.ENGLISH)

            val time = sdf.parse(createdAt).time
            val now = System.currentTimeMillis()
            Log.e("createdAt", createdAt)
            Log.e("createdAt", "$time")
            val context = MApplication.getInstance()?.applicationContext
            if (context != null) {
                conversionTime =
                    TimeAgo(context).timeAgo(time)
            }

        } catch (ex: java.lang.Exception) {
            Log.e("formatTimeAgo", ex.toString())
        }
        Log.e("createdAt", conversionTime)
        return conversionTime
    }


    fun getHomeTileFile(resource: Resources): String? {
        val json = R.raw.alphabets
        return inputStreamToString(resource.openRawResource(json))
    }


    fun inputStreamToString(inputStream: InputStream): String? {
        return try {
            val bytes = ByteArray(inputStream.available())
            inputStream.read(bytes, 0, bytes.size)
            String(bytes)
        } catch (e: IOException) {
            null
        }

    }
}