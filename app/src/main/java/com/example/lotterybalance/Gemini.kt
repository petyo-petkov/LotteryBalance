package com.example.lotterybalance

import com.google.ai.client.generativeai.GenerativeModel

suspend fun Gemini() {

    val generativeModel = GenerativeModel(
        modelName = "gemini-pro",
        apiKey = com.example.lotterybalance.BuildConfig.apiKey
    )

    val prompt = "Write a story about a magic backpack."
    val response = generativeModel.generateContent(prompt)

   // response.text?.let { Log.i("prompt", it, ) }

}
