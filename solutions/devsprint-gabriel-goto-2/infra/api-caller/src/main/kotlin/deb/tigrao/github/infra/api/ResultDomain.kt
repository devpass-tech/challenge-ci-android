package deb.tigrao.github.infra.api

sealed class ResultDomain<out S, out E> {
    data class Success<out S>(val data: S) : ResultDomain<S, Nothing>()
    data class Error<E>(val errorResult: E) : ResultDomain<Nothing, E>()

    fun <T> map(success: (S) -> T, error: (E) -> T): T = when (this) {
        is Success -> success(data)
        is Error -> error(errorResult)
    }

    fun <T, V> transformMap(success: (S) -> T, error: (E) -> V): ResultDomain<T, V> = when (this) {
        is Success -> Success(success(data))
        is Error -> Error(error(errorResult))
    }

    fun mapSuccess() = map({
        it
    }) {
        null
    }

    fun onSuccess(block: (S) -> Unit): ResultDomain<S, E> {
        if (this is Success) {
            block(this.data)
        }

        return this
    }

    fun onError(block: (E) -> Unit): ResultDomain<S, E> {
        if (this is Error) {
            block(this.errorResult)
        }

        return this
    }

    fun onComplete(block: () -> Unit): ResultDomain<S, E> {
        block()

        return this
    }
}
