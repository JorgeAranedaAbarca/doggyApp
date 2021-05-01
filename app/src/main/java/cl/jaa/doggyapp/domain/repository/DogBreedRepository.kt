package cl.jaa.doggyapp.domain.repository

import cl.jaa.doggyapp.commons.DoggyAppException
import cl.jaa.doggyapp.data.api.DogBreedService
import cl.jaa.doggyapp.domain.entities.DogBreedEntity


interface DogBreedRepository {
    @Throws(DoggyAppException::class)
    suspend fun getDogBreedInfo(breedName: String): DogBreedEntity?

    @Throws(DoggyAppException::class)
    suspend fun getBreeds(): List<DogBreedEntity>
}

class DefaultDogBreedRepository(private val service: DogBreedService) : DogBreedRepository {

    override suspend fun getDogBreedInfo(breedName: String): DogBreedEntity? {
        try {
            val images = getImages(breedName)
            val subBreeds = getSubBreed(breedName)
            return DogBreedEntity(breedName, subBreeds, images)

        } catch (ex: Exception) {
            throw DoggyAppException.fromException(ex)
        }
    }

    private suspend fun getSubBreed(breedName: String): List<String> {
        try {
            return service.getSubDogBreed(breedName).message

        } catch (ex: Exception) {
            throw DoggyAppException.fromException(ex)
        }
    }

    override suspend fun getBreeds(): List<DogBreedEntity> {
        try {
            val list = mutableListOf<DogBreedEntity>()
            service.getBreeds().message.map {
                list.add(DogBreedEntity(it))
            }
            return list

        } catch (ex: Exception) {
            throw DoggyAppException.fromException(ex)
        }
    }

    private suspend fun getImages(breedName: String): List<String> {
        try {
            return service.getPicturesByDogBreedName(breedName).message

        } catch (ex: Exception) {
            throw DoggyAppException.fromException(ex)
        }
    }
}


