package com.alvaro.civitatis.model.remote.data

data class Events(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)