package com.example.buah.recyclerView

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserData (
        var repository: Int? = 0,
        var followers: Int? = 0,
        var following: Int? = 0,
        var logo: String? = null,
        var name: String? = null,
        var username: String? = null,
        var login: String? = null,
        var location: String? = null
)  : Parcelable