package com.ajeet.quizapp.api

import com.ajeet.quizapp.ModelClass.QuizModel
import com.ajeet.quizapp.ModelClass.Result
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("/api.php?")
    suspend fun getQuiz(@Query("amount") amount:Int):Response<QuizModel>
}