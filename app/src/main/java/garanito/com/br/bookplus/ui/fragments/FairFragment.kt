package garanito.com.br.bookplus.ui.fragments


import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
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
class FairFragment(context: Context) : Fragment() {

    lateinit var myDb: BookFairManager
    lateinit var btSaveFair: Button
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


        myDb = BookFairManager(context)


        val view = inflater.inflate(R.layout.fragment_fair, container, false)
        btSaveFair = view.findViewById(garanito.com.br.bookplus.R.id.btSaveFair) as Button
        btSaveFair.setOnClickListener {
            write()
        }


        // Inflate the layout for this fragment
        return view


    }

    fun write() {
        var nameFair = etNameFair.text
        var description = etDescription.text
        var address = etAddressFair.text
        var initialDate = etInitialDate.text
        var finalDate = etFinalDate.text
        var initialHour = etInitialHour.text
        var finalHour = etFinalHour.text
        val str: String = getString(R.string.lblObrigatorio)
        var isNullEmpty = false
        if (TextUtils.isEmpty(nameFair)) {
            etNameFair.error = str

            return
        }
        if (TextUtils.isEmpty(description)) {
            etDescription.error = str

            return
        }
        if (TextUtils.isEmpty(initialDate)) {
            etInitialDate.error = str

            return
        }
        if (TextUtils.isEmpty(address)) {
            etAddressFair.error = str

            return
        }

        if (TextUtils.isEmpty(finalDate)) {
            etFinalDate.error = str

            return
        }


        if (TextUtils.isEmpty(finalHour)) {
            etFinalHour.error = str

            return
        }
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

