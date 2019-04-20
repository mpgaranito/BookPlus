package garanito.com.br.bookplus.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import garanito.com.br.bookplus.R
import garanito.com.br.bookplus.ui.fragments.MainFragment
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_menu.*


class MenuActivity : AppCompatActivity() , OnNavigationItemSelectedListener,
        BottomNavigationView.OnNavigationItemReselectedListener{
    override fun onNavigationItemSelected(p0: MenuItem): Boolean {

        Log.i("oka","ola")

        return true
    }

    override fun onNavigationItemReselected(p0: MenuItem) {
        Log.i("oka","ola")
        return
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
