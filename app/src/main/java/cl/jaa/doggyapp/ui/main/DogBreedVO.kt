package cl.jaa.doggyapp.ui.main

import cl.jaa.doggyapp.domain.entities.DogBreed

data class DogBreedVO(
    val breedName : String? = ""
)
fun DogBreed.toViewObject() = DogBreedVO(
    breedName = breedName
)

