package cl.jaa.doggyapp.ui.main

import cl.jaa.doggyapp.domain.entities.DogBreedEntity

data class DogBreedVO(
    val breedName : String? = ""
)
fun DogBreedEntity.toViewObject() = DogBreedVO(
    breedName = breedName
)

