package th.ac.up.melondev.melonpoke.utill

interface NetworkCallback<T> {
    fun onSuccess(data :T?)
    fun onError()
    fun onLoading()
}