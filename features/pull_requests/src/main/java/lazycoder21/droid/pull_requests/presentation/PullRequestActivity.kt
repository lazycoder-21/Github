package lazycoder21.droid.pull_requests.presentation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import lazycoder21.droid.pull_requests.R

@AndroidEntryPoint
class PullRequestActivity : AppCompatActivity() {

    private val viewModel: PullRequestViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pull_request)

        viewModel.fetchPullRequest()
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