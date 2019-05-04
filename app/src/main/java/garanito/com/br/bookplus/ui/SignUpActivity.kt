package garanito.com.br.bookplus.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import garanito.com.br.bookplus.R
import garanito.com.br.bookplus.model.User
import kotlinx.android.synthetic.main.activity_sign_up.*


class SignUpActivity : AppCompatActivity() {


    private lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        btCadastrar.setOnClickListener {
            mAuth = FirebaseAuth.getInstance()

            var zipCode: Double = 0.0

            if (!etCep.text.toString().isEmpty()) {
                zipCode = etCep.text.toString().toDouble()
            }

            var name = etNameSign.text.toString()
            var email = etEmailSign.text.toString()
            var confirmPassword = etConfirmPassword.text.toString()
            var password = etPasswordSign.text.toString()
            var city = etPasswordSign.text.toString()

            if (isValid(name, email, zipCode, confirmPassword, password, city)) {
                mAuth.createUserWithEmailAndPassword(
                        email,
                        password
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        salvarNoRealTimeDatabase()
                    } else {
                        Toast.makeText(this@SignUpActivity, it.exception?.message, Toast.LENGTH_LONG).show()
                    }
                }

/*
              var x = HashMap<String, Any>()
              if(FirebaseRepository.InsertDocument(x,"documents","documents/usuers","users"))
                  Toast.makeText(this,"Registro Inserido com Sucesso!",Toast.LENGTH_LONG)
  */
            }
        }



    }

    private fun salvarNoRealTimeDatabase() {

        var city = etCidade.text.toString()
        var name = etNameSign.text.toString()
        var email = etEmailSign.text.toString()
        var confirmPassword = etConfirmPassword.text.toString()
        var password = etPasswordSign.text.toString()
        var zipCode: Double = 0.0



        if (!etCep.text.toString().isEmpty()) {
            zipCode = etCep.text.toString().toDouble()
        }

        val xuser = User(name, email, zipCode, city, 0.0, 0.0)

        FirebaseDatabase.getInstance().getReference("usuarios")
                .child(FirebaseAuth.getInstance().currentUser!!.uid)
                .setValue(xuser).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(this@SignUpActivity, "Usuario cadastrado com sucesso", Toast.LENGTH_LONG).show()
                        val intent = Intent(this, MainActivity::class.java)
                        //intent.putExtra("email",etEmail.text.toString())
                        //intent.putExtra("senha",etSenha.text.toString())
                        startActivityForResult(intent, Activity.RESULT_OK)
                        //finish()
                    } else {
                        Toast.makeText(this@SignUpActivity, it.exception?.message, Toast.LENGTH_LONG).show()
                    }
                }
    }


    fun isValid(name: String, email: String, zipocode: Double, repeatpassword: String, password: String, city: String): Boolean {

        val str: String = getString(R.string.lblObrigatorio)
        val strPasswd: String = getString(R.string.passwordNotMatch)

        if (name.isEmpty()) {
            etNameSign.error = str
            etNameSign.requestFocus()
            return false
        }
        if (email.isEmpty()) {
            etEmailSign.error = str
            etEmailSign.requestFocus()
            return false
        }

        if (password.isEmpty()) {
            etPasswordSign.error = str
            etPasswordSign.requestFocus()
            return false
        }
        if (repeatpassword.isEmpty()) {
            etConfirmPassword.error = str
            etConfirmPassword.requestFocus()
            return false
        }
        if (password != repeatpassword) {
            etPasswordSign.setText("")
            etConfirmPassword.setText("")
            etPasswordSign.error = strPasswd
            etPasswordSign.requestFocus()
            return false
        }

        if (password.isEmpty()) {
            etEmailSign.error = str
            etEmailSign.requestFocus()
            return false
        }

        if (zipocode < 1) {
            etCep.error = str
            etCep.requestFocus()
            return false
        }
        if (city.isEmpty()) {
            etCidade.error = str
            etCidade.requestFocus()
            return false
        }

        return true
    }

}
