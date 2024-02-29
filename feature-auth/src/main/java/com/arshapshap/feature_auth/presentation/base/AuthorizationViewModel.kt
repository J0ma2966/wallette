package com.arshapshap.feature_auth.presentation.base

import androidx.lifecycle.viewModelScope
import com.arshapshap.common.presentation.base.BaseViewModel
import com.arshapshap.feature_auth.R
import com.arshapshap.feature_auth.domain.AuthorizationInteractor
import com.arshapshap.common.domain.models.network.AuthorizationResult
import com.arshapshap.feature_auth.presentation.screen.AuthorizationRouter
import kotlinx.coroutines.launch
import java.net.UnknownHostException

abstract class AuthorizationViewModel(
    private val interactor: AuthorizationInteractor,
    private val router: AuthorizationRouter
) : BaseViewModel() {

    protected fun handleServerResult(result: AuthorizationResult, exceptionHandler: (String) -> Unit) {
        viewModelScope.launch {
            if (result.isSuccessful)
                router.openStatistics()
            else exceptionHandler.invoke(result.errorMessage)
        }
    }

    protected fun tryAuthorize(request: suspend () -> Unit) {
        _loadingLiveData.postValue(true)
        viewModelScope.launch {
            kotlin.runCatching {
                request.invoke()
            }.onFailure(::handleNetworkException)
                .also { _loadingLiveData.postValue(false) }
        }
    }

    private fun handleNetworkException(error: Throwable) {
        when (error) {
            is UnknownHostException -> _errorFromResourceLiveData.postValue(R.string.network_unavailable)
            else -> _errorFromResourceLiveData.postValue(R.string.error)
        }
    }
}