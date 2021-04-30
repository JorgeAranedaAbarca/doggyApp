package cl.jaa.doggyapp.ui.main

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cl.jaa.doggyapp.commons.BaseViewHolder

class DogBreedAdapter(
    private val listBreeds: List<DogBreedVO>,
    private val listener: DogBreedViewHolderListener
) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    private val BREED: Int = 1


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return when (viewType) {
            BREED -> breedCard(parent)
            else -> breedCard(parent)
        }

    }

    private fun breedCard(parent: ViewGroup): BaseViewHolder<*> = DogBreedVH(parent, listener)

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder.itemViewType) {
            BREED -> bindBreed(holder as DogBreedVH, position)
        }
    }

    private fun bindBreed(dogBreedVH: DogBreedVH, position: Int) {
        dogBreedVH.bind(listBreeds[position])

    }

    override fun getItemViewType(position: Int) = BREED
    override fun getItemCount(): Int = listBreeds.size
}