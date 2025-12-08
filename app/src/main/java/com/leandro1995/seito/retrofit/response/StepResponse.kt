package com.leandro1995.seito.retrofit.response

import com.google.gson.annotations.SerializedName
import com.leandro1995.seito.model.entity.Solution
import com.leandro1995.seito.retrofit.config.Setting

class StepResponse(
    @SerializedName(Setting.DESCRIPTION) val description: String = "",
    @SerializedName(Setting.OPERATION) val operation: String = "",
    @SerializedName(Setting.RESULT) val result: String = ""
) {
    fun toSolution() = Solution(description = description, operation = operation, result = result)
}