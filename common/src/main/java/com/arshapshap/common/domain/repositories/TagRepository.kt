package com.arshapshap.common.domain.repositories

import com.arshapshap.common.domain.models.Tag

interface TagRepository {

    suspend fun createTag(tag: Tag)

    suspend fun updateTag(tag: Tag)

    suspend fun deleteTag(tag: Tag)

    suspend fun getTags(): List<Tag>
}