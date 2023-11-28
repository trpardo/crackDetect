package com.pardo.crackdetect.core.utils

sealed class State<T>(val message: String? = null, val data: T? = null) {
    class Initial<T>(data: T? = null) : State<T>(data = data)

    class Active<T>(data: T? = null) : State<T>(data = data)

    class Loading<T>(data: T? = null) : State<T>(data = data)

    class Success<T>(data: T? = null) : State<T>(data = data)

    class Error<T>(message: String? = null, data: T? = null) :
        State<T>(message = message, data = data)
}

abstract class ViewState<T>(
    val state: State<T>
) {
    val isLoading get() = this.state is State.Loading

    val isSuccess get() = this.state is State.Success

    val isActive get() = this.state is State.Active

    val isError get() = this.state is State.Error
}
