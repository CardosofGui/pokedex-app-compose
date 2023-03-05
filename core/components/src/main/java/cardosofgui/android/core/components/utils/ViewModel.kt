package cardosofgui.android.core.components.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

abstract class ViewModel<State: UIState, Action: UIAction>(
    initialState: State
): ViewModel() {

    var state = MutableStateFlow<State>(initialState)

    var action = MutableStateFlow<Action?>(null)

    fun setState(
        newState: State
    ) {
        viewModelScope.launch {
            state.value = newState
        }
    }

    fun sendAction(
        newAction: Action
    ) {
        viewModelScope.launch {
            action.value = newAction
        }
    }

}

interface UIState {

}
interface UIAction