package de.amirrocker.flowplayground.domain.usecase.search

import de.amirrocker.flowplayground.core.Resource
import de.amirrocker.flowplayground.data.WordInfo
import de.amirrocker.flowplayground.domain.repository.search.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

class GetWordInfo(
    private val repository: WordInfoRepository
) {

    operator fun invoke(word:String) : Flow<Resource<List<WordInfo>>> = if(word.isBlank()) {
            emptyFlow()
        } else {
            repository.getWordInfo(word)
        }
}