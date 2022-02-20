package de.amirrocker.flowplayground.data

typealias Antonym = String
typealias Example = String
typealias Synonym = String
typealias DefinitionValue = String

data class Definition(
    val antonyms: List<Antonym>?,
    val definition: DefinitionValue?, // this is a bit weird! rethink that one!
    val example: Example?,
    val synonyms: List<Synonym>?,
)