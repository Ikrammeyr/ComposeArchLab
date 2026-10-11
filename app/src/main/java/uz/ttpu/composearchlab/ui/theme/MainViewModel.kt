package uz.ttpu.composearchlab

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    var name by mutableStateOf("")
        private set

    fun onNameChange(newName: String) {
        name = newName
    }
}