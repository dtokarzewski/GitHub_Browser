package pl.dtokarzewski.github.core.network.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import pl.dtokarzewski.github.core.network.GithubNetworkDataSource
import pl.dtokarzewski.github.core.network.RetrofitGithubDataSource
import pl.dtokarzewski.github.data.network.BuildConfig
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {

    companion object {

        @Provides
        @Singleton
        fun providesNetworkJson(): Json = Json {
            ignoreUnknownKeys = true
            prettyPrint = true
        }

        @Provides
        @Singleton
        fun provideOkHttp(): OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor()
                    .apply {
                        if (BuildConfig.DEBUG) {
                            setLevel(HttpLoggingInterceptor.Level.BODY)
                        }
                    }
            )
            .build()

        @OptIn(ExperimentalSerializationApi::class)
        @Provides
        fun provideRetrofit(
            networkJson: Json,
            okHttp: OkHttpClient
        ): Retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(
                networkJson.asConverterFactory("application/json".toMediaType())
            )
            .client(okHttp)
            .build()
    }

    @Binds
    abstract fun bindGithubNetworkDataSource(
        retrofitDataSource: RetrofitGithubDataSource
    ): GithubNetworkDataSource
}