package cl.jaa.doggyapp.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import cl.jaa.doggyapp.commons.DoggyAppException
import cl.jaa.doggyapp.domain.entities.DogBreedEntity
import cl.jaa.doggyapp.domain.repository.DogBreedRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class DetailDogBreedViewModelTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()
    private val testDispatcher = TestCoroutineDispatcher()

    @Mock
    private lateinit var mockRepository: DogBreedRepository

    @Mock
    private lateinit var mockObserver: Observer<DetailDogBreedViewState>

    private lateinit var SUTDetailViewModel: DetailDogBreedViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        SUTDetailViewModel = DetailDogBreedViewModel(mockRepository)
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun `given expection when getBreedInfo then LoadingState`() = runBlockingTest {
        //given
        val breedName = "Bulldog"
        //when

        SUTDetailViewModel.getBreedInfo(breedName)
        SUTDetailViewModel.viewState.observeForever(mockObserver)

        Mockito.verify(mockRepository).getDogBreedInfo(breedName)
        Mockito.verify(mockObserver)
            .onChanged(ArgumentMatchers.any(DetailDogBreedViewState.LoadingState::class.java))

    }
    @Test
    fun `given breedName when getBreedInfo then successViewState`() = runBlockingTest {
        //given
        val breedName = "Bulldog"
        val result =
            DogBreedEntity(
                "Bulldog",
                listOf("French", "English"),
                listOf("https://image.com")
            )
        //when
        Mockito.`when`(mockRepository.getDogBreedInfo(breedName)).thenReturn(result)

        SUTDetailViewModel.getBreedInfo(breedName)
        SUTDetailViewModel.viewState.observeForever(mockObserver)

        Mockito.verify(mockRepository).getDogBreedInfo(breedName)
        Mockito.verify(mockObserver)
            .onChanged(ArgumentMatchers.any(DetailDogBreedViewState.SuccessState::class.java))

    }

    @Test
    fun `given expection when getBreedInfo then NoResultState`() = runBlockingTest {
        //given
        val breedName = "Bulldog"
        //when
        Mockito.`when`(mockRepository.getDogBreedInfo(breedName))
            .thenThrow(DoggyAppException.NotFoundException)

        SUTDetailViewModel.getBreedInfo(breedName)
        SUTDetailViewModel.viewState.observeForever(mockObserver)

        Mockito.verify(mockRepository).getDogBreedInfo(breedName)
        Mockito.verify(mockObserver)
            .onChanged(ArgumentMatchers.any(DetailDogBreedViewState.NoResultState::class.java))

    }
}