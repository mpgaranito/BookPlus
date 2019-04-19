package garanito.com.br.bookplus

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    private lateinit var mAuth: FirebaseAuth
    private val CADASTRO_REQUEST_CODE = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //FirebaseApp.initializeApp(this.MainActivity)
        setContentView(R.layout.activity_main)
        mAuth = FirebaseAuth.getInstance()

        if (mAuth.currentUser != null) {
            vaiParaTelaMenu()
        }

        btLogar.setOnClickListener {
            mAuth.signInWithEmailAndPassword(
                    etEmail.text.toString(),
                    etSenha.text.toString()
            ).addOnCompleteListener {
                if (it.isSuccessful) {
                    vaiParaTelaMenu()
                } else {
                    Toast.makeText(this@MainActivity, it.exception?.message, Toast.LENGTH_LONG).show()
                }
            }
        }

        btNovaConta.setOnClickListener {
            val telaSeguinte = Intent(this, SignUpActivity::class.java)
            startActivityForResult(telaSeguinte, CADASTRO_REQUEST_CODE)
        }


    }

    fun vaiParaTelaMenu(){
        val telaSeguinteMenu = Intent(this, MenuActivity::class.java)
        startActivityForResult(telaSeguinteMenu, CADASTRO_REQUEST_CODE)
    }

}

