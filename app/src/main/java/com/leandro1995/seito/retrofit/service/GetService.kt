package com.leandro1995.seito.retrofit.service

import com.leandro1995.seito.model.entity.Solution
import com.leandro1995.seito.retrofit.config.RetrofitConfig

class GetService {

    companion object {
        suspend fun getSolution(
            problemText: String, callBack: (ArrayList<Solution>) -> Unit, error: () -> Unit
        ) {

            try {
                RetrofitConfig.getApi.solution(problemText = problemText).let {

                    val solutionArrayList = arrayListOf<Solution>()

                    if (it.isSuccessful) {
                        it.body()?.solutionResponse?.stepArrayList?.forEach { step ->
                            solutionArrayList.add(step.toSolution())
                        }

                        callBack(solutionArrayList)
                    } else {
                        error()
                    }
                }
            } catch (_: Exception) {
                error()
            }
        }
    }
}