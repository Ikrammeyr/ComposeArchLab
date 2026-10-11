package uz.ttpu.composearchlab.ui.signin

// A sealed interface is a better fit here than an enum because its cases can carry different data (like message or email), which an enum cannot do.
sealed interface SignInUiState {
    data object SignedOut : SignInUiState
    data object InProgress : SignInUiState
    data class Error(val message: String) : SignInUiState
    data class SignedIn(val email: String) : SignInUiState
}