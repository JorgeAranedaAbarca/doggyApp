package cl.jaa.doggyapp.domain.repository

import cl.jaa.doggyapp.commons.DoggyAppException
import cl.jaa.doggyapp.data.api.DogBreedService
import cl.jaa.doggyapp.domain.entities.DogBreed


interface DogBreedRepository {
    @Throws(DoggyAppException::class)
    suspend fun getDogBreedInfo(breedName: String): DogBreed?

    @Throws(DoggyAppException::class)
    suspend fun getBreeds(): List<DogBreed>
}

class DefaultDogBreedRepository(private val service: DogBreedService) : DogBreedRepository {

    override suspend fun getDogBreedInfo(breedName: String): DogBreed? {
        return try {


            val images = getImages(breedName)
            return DogBreed("")

        } catch (ex: Exception) {
            throw DoggyAppException.fromException(ex)
        }
    }

    override suspend fun getBreeds(): List<DogBreed> {
        try {
            val list = mutableListOf<DogBreed>()
            service.getBreeds().message.map {
                list.add(DogBreed(it))
            }
            return list

        } catch (ex: Exception) {
            throw DoggyAppException.fromException(ex)
        }
    }

    private fun getImages(breedName: String): List<String> {

        return listOf()
    }
}


