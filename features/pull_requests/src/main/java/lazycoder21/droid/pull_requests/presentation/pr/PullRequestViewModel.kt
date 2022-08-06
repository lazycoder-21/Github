package lazycoder21.droid.pull_requests.presentation.pr

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import lazycoder21.droid.common.enitity.Resource
import lazycoder21.droid.pull_requests.domain.model.PullRequest
import lazycoder21.droid.pull_requests.domain.model.parms.PullRequestParams
import lazycoder21.droid.pull_requests.domain.use_case.GithubPullRequestUseCase
import javax.inject.Inject

@HiltViewModel
class PullRequestViewModel @Inject constructor(
    private val useCase: GithubPullRequestUseCase
) : ViewModel() {

    private val _pullRequests = MutableLiveData<Resource<List<PullRequest>>>()
    val pullRequest: LiveData<Resource<List<PullRequest>>> = _pullRequests

    fun fetchPullRequest(params: PullRequestParams = PullRequestParams()) = viewModelScope.launch {
        useCase.fetchPullRequests(params).collect {
            _pullRequests.value = it
        }
    }

}