package com.arshapshap.common.presentation.floatingButtonInterfaces

interface FloatingButtonListenersManager {

    fun subscribeOnFloatingButtonClick(listener: OnFloatingButtonClickListener)

    fun setDefaultOnFloatingButtonClickListener()
}