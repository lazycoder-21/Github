package lazycoder21.droid.pull_requests.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import lazycoder21.droid.common.constant.DefaultValues
import lazycoder21.droid.common.enitity.Resource
import lazycoder21.droid.pull_requests.domain.model.GithubPullRequest
import lazycoder21.droid.pull_requests.domain.use_case.GithubPullRequestUseCase
import lazycoder21.droid.pull_requests.utils.constants.GithubPRStatus
import javax.inject.Inject

@HiltViewModel
class PullRequestViewModel @Inject constructor(
    private val useCase: GithubPullRequestUseCase
) : ViewModel() {

    private val _pullRequests = MutableLiveData<Resource<List<GithubPullRequest>>>()
    val pullRequest: LiveData<Resource<List<GithubPullRequest>>> = _pullRequests

    fun fetchPullRequest(
        userId: String = DefaultValues.USER_ID,
        repositoryName: String = DefaultValues.REPO_NAME,
        status: GithubPRStatus = GithubPRStatus.Default,
    ) = viewModelScope.launch {
        useCase.fetchPullRequests(userId, repositoryName, status).collect {
            _pullRequests.value = it
        }
    }

}