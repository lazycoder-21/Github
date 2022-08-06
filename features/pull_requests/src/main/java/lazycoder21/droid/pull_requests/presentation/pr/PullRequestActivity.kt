package lazycoder21.droid.pull_requests.presentation.pr

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import lazycoder21.droid.common.enitity.Resource
import lazycoder21.droid.common.recycler_view.InfiniteScrollListener
import lazycoder21.droid.common.utils.isFirstPage
import lazycoder21.droid.common.utils.showErrorMessage
import lazycoder21.droid.common.utils.showIf
import lazycoder21.droid.pull_requests.databinding.ActivityPullRequestBinding
import lazycoder21.droid.pull_requests.domain.api_param_model.PullRequestParam
import lazycoder21.droid.pull_requests.domain.model.PullRequest
import lazycoder21.droid.pull_requests.presentation.adapter.PullRequestRvAdapter
import lazycoder21.droid.pull_requests.presentation.adapter.factory.ItemTypeFactory

@AndroidEntryPoint
class PullRequestActivity : AppCompatActivity() {

    private val viewModel: PullRequestViewModel by viewModels()
    private var _binding: ActivityPullRequestBinding? = null
    private val adapter = PullRequestRvAdapter(ItemTypeFactory())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityPullRequestBinding.inflate(layoutInflater).apply {
            setContentView(this.root)
        }

        initRecyclerView()
        observeLiveData()
        loadData()
    }

    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        _binding?.recyclerView?.apply {
            this.layoutManager = layoutManager
            adapter = this@PullRequestActivity.adapter
            addOnScrollListener(object : InfiniteScrollListener(layoutManager) {
                override fun onLoadMore() {
                    this@PullRequestActivity.adapter.addLoadingState()
                    fetchPullRequest(isInitialLoad = false)
                }

                override fun isDataLoading(): Boolean {
                    val state = viewModel.pullRequestLiveData.value as? Resource.Loading
                    return state?.isLoading == true
                }
            })
        }
    }

    private fun fetchPullRequest(isInitialLoad: Boolean = false) {
        viewModel.fetchPullRequest(PullRequestParam(isInitialLoading = isInitialLoad))
    }

    private fun loadData() {
        fetchPullRequest(isInitialLoad = true)
    }

    private fun observeLiveData() {
        viewModel.pullRequestLiveData.observe(this) {
            when (it) {
                is Resource.Error -> showErrorMessage(it.message.asString(this))
                is Resource.Loading -> updateLoadingState(it.isLoading)
                is Resource.Success -> onSuccessList(it.data)
            }
        }
    }

    private fun onSuccessList(list: List<PullRequest>) {
        adapter.submitList(list)
    }

    private fun updateLoadingState(isLoading: Boolean) {
        if (viewModel.pageNo.isFirstPage)
            _binding?.progressBarRoot?.progressBar?.showIf(isLoading)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        viewModel.pullRequestLiveData.removeObservers(this)
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