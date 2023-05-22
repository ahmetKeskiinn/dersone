package com.example.listexample

data class MovieItem(
    val id: Int? = null,
    val name: String? = null,
    val content: String? = null,
    val isSelected: Boolean? = false
): java.io.Serializable