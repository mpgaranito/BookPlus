package garanito.com.br.bookplus.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentTransaction
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import garanito.com.br.bookplus.R
import garanito.com.br.bookplus.manager.BookFairManager
import garanito.com.br.bookplus.model.Fair
import garanito.com.br.bookplus.ui.fragments.FairFragment
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
            pushFragment(FairFragment(), context!!, fair)
        }
        holder.itemView.setOnLongClickListener {
            sendMail(fair)
            return@setOnLongClickListener true
        }
    }

    private fun sendMail(fair: Fair) {
        val i = Intent(Intent.ACTION_SEND)
        var subject = context!!.getString(R.string.Share)
        var body = context.getString(R.string.TextMail)
        body = body.replace("{name}", fair.Name)
        body = body.replace("{end}", fair.Address)
        body = body.replace("{startDate}", fair.InitialDate)
        body = body.replace("{startHour}", fair.InitialHour)
        body = body.replace("{endDate}", fair.FinalDate)
        body = body.replace("{endHour}", fair.FinalHour)
        i.type = "message/rfc822"
        i.putExtra(Intent.EXTRA_EMAIL, arrayOf("recipient@example.com"))
        i.putExtra(Intent.EXTRA_SUBJECT, subject)
        i.putExtra(Intent.EXTRA_TEXT, body)
        try {
            context.startActivity(Intent.createChooser(i, "Hi, share this software"))
        } catch (ex: android.content.ActivityNotFoundException) {
            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.fairs_row, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return fairs.size
    }

    fun pushFragment(newFragment: Fragment, context: Context, fair: Fair) {

        val transaction = (context as FragmentActivity).supportFragmentManager.beginTransaction()
        val bundle = Bundle()
        bundle.putInt("ID", fair.ID)
        bundle.putString("name", fair.Name)
        bundle.putString("description", fair.Description)
        bundle.putString("initialDate", fair.InitialDate)
        bundle.putString("finalDate", fair.FinalDate)
        bundle.putString("initialHour", fair.InitialHour)
        bundle.putString("finalHour", fair.FinalHour)
        bundle.putString("address", fair.Address)
        newFragment.arguments = bundle
        transaction.replace(R.id.frame_container, newFragment)
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        transaction.addToBackStack(null)
        transaction.commit()
        FairFragment.newInstance()
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
            val tvAddressRow = tvAddressRow
            val tvDateRow = tvDateRow
            val tvHourRow = tvHourRow
            tvNameFairRow.text = fair.Name
            tvAddressRow.text = fair.Address
            tvDateRow.text = fair.InitialDate + " - " + fair.FinalDate
            tvHourRow.text = fair.InitialHour + " - " + fair.FinalHour
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