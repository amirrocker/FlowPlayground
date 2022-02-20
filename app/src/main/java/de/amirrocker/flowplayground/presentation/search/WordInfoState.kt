package de.amirrocker.flowplayground.presentation.search

import de.amirrocker.flowplayground.data.WordInfo

data class WordInfoState(
    val wordInfoItems: List<WordInfo> = emptyList(),
    val isLoading: Boolean = false
)