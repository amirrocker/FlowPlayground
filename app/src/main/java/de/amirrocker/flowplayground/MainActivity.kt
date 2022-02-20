package de.amirrocker.flowplayground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.amirrocker.flowplayground.presentation.search.SearchViewModel
import de.amirrocker.flowplayground.presentation.search.WordInfoItem
import de.amirrocker.flowplayground.ui.theme.FlowPlaygroundTheme
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel

val defaultPadding = 16.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlowPlaygroundTheme {

                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colors.background
//                ) {
//                    Greeting("Android")
//                }

                val viewModel:SearchViewModel by viewModel()
                val state = viewModel.state.value
                val scaffoldState = rememberScaffoldState()

                LaunchedEffect(key1 = true, block = {
                    println("launched Effect in block function ... ")
                    viewModel.eventFlow.collectLatest { uiEvent ->
                        when(uiEvent) {
                            is SearchViewModel.UIEvent.ShowSnackbar -> {
                                scaffoldState.snackbarHostState.showSnackbar(message = uiEvent.message)
                            }
                        }

                    }
                } )

                Scaffold(
                    scaffoldState = scaffoldState
                ) {
                    Box(modifier = Modifier
                        .background(
                            MaterialTheme.colors.background)) {

                        Column(modifier = Modifier
                            .background(MaterialTheme.colors.surface)
                            .fillMaxSize()
                            .padding(defaultPadding)
                        ) {

                            TextField(
                                value = viewModel.searchQuery.value,
                                onValueChange = viewModel::onSearch,
                                modifier = Modifier.fillMaxWidth(),
                                placeholder = {
                                    Text(text = "Enter search term...")
                                }
                            )
                            Spacer(modifier = Modifier.width(defaultPadding))

                            LazyColumn(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                items(state.wordInfoItems.size) { i:Int ->
                                    val wordInfo = state.wordInfoItems[i]
                                    WordInfoItem(wordInfo)
                                }
                            }
                        }
                    }
                }

            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FlowPlaygroundTheme {
//        Greeting("Android")
    }
}