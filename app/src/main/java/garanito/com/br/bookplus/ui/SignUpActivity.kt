package garanito.com.br.bookplus.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import garanito.com.br.bookplus.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_sign_up.*


class SignUpActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)


        btCadastrar.setOnClickListener {
            if (isValid(etNameSign.text.toString(), etEmail.text.toString(), etCep.text.toString().toDouble(), etConfirmPassword.text.toString(), etPasswordSign.text.toString())) {

/*
              var x = HashMap<String, Any>()
              if(FirebaseRepository.InsertDocument(x,"documents","documents/usuers","users"))
                  Toast.makeText(this,"Registro Inserido com Sucesso!",Toast.LENGTH_LONG)
  */
            }
        }


    }

    fun isValid(name: String, email: String, zipocode: Double, repeatpassword: String, password: String): Boolean {

        val str: String = getString(R.string.lblObrigatorio)
        val strPasswd: String = getString(R.string.passwordNotMatch)
        if (zipocode < 1) {
            etCep.error = str
            etCep.requestFocus()
            return false
        }

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
        return true
    }

}
