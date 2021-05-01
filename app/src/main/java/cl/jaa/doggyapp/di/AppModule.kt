package cl.jaa.doggyapp.di

import cl.jaa.doggyapp.domain.repository.DefaultDogBreedRepository
import cl.jaa.doggyapp.domain.repository.DogBreedRepository
import cl.jaa.doggyapp.ui.detail.DetailDogBreedViewModel
import cl.jaa.doggyapp.ui.main.MainViewModel
import cl.jaa.doggyapp.ui.welcome.WelcomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {
    single<DogBreedRepository> { DefaultDogBreedRepository(get()) }
    viewModel { WelcomeViewModel() }
    viewModel { MainViewModel(get()) }
    viewModel { DetailDogBreedViewModel(get()) }
}

