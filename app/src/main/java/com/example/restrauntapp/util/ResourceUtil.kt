package com.example.restrauntapp.util

import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.example.restrauntapp.BuildConfig
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

object ResourceUtil {

    fun getJsonString(fileName: String, context: Context): String? {
        try {
            val inputStream = context.assets.open(fileName)
            val bufferedReader = BufferedReader(InputStreamReader(inputStream))
            val stringBuilder = StringBuilder()
            var line = bufferedReader.readLine()
            while (line != null) {
                stringBuilder.append(line)
                line = bufferedReader.readLine()
            }
            inputStream.close()
            bufferedReader.close()
            return stringBuilder.toString()
        } catch (e: IOException) {
            if (BuildConfig.DEBUG)
                Log.e("ERROR ",""+e)
        }
        return null
    }

    fun hideKeyboard(view: View) {

        //Set up touch listener for non-text box views to hide keyboard.
        if (view !is EditText) {
            view.setOnTouchListener { v, event ->
                hideSoftKeyBoard(view.context, v)
                false
            }
        }

        //If a layout container, iterate over children and seed recursion.
        if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                val innerView = view.getChildAt(i)
                hideKeyboard(innerView)
            }
        }
    }

    /**
     * method to hide soft keyboard
     *
     */
    fun hideSoftKeyBoard(context:Context, view: View?) {
        if (context == null || view == null) return
        val imm = context!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        try {
            imm?.hideSoftInputFromWindow(view.windowToken, 0)
        } catch (e: Exception) {
        }

    }
}