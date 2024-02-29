package com.arshapshap.wallette.core.data.mappers

import com.arshapshap.wallette.core.common.domain.models.Tag
import com.arshapshap.wallette.core.db.models.entities.TagLocal
import com.arshapshap.wallette.core.network.data.models.request.tag.TagCreatingModel
import com.arshapshap.wallette.core.network.data.models.request.tag.TagEditingModel
import com.arshapshap.wallette.core.network.data.models.response.TagResponse
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

    fun mapToCreatingModel(tag: Tag): TagCreatingModel {
        return with (tag) {
            TagCreatingModel(
                id = id.toString(),
                name = name,
                color = color
            )
        }
    }

    fun mapToEditingModel(tag: Tag): TagEditingModel {
        return with (tag) {
            TagEditingModel(
                id = id.toString(),
                name = name,
                color = color
            )
        }
    }
}