package cl.jaa.doggyapp.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.jaa.doggyapp.commons.DoggyAppException
import cl.jaa.doggyapp.domain.repository.DogBreedRepository
import kotlinx.coroutines.launch

class DetailDogBreedViewModel(private val repository: DogBreedRepository) : ViewModel() {


    private val TAG: String = DetailDogBreedViewModel::class.java.simpleName

    private val _viewState = MutableLiveData<DetailDogBreedViewState>()
    val viewState: LiveData<DetailDogBreedViewState>
        get() = _viewState

    fun getBreedInfo(breedName: String) {
        _viewState.postValue(DetailDogBreedViewState.LoadingState)
        //get images
        viewModelScope.launch {
            try {
                val vo = repository.getDogBreedInfo(breedName)?.toDetailDogBreedVO()
                vo?.let {
                    _viewState.postValue(DetailDogBreedViewState.SuccessState(vo))
                }
            } catch (ex: DoggyAppException) {
                when (ex) {
                    is DoggyAppException.NotFoundException -> {
                        _viewState.postValue(DetailDogBreedViewState.NoResultState("Error interno, por favor intente mas tarde"))
                        Log.d(TAG, ex.toString())

                    }
                }
            }


        }
    }

}