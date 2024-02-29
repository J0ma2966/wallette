package com.arshapshap.wallette.core.db.dao

import androidx.room.*
import com.arshapshap.wallette.core.db.models.entities.CategoryLocal

@Dao
abstract class CategoryDao {

    @Insert
    abstract suspend fun addCategory(categoryLocal: CategoryLocal): Long

    @Update
    abstract suspend fun updateCategory(categoryLocal: CategoryLocal)

    @Delete
    abstract suspend fun deleteCategory(categoryLocal: CategoryLocal)

    @Query("SELECT * FROM Categories")
    abstract suspend fun getCategories(): List<CategoryLocal>

    @Query("SELECT * FROM Categories WHERE category_id=:id")
    abstract suspend fun getCategoryById(id: Long): CategoryLocal
}