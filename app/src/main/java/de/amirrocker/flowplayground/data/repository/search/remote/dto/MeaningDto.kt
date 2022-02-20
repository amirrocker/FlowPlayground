package de.amirrocker.flowplayground.data.repository.search.remote.dto

import de.amirrocker.flowplayground.data.Meaning

data class MeaningDto(
    val definitions: List<DefinitionDto>,
    val partOfSpeech: String
) {
    fun toMeaning() = Meaning(
        definitions = definitions.map { it.toDefinition() },
        partOfSpeech = partOfSpeech
    )
}