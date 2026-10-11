package uz.ttpu.composearchlab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uz.ttpu.composearchlab.ui.taste.TastePickerRoute
import uz.ttpu.composearchlab.ui.theme.ComposeArchLabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeArchLabTheme {
                TastePickerRoute()
            }
        }
    }
}


@Composable
fun NameField(name: String, onNameChange: (String) -> Unit, modifier: Modifier = Modifier) {
    OutlinedTextField(
        value = name,
        onValueChange = onNameChange,
        label = { Text("Name") },
        modifier = modifier
    )
}

@Composable
fun NameScreen(modifier: Modifier = Modifier) {
    // rememberSaveable keeps the state in a Bundle across configuration changes (like rotation),
    // whereas remember loses it when the activity is recreated.
    var name by rememberSaveable { mutableStateOf("") }

    Column(modifier = modifier.padding(24.dp)) {
        NameField(
            name = name,
            onNameChange = { name = it }
        )
        Text("Hello, $name!")
    }
}

@Preview(showBackground = true)
@Composable
fun NameFieldPreview() {
    ComposeArchLabTheme {
        NameField(name = "Amin", onNameChange = {})
    }
}