package ch.morgias.cookgenda.android.network

sealed interface RequestState {
    data class Success<out T>(val result: T) : RequestState
    data object Loading : RequestState
    data object Error : RequestState
}