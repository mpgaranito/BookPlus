package garanito.com.br.bookplus.ui

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import com.google.firebase.auth.FirebaseAuth
import garanito.com.br.bookplus.R
import garanito.com.br.bookplus.ui.fragments.MainFragment
import kotlinx.android.synthetic.main.activity_menu.*


class MenuActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener,
        BottomNavigationView.OnNavigationItemReselectedListener{
    override fun onNavigationItemSelected(p0: MenuItem): Boolean {

        Log.i("oka","ola")

        return true
    }

    override fun onNavigationItemReselected(p0: MenuItem) {
      when (p0.itemId){
          0 -> logoff()
      }
        Log.i("oka","ola")
        return
    }

    private fun logoff() {
        FirebaseAuth.getInstance().signOut();
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        initBottomNavigation()
        if (savedInstanceState == null) {
            // adicionar o fragmento inicial
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.frame_container, MainFragment())
                    .commit()
        }


    }

    private fun initBottomNavigation() {
        navbot.setOnNavigationItemSelectedListener( this )
        navbot.setOnNavigationItemReselectedListener( this )
    }
}
