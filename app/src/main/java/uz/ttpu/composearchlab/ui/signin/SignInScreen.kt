package uz.ttpu.composearchlab.ui.signin

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import uz.ttpu.composearchlab.ui.theme.ComposeArchLabTheme

@Composable
fun SignInRoute(
    modifier: Modifier = Modifier,
    viewModel: SignInViewModel = viewModel()
) {
    val uiState by viewModel.uiState
    val snackbarHostState = remember { SnackbarHostState() }

    // Bug explanation: The ViewModel survives rotation and still holds the Error state.
    // When the screen is recreated after rotation, the LaunchedEffect runs again and shows the same Snackbar
    LaunchedEffect(uiState) {
        if (uiState is SignInUiState.Error) {
            snackbarHostState.showSnackbar((uiState as SignInUiState.Error).message)
            viewModel.onErrorShown()
        }
    }

    Scaffold(
        modifier = modifier,
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->
        SignInScreen(
            uiState = uiState,
            onSignIn = viewModel::onSignIn,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun SignInScreen(
    uiState: SignInUiState,
    onSignIn: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when (uiState) {
            is SignInUiState.SignedIn -> {
                Text("Welcome, ${uiState.email}")
            }
            is SignInUiState.SignedOut,
            is SignInUiState.Error,
            is SignInUiState.InProgress -> {
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { onSignIn(email, password) },
                    enabled = uiState !is SignInUiState.InProgress,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Sign in")
                }
                if (uiState is SignInUiState.InProgress) {
                    Spacer(modifier = Modifier.height(16.dp))
                    CircularProgressIndicator()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignInScreenSignedOutPreview() {
    ComposeArchLabTheme {
        SignInScreen(uiState = SignInUiState.SignedOut, onSignIn = { _, _ -> })
    }
}

@Preview(showBackground = true)
@Composable
fun SignInScreenInProgressPreview() {
    ComposeArchLabTheme {
        SignInScreen(uiState = SignInUiState.InProgress, onSignIn = { _, _ -> })
    }
}

@Preview(showBackground = true)
@Composable
fun SignInScreenErrorPreview() {
    ComposeArchLabTheme {
        SignInScreen(uiState = SignInUiState.Error("Wrong email or password"), onSignIn = { _, _ -> })
    }
}

@Preview(showBackground = true)
@Composable
fun SignInScreenSignedInPreview() {
    ComposeArchLabTheme {
        SignInScreen(uiState = SignInUiState.SignedIn("test@email.com"), onSignIn = { _, _ -> })
    }
}