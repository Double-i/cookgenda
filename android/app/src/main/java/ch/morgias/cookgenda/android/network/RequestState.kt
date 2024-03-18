package ch.morgias.cookgenda.android.network

sealed interface RequestState {
    class Success(result: String) : RequestState
    object Loading : RequestState
    object Error : RequestState
}