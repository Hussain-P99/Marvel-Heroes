/**
 * Created by Hussain on 9/17/2022
 *
 */

package com.hsn.marvelheroes.ui.characterList

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.hsn.marvelheroes.ui.MainViewModel

@Composable
fun CharacterList(viewModel: MainViewModel, onClickCharacter: (id: String) -> Unit) {

    val characters = remember {
        viewModel.characters
    }
    
    val isRefreshing = viewModel.isRefreshing.observeAsState()

    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing = isRefreshing.value ?: false),
        onRefresh = { viewModel.onRefresh() }) {
        LazyColumn {
            items(items = characters.value ?: emptyList()) { character ->
                CharacterItem(character = character, modifier = Modifier) {
                    onClickCharacter.invoke(it.characterId)
                }
            }
        }
    }

}