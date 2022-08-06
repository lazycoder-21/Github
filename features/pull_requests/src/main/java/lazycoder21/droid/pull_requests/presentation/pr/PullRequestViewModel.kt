package lazycoder21.droid.pull_requests.presentation.pr

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import lazycoder21.droid.common.constant.DefaultValues
import lazycoder21.droid.common.enitity.Resource
import lazycoder21.droid.pull_requests.domain.api_param_model.PullRequestParam
import lazycoder21.droid.pull_requests.domain.model.PullRequest
import lazycoder21.droid.pull_requests.domain.use_case.PullRequestUseCase
import javax.inject.Inject

@HiltViewModel
class PullRequestViewModel @Inject constructor(
    private val useCase: PullRequestUseCase,
) : ViewModel() {

    var pageNo: Int = 0
        private set

    private val _pullRequests: MutableList<PullRequest> = mutableListOf()
    private val _pullRequestsLiveData = MutableLiveData<Resource<List<PullRequest>>>()
    val pullRequestLiveData: LiveData<Resource<List<PullRequest>>> = _pullRequestsLiveData

    fun fetchPullRequest(
        param: PullRequestParam = PullRequestParam(),
    ) = viewModelScope.launch {
        if (param.isInitialLoading) resetPageNo()

        ++pageNo
        useCase.fetchPullRequests(param.copy(pageNo = pageNo)).collect {
            _pullRequestsLiveData.value = if (it is Resource.Success) {
                _pullRequests.addAll(it.data)
                Resource.Success(_pullRequests)
            } else {
                it
            }
        }
    }

    private fun resetPageNo() {
        pageNo = DefaultValues.PAGE_NO.minus(1)
        _pullRequests.clear()
    }
}