package lazycoder21.droid.common.utils

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
    } catch (e: SocketTimeoutException) {
        error.invoke(StringHandler.NormalString("Slow Internet"))
    } catch (e: HttpException) {
        error.invoke(
            StringHandler.NormalString(
                e.localizedMessage ?: "An unexpected error occurred"
            )
        )
    } catch (e: IOException) {
        error.invoke(StringHandler.NormalString("Couldn't reach server. Check your internet connection."))
    } catch (e: Exception) {
        val so = e
        error.invoke(StringHandler.NormalString(e.message ?: "Something went wrong"))
    }
}

fun <T> Response<T>.errorMessage(): StringHandler {
    val data = Gson().fromJson(
        this@errorMessage.errorBody()?.charStream(), RetrofitErrorMessage::class.java
    )
    return StringHandler.NormalString(data.message)
}