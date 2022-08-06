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
import lazycoder21.droid.common.utils.isFirst
import lazycoder21.droid.common.utils.showErrorMessage
import lazycoder21.droid.common.utils.showIf
import lazycoder21.droid.pull_requests.databinding.ActivityPullRequestBinding
import lazycoder21.droid.pull_requests.domain.api_param_model.PullRequestParam
import lazycoder21.droid.pull_requests.domain.model.PullRequest
import lazycoder21.droid.pull_requests.presentation.adapter.PullRequestRvAdapter
import lazycoder21.droid.pull_requests.presentation.adapter.factory.ItemTypeFactory

@AndroidEntryPoint
class PullRequestActivity : AppCompatActivity() {

    private var pageNo: Int = 0
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
                    fetchPullRequest(++pageNo)
                }

                override fun isDataLoading(): Boolean {
                    val state = viewModel.pullRequest.value as? Resource.Loading
                    return state?.isLoading == true
                }
            })
        }
    }

    private fun fetchPullRequest(pageNo: Int) {
        viewModel.fetchPullRequest(PullRequestParam(pageNo = pageNo))
    }

    private fun loadData() {
        pageNo = 0
        fetchPullRequest(pageNo)
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

    private fun onSuccessList(list: List<PullRequest>) {
        if (pageNo.isFirst) {
            adapter.clearAndInsertItems(list)
            return
        }
        adapter.addItems(list)
    }

    private fun updateLoadingState(isLoading: Boolean) {
        if (pageNo.isFirst) {
            _binding?.progressBarRoot?.progressBar?.showIf(isLoading)
            return
        }
        if (!isLoading) return
        adapter.addLoadingState()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
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