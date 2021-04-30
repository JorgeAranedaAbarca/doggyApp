package cl.jaa.doggyapp.ui.main

sealed class MainViewState {

    object LoadingState : MainViewState()

    class NoResultState(val message: String) : MainViewState()

    class SuccessState(val items: List<DogBreedVO>) : MainViewState()
}