package garanito.com.br.bookplus.ui.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import garanito.com.br.bookplus.R
import garanito.com.br.bookplus.adapter.FairAdapter
import garanito.com.br.bookplus.manager.BookFairManager
import garanito.com.br.bookplus.ui.fragments.FairFragment as FairFragment1

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */

class MainFragment : Fragment() {
    lateinit var myDb: BookFairManager

    companion object {
        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        myDb = BookFairManager(activity)
        var xptoFair = myDb.select()
        val rvFairs = view.findViewById(garanito.com.br.bookplus.R.id.rvFairs) as RecyclerView
        val adapterFair = FairAdapter(xptoFair, activity, {
            Log.i("holf", "eeee")
        })
        rvFairs.adapter = adapterFair
        val layoutManager = LinearLayoutManager(activity)
        rvFairs.layoutManager = layoutManager
        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(p0: RecyclerView, p1: RecyclerView.ViewHolder, p2: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(p0: RecyclerView.ViewHolder, p1: Int) {
                Log.i("swipe", "slipe" + p1)
                try {
                    adapterFair.removeItem(p0, myDb)
                } catch (ex: Exception) {
                    Log.e("swipe", ex.toString())
                }

            }

        }
        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(rvFairs)

        return view
    }

}
