import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.SavedStateHandle
import com.arshapshap.wallette.core.common.presentation.base.BaseViewModel
import com.arshapshap.wallette.core.common.di.ViewModelFactory

inline fun <reified T : BaseViewModel> Fragment.lazyViewModel(
    noinline create: (stateHandle: SavedStateHandle) -> T
) = viewModels<T> {
    ViewModelFactory(this, create)
}