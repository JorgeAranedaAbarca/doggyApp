package cl.jaa.doggyapp.data.response

import cl.jaa.doggyapp.domain.entities.DogBreed
import com.google.gson.annotations.SerializedName

data class DogBreedResponse(
//    @SerializedName("message")
//    val dogBreeds: List<DogBreed>,
    val message: List<String>,
    val status: String
)
