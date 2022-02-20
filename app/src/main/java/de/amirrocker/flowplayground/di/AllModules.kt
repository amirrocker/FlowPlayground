package de.amirrocker.flowplayground.di

import androidx.room.Room
import de.amirrocker.flowplayground.BuildConfig
import de.amirrocker.flowplayground.data.repository.search.WordInfoRepositoryImpl
import de.amirrocker.flowplayground.data.repository.search.local.WordInfoDatabase
import de.amirrocker.flowplayground.data.repository.search.remote.DictionaryApi
import de.amirrocker.flowplayground.domain.usecase.search.GetWordInfo
import de.amirrocker.flowplayground.presentation.search.SearchViewModel
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val WORD_INFO_DATABASE = "wordinfodb"

val presentationModule = module {
    single { SearchViewModel(get()) }
}


val databaseModule = module {
    single {
        Room.databaseBuilder(androidContext(), WordInfoDatabase::class.java, WORD_INFO_DATABASE)
            .fallbackToDestructiveMigration()
            .build()
    }
    factory { get<WordInfoDatabase>().wordInfoDao() }
}

val repositoryModule = module {
    factory { WordInfoRepositoryImpl(get(), get()) }
}

val domainModule = module {
    factory { GetWordInfo(get() as WordInfoRepositoryImpl) }
}

val networkModule = module {
    single { GsonConverterFactory.create() as Converter.Factory }
    single { HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY) as Interceptor }

    single {
        OkHttpClient.Builder().apply {
            if(BuildConfig.DEBUG) addInterceptor(get() as Interceptor)
                .callTimeout(10, TimeUnit.SECONDS)
        }.build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(DictionaryApi.BASE_URL)
            .client(get())
            .addConverterFactory(get())
            .build()
    }
    single { get<Retrofit>().create(DictionaryApi::class.java) }

}


