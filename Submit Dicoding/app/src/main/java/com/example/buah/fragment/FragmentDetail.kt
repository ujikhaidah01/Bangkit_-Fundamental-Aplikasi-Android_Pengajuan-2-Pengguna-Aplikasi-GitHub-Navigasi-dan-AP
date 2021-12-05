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

class FragmentDetail : ViewModel() {

    val userData = MutableLiveData<ArrayList<UserData>>()
    fun getDetail(): LiveData<ArrayList<UserData>> { return userData }
    fun setUserDetail(username: String) {
        val url = "https://api.github.com/users/$username"
        val listData = ArrayList<UserData>()
        val client = AsyncHttpClient()

        client.addHeader("Authorization", "token 4bc15f1cc15de8433a262970cb23d1ad0681d3a8")
        client.addHeader("User-Agent", "request")
        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>,
                responseBody: ByteArray) {
                try {
                    val hasil = String(responseBody)
                    val respon = JSONObject(hasil)
                    val userDetail = UserData()
                    userDetail.apply {
                        login = respon.getString("login")
                        name = respon.getString("name")
                        location = respon.getString("location")
                        repository = respon.getInt("public_repos")
                        followers = respon.getInt("followers")
                        following = respon.getInt("following")
                        logo = respon.getString("avatar_url")
                    }
                    listData.add(userDetail)

                    userData.postValue(listData)
                }
                catch (e: Exception) {
                    Log.d("Exception", e.message.toString())
                }
            }
            override fun onFailure(
                    statusCode: Int,
                    headers: Array<out Header>,
                    responseBody: ByteArray,
                    error: Throwable) {
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
