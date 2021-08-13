package com.example.restrauntapp.network.stub

import android.app.Application
import com.example.restrauntapp.util.ResourceUtil
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull

class MockInterceptor(
    private val context : Application
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = Response.Builder()

        when (chain.request().url.encodedPath) {

            "/getRestaurantListData" -> builder.body(
                ResponseBody.create(
                    "application/json".toMediaTypeOrNull(),
                    ResourceUtil.getJsonString(
                        "restauraunt.json",
                        context
                    )!!
                )
            )

            "/getRestaurantDetail" -> builder.body(
                ResponseBody.create(
                    "application/json".toMediaTypeOrNull(),
                    ResourceUtil.getJsonString(
                        "restauraunt.json",
                        context
                    )!!
                )
            )

            else -> return chain.proceed(chain.request())
        }

        return builder
            .request(chain.request())
            .protocol(Protocol.HTTP_2)
            .code(200)
            .message("Mock Response")
            .build()
    }
}