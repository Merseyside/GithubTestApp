package com.example.githublibrary.net

import android.content.Context
import android.util.Log
import com.example.githublibrary.R
import com.example.githublibrary.URIQueryBuilder
import com.example.githublibrary.entity.response.DetailResponse
import com.example.githublibrary.entity.response.RepoResponse
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.reactivex.Single
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class GithubResponseCreator(context : Context) {

    private val TAG = javaClass.simpleName

    private val githubRest: GithubRest

    private class ResponseInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val response = chain.proceed(chain.request())
            val modified = response.newBuilder()
                    .addHeader("Content-Type", "application/json; charset=utf-8")
                    .addHeader("Accept", "application/vnd.github.v3+json")
                    .build()

            return modified
        }
    }

    init {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.HEADERS

        val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(ResponseInterceptor())
                .connectTimeout(8, TimeUnit.SECONDS)
                .writeTimeout(8, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build()

        val retrofit = Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(context.getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create(createConverterFactory()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        githubRest = retrofit.create(GithubRest::class.java)
    }

    private fun createConverterFactory(): Gson {
        return GsonBuilder()
            .setLenient()
            .create()
    }

    fun getPage(limit: Int, page: Int, findString: String?) : Single<List<RepoResponse>> {

        val uriQueryBuilder  = URIQueryBuilder()
        uriQueryBuilder.addParam("page", page.toString())
        uriQueryBuilder.addParam("per_page", limit.toString())

        return if (!findString.isNullOrEmpty()) {
            var formatString = findString.replace(" ", "+")
            formatString = "$formatString+in:name"

            uriQueryBuilder.addParam("q", formatString)

            githubRest.getSearchRepositories(uriQueryBuilder.getParams())
                    .map {
                        it.items
                    }
        } else {
            githubRest.getRepositories(uriQueryBuilder.getParams())
        }
    }

    fun getRepoDetails(fullName: String) : Single<DetailResponse> {
        val strs = fullName.split("/")

        return githubRest.getRepoDetails(strs[0], strs[1])
    }
}