package com.example.data.mappers

import com.example.common.data.models.response.TagResponse
import com.example.common.domain.models.Tag
import com.example.core_db.models.entities.TagLocal
import javax.inject.Inject

class TagMapper @Inject constructor() {

    fun map(response: TagResponse?): Tag {
        return response?.let {
            Tag(
                id = response.id ?: 0,
                name = response.name ?: "",
                color = response.color ?: "#000000"
            )
        } ?: Tag(
            id = 0,
            name = "",
            color = "#000000"
        )
    }

    fun map(local: TagLocal): Tag {
        return with (local) {
            Tag(
                id = tagId,
                name = name,
                color = color
            )
        }
    }

    fun map(tag: Tag): TagLocal {
        return with (tag) {
            TagLocal(
                tagId = id,
                name = name,
                color = color
            )
        }
    }
}