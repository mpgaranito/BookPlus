package garanito.com.br.bookplus.ui.fragments


import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import garanito.com.br.bookplus.manager.BookFairManager

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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        myDb = BookFairManager(context)
         
        // Inflate the layout for this fragment
        return inflater.inflate(garanito.com.br.bookplus.R.layout.fragment_fair, container, false)


    }


}

