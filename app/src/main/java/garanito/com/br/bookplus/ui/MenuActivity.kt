package garanito.com.br.bookplus.ui

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import com.google.firebase.auth.FirebaseAuth
import garanito.com.br.bookplus.R
import garanito.com.br.bookplus.ui.fragments.FairFragment
import garanito.com.br.bookplus.ui.fragments.MainFragment
import kotlinx.android.synthetic.main.activity_menu.*


class MenuActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener,
        BottomNavigationView.OnNavigationItemReselectedListener{

    override fun onBackPressed() {
        var fragment = supportFragmentManager.findFragmentById(R.id.FairFragment)
        if (fragment != null) {
            supportFragmentManager
                    .beginTransaction()
                    .remove(fragment)
                    .commit()
        } else
            super.onBackPressed()
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        when (p0.itemId){
            R.id.nav_exit -> logoff()
            R.id.nav_add_fa -> addFair()
        }
        Log.i("oka","ola")
        return true
    }

    override fun onNavigationItemReselected(p0: MenuItem) {
      when (p0.itemId){
         R.id.nav_exit -> logoff()
          R.id.nav_events -> events()
      }
        return
    }


    private fun events() {
        var fragment = supportFragmentManager.findFragmentById(R.id.frame_container)
        if (fragment != null) {
            supportFragmentManager
                    .beginTransaction()
                    .remove(fragment)
                    .commit()
            //   events()
        }
        // adicionar o fragmento inicial
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.frame_container, MainFragment())
                .commit()

    }
private fun addFair(){
    var fragment = supportFragmentManager.findFragmentById(R.id.frame_container)
    if (fragment != null) {
        supportFragmentManager
                .beginTransaction()
                .remove(fragment)
                .commit()
        //  addFair()
    }
    supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_container, FairFragment(this))
            .commit()

}
    private fun logoff() {
        FirebaseAuth.getInstance().signOut()
        finishAffinity()
        Log.i("logOff","Saindo da Aplicacao")
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
        var fragment = supportFragmentManager.findFragmentById(R.id.frame_container)
        if (fragment != null) {
            supportFragmentManager
                    .beginTransaction()
                    .remove(fragment)
                    .commit()
        }
        navbot.setOnNavigationItemSelectedListener( this )
        navbot.setOnNavigationItemReselectedListener( this )
    }
}
