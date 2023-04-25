package cardosofgui.android.core.components.utils.state

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cardosofgui.android.core.components.utils.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

abstract class ViewModel<State: UIState>(
    initialState: State
): ViewModel() {

    var state = MutableStateFlow<State>(initialState)

    fun setState(
        newState: State
    ) {
        viewModelScope.launch {
            state.value = newState
        }
    }
}