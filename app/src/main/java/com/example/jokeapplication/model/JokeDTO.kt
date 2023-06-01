package com.example.jokeapplication.model

import java.util.FormattableFlags

data class JokeDTO(
    val category: String,
    val type: String,
    val joke: String,
    val lang: String,
)

