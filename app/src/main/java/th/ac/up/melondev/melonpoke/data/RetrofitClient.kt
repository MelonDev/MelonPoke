package th.ac.up.melondev.melonpoke.data

import com.google.gson.GsonBuilder
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient {

    companion object {
        fun <T> createService(t: Class<T>?): T = RetrofitClient().createService(t)
    }

    private val okHttpClient by lazy { OkHttpClient() }

    private val retrofit: Retrofit by lazy {
        initialBuilder().client(initialClient()).build()
    }

    private fun initialBuilder() :Retrofit.Builder = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))

    private fun initialClient() :OkHttpClient = okHttpClient.newBuilder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        //.addInterceptor(initialLoggingInterceptor())
        .dispatcher(Dispatcher().apply { maxRequests = 1 })
        .build()

    private fun initialLoggingInterceptor():HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    fun <T> createService(tClass: Class<T>?): T = retrofit.create(tClass)


}