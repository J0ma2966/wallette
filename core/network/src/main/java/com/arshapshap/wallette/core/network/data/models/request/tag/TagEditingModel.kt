package com.arshapshap.wallette.core.network.data.models.request.tag

import com.google.gson.annotations.SerializedName

data class TagEditingModel(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("color")
    val color: String,
)
