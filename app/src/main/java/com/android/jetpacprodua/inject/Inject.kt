package com.android.jetpacprodua.inject

import android.content.Context
import androidx.viewbinding.BuildConfig
import com.android.jetpacprodua.MovieTvApplication
import com.android.jetpacprodua.data.source.remote.api.ApiService
import com.android.jetpacprodua.utils.Benefit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Inject {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): MovieTvApplication {
        return app as MovieTvApplication
    }

    @Provides
    fun provideBaseUrl() = Benefit.BASE_URL

    @Singleton
    @Provides
    fun forOkHttpClient() =
        if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else {
        OkHttpClient
            .Builder()
            .build()
    }

    @Singleton
    @Provides
    fun forApiService(okHttpClient: OkHttpClient): ApiService {
        return Retrofit.Builder()
            .baseUrl(Benefit.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}