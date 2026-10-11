package uz.ttpu.composearchlab // проверь, чтобы пакет совпадал с твоим

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    // Состояние теперь живет здесь
    var name by mutableStateOf("")
        private set // Менять напрямую снаружи нельзя, только читать

    // Функция для обновления состояния
    fun onNameChange(newName: String) {
        name = newName
    }
}