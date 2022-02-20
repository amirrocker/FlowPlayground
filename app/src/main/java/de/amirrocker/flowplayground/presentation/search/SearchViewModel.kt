package de.amirrocker.flowplayground.presentation.search

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.amirrocker.flowplayground.core.Resource
import de.amirrocker.flowplayground.domain.usecase.search.GetWordInfo
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class SearchViewModel(
    val getWordInfo: GetWordInfo
) : ViewModel(), KoinComponent{

    sealed class UIEvent {
        data class ShowSnackbar(val message:String) : UIEvent()
    }

    private val _searchQuery: MutableState<String> = mutableStateOf("")
    val searchQuery: State<String> = _searchQuery

    private val _state: MutableState<WordInfoState> = mutableStateOf(WordInfoState())
    val state: State<WordInfoState> = _state

    private val _eventFlow: MutableSharedFlow<UIEvent> = MutableSharedFlow()
    val eventFlow = _eventFlow.asSharedFlow()

    private var searchJob: Job? = null

    fun onSearch(search:String) {
        _searchQuery.value = search
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500)
            getWordInfo(search)
                .onEach { result ->
                    when(result) {
                        is Resource.Success -> {
                            _state.value = state.value.copy(
                                wordInfoItems = result.data ?: emptyList(),
                                isLoading = false
                            )
                        }
                        is Resource.Loading -> {
                            _state.value = state.value.copy(
                                // would an empty list not suffice?
                                wordInfoItems = result.data ?: emptyList(),
                                isLoading = true
                            )
                        }
                        is Resource.Error -> {
                            _state.value = state.value.copy(
                                wordInfoItems = result.data ?: emptyList(),
                                isLoading = false
                            )
                            _eventFlow.emit(UIEvent.ShowSnackbar(
                                message = "A programming error occurred."
                            ))
                        }
                    }
                }.launchIn(this)
        }
    }
}