package com.example.restrauntapp.di

import com.example.restrauntapp.RestaurauntApp
import com.example.restrauntapp.data.remote.ApiManager
import com.example.restrauntapp.data.remote.source.ApiService
import com.example.restrauntapp.network.stub.MockInterceptor
import dagger.Module
import dagger.Provides
import io.reactivex.internal.schedulers.RxThreadFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkResolver {

    @Provides
    fun provideOkHttp(context : RestaurauntApp) : OkHttpClient = OkHttpClient()
                                                                .newBuilder()
                                                                .addInterceptor(MockInterceptor(context))
                                                                .build()

    @Provides
    fun provideRetrofitBuilder(okHttpClient: OkHttpClient) : Retrofit = Retrofit
                                                                .Builder()
                                                                .baseUrl("https://run.mocky.io/v3/")
                                                                .addConverterFactory(GsonConverterFactory.create())
                                                                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                                                                .build()

    @Provides
    fun provideRetrofit(retrofit: Retrofit) = ApiManager(retrofit)

    @Provides
    fun providesApiService(apiManager: ApiManager) : ApiService = apiManager.apiService
}