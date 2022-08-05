package lazycoder21.droid.pull_requests.presentation

import androidx.lifecycle.ViewModel
import lazycoder21.droid.pull_requests.domain.use_case.GithubUseCase
import lazycoder21.droid.pull_requests.utils.constants.GithubPRStatus

class PullRequestViewModel(
    private val useCase: GithubUseCase
) : ViewModel() {

    fun getPullRequest(
        userId: String,
        repositoryName: String,
        status: GithubPRStatus,
    ) {

    }
}