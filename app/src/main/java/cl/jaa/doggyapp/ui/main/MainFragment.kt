package cl.jaa.doggyapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import cl.jaa.doggyapp.databinding.MainFragmentBinding
import org.koin.android.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {
    private lateinit var navController: NavController

    private lateinit var bind: MainFragmentBinding

    private val viewModel by viewModel<MainViewModel>()

    private lateinit var dogBreedAdapter: DogBreedAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = MainFragmentBinding.inflate(inflater, container, false)

        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        viewModel.viewState.observe(viewLifecycleOwner) {
            processViewState(it)
        }
        viewModel.getDogBreeds()
    }

    private fun processViewState(viewState: MainViewState) {
        when (viewState) {
            is MainViewState.LoadingState -> showLoading()
            is MainViewState.NoResultState -> showErrorView(viewState.message)
            is MainViewState.SuccessState -> onSuccess(viewState.items)
        }
    }

    private fun onSuccess(items: List<DogBreedVO>) {
        hideLoading()
        dogBreedAdapter = DogBreedAdapter(items, this::openDetailBreed)
        bind.rvDogBreed.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = dogBreedAdapter
        }
    }

    private fun openDetailBreed(vo: DogBreedVO) {
        val action =
            MainFragmentDirections.actionMainFragmentToDetailDogBreedFragment(vo.breedName.toString())
        navController.navigate(action)
    }

    private fun showErrorView(message: String) {
        hideLoading()
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()

    }

    private fun showLoading() {
        bind.pbSearchResult.visibility = VISIBLE
        bind.rvDogBreed.visibility = GONE
    }

    private fun hideLoading() {
        bind.pbSearchResult.visibility = GONE
        bind.rvDogBreed.visibility = VISIBLE
    }


}