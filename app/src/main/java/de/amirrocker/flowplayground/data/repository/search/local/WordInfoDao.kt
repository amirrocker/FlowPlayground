package de.amirrocker.flowplayground.data.repository.search.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import de.amirrocker.flowplayground.data.Word
import de.amirrocker.flowplayground.data.WordInfo
import de.amirrocker.flowplayground.data.repository.search.local.entity.WordInfoEntity

@Dao
interface WordInfoDao {

    @Query("DELETE FROM wordinfoentity WHERE word IN(:words)")
    suspend fun deleteWordInfos(words:List<String>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWordInfos(wordInfos:List<WordInfoEntity>)

    @Query("SELECT * FROM wordinfoentity WHERE word LIKE '%' || :word ")
    suspend fun getWordInfos(word:Word):List<WordInfoEntity>
}