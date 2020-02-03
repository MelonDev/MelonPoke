package th.ac.up.melondev.melonpoke.utill

data class NetworkResponse<out T>(val status: Status,
                           val data: T?,
                           val msg: String?) {
    companion object {
        fun <T> success(data: T?): NetworkResponse<T> {
            return NetworkResponse(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T? = null): NetworkResponse<T> {
            return NetworkResponse(Status.ERROR, data, msg)
        }

        fun <T> loading(data: T? = null): NetworkResponse<T> {
            return NetworkResponse(Status.LOADING, data, null)
        }
    }
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}