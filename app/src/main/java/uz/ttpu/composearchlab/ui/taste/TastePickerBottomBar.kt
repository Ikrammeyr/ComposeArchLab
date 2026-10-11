package uz.ttpu.composearchlab.ui.taste

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TastePickerBottomBar(
    likedCount: Int,
    required: Int,
    canContinue: Boolean,
    onContinueClick: () -> Unit,
    onSkipClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        tonalElevation = 8.dp,
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(
                        progress = { (likedCount.toFloat() / required).coerceAtMost(1f) },
                        modifier = Modifier.size(48.dp)
                    )
                    Text(text = "$likedCount")
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = "Pick $required artists you like")
            }

            Row {
                TextButton(onClick = onSkipClick) {
                    Text("Later")
                }
                Button(
                    onClick = onContinueClick,
                    enabled = canContinue
                ) {
                    Text("Continue")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TastePickerBottomBarPreview0() {
    TastePickerBottomBar(0, 3, false, {}, {})
}

@Preview(showBackground = true)
@Composable
fun TastePickerBottomBarPreview2() {
    TastePickerBottomBar(2, 3, false, {}, {})
}

@Preview(showBackground = true)
@Composable
fun TastePickerBottomBarPreview3() {
    TastePickerBottomBar(3, 3, true, {}, {})
}