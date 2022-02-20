package de.amirrocker.flowplayground.data.repository.search.remote.dto

import de.amirrocker.flowplayground.data.*

class DefinitionDto(
    val antonyms: List<Antonym>?,
    val definition: DefinitionValue,
    val example: Example?,
    val synonyms: List<Synonym>?,
) {
    fun toDefinition() = Definition(
        antonyms = this.antonyms ?: emptyList(),
        definition = this.definition,
        example = this.example,
        synonyms = this.synonyms ?: emptyList(),
    )
}