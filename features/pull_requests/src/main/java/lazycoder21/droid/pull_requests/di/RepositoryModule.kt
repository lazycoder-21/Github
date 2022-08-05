package lazycoder21.droid.pull_requests.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import lazycoder21.droid.pull_requests.data.repository.GithubPullRequestRepository
import lazycoder21.droid.pull_requests.domain.repository.IGithubPullRequestRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun providePRRepo(repo: GithubPullRequestRepository): IGithubPullRequestRepository
}