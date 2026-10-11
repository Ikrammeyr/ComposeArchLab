package uz.ttpu.composearchlab.ui.taste

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ArtistCard(
    artist: Artist,
    liked: Boolean,
    onLikeClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(88.dp)
                .clip(CircleShape)
                .background(Color.hsv((artist.id * 47 % 360).toFloat(), 0.5f, 0.8f)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = artist.name.take(1),
                style = MaterialTheme.typography.headlineMedium
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = artist.name,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            modifier = Modifier.height(48.dp)
        )
        IconButton(onClick = onLikeClick) {
            Text(
                text = if (liked) "❤️" else "🤍",
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}

@Composable
fun ArtistGrid(
    artists: List<Artist>,
    likedIds: Set<Int>,
    onLikeClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = modifier
    ) {
        items(artists, key = { it.id }) { artist ->
            ArtistCard(
                artist = artist,
                liked = artist.id in likedIds,
                onLikeClick = { onLikeClick(artist.id) }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ArtistCardLikedPreview() {
    ArtistCard(artist = seedArtists[0], liked = true, onLikeClick = {})
}

@Preview(showBackground = true)
@Composable
fun ArtistCardNotLikedPreview() {
    ArtistCard(artist = seedArtists[1], liked = false, onLikeClick = {})
}

@Preview(showBackground = true)
@Composable
fun ArtistGridPreview() {
    ArtistGrid(
        artists = seedArtists,
        likedIds = setOf(1, 2, 4),
        onLikeClick = {}
    )
}