package cl.jaa.doggyapp.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import cl.jaa.doggyapp.R
import cl.jaa.doggyapp.commons.BaseViewHolder

typealias DogBreedViewHolderListener = (DogBreedVO) -> Unit


class DogBreedVH(
    parent: ViewGroup,
    private val listener: DogBreedViewHolderListener
) :
    BaseViewHolder<DogBreedVO>(
        LayoutInflater.from(parent.context).inflate(R.layout.item_breed, parent, false)
    ) {

    lateinit var tvTitleBreed: AppCompatTextView


    override fun bind(vo: DogBreedVO) {
        tvTitleBreed = itemView.findViewById(R.id.tvTitleBreed)
        tvTitleBreed.text = vo.breedName
        itemView.setOnClickListener {
            listener.invoke(vo)
        }


    }
}