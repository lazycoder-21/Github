package lazycoder21.droid.pull_requests.utils.constants

enum class PullRequestStatus(val status: String) {
    All("all"),
    Open("open"),
    Closed("closed"),
    Default(All.status),
}