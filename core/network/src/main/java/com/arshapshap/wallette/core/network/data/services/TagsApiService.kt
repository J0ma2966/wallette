package com.arshapshap.wallette.core.network.data.services

import com.arshapshap.wallette.core.network.data.models.request.tag.TagCreatingModel
import com.arshapshap.wallette.core.network.data.models.request.tag.TagEditingModel
import com.arshapshap.wallette.core.network.data.models.response.BasicResponse
import com.arshapshap.wallette.core.network.data.models.response.TagResponse
import retrofit2.http.*

interface TagsApiService {

    @GET("tags/get")
    suspend fun getTags(): ArrayList<TagResponse>

    @GET("tags/get/:id")
    suspend fun getTagById(@Query("id")  id: Long): TagResponse

    @POST("tags/create")
    suspend fun createTag(@Body model: TagCreatingModel): BasicResponse

    @PUT("tags/update")
    suspend fun updateTag(@Body model: TagEditingModel): BasicResponse

    @DELETE("tags/delete/:id")
    suspend fun deleteTagById(@Query("id")  id: Long): BasicResponse
}