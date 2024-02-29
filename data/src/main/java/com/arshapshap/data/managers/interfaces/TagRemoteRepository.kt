package com.arshapshap.data.managers.interfaces

import com.arshapshap.common.domain.models.Tag
import com.arshapshap.common.domain.models.network.BasicResult

interface TagRemoteRepository {

    suspend fun createTagRemote(tag: Tag): BasicResult

    suspend fun updateTagRemote(tag: Tag): BasicResult

    suspend fun deleteTagRemote(tag: Tag): BasicResult
}