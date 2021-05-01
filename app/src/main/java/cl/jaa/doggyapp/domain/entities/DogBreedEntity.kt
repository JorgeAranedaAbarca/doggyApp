package cl.jaa.doggyapp.domain.entities

data class DogBreedEntity(
    val breedName: String,
    val subBreed: List<String>? = null,
    val images: List<String>? = null

)