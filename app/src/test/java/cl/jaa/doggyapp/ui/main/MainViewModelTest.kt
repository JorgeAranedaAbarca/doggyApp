package cl.jaa.doggyapp.ui.main

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
class MainViewModelTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()
    private val testDispatcher = TestCoroutineDispatcher()

    @Mock
    private lateinit var mockRepository: DogBreedRepository

    @Mock
    private lateinit var mockObserver: Observer<MainViewState>

    private lateinit var SUTMainViewModel: MainViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        SUTMainViewModel = MainViewModel(mockRepository)
        Dispatchers.setMain(testDispatcher)
    }


    @Test
    fun `when getDogBreeds then SuccesState`() = runBlockingTest {
        //when
        val result =
            listOf(
                DogBreedEntity("Bulldog"),
                DogBreedEntity("Akita"),
            )

        Mockito.`when`(mockRepository.getBreeds()).thenReturn(result)
        SUTMainViewModel.getDogBreeds()
        SUTMainViewModel.viewState.observeForever(mockObserver)

        Mockito.verify(mockRepository).getBreeds()
        Mockito.verify(mockObserver)
            .onChanged(ArgumentMatchers.any(MainViewState.SuccessState::class.java))


    }

    @Test
    fun `when throwException then NoResultState`() = runBlockingTest {
        //when
        val result =
            listOf(
                DogBreedEntity("Bulldog"),
                DogBreedEntity("Akita"),
            )

        Mockito.`when`(mockRepository.getBreeds()).thenThrow(DoggyAppException.NotFoundException)
        SUTMainViewModel.getDogBreeds()
        SUTMainViewModel.viewState.observeForever(mockObserver)

        Mockito.verify(mockRepository).getBreeds()
        Mockito.verify(mockObserver)
            .onChanged(ArgumentMatchers.any(MainViewState.NoResultState::class.java))


    }
}