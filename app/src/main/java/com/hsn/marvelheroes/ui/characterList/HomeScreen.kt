/**
 * Created by Hussain on 9/18/2022
 *
 */

package com.hsn.marvelheroes.ui.characterList

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.hsn.marvelheroes.R
import com.hsn.marvelheroes.ui.MainViewModel

@Composable
fun HomeScreen(viewModel: MainViewModel, onClickCharacter: (id: String) -> Unit) {

    var searchText by rememberSaveable {
        mutableStateOf("")
    }

    var expanded by remember {
        mutableStateOf(false)
    }

    val scaffoldState = rememberScaffoldState()

    var isLoading = viewModel.isLoading

    Scaffold(scaffoldState = scaffoldState) {
        LaunchedEffect(key1 = viewModel.error.value, block = {
            if (viewModel.error.value != null) {
                scaffoldState.snackbarHostState.showSnackbar(
                    viewModel.error.value ?: "Something Went Wrong",
                    duration = SnackbarDuration.Short
                )
                viewModel.resetError()
            }
        })

        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                OutlinedTextField(
                    value = searchText,
                    onValueChange = {
                        searchText = it
                        viewModel.searchCharacter(searchText)
                    },
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                        .padding(start = 8.dp, end = 2.dp)
                        .weight(1f),
                    label = { Text("Search Character") },
                    singleLine = true,
                    trailingIcon = {
                        IconButton(onClick = { viewModel.searchCharacter(searchText) }) {
                            Icon(
                                imageVector = Icons.Filled.Search,
                                contentDescription = "Search Character"
                            )
                        }
                    }
                )

                IconButton(
                    modifier = Modifier.padding(horizontal = 2.dp),
                    onClick = { expanded = true }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_filter_list_24),
                        contentDescription = "Filter List"
                    )
                    DropdownMenu(expanded = expanded, onDismissRequest = {
                        expanded = false
                    }) {
                        DropdownMenuItem(onClick = {
                            expanded = false
                            viewModel.getAllCharacters()
                        }) {
                            Text(text = "All")
                        }

                        DropdownMenuItem(onClick = {
                            expanded = false
                            viewModel.getBookmarkedCharacters()
                        }) {
                            Text(text = "Bookmarked")
                        }
                    }
                }
            }
            if (!isLoading.value) {
                CharacterList(viewModel = viewModel, onClickCharacter)
            } else {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}