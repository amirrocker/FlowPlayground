package de.amirrocker.flowplayground.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.amirrocker.flowplayground.data.Definition
import de.amirrocker.flowplayground.data.Meaning
import de.amirrocker.flowplayground.data.WordInfo

@Composable
fun WordInfoItem(wordInfo:WordInfo) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = wordInfo.origin,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = wordInfo.phonetic,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = wordInfo.word,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        wordInfo.meanings.forEachIndexed { index, meaning ->
            Text(
                text = meaning.partOfSpeech,
                modifier = Modifier.fillMaxWidth()
            )
            
            meaning.definitions.forEachIndexed { index, definition ->
                Text(
                    text = "${index+1}. definition: ${definition.definition}",
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = definition.example ?: "",
                    modifier = Modifier.fillMaxWidth()
                )
            }
            
        }

    }
}

@Preview
@Composable
fun testPreview() {
    WordInfoItem(
        wordInfo = WordInfo(
            listOf(
                Meaning(
                    listOf(
                        Definition(
                            listOf("Some Antonym", "Another One"),
                            definition = "A Definition",
                            example = "An example",
                            synonyms = listOf(
                                "A Synonym A", "A Synonym B",
                            )
                        )
                    ),
                    partOfSpeech = "Some Part of Speech"
                ),
                Meaning(
                    listOf(
                        Definition(
                            listOf("Some Antonym", "Another One"),
                            definition = "A Definition",
                            example = "An example",
                            synonyms = listOf(
                                "A Synonym A2", "A Synonym B2",
                            )
                        )
                    ),
                    partOfSpeech = "Some Part of Speech 2"
                )
            ),
            origin = "Some Origin",
            phonetic = "Some Phonetic",
            word = "A Word"
        )
    )
}