package cl.jaa.doggyapp.ui.detail

data class DetailDogBreedVO(
    val breedName: String,
    val desc: String,
    val listImages: List<String>? = null
)