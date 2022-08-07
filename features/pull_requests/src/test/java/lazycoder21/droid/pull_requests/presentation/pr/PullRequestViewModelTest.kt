package lazycoder21.droid.pull_requests.presentation.pr

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import lazycoder21.droid.common.enitity.Resource
import lazycoder21.droid.pull_requests.CoroutineTestRule
import lazycoder21.droid.pull_requests.domain.api_param_model.PullRequestParam
import lazycoder21.droid.pull_requests.domain.model.PullRequest
import lazycoder21.droid.pull_requests.domain.use_case.PullRequestUseCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class PullRequestViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutineRule = CoroutineTestRule()

    private val useCase: PullRequestUseCase = mockk(relaxed = true)
    private val viewModel = PullRequestViewModel(useCase)

    @Before
    fun before() {
        viewModel.pullRequestLiveData.observeForever {}
    }

    @Test
    fun `fetchPullRequests test, for inital call page no should be 1`() = runBlocking {
        viewModel.fetchPullRequest(PullRequestParam(isInitialLoading = false))
        viewModel.fetchPullRequest(PullRequestParam(isInitialLoading = true))

        coVerify {
            useCase.fetchPullRequests(any())
        }
        assertTrue(viewModel.pageNo == 1)
    }

    @Test
    fun `fetchPullRequests should increase page no in each call`() {
        viewModel.fetchPullRequest(PullRequestParam(isInitialLoading = false))
        viewModel.fetchPullRequest(PullRequestParam(isInitialLoading = false))
        viewModel.fetchPullRequest(PullRequestParam(isInitialLoading = false))

        assertTrue(viewModel.pageNo == 3)
    }

    @Test
    fun `fetchPullRequests should emit list we get from usecase is initalLoading`() = runBlocking {
        val list = listOf<PullRequest>(PullRequest(), PullRequest())
        coEvery { useCase.fetchPullRequests(any()) } returns flowOf(Resource.Success(list))

        viewModel.fetchPullRequest(PullRequestParam(isInitialLoading = true))

        assertEquals((viewModel.pullRequestLiveData.value as Resource.Success).data, list)
    }

    @Test
    fun `fetchPullRequests test ,last values in livedata should be equal to list from usecase`() =
        runBlocking {
            val list = listOf<PullRequest>(PullRequest(), PullRequest())
            coEvery { useCase.fetchPullRequests(any()) } returns flowOf(Resource.Success(list))

            viewModel.fetchPullRequest(PullRequestParam(isInitialLoading = true))
            viewModel.fetchPullRequest(PullRequestParam(isInitialLoading = false))

            val actual = (viewModel.pullRequestLiveData.value as Resource.Success).data
            val expected = mutableListOf<PullRequest>()
            for (i in actual.indices.reversed()) {
                if (expected.size < list.size) {
                    expected.add(actual[i])
                } else {
                    break
                }
            }

            assertEquals(expected, list)
        }
}