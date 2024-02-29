package com.arshapshap.wallette.core.data.managers.interfaces

import com.arshapshap.wallette.core.common.domain.models.Tag
import com.arshapshap.wallette.core.common.domain.models.network.BasicResult

interface TagRemoteRepository {

    suspend fun createTagRemote(tag: Tag): BasicResult

    suspend fun updateTagRemote(tag: Tag): BasicResult

    suspend fun deleteTagRemote(tag: Tag): BasicResult
}