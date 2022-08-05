package lazycoder21.droid.pull_requests.presentation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import lazycoder21.droid.common.enitity.Resource
import lazycoder21.droid.common.utils.showErrorMessage
import lazycoder21.droid.pull_requests.R
import lazycoder21.droid.pull_requests.domain.model.GithubPullRequest

@AndroidEntryPoint
class PullRequestActivity : AppCompatActivity() {

    private val viewModel: PullRequestViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pull_request)

        observeLiveData()
        loadData()
    }

    private fun loadData() {
        viewModel.fetchPullRequest()
    }

    private fun observeLiveData() {
        viewModel.pullRequest.observe(this) {
            when (it) {
                is Resource.Error -> showErrorMessage(it.message.asString(this))
                is Resource.Loading -> updateLoadingState(it.isLoading)
                is Resource.Success -> onSuccessList(it.data)
            }
        }
    }

    private fun onSuccessList(data: List<GithubPullRequest>) {

    }

    private fun updateLoadingState(isLoading: Boolean) {

    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.pullRequest.removeObservers(this)
    }

    companion object {
        fun Activity.navigateToPullRequestActivity() {
            val source = this
            val destination = PullRequestActivity::class.java

            source.startActivity(
                Intent(
                    source, destination
                )
            )
        }
    }
}