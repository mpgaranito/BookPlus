package garanito.com.br.bookplus.ui.fragments


import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import garanito.com.br.bookplus.R
import garanito.com.br.bookplus.manager.BookFairManager
import kotlinx.android.synthetic.main.fragment_fair.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
@SuppressLint("ValidFragment")
class FairFragment : Fragment() {

    companion object {
        fun newInstance(): FairFragment {
            return FairFragment()
        }
    }

    lateinit var myDb: BookFairManager
    lateinit var btSaveFair: Button
    var ID: Int = 0
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        myDb = BookFairManager(activity)
        val b = arguments
        var isInsert = true //modo inserção
        val view = inflater.inflate(R.layout.fragment_fair, container, false)
        if (b != null) {
            isInsert = false //modo edição
            val id = b.getInt("ID")
            ID = id
            val name = b.getString("name")
            val description = b.getString("description")
            val address = b.getString("address")
            val initialDate = b.getString("initialDate")
            val finalDate = b.getString("finalDate")
            val initialHour = b.getString("initialHour")
            val finalHour = b.getString("finalHour")

            var etNameFair = view.findViewById(garanito.com.br.bookplus.R.id.etNameFair) as EditText
            etNameFair.setText(name)

            var etDescription = view.findViewById(garanito.com.br.bookplus.R.id.etDescription) as EditText
            etDescription.setText(description)
            var etAddressFair = view.findViewById(garanito.com.br.bookplus.R.id.etAddressFair) as EditText
            etAddressFair.setText(address)
            var etInitialDate = view.findViewById(garanito.com.br.bookplus.R.id.etInitialDate) as EditText
            etInitialDate.setText(initialDate)
            var etFinalDate = view.findViewById(garanito.com.br.bookplus.R.id.etFinalDate) as EditText
            etFinalDate.setText(finalDate)
            var etInitialHour = view.findViewById(garanito.com.br.bookplus.R.id.etInitialHour) as EditText
            etInitialHour.setText(initialHour)
            var etFinalHour = view.findViewById(garanito.com.br.bookplus.R.id.etFinalHour) as EditText
            etFinalHour.setText(finalHour)

        }
        btSaveFair = view.findViewById(garanito.com.br.bookplus.R.id.btSaveFair) as Button
        btSaveFair.setOnClickListener {
            if (isInsert)
                write()
            else
                update()
        }
        // Inflate the layout for this fragment
        return view
    }

    private fun update() {
        var nameFair = etNameFair.text
        var description = etDescription.text
        var address = etAddressFair.text
        var initialDate = etInitialDate.text
        var finalDate = etFinalDate.text
        var initialHour = etInitialHour.text
        var finalHour = etFinalHour.text
        if (validade()) {
            var isNullEmpty = false
            if (!isNullEmpty) {
                if (ID == 0)
                    return
                val isInserted = myDb.update(ID.toString(), nameFair.toString(), description.toString(), address.toString(), initialDate.toString(), finalDate.toString(), initialHour.toString(), finalHour.toString())
                if (isInserted == true) {
                    Toast.makeText(context, getString(R.string.Success), Toast.LENGTH_SHORT).show()
                    etNameFair.text.clear()
                    etDescription.text.clear()
                    etAddressFair.text.clear()
                    etInitialDate.text.clear()
                    etFinalDate.text.clear()
                    etInitialHour.text.clear()
                    etFinalHour.text.clear()
                } else {
                    Toast.makeText(context, getString(R.string.Error), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun validade(): Boolean {
        var nameFair = etNameFair.text
        var description = etDescription.text
        var address = etAddressFair.text
        var initialDate = etInitialDate.text
        var finalDate = etFinalDate.text
        var initialHour = etInitialHour.text
        var finalHour = etFinalHour.text
        val str: String = getString(R.string.lblObrigatorio)

        if (TextUtils.isEmpty(nameFair)) {
            etNameFair.error = str
            etNameFair.requestFocus()
            return false
        }
        if (TextUtils.isEmpty(description)) {
            etDescription.error = str
            etDescription.requestFocus()
            return false
        }
        if (TextUtils.isEmpty(initialDate)) {
            etInitialDate.error = str
            etInitialDate.requestFocus()
            return false
        }
        if (TextUtils.isEmpty(address)) {
            etAddressFair.error = str
            etAddressFair.requestFocus()
            return false
        }

        if (TextUtils.isEmpty(finalDate)) {
            etFinalDate.error = str
            etFinalDate.requestFocus()
            return false
        }

        if (TextUtils.isEmpty(initialHour)) {
            etInitialHour.error = str
            etInitialHour.requestFocus()
            return false
        }

        if (TextUtils.isEmpty(finalHour)) {
            etFinalHour.error = str
            etFinalHour.requestFocus()
            return false
        }
        return true
    }

    fun write() {
        var nameFair = etNameFair.text
        var description = etDescription.text
        var address = etAddressFair.text
        var initialDate = etInitialDate.text
        var finalDate = etFinalDate.text
        var initialHour = etInitialHour.text
        var finalHour = etFinalHour.text
        if (validade()) {
            var isNullEmpty = false
            if (!isNullEmpty) {
                val isInserted = myDb.insert(nameFair.toString(), description.toString(), address.toString(), initialDate.toString(), finalDate.toString(), initialHour.toString(), finalHour.toString())
                if (isInserted == true) {
                    Toast.makeText(context, getString(R.string.Success), Toast.LENGTH_SHORT).show()
                    etNameFair.text.clear()
                    etDescription.text.clear()
                    etAddressFair.text.clear()
                    etInitialDate.text.clear()
                    etFinalDate.text.clear()
                    etInitialHour.text.clear()
                    etFinalHour.text.clear()
                } else {
                    Toast.makeText(context, getString(R.string.Error), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

