package com.arshapshap.wallette.core.data.repositories

import com.arshapshap.wallette.core.common.data.TokenManager
import com.arshapshap.wallette.core.common.domain.models.Tag
import com.arshapshap.wallette.core.common.domain.repositories.TagRepository
import com.arshapshap.wallette.core.db.dao.TagDao
import com.arshapshap.wallette.core.data.managers.SyncQueueManager
import com.arshapshap.wallette.core.data.managers.enums.RequestType
import com.arshapshap.wallette.core.data.mappers.TagMapper
import javax.inject.Inject

class TagRepositoryImpl @Inject constructor(
    private val localSource: TagDao,
    private val tokenManager: TokenManager,
    private val syncQueueManager: SyncQueueManager,
    private val mapper: TagMapper
): TagRepository {

    override suspend fun createTag(tag: Tag) {
        val local = mapper.map(tag)
        val id = localSource.addTag(local)

        if (!tokenManager.isAuthorized()) return

        syncQueueManager.addRequest(RequestType.Create, tag.copy(id = id))
        syncQueueManager.trySynchronize()
    }

    override suspend fun updateTag(tag: Tag) {
        val local = mapper.map(tag)
        localSource.updateTag(local)

        if (!tokenManager.isAuthorized()) return

        syncQueueManager.addRequest(RequestType.Update, tag)
        syncQueueManager.trySynchronize()
    }

    override suspend fun deleteTag(tag: Tag) {
        val local = mapper.map(tag)
        localSource.deleteTag(local)

        if (!tokenManager.isAuthorized()) return

        syncQueueManager.addRequest(RequestType.Delete, tag)
        syncQueueManager.trySynchronize()
    }

    override suspend fun getTags(): List<Tag> {
        val list = localSource.getTags()
        return list.map { mapper.map(it) }
    }
}