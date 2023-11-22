package br.senai.sp.jandira.limpeanapp.presentation.features.find_cleanings.components


import androidx.compose.material3.Icon
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.StarHalf
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.ceil
import kotlin.math.floor


@Composable
fun StarView(
    modifier: Modifier = Modifier,
    rating: Double = 0.0,
    stars: Int = 5,
    starsColor: Color = Color(255, 170, 51),
) {
    val filledStars = floor(rating).toInt()
    val unfilledStars = (stars - ceil(rating)).toInt()
    val halfStar = !(rating.rem(1).equals(0.0))
    Row(modifier = modifier) {
        repeat(filledStars) {
            Icon(imageVector = Icons.Outlined.Star, contentDescription = null, tint = starsColor, modifier = Modifier.height(30.dp).width(30.dp))
        }
        if (halfStar) {
            Icon(
                imageVector = Icons.Outlined.StarHalf,
                contentDescription = null,
                tint = starsColor,
                modifier = Modifier.height(30.dp).width(30.dp)
            )
        }
        repeat(unfilledStars) {
            Icon(
                imageVector = Icons.Outlined.StarOutline,
                contentDescription = null,
                tint = starsColor,
                modifier = Modifier.height(30.dp).width(30.dp)
            )
        }
    }
}
@Preview
@Composable
fun RatingPreview() {
    StarView(rating = 2.5)
}
@Preview
@Composable
fun TenStarsRatingPreview() {
    StarView(stars = 10, rating = 8.5)
}
@Preview
@Composable
fun RatingPreviewFull() {
    StarView(rating = 5.0)
}
@Preview
@Composable
fun RatingPreviewWorst() {
    StarView(rating = 1.0)
}
@Preview
@Composable
fun RatingPreviewDisabled() {
    StarView(rating = 0.0, starsColor = Color.Gray)
}

//comentado