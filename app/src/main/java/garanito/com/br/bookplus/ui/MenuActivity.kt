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
import garanito.com.br.bookplus.ui.fragments.TitleFragment
import kotlinx.android.synthetic.main.activity_menu.*


class MenuActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener,
        BottomNavigationView.OnNavigationItemReselectedListener{
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

        // adicionar o fragmento inicial
        supportFragmentManager
                .beginTransaction()
                .add(R.id.frame_container, TitleFragment())
                .commit()

    }
private fun addFair(){
    supportFragmentManager
            .beginTransaction()
            .add(R.id.frame_container, FairFragment(this))
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
        navbot.setOnNavigationItemSelectedListener( this )
        navbot.setOnNavigationItemReselectedListener( this )
    }
}
