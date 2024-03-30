package com.arshapshap.wallette.core.db.dao

import androidx.room.*
import com.arshapshap.wallette.core.db.models.FullTransactionLocal
import com.arshapshap.wallette.core.db.models.TransactionTagCrossRef
import com.arshapshap.wallette.core.db.models.entities.TransactionLocal

@Dao
abstract class TransactionDao {

    @Insert
    abstract suspend fun addTransaction(transactionLocal: TransactionLocal): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun addTransactionTag(transactionTagCrossRef: TransactionTagCrossRef)

    @Update
    abstract suspend fun updateTransaction(transactionLocal: TransactionLocal)

    @Delete
    abstract suspend fun deleteTransaction(transactionLocal: TransactionLocal)

    @Delete
    abstract suspend fun deleteTransactionTag(transactionTagCrossRef: TransactionTagCrossRef)

    @Transaction
    @Query("SELECT * FROM Transactions")
    abstract suspend fun getTransactions(): List<FullTransactionLocal>

    @Transaction
    @Query("SELECT * FROM Transactions WHERE transaction_id=:id")
    abstract fun getTransactionById(id: Long): FullTransactionLocal

    @Transaction
    @Query("SELECT tag_id FROM TransactionTag WHERE transaction_id=:transactionId")
    abstract suspend fun getTagsByTransactionId(transactionId: Long): List<Long>
}