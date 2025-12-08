package com.leandro1995.seito.retrofit.response

import com.google.gson.annotations.SerializedName
import com.leandro1995.seito.retrofit.config.Setting

class ChatResponse(
    @SerializedName(Setting.SOLUTION) val solutionResponse: SolutionResponse = SolutionResponse()
)