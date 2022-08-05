package lazycoder21.droid.pull_requests.presentation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import lazycoder21.droid.pull_requests.R

class PullRequestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pull_request)
    }

    companion object {
        fun navigate(host: Activity) {
            host.startActivity(
                Intent(
                    host, PullRequestActivity::class.java
                )
            )
        }
    }
}