package garanito.com.br.bookplus.ui.fragments


import android.app.AlertDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import garanito.com.br.bookplus.R
import garanito.com.br.bookplus.adapter.FairAdapter
import garanito.com.br.bookplus.manager.BookFairManager
import garanito.com.br.bookplus.ui.fragments.FairFragment as FairFragment1

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class MainFragment : Fragment() {
    lateinit var myDb: BookFairManager

    fun realtime(rvFairs: RecyclerView): FairAdapter {
        myDb = BookFairManager(activity)
        var lstFairs = myDb.select()
        val adapterFair = FairAdapter(lstFairs, activity, {})
        rvFairs.adapter = adapterFair
        val layoutManager = LinearLayoutManager(activity)
        rvFairs.layoutManager = layoutManager
        return adapterFair
    }

    companion object {
        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        val rvFairs = view!!.findViewById(garanito.com.br.bookplus.R.id.rvFairs) as RecyclerView
        var adapterFair = realtime(rvFairs)

        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(p0: RecyclerView, p1: RecyclerView.ViewHolder, p2: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(p0: RecyclerView.ViewHolder, p1: Int) {
                Log.i("swipe", "slipe" + p1)
                try {
                    val builder = AlertDialog.Builder(activity)
                    val strDelete: String = getString(R.string.Removed)
                    val strConfDelete: String = getString(R.string.really)
                    builder.setTitle("Ops")
                    builder.setMessage(strConfDelete)
                    builder.setPositiveButton(getString(R.string.YES)) { dialog, which ->
                        adapterFair.removeItem(p0, myDb)
                        Toast.makeText(activity, strDelete, Toast.LENGTH_SHORT).show()
                    }
                    builder.setNegativeButton(getString(R.string.NO)) { dialog, which ->
                        adapterFair = realtime(rvFairs)

                    }
                    builder.setNeutralButton(getString(R.string.CANCEL)) { _, _ ->
                        adapterFair = realtime(rvFairs)
                    }
                    val dialog: AlertDialog = builder.create()
                    dialog.show()
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
