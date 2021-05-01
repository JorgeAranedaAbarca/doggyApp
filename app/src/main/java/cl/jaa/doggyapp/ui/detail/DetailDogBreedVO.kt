package cl.jaa.doggyapp.ui.detail

import cl.jaa.doggyapp.domain.entities.DogBreedEntity

data class DetailDogBreedVO(
    val breedName: String,
    val listImages: List<String>? = null,
    val subBreeds: List<String>? = null,
    val description: String
)

fun DogBreedEntity.toDetailDogBreedVO() = DetailDogBreedVO(
    breedName = breedName,
    listImages = images,
    subBreeds = subBreed,
    description = getDescription(subBreed, breedName)
)

fun getDescription(subBreed: List<String>?, breedName: String): String {
    val descr = StringBuilder()
    if (subBreed != null) {
        if (subBreed.isNotEmpty()) {
            descr.append("Existen ${subBreed.size} subraza de $breedName; ")

            for ((idx, value) in subBreed.withIndex()) {
                descr.append(value)
                if (idx < subBreed.size) {
                    descr.append("-")
                }
            }

        } else {
            descr.append("No existen subtipos para $breedName")

        }
    }
    return descr.toString()
}
