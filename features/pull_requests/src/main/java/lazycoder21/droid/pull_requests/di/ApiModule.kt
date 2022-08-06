package lazycoder21.droid.pull_requests.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import lazycoder21.droid.common.utils.buildApi
import lazycoder21.droid.pull_requests.data.remote.PullRequestApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun providePRApi(): PullRequestApi = buildApi()
}