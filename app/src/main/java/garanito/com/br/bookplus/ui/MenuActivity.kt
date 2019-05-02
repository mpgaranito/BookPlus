package garanito.com.br.bookplus.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import com.google.firebase.auth.FirebaseAuth
import garanito.com.br.bookplus.R
import garanito.com.br.bookplus.ui.about.MapsActivity
import garanito.com.br.bookplus.ui.fragments.FairFragment
import garanito.com.br.bookplus.ui.fragments.MainFragment
import kotlinx.android.synthetic.main.activity_menu.*


class MenuActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener,
        BottomNavigationView.OnNavigationItemReselectedListener{

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        when (p0.itemId){
            R.id.nav_exit -> logoff()
            R.id.nav_add_fa -> addFair()
            R.id.nav_map -> showMap()
        }
        Log.i("oka","ola")
        return true
    }

    private fun showMap() {
        val intent = Intent(this, MapsActivity::class.java)
        startActivityForResult(intent, Activity.RESULT_OK)
    }

    override fun onNavigationItemReselected(p0: MenuItem) {
      when (p0.itemId){
         R.id.nav_exit -> logoff()
          R.id.nav_events -> events()
          R.id.nav_map -> showMap()
      }
        return
    }


    private fun events() {
        // adicionar o fragmento inicial
        openFragment(MainFragment.newInstance())

    }
private fun addFair(){
    openFragment(FairFragment.newInstance())
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
                    .replace(R.id.frame_container, MainFragment())
                    .commit()
        }


    }

    private fun initBottomNavigation() {

        navbot.setOnNavigationItemSelectedListener( this )
        navbot.setOnNavigationItemReselectedListener( this )
    }

    private fun openFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.frame_container, fragment).addToBackStack(null).commit()
    }

}
