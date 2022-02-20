package de.amirrocker.flowplayground.data

typealias Origin = String
typealias Phonetic = String
typealias Word = String
typealias PartOfSpeech = String

data class WordInfo(
    val meanings: List<Meaning>,
    val origin: Origin,
    val phonetic: Phonetic,
    val word: Word
)