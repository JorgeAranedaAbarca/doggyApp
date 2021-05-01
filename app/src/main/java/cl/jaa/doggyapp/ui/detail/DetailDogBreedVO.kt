package cl.jaa.doggyapp.ui.detail

import android.text.SpannableStringBuilder
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
    val descr = ""
    if (subBreed != null) {
        if (subBreed.isNotEmpty()) {
            descr.plus("Existen ${subBreed.size} subraza de $breedName; ")

            for ((idx, value) in subBreed.withIndex()) {
                descr.plus(value)
                if(idx < subBreed.size){
                    descr.plus("-")
                }
            }

        } else {
            descr.plus("No existen subtipos para $breedName")

        }
    }
    return descr
}
