package garanito.com.br.bookplus.ui.fragments


import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import garanito.com.br.bookplus.R
import garanito.com.br.bookplus.adapter.FairAdapter
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
class MainFragment(context: Context) : Fragment() {
    lateinit var myDb: BookFairManager
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        myDb = BookFairManager(context)
        var xptoFair = myDb.select()
        val rvFairs = view.findViewById(garanito.com.br.bookplus.R.id.rvFairs) as RecyclerView
        rvFairs.adapter = FairAdapter(xptoFair, context, {
            Log.i("TAG", "MEU ITEM" + it.Name.toString() + view.toString())
        })
        val layoutManager = LinearLayoutManager(context)
        rvFairs.layoutManager = layoutManager
        return view
    }


}
