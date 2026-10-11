package uz.ttpu.composearchlab.ui.signin

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SignInViewModel : ViewModel() {

    // _uiState is private and mutable so it can only be changed inside the ViewModel (state encapsulation).
    // uiState is public and read-only so the UI can observe it but cannot modify it directly.
    private val _uiState = mutableStateOf<SignInUiState>(SignInUiState.SignedOut)
    val uiState: State<SignInUiState>
        get() = _uiState

    fun onSignIn(email: String, password: String) {
        // 1: ignore the call if a sign-in is already running
        if (_uiState.value is SignInUiState.InProgress) return

        // 2: set InProgress, then launch a coroutine in viewModelScope
        _uiState.value = SignInUiState.InProgress

        viewModelScope.launch {
            // 3: simulate the network with delay(1500)
            delay(1500)

            // 4: "kotlin123" -> SignedIn(email); else -> Error(...)
            if (password == "kotlin123") {
                _uiState.value = SignInUiState.SignedIn(email)
            } else {
                _uiState.value = SignInUiState.Error("Wrong email or password")
            }
        }
    }

    fun onErrorShown() {
        if (_uiState.value is SignInUiState.Error) {
            _uiState.value = SignInUiState.SignedOut
        }
    }
}

