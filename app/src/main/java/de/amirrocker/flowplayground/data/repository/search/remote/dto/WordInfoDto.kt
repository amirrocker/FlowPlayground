package de.amirrocker.flowplayground.data.repository.search.remote.dto

import de.amirrocker.flowplayground.data.Origin
import de.amirrocker.flowplayground.data.Phonetic
import de.amirrocker.flowplayground.data.Word
import de.amirrocker.flowplayground.data.repository.search.local.entity.WordInfoEntity

data class WordInfoDto(
    val meanings: List<MeaningDto>,
    val origin: Origin,
    val phonetic: Phonetic,
    val phonetics: List<PhoneticDto>,
    val word: Word
) {
    fun toWordInfoEntity(): WordInfoEntity = WordInfoEntity(
        meanings = meanings.map { it.toMeaning() },
        origin = origin ?: "",
        phonetic = phonetic,
        word = word
    )
}