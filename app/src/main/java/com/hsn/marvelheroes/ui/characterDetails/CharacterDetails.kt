/**
 * Created by Hussain on 9/18/2022
 *
 */

package com.hsn.marvelheroes.ui.characterDetails

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.hsn.marvelheroes.R
import com.hsn.marvelheroes.ui.MainViewModel

@SuppressLint("UnrememberedMutableState")
@Composable
fun CharacterDetails(
    viewModel: MainViewModel,
    characterId: String = "",
    modifier: Modifier = Modifier,
    onNavigateBack: () -> Unit,
    onClickLink: (String) -> Unit
) {


    BackHandler { onNavigateBack.invoke() }
    val dispatcher = LocalOnBackPressedDispatcherOwner.current!!.onBackPressedDispatcher

    viewModel.getCharacter(characterId)
    val character = viewModel.character

    val data = character.value

    val scaffoldState =  rememberScaffoldState()

    if (data != null) {
        val isBookmarked = mutableStateOf(data.isBookmarked)

        Scaffold(topBar = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier.padding(vertical = 8.dp, horizontal = 4.dp)
            ) {
                IconButton(onClick = { dispatcher.onBackPressed() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Navigate Back"
                    )
                }
                Text(
                    modifier = Modifier.weight(1f),
                    text = data.name,
                    style = TextStyle(fontSize = 20.sp, color = MaterialTheme.colors.onSurface)
                )
                Box(
                    modifier = Modifier
                        .padding(8.dp),
                    contentAlignment = Alignment.TopEnd
                ) {
                    val resourceId =
                        if (isBookmarked.value) R.drawable.ic_baseline_bookmark_24 else R.drawable.ic_bookmark_idle
                    IconButton(onClick = {
                        isBookmarked.value = !isBookmarked.value
                        viewModel.bookmarkCharacter(data, isBookmarked.value)
                    }) {
                        Icon(painter = painterResource(id = resourceId), contentDescription = "")
                    }
                }
            }
        }, scaffoldState = scaffoldState) {

            LaunchedEffect(key1 = viewModel.error.value, block = {
                if (viewModel.error.value != null) {
                    scaffoldState.snackbarHostState.showSnackbar(
                        viewModel.error.value ?: "Something Went Wrong",
                        duration = SnackbarDuration.Short
                    )
                    viewModel.resetError()
                }
            })

            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {

                Box(
                    modifier = Modifier
                        .height(250.dp)
                        .fillMaxWidth()
                ) {
                    val painter = rememberImagePainter(data = data.thumbnail) {
                        placeholder(R.drawable.ic_placeholder_image)
                        error(R.drawable.ic_error_image)
                    }
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        painter = painter,
                        contentDescription = data.name,
                        contentScale = ContentScale.Crop
                    )
                }

                Text(
                    modifier = modifier.padding(top = 16.dp),
                    text = "Description",
                    style = MaterialTheme.typography.h6
                )

                Text(
                    modifier = modifier,
                    text = if (data.description.isEmpty()) "No Description Found" else data.description,
                    style = TextStyle(color = MaterialTheme.colors.onSurface, fontSize = 15.sp)
                )

                if (data.comics.isNotEmpty()) {
                    val comics =
                        data.comics.subList(0, if (data.comics.size < 5) data.comics.size else 5)

                    Text(
                        modifier = modifier.padding(top = 16.dp),
                        text = "Comics",
                        style = MaterialTheme.typography.h6
                    )
                    comics.forEachIndexed { index, comic ->
                        Text(
                            modifier = modifier,
                            text = "${index + 1}. ${comic.name}",
                            style = MaterialTheme.typography.body1
                        )
                    }
                }

                if (data.series.isNotEmpty()) {
                    val series =
                        data.series.subList(0, if (data.series.size < 5) data.series.size else 5)

                    Text(
                        modifier = modifier.padding(top = 16.dp),
                        text = "Series",
                        style = MaterialTheme.typography.h6,
                    )
                    series.forEachIndexed { index, series ->
                        Text(
                            modifier = modifier,
                            text = "${index + 1}. ${series.name}",
                            style = MaterialTheme.typography.body1
                        )
                    }
                }

                if (data.events.isNotEmpty()) {
                    val events =
                        data.events.subList(0, if (data.events.size < 5) data.events.size else 5)

                    Text(
                        modifier = modifier.padding(top = 16.dp),
                        text = "Events",
                        style = MaterialTheme.typography.h6
                    )
                    events.forEachIndexed { index, events ->
                        Text(
                            modifier = modifier,
                            text = "${index + 1}. ${events.name}",
                            style = MaterialTheme.typography.body1
                        )
                    }
                }

                if (data.urls.isNotEmpty()) {
                    val urls =
                        data.urls.subList(0, if (data.urls.size < 5) data.urls.size else 5)

                    Text(
                        modifier = modifier.padding(top = 16.dp),
                        text = "Furthur Links",
                        style = MaterialTheme.typography.h6
                    )
                    urls.forEachIndexed { index, url ->
                        Text(
                            modifier = modifier
                                .padding(bottom = 8.dp)
                                .clickable {
                                    onClickLink.invoke(url.url ?: "")
                                },
                            text = "${index + 1}. ${url.url}",
                            style = MaterialTheme.typography.body1,
                            color = Color.Green
                        )
                    }
                }
            }

        }
    }
}