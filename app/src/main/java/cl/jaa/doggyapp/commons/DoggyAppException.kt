package cl.jaa.doggyapp.commons

sealed class DoggyAppException : Throwable() {

    object NotFoundException : DoggyAppException()

    companion object {
        fun fromException(ex: Exception): DoggyAppException {
            return NotFoundException
        }
    }
}
