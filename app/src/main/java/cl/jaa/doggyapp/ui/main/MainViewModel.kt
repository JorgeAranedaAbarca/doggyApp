package cl.jaa.doggyapp.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.jaa.doggyapp.commons.DoggyAppException
import cl.jaa.doggyapp.domain.repository.DogBreedRepository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: DogBreedRepository) : ViewModel() {
    private val TAG: String = MainViewModel::class.java.simpleName

    private val _viewState = MutableLiveData<MainViewState>()
    val viewState: LiveData<MainViewState>
        get() = _viewState

    fun getDogBreeds() {
        _viewState.postValue(MainViewState.LoadingState)
        viewModelScope.launch {
            try {
                val list = repository.getBreeds().map {
                    it.toViewObject()
                }
                _viewState.postValue(MainViewState.SuccessState(list))

            } catch (ex: DoggyAppException) {
                when (ex) {
                    is DoggyAppException.NotFoundException -> {
                        _viewState.postValue(MainViewState.NoResultState("Error interno, por favor intente mas tarde"))
                        Log.d(TAG, ex.toString())

                    }
                }
            }
        }
    }
}