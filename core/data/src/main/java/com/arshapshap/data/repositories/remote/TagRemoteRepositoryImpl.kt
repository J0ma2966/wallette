package com.arshapshap.data.repositories.remote

import com.arshapshap.common.domain.models.Tag
import com.arshapshap.common.domain.models.network.BasicResult
import com.arshapshap.core_network.data.services.TagsApiService
import com.arshapshap.data.managers.interfaces.TagRemoteRepository
import com.arshapshap.data.mappers.BasicResultMapper
import com.arshapshap.data.mappers.TagMapper
import javax.inject.Inject

class TagRemoteRepositoryImpl @Inject constructor(
    private val remoteSource: TagsApiService,
    private val mapper: TagMapper,
    private val resultMapper: BasicResultMapper
): TagRemoteRepository {

    override suspend fun createTagRemote(tag: Tag): BasicResult {
        val model = mapper.mapToCreatingModel(tag)
        val response = remoteSource.createTag(model)
        return resultMapper.map(response)
    }

    override suspend fun updateTagRemote(tag: Tag): BasicResult {
        val model = mapper.mapToEditingModel(tag)
        val response = remoteSource.updateTag(model)
        return resultMapper.map(response)
    }

    override suspend fun deleteTagRemote(tag: Tag): BasicResult {
        val response = remoteSource.deleteTagById(tag.id)
        return resultMapper.map(response)
    }
}