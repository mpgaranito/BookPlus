package garanito.com.br.bookplus.ui


import android.content.Context

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import garanito.com.br.bookplus.R
import garanito.com.br.bookplus.model.Fair
import kotlinx.android.synthetic.main.fairs_row.view.*


class ListaFairAdapter(
        private val context: Context,
        private val fairs: List<Fair>,
        private val listener: (Fair) -> Unit
) :

        RecyclerView.Adapter<ListaFairAdapter.FairsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FairsViewHolder {
        val view = LayoutInflater.from(context)
                .inflate(R.layout.fairs_row, parent, false)
        return FairsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return fairs.size
    }


    override fun onBindViewHolder(holder: FairsViewHolder, position: Int) {
        holder.bindView(fairs[position], listener)
    }

    class FairsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(fair: Fair, listener: (Fair) -> Unit) = with(itemView) {
            tvNameFairRow.text = fair.Name
            /*getPicassoAuth(itemView.context)
                    .load("https://pokedexdx.herokuapp.com${pokemon.imagem}")
                    .into(ivPokemon)*/
            setOnClickListener { listener(fair) }
        }
    }
}