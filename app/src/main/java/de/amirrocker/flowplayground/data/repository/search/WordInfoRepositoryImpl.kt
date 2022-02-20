package de.amirrocker.flowplayground.data.repository.search

import de.amirrocker.flowplayground.core.Resource
import de.amirrocker.flowplayground.data.Word
import de.amirrocker.flowplayground.data.WordInfo
import de.amirrocker.flowplayground.data.repository.search.local.WordInfoDao
import de.amirrocker.flowplayground.data.repository.search.remote.DictionaryApi
import de.amirrocker.flowplayground.domain.repository.search.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class WordInfoRepositoryImpl(
    val api: DictionaryApi,
    val dao: WordInfoDao
) : WordInfoRepository {

    override fun getWordInfo(word:Word): Flow<Resource<List<WordInfo>>> = flow {
        emit(Resource.Loading())

        val wordInfos = dao.getWordInfos(word).map {
            it.toWordInfo()
        }
        emit(Resource.Loading(wordInfos))

        try {
            val remoteWordInfos = api.getDictionary(word)
            if( remoteWordInfos.isNotEmpty() ) {
                dao.deleteWordInfos(remoteWordInfos.map { it.word })
                dao.insertWordInfos(remoteWordInfos.map { it.toWordInfoEntity() })
            }
        } catch (http:HttpException) {
            emit(Resource.Error(
                message = "unknown error: ${http.message()}",
                data = wordInfos
            ))
        } catch (ex: IOException) {
            emit(Resource.Error(
                message = "connection error: ${ex.message}",
                data = wordInfos
            ))
        }

        val newWordInfos = dao.getWordInfos(word).map { it.toWordInfo() }
        emit(Resource.Success(newWordInfos))

    }


}