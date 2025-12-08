package com.leandro1995.seito.retrofit.response

import com.google.gson.annotations.SerializedName
import com.leandro1995.seito.retrofit.config.Setting

class SolutionResponse(
    @SerializedName(Setting.STEPS) val stepArrayList: ArrayList<StepResponse> = arrayListOf()
)