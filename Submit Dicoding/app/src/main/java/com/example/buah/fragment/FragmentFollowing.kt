package com.example.buah.fragment

import android.service.autofill.UserData
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONArray
import java.lang.Exception

class FragmentFollowing : ViewModel() {

    val userdatafollowing = MutableLiveData<ArrayList<com.example.buah.recyclerView.UserData>>()
    fun getFollowing(): MutableLiveData<ArrayList<com.example.buah.recyclerView.UserData>> { return userdatafollowing }
    fun setFollowing(username: String) {
        val listData = ArrayList<com.example.buah.recyclerView.UserData>()
        val url = "https://api.github.com/users/$username/followers"
        val client = AsyncHttpClient()

        client.addHeader("Authorization", "token 4bc15f1cc15de8433a262970cb23d1ad0681d3a8")
        client.addHeader("User-agent", "request")
        client.get(url, object: AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>,
                responseBody: ByteArray) {
                try {
                    val result = String(responseBody)
                    val responseArray = JSONArray(result)
                    for (i in 0 until responseArray.length()) {
                        val following = responseArray.getJSONObject(i)
                        val dataUser = com.example.buah.recyclerView.UserData()
                        dataUser.apply {
                            dataUser.login = following.getString("login")
                            dataUser.logo = following.getString("avatar_url")
                        }
                        listData.add(dataUser)
                    }
                    userdatafollowing.postValue(listData)

                } catch (e: Exception) {
                    Log.d("Exception", e.message.toString())
                }
            }

            override fun onFailure(statusCode: Int, headers: Array<out Header>, responseBody: ByteArray, error: Throwable) {
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