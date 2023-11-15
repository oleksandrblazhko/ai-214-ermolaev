package com.example.tspp.models


data class Responseforthesingvideos(
    var id:Int,
    var textResponse:String = "",
    var request: Requestforthesingvideos
)
