package com.arshapshap.wallette.feature.settings.presentation.screen.singleTag

import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import by.kirich1409.viewbindingdelegate.viewBinding
import com.arshapshap.wallette.core.common.di.FeatureUtils
import com.arshapshap.wallette.core.common.domain.models.Tag
import com.arshapshap.wallette.core.common.presentation.base.BaseFragment
import com.arshapshap.wallette.core.common.presentation.base.BaseViewModel
import com.arshapshap.wallette.core.common.presentation.extensions.setColor
import com.arshapshap.wallette.core.common.presentation.extensions.setImage
import com.arshapshap.wallette.core.common.presentation.extensions.setOnClickListener
import com.arshapshap.wallette.core.common.presentation.extensions.setRightArrowVisible
import com.arshapshap.wallette.core.common.presentation.extensions.setStrokeVisibility
import com.arshapshap.wallette.core.common.presentation.extensions.setTitle
import com.arshapshap.wallette.core.common.presentation.floatingButtonInterfaces.FloatingButtonListenersManager
import com.arshapshap.wallette.core.common.presentation.floatingButtonInterfaces.OnFloatingButtonClickListener
import com.arshapshap.wallette.feature.settings.R
import com.arshapshap.wallette.feature.settings.databinding.FragmentSingleTagBinding
import com.arshapshap.wallette.feature.settings.di.SettingsComponent
import com.arshapshap.wallette.feature.settings.di.SettingsFeatureApi
import vadiole.colorpicker.ColorModel
import vadiole.colorpicker.ColorPickerDialog

class SingleTagFragment :
    BaseFragment<SingleTagViewModel>(R.layout.fragment_single_tag), OnFloatingButtonClickListener {

    companion object {

        const val TAG_KEY = "category_key"
    }

    private val binding by viewBinding(FragmentSingleTagBinding::bind)
    private val component: SettingsComponent by lazy {
        FeatureUtils.getFeature(this, SettingsFeatureApi::class.java)
    }

    override fun inject() {
        component.inject(this)
    }

    override fun onStart() {
        super.onStart()
        (requireActivity() as? FloatingButtonListenersManager)?.subscribeOnFloatingButtonClick(this)
    }

    override fun onStop() {
        super.onStop()
        (requireActivity() as? FloatingButtonListenersManager)?.setDefaultOnFloatingButtonClickListener()
    }

    @Suppress("DEPRECATION")
    override fun createViewModel(): BaseViewModel {
        return component.singleTagViewModel()
            .create(arguments?.getSerializable(TAG_KEY) as? Tag)
    }

    override fun initViews() {
        with (binding.tagColorLayout) {
            setStrokeVisibility(true)
            setRightArrowVisible(true)
            setImage(R.drawable.ic_pallette)
            setTitle(R.string.tag_color)
            setOnClickListener {
                openColorPickerDialog {
                    viewModel.selectColor(it)
                }
            }
        }

        binding.tagNameEditText.doAfterTextChanged {
            viewModel.editName(it.toString())
        }
    }

    override fun subscribe() {
        viewModel.startTagLiveData.observe(viewLifecycleOwner) {
            it?.name?.let {
                binding.tagNameEditText.setText(it)

                binding.deleteImageButton.isVisible = true
                binding.deleteImageButton.setOnClickListener {
                    viewModel.delete()
                }
            }
        }

        viewModel.editingTagLiveData.observe(viewLifecycleOwner) {
            it?.color?.let {
                binding.tagColorLayout.setColor(it)
                binding.tagColorLayout.setOnClickListener {
                    openColorPickerDialog(it) {
                        viewModel.selectColor(it)
                    }
                }
            }
        }
    }

    override fun onFloatingButtonClick() {
        viewModel.save()
    }

    private fun openColorPickerDialog(@ColorInt currentColor: Int? = null, onSelect: (Int) -> Unit) {
        val colorPicker: ColorPickerDialog = ColorPickerDialog.Builder()
            .setInitialColor(currentColor ?: Color.BLACK)
            .setColorModel(ColorModel.RGB)
            .setButtonOkText(android.R.string.ok)
            .setButtonCancelText(android.R.string.cancel)
            .onColorSelected { color: Int ->
                onSelect.invoke(color)
            }
            .create()
        colorPicker.show(childFragmentManager, "color_picker")
    }
}