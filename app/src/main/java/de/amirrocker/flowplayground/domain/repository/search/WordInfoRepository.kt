package de.amirrocker.flowplayground.domain.repository.search

import de.amirrocker.flowplayground.core.Resource
import de.amirrocker.flowplayground.data.Word
import de.amirrocker.flowplayground.data.WordInfo
import kotlinx.coroutines.flow.Flow

interface WordInfoRepository {
    fun getWordInfo(word:Word):Flow<Resource<List<WordInfo>>>
}