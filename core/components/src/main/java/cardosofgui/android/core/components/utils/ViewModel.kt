package cardosofgui.android.core.components.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class ViewModel<State: UIState, Action: UIAction>(
    initialState: State
): ViewModel() {

    var state = MutableStateFlow<State>(initialState)

    private val _action = Channel<Action>(Channel.BUFFERED)
    var action = _action.receiveAsFlow()

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
            _action.send(newAction)
        }
    }

}

interface UIState {

}
interface UIAction