package de.amirrocker.flowplayground.core

typealias Message = String
typealias SimpleResource = Resource<Unit>

sealed class Resource<T>(val data : T? = null, val message: Message = "") {
    class Loading<T>(data:T? = null) : Resource<T>(data)
    class Success<T>(data:T? = null) : Resource<T>(data)
    class Error<T>(message:Message, data: T? = null) : Resource<T>(data, message)
}