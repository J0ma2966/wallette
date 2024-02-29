package com.arshapshap.wallette.core.common.domain.repositories

import com.arshapshap.wallette.core.common.domain.models.Tag

interface TagRepository {

    suspend fun createTag(tag: Tag)

    suspend fun updateTag(tag: Tag)

    suspend fun deleteTag(tag: Tag)

    suspend fun getTags(): List<Tag>
}