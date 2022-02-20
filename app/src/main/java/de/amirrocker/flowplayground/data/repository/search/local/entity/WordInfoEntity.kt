package de.amirrocker.flowplayground.data.repository.search.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import de.amirrocker.flowplayground.data.*

@Entity
class WordInfoEntity(
    @PrimaryKey
    val id:Int? = null,
    val word:Word,
    val phonetic: Phonetic,
    val origin: Origin,
    val meanings: List<Meaning>
) {
    fun toWordInfo() = WordInfo(
        meanings = meanings,
        origin = origin,
        phonetic = phonetic,
        word = word
    )
}