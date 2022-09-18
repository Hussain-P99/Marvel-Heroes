/**
 * Created by Hussain on 9/17/2022
 *
 */

package com.hsn.marvelheroes.ui.characterList

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.hsn.marvelheroes.R
import com.hsn.marvelheroes.dto.Character
import com.hsn.marvelheroes.ui.theme.MarvelHeroesTheme

@OptIn(ExperimentalCoilApi::class)
@Composable
fun CharacterItem(
    character: Character,
    modifier: Modifier,
    onClick: (Character) -> Unit
) {

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 16.dp)
            .clickable {
                onClick.invoke(character)
            },
        shape = RoundedCornerShape(10.dp),
        elevation = 2.dp,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentAlignment = Alignment.Center
        ) {
            val painter = rememberImagePainter(data = character.thumbnail, builder = {
                placeholder(R.drawable.ic_placeholder_image)
                error(R.drawable.ic_error_image)
                crossfade(200)
            })

            Image(
                painter = painter,
                contentDescription = character.name,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            ),
                            startY = 350f
                        )
                    )
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp), contentAlignment = Alignment.BottomStart
            ) {
                Text(
                    text = character.name,
                    style = TextStyle(color = Color.White, fontSize = 16.sp)
                )
            }


        }

    }
}


@Composable
@Preview
fun CharacterItemPreview() {
    val character = Character(
        characterId = "1011334", name = "Sample Name", description = "Sample Description",
        listOf(), listOf(), listOf(), listOf(), ""
    )
    MarvelHeroesTheme {
        CharacterItem(character = character, modifier = Modifier) {

        }
    }
}