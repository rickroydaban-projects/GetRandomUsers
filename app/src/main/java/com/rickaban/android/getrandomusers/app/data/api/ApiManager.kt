package com.rickaban.android.getrandomusers.app.data.api

import com.rickaban.android.getrandomusers.app.ui.AppViewModel
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import com.rickaban.android.getrandomusers.feature.random.data.api.RandomApi
import com.rickaban.android.getrandomusers.feature.random.domain.RandomRepository

class ApiManager{
    companion object{
        private lateinit var instance: ApiManager
        val RandomRepository.randomApi: RandomApi
            get() = instance.randomApi

        fun init(
            baseUrl: String,
            isNetworkEnabled: Boolean
        ){
            instance = ApiManager()
            instance.reset(baseUrl, isNetworkEnabled)
        }

        fun AppViewModel.resetApi(
            baseUrl: String,
            isNetworkEnabled: Boolean
        ){
            instance.reset(baseUrl, isNetworkEnabled)
        }
        
    }

    private fun reset(
        baseUrl: String,
        isNetworkEnabled: Boolean
    ){
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                if(isNetworkEnabled){
                    defaultClient(baseUrl)
                }else{
                    noNetworkClient()
                }
            )
            .build()
            
        //reset di
        randomApi = retrofit.create(RandomApi::class.java)
    }

    private fun defaultClient(
        baseUrl: String,
    ) = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            }
        )
        .addInterceptor { chain ->
            val builder = chain.request().newBuilder()
//            builder.addHeader("Authorization", "Bearer $token")
            chain.proceed(builder.build())
        }
        .connectTimeout(12, TimeUnit.SECONDS)
        .readTimeout(12, TimeUnit.SECONDS)
        .build()

    private fun noNetworkClient() = OkHttpClient.Builder()
        .addInterceptor { chain ->
            Response.Builder()
                .request(chain.request())
                .protocol(Protocol.HTTP_1_1)
                .code(503) // Service Unavailable
                .message("Network unavailable")
                .body("".toResponseBody("text/plain".toMediaTypeOrNull()))
                .build()
        }
        .build()
        
    //di properties
    private lateinit var randomApi: RandomApi
}