package lazycoder21.droid.pull_requests.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import lazycoder21.droid.common.constant.DefaultValues
import lazycoder21.droid.pull_requests.domain.use_case.GithubUseCase
import lazycoder21.droid.pull_requests.utils.constants.GithubPRStatus

class PullRequestViewModel(
    private val useCase: GithubUseCase
) : ViewModel() {

    fun fetchPullRequest(
        userId: String = DefaultValues.USER_ID,
        repositoryName: String = DefaultValues.REPO_NAME,
        status: GithubPRStatus = GithubPRStatus.Default,
    ) {
        viewModelScope.launch {
            useCase.fetchPullRequests()
        }
    }
}