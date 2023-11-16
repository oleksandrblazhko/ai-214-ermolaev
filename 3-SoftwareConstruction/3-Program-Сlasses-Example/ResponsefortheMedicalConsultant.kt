package com.example.tspp.models

data class ResponsefortheMedicalConsultant(
    var id:Int,
    var textResponse:String = "",
    var request: RequestfortheMedicalConsultant
)
