package com.arshapshap.wallette.feature.auth.presentation.screen.login

import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import by.kirich1409.viewbindingdelegate.viewBinding
import com.arshapshap.wallette.core.common.di.FeatureUtils
import com.arshapshap.wallette.core.common.presentation.base.BaseFragment
import com.arshapshap.wallette.core.common.presentation.base.BaseViewModel
import com.arshapshap.wallette.feature.auth.R
import com.arshapshap.wallette.feature.auth.databinding.FragmentLoginBinding
import com.arshapshap.wallette.feature.auth.di.AuthorizationComponent
import com.arshapshap.wallette.feature.auth.di.AuthorizationFeatureApi

class LoginFragment : BaseFragment<LoginViewModel>(R.layout.fragment_login) {

    private val binding by viewBinding(FragmentLoginBinding::bind)
    private val authorizationComponent: AuthorizationComponent by lazy {
        FeatureUtils.getFeature(this, AuthorizationFeatureApi::class.java)
    }

    override fun inject() {
        authorizationComponent.inject(this)
    }

    override fun createViewModel(): BaseViewModel =
        authorizationComponent.loginViewModel().create()

    override fun initViews() {
        with (binding) {
            openRegisterPageButton.setOnClickListener {
                viewModel.openRegisterPage()
            }

            loginButton.setOnClickListener {
                val email = emailEditText.text.toString().trim()
                val password = passwordEditText.text.toString().trim()

                viewModel.tryLogin(
                    email = email,
                    password = password)
            }

            emailEditText.addTextChangedListener {
                binding.errorTextView.isVisible = false
            }

            passwordEditText.addTextChangedListener {
                binding.errorTextView.isVisible = false
            }
        }
    }

    override fun subscribe() {
        with (viewModel) {
            errorLiveData.observe(viewLifecycleOwner) {
                binding.errorTextView.isGone = it.isNullOrEmpty()
                binding.errorTextView.text = it
            }

            errorFromResourceLiveData.observe(viewLifecycleOwner) {
                binding.errorTextView.isGone = it == 0
                binding.errorTextView.text = getString(it)
            }

            loadingLiveData.observe(viewLifecycleOwner) {
                binding.loadingProgressBar.isGone = !it
            }
        }
    }
}