package com.newbieloper.millie.data.repository

import com.google.gson.Gson
import com.newbieloper.millie.data.Result
import com.newbieloper.millie.data.remote.ErrorBody
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

open class BaseRepository @Inject constructor(
    val dispatcher: CoroutineDispatcher,
    val gson: Gson
) {
    inline fun <reified T : Any> callToFlow(action: Response<T>): Flow<Result<T>> {
        return flow {
            action.run {
                if (isSuccessful) {
                    emit(Result.Success(body() as T) as Result<T>)
                } else {
                    emit(Result.ErrorResponse(gson.fromJson(errorBody()?.string(), ErrorBody::class.java)) as Result<T>)
                }
            }
        }.catch {
            Timber.e(it)
            emit(Result.Error(it as Exception))
        }.flowOn(dispatcher)
    }
}