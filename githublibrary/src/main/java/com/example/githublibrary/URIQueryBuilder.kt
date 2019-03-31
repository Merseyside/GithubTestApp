package com.example.githublibrary

import android.util.Log
import java.io.UnsupportedEncodingException
import java.net.URLEncoder
import java.util.LinkedHashMap

class URIQueryBuilder {

    private val TAG = this.javaClass.simpleName

    private var params: MutableMap<String, String> = LinkedHashMap()

    val string: String?
        get() {
            val builder = StringBuilder()
            for ((key, value) in params) {
                try {
                    if (builder.length != 0) builder.append('&')
                    builder.append(URLEncoder.encode(key, "UTF-8"))
                    builder.append('=')
                    builder.append(URLEncoder.encode(value, "UTF-8"))
                } catch (e: UnsupportedEncodingException) {
                    Log.e(TAG, e.toString())
                    return null
                }

            }
            return builder.toString()
        }

    fun addParam(key: String, value: String) {
        params[key] = value
    }

    fun addParams(params: Map<String, String>) {
        this.params.putAll(params)
    }

    fun clear() {
        params.clear()
    }

    fun getParams(): Map<String, String> {
        return params
    }
}