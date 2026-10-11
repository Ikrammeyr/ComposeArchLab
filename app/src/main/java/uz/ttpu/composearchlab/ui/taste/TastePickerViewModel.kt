package uz.ttpu.composearchlab.ui.taste

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TastePickerViewModel : ViewModel() {

    private val _state = MutableStateFlow(TastePickerState(artists = seedArtists))
    val state: StateFlow<TastePickerState> = _state.asStateFlow()

    fun onGenreClick(genre: String) {
        _state.update { currentState ->
            val newGenre = if (currentState.selectedGenre == genre) null else genre
            currentState.copy(selectedGenre = newGenre)
        }
    }

    fun onArtistLikeToggled(id: Int) {
        _state.update { currentState ->
            val newLikes = if (currentState.likedIds.contains(id)) {
                currentState.likedIds - id
            } else {
                currentState.likedIds + id
            }
            currentState.copy(likedIds = newLikes)
        }
    }
}