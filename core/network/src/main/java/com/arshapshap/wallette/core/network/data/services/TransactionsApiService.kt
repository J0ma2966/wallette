package com.arshapshap.wallette.core.network.data.services

import com.arshapshap.wallette.core.network.data.models.request.transaction.TransactionCreatingModel
import com.arshapshap.wallette.core.network.data.models.request.transaction.TransactionEditingModel
import com.arshapshap.wallette.core.network.data.models.response.BasicResponse
import com.arshapshap.wallette.core.network.data.models.response.TransactionResponse
import retrofit2.http.*

interface TransactionsApiService {

    @GET("transaction/get")
    suspend fun getTransactions(): ArrayList<TransactionResponse>

    @GET("transaction/get/:id")
    suspend fun getTransactionById(@Query("id")  id: Long): TransactionResponse

    @POST("transaction/create")
    suspend fun createTransaction(@Body model: TransactionCreatingModel): BasicResponse

    @PUT("transaction/update")
    suspend fun updateTransaction(@Body model: TransactionEditingModel): BasicResponse

    @DELETE("transaction/delete/:id")
    suspend fun deleteTransactionById(@Query("id")  id: Long): BasicResponse
}