package cl.jaa.doggyapp.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import cl.jaa.doggyapp.databinding.DetailDogBreedFragmentBinding
import com.squareup.picasso.Picasso
import org.koin.android.viewmodel.ext.android.viewModel

class DetailDogBreedFragment : Fragment() {

    private lateinit var bind: DetailDogBreedFragmentBinding
    private val viewModel by viewModel<DetailDogBreedViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = DetailDogBreedFragmentBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.viewState.observe(viewLifecycleOwner) {
            processViewState(it)
        }
        val arg = arguments?.getString("breedNameArg")
        arg?.let {
            viewModel.getBreedInfo(it)
        }

    }

    private fun processViewState(viewState: DetailDogBreedViewState) {
        when (viewState) {
            is DetailDogBreedViewState.LoadingState -> showLoading()
            is DetailDogBreedViewState.NoResultState -> showErrorView(viewState.message)
            is DetailDogBreedViewState.SuccessState -> onSuccess(viewState.items)
        }
    }

    private fun onSuccess(vo: DetailDogBreedVO) {
        bind.tvBreedName.text = vo.breedName
        bind.tvBreedDescription.text = vo.description
        vo.listImages?.let {
            if (it.isNotEmpty()) {
                bind.cvDogBreedImages.setImageListener { position, imageView ->
                    hideLoading()
                    Picasso.get().load(it[position]).into(imageView)
                }
                bind.cvDogBreedImages.pageCount = it.size
            }
        }


    }

    private fun showErrorView(message: String) {
        hideLoading()
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    private fun showLoading() {
        bind.pbSearchResult.visibility = View.VISIBLE
        bind.clInfoBreed.visibility = View.GONE
    }

    private fun hideLoading() {
        bind.pbSearchResult.visibility = View.GONE
        bind.clInfoBreed.visibility = View.VISIBLE
    }

}