package com.arshapshap.wallette.core.common.presentation.floatingButtonInterfaces

interface FloatingButtonListenersManager {

    fun subscribeOnFloatingButtonClick(listener: OnFloatingButtonClickListener)

    fun setDefaultOnFloatingButtonClickListener()
}