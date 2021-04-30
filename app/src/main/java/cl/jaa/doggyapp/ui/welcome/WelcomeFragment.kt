package cl.jaa.doggyapp.ui.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import cl.jaa.doggyapp.R
import cl.jaa.doggyapp.databinding.WelcomeFragmentBinding
import org.koin.android.viewmodel.ext.android.viewModel

class WelcomeFragment : Fragment() {

    private lateinit var navController: NavController
    private lateinit var bind: WelcomeFragmentBinding

    private val viewModel by viewModel<WelcomeViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = WelcomeFragmentBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        viewModel.welcomeText.observe(viewLifecycleOwner) { welcomeText ->
            setupWelcomeText(welcomeText)

        }
        viewModel.setWelcomeText(getString(R.string.txt_welcome))
        bind.btnGO.setOnClickListener {
            showDogBreeds()
        }

    }

    private fun showDogBreeds() {
        val action = WelcomeFragmentDirections.actionWelcomeFragmentToMainFragment()
        navController.navigate(action)
    }

    private fun setupWelcomeText(welcomeText: String?) {
        bind.tvWelcomeText.text = welcomeText
    }

}