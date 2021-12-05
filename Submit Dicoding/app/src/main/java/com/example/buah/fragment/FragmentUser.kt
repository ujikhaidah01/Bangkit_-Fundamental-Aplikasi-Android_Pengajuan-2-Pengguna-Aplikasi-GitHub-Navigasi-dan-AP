package com.example.buah.fragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.buah.recyclerView.UserData
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject
import java.lang.Exception

class FragmentUser : ViewModel() {

    val dataUser = MutableLiveData<ArrayList<UserData>>()
    fun getUser(): LiveData<ArrayList<UserData>> { return dataUser }
    fun setUser(id: String) {
        val listData = ArrayList<UserData>()
        val url = "https://api.github.com/search/users?q=$id"
        val client = AsyncHttpClient()
        client.addHeader("Authorization", "token 4bc15f1cc15de8433a262970cb23d1ad0681d3a8")
        client.addHeader("User-Agent", "request")
        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>,
                responseBody:
                ByteArray
            ) {
                try {
                    val result = String(responseBody)
                    val responseObject = JSONObject(result)
                    val item = responseObject.getJSONArray("items")

                    for (i in 0 until item.length()) {
                        val user = item.getJSONObject(i)
                        val dataUser = UserData()
                        dataUser.login = user.getString("login")
                        dataUser.logo = user.getString("avatar_url")
                        listData.add(dataUser)
                    }
                    dataUser.postValue(listData)
                }
                catch (e: Exception) {
                    Log.d("Exception", e.message.toString())
                }

            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>,
                responseBody: ByteArray,
                error: Throwable
            ) {
                val errorMessage = when (statusCode) {
                    401 -> "$statusCode : Bad Request"
                    403 -> "$statusCode : Forbidden"
                    404 -> "$statusCode : Not Found"
                    else -> "$statusCode : ${error.message}"
                }
                Log.d("onFailure", error.message.toString())
            }
        })
    }
}
