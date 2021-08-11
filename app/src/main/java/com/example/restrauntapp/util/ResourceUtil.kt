package com.example.restrauntapp.util

import android.content.Context
import android.util.Log
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
}