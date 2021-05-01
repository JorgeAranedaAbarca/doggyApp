package cl.jaa.doggyapp.ui.detail

sealed class DetailDogBreedViewState {

    object LoadingState : DetailDogBreedViewState()

    class NoResultState(val message: String) : DetailDogBreedViewState()

    class SuccessState(val items: DetailDogBreedVO) : DetailDogBreedViewState()
}