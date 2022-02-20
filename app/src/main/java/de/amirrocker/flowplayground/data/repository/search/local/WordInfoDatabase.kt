package de.amirrocker.flowplayground.data.repository.search.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import de.amirrocker.flowplayground.data.repository.search.local.entity.WordInfoEntity

@Database(
    entities = [WordInfoEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class WordInfoDatabase : RoomDatabase() {
    abstract fun wordInfoDao() : WordInfoDao
}