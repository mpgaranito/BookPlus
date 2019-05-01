package garanito.com.br.bookplus.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import garanito.com.br.bookplus.R
import garanito.com.br.bookplus.manager.BookFairManager
import garanito.com.br.bookplus.model.Fair
import kotlinx.android.synthetic.main.fairs_row.view.*

class FairAdapter(private val fairs: ArrayList<Fair>,
                  private val context: Context?,
                  val listener: (Fair) -> Unit) : Adapter<FairAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fair = fairs[position]
        holder.let {
            it.bindView(fair, listener)
        }
        holder.itemView.setOnClickListener {
            Log.i("bindholdeClick", "NAME FAIR >>>> " + fair.Name)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.fairs_row, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return fairs.size
    }

    fun alterItem(viewHolder: RecyclerView.ViewHolder) {
        var id = fairs[viewHolder.adapterPosition].ID
        var name = fairs[viewHolder.adapterPosition].Name
        var description = fairs[viewHolder.adapterPosition].Description
        var initialDate = fairs[viewHolder.adapterPosition].InitialDate
        var finalDate = fairs[viewHolder.adapterPosition].FinalDate
        var initialHour = fairs[viewHolder.adapterPosition].InitialHour
        var finalHour = fairs[viewHolder.adapterPosition].FinalHour
        //var imagem= fairs[viewHolder.adapterPosition].imagem //preparado para a imagem.
    }

    fun removeItem(viewHolder: RecyclerView.ViewHolder, myDb: BookFairManager) {
        var id = fairs[viewHolder.adapterPosition].ID
        try {
            myDb.delete(id.toString())
            fairs.removeAt(viewHolder.adapterPosition)
            notifyItemRemoved(viewHolder.adapterPosition)
            Log.i("removeItem", "ID>>>>> " + id)
        } catch (ex: Exception) {
            Log.e("removeItem", ex.toString())
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(fair: Fair,
                     listener: (Fair) -> Unit) = with(itemView) {
            val tvNameFairRow = tvNameFairRow
            tvNameFairRow.text = fair.Name
            setOnClickListener {
                listener(fair)
            }

        }
    }
    interface ClickListener {
        fun onClick(view: View, position: Int) {
        }
    }

}