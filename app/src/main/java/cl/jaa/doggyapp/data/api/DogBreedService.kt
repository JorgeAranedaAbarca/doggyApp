package cl.jaa.doggyapp.data.api

import cl.jaa.doggyapp.data.response.DogBreedResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface DogBreedService {

    @GET("breeds/list")
    suspend fun getBreeds(): DogBreedResponse

    @GET("breed/{name}/images")
    suspend fun getPicturesByDogBreedName(@Path("name") name: String): DogBreedResponse

    @GET("breed/{breedName}/list")
    suspend fun getSubDogBreed(@Path("breedName") breedName: String): DogBreedResponse

}