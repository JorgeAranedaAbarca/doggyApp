package cl.jaa.doggyapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class DetailDogBreedViewModel : ViewModel() {


    private val TAG: String = DetailDogBreedViewModel::class.java.simpleName

    val detailDogBreed = MutableLiveData<DetailDogBreedVO>()
    private val _viewState = MutableLiveData<DetailDogBreedViewState>()
    val viewState: LiveData<DetailDogBreedViewState>
        get() = _viewState

    fun getBreedInfo(breedName: String) {
        _viewState.postValue(DetailDogBreedViewState.LoadingState)
        //get images
        viewModelScope.launch {
//            val vo = DetailDogBreedVO("Bulldog",getDesc)
//            _viewState.postValue(DetailDogBreedViewState.SuccessState(vo))


        }
    }

    fun getImages(breedName: String) : List<String>{
        //call api
        return listOf()
    }


}