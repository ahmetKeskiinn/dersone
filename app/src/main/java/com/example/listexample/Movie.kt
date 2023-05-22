package com.example.listexample

data class Movie(
    val movieItem: List<MovieItem>? = null,
    val id: Int? = null
) : java.io.Serializable