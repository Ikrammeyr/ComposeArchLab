package uz.ttpu.composearchlab.ui.taste

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun TastePickerRoute(
    modifier: Modifier = Modifier,
    viewModel: TastePickerViewModel = viewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    TastePickerScreen(
        state = state,
        onGenreClick = viewModel::onGenreClick,
        onLikeClick = viewModel::onArtistLikeToggled,
        modifier = modifier
    )
}

@Composable
fun TastePickerScreen(
    state: TastePickerState,
    onGenreClick: (String) -> Unit,
    onLikeClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        bottomBar = {
            TastePickerBottomBar(
                likedCount = state.likedCount,
                required = REQUIRED_LIKES,
                canContinue = state.canContinue,
                onContinueClick = { Log.d("TastePicker", "Continue clicked") },
                onSkipClick = { Log.d("TastePicker", "Later clicked") }
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            GenreChips(
                genres = state.genres,
                selectedGenre = state.selectedGenre,
                onGenreClick = onGenreClick
            )
            ArtistGrid(
                artists = state.visibleArtists,
                likedIds = state.likedIds,
                onLikeClick = onLikeClick,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenreChips(
    genres: List<String>,
    selectedGenre: String?,
    onGenreClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(genres) { genre ->
            FilterChip(
                selected = genre == selectedGenre,
                onClick = { onGenreClick(genre) },
                label = { Text(genre) }
            )
        }
    }
}