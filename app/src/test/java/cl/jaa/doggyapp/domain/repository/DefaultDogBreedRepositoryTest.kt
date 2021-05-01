package cl.jaa.doggyapp.domain.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import cl.jaa.doggyapp.commons.DoggyAppException
import cl.jaa.doggyapp.data.api.DogBreedService
import cl.jaa.doggyapp.data.response.DogBreedResponse
import cl.jaa.doggyapp.domain.entities.DogBreedEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class DefaultDogBreedRepositoryTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()
    private val testDispatcher = TestCoroutineDispatcher()


    @Mock
    private lateinit var mockService: DogBreedService

    private lateinit var SUTRepository: DogBreedRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        SUTRepository = DefaultDogBreedRepository(mockService)
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun `given breedName when getDogBreedInfo then return DogBreedEntity`() = runBlockingTest {
        //given
        val breedName = "Bulldog"
        val resultSubBreed = listOf("French", "English")
        val resultPictures = listOf("https://image.com")
        val responseSubBreed = DogBreedResponse(resultSubBreed, "success")
        val responsePictures = DogBreedResponse(resultPictures, "success")
        val resultEntity = DogBreedEntity(
            "Bulldog",
            resultSubBreed,
            resultPictures
        )
        //when
        Mockito.`when`(mockService.getSubDogBreed(breedName)).thenReturn(responseSubBreed)
        Mockito.`when`(mockService.getPicturesByDogBreedName(breedName))
            .thenReturn(responsePictures)

        val result = SUTRepository.getDogBreedInfo(breedName)

        //then
        assert(result == resultEntity)
    }

    @Test
    fun `when getBreeds then return list dogEntity`() = runBlockingTest {
        val response = DogBreedResponse(listOf("Bulldog", "Akita"), "success")

        //when
        Mockito.`when`(mockService.getBreeds()).thenReturn(response)

        val result = SUTRepository.getBreeds()

        //then
        assert(result.isNotEmpty())
    }
}