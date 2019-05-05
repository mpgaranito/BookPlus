package garanito.com.br.bookplus.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.iid.FirebaseInstanceId
import garanito.com.br.bookplus.R
import garanito.com.br.bookplus.ui.about.AboutActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    private lateinit var mAuth: FirebaseAuth
    private val CADASTRO_REQUEST_CODE = 1

    companion object {
        @JvmField
        var UserID = ""
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mAuth = FirebaseAuth.getInstance()

        if (mAuth.currentUser != null) {
            UserID = FirebaseAuth.getInstance().currentUser!!.uid
            goToMenuScreen()
        }
        btLogar.setOnClickListener {
            if (isValid(etEmail.text.toString(), etSenha.text.toString())) {

                mAuth.signInWithEmailAndPassword(
                        etEmail.text.toString(),
                        etSenha.text.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        goToMenuScreen()
                    } else {
                        Toast.makeText(this@MainActivity, it.exception?.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
        btNovaConta.setOnClickListener {
            val telaSeguinte = Intent(this, SignUpActivity::class.java)
            startActivityForResult(telaSeguinte, CADASTRO_REQUEST_CODE)
        }
        btAbout.setOnClickListener {
            FirebaseInstanceId.getInstance().instanceId
                    .addOnCompleteListener(OnCompleteListener { task ->
                        if (!task.isSuccessful) {
                            Log.w("FIRE", "getInstanceId failed", task.exception)
                            return@OnCompleteListener
                        }
                        val token = task.result?.token
                        val msg = token
                        Log.d("FIRE", msg)
                        //Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
                    })
            val telaAbout = Intent(this, AboutActivity::class.java)
            startActivityForResult(telaAbout, CADASTRO_REQUEST_CODE)
        }

    }

    private fun goToMenuScreen() {
        val telaSeguinteMenu = Intent(this, MenuActivity::class.java)
        startActivityForResult(telaSeguinteMenu, CADASTRO_REQUEST_CODE)
    }

    private fun isValid(email: String, password: String): Boolean {
        val str: String = getString(R.string.lblObrigatorio)
        if (email.isEmpty()) {
            etEmail.error = str
            etEmail.requestFocus()
            return false
        }
        if (password.isEmpty()) {
            etSenha.error = str
            etSenha.requestFocus()
            return false
        }
        return true
    }

}

