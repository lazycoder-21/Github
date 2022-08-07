package lazycoder21.droid.pull_requests.presentation.pr

import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.runBlocking
import lazycoder21.droid.pull_requests.CoroutineTestRule
import lazycoder21.droid.pull_requests.domain.api_param_model.PullRequestParam
import lazycoder21.droid.pull_requests.domain.use_case.PullRequestUseCase
import org.junit.Rule
import org.junit.Test

class PullRequestViewModelTest {

    @get:Rule
    var coroutineRule = CoroutineTestRule()

    private val useCase: PullRequestUseCase = mockk(relaxed = true)
    private val viewModel = PullRequestViewModel(useCase)

    fun fo() {

    }

    @Test
    fun `fetchPullRequests test, for inital call page no should be 1`() = runBlocking {
        viewModel.fetchPullRequest(PullRequestParam(isInitialLoading = true))

        coVerify {
            useCase.fetchPullRequests(any())
        }
        assertTrue(viewModel.pageNo == 1)
    }
}