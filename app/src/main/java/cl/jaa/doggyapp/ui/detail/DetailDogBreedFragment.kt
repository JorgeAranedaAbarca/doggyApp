package cl.jaa.doggyapp.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import cl.jaa.doggyapp.databinding.DetailDogBreedFragmentBinding

class DetailDogBreedFragment : Fragment() {

    private lateinit var bind: DetailDogBreedFragmentBinding
    private lateinit var vo : DetailDogBreedVO

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = DetailDogBreedFragmentBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}