package garanito.com.br.bookplus.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import garanito.com.br.bookplus.R
import garanito.com.br.bookplus.model.Fair


class FairAdapter(private val fairs: ArrayList<Fair>,
                  private val context: Context?,
                  val listener: (Fair) -> Unit) : Adapter<FairAdapter.ViewHolder>() {
    //Método que recebe o ViewHolder e a posição da lista.
//Aqui é recuperado o objeto da lista de Objetos pela posição e associado à ViewHolder.
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fair = fairs[position]
        holder.let {
            it.bindView(fair, listener)
        }
    }

    //Método que deverá retornar layout criado pelo ViewHolder já inflado em uma view.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.fragment_main, parent, false)
        return ViewHolder(view)
    }

    //Método que deverá retornar quantos itens há na lista.
    override fun getItemCount(): Int {
        return fairs.size
    }

    /*
    Com o ViewHolder iremos relacionar o layout criado e adicionar os valores a ele*/
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(fair: Fair,
                     listener: (Fair) -> Unit) = with(itemView) {
            var v = this

            setOnClickListener { listener(fair) }
        }
    }

    interface ClickListener {
        fun onClick(view: View, position: Int)
    }

}