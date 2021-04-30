package cl.jaa.doggyapp.ui.welcome

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WelcomeViewModel : ViewModel() {

    val welcomeText = MutableLiveData<String>()

    fun setWelcomeText(text: String) {
        welcomeText.postValue(text)
    }
}