package com.example.tspp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class User(
    var userName:String = "",
    var userMessage:String = "") : Parcelable

