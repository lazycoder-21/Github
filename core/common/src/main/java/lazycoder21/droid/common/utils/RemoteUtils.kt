package lazycoder21.droid.common.utils

import android.util.Log
import com.google.gson.Gson
import lazycoder21.droid.common.enitity.RetrofitErrorMessage
import lazycoder21.droid.common.enitity.StringHandler
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.io.IOException
import java.net.SocketTimeoutException

inline fun <reified T> buildApi(
    baseUrl: String = "https://api.github.com/"
): T {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(
            GsonConverterFactory.create()
        )
        .client(okHttpClient)
        .build()
        .create()
}

suspend inline fun safeApiCall(
    block: suspend () -> Unit,
    error: (StringHandler) -> Unit,
) {
    try {
        block.invoke()
    } catch (e: Exception) {
        Log.d("Github", "safeApiCall: ${e.printStackTrace()}")
        error.invoke(
            StringHandler.ResourceString(
                when (e) {
                    is SocketTimeoutException -> R.string.slow_internet
                    is HttpException -> R.string.an_unexpected_error_occurred
                    is IOException -> R.string.check_your_internet_connection
                    else -> R.string.something_went_wrong
                }
            )
        )
    }
}

fun <T> Response<T>.errorMessage(): StringHandler {
    val data = Gson().fromJson(
        this@errorMessage.errorBody()?.charStream(), RetrofitErrorMessage::class.java
    )
    return StringHandler.NormalString(data.message)
}