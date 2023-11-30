package br.senai.sp.jandira.limpeanapp.presentation.features.find_cleanings.components


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Icon
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.StarHalf
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.ceil
import kotlin.math.floor


@Composable
fun StarView(
    modifier: Modifier = Modifier,
    onRatingChanged: (Double) -> Unit,
    rating: Double = 0.0,
    stars: Int = 5,
    starsColor: Color = Color(255, 170, 51),
    starSize : Dp = 50.dp,
)
{
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in 1..stars) {
            val isSelected = i <= rating
            val starIcon = if (isSelected) Icons.Default.Star else Icons.Default.StarBorder
            Icon(
                imageVector = starIcon,
                contentDescription = null,
                tint = starsColor,
                modifier = Modifier
                    .size(starSize)
                    .clickable { onRatingChanged(i.toDouble()) }
            )
        }
    }



}
@Preview(showSystemUi = true)
@Composable
fun RatingPreview() {
    var rating by remember { mutableDoubleStateOf(0.0) }

    Box (Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        StarView(
            rating = rating,
            onRatingChanged = { newRating ->
                rating = newRating
            },
            starSize = 40.dp
        )
    }

}



//@Preview
//@Composable
//fun TenStarsRatingPreview() {
//    StarView(stars = 10, rating = 8.5)
//}
//@Preview
//@Composable
//fun RatingPreviewFull() {
//    StarView(rating = 5.0)
//}
//@Preview
//@Composable
//fun RatingPreviewWorst() {
//    StarView(rating = 1.0)
//}
//@Preview
//@Composable
//fun RatingPreviewDisabled() {
//    StarView(rating = 0.0, starsColor = Color.Gray)
//}

//comentado