package cl.jaa.doggyapp.di

import android.util.Log
import cl.jaa.doggyapp.BuildConfig
import cl.jaa.doggyapp.data.api.DogBreedService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val networkModule = module {
    factory { provideOkHttpClient() }
    single { provideRetrofit(get()) }
    factory { provideMeliApi(get()) }

}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit
        .Builder()
        .baseUrl(BuildConfig.URL_DOGS_BREEDS)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()
}

var logging = HttpLoggingInterceptor { message -> Log.i("OkHttp", message) }

fun provideOkHttpClient(): OkHttpClient {
    return if (BuildConfig.DEBUG) {
        logging.level = HttpLoggingInterceptor.Level.HEADERS
        OkHttpClient().newBuilder()
            .addInterceptor(logging)
            .build()
    } else {
        OkHttpClient().newBuilder().build()
    }
}


fun provideMeliApi(retrofit: Retrofit): DogBreedService = retrofit.create(DogBreedService::class.java)
