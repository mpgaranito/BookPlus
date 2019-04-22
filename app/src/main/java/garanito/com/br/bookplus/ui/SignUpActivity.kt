package garanito.com.br.bookplus.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import garanito.com.br.bookplus.R
import kotlinx.android.synthetic.main.activity_sign_up.*


class SignUpActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        btCadastrar.setOnClickListener {
            var zipCode: Double = 0.0

            if (!etCep.text.toString().isEmpty()) {
                zipCode = etCep.text.toString().toDouble()
            }

            if (isValid(etNameSign.text.toString(), etEmailSign.text.toString(), zipCode, etConfirmPassword.text.toString(), etPasswordSign.text.toString())) {

/*
              var x = HashMap<String, Any>()
              if(FirebaseRepository.InsertDocument(x,"documents","documents/usuers","users"))
                  Toast.makeText(this,"Registro Inserido com Sucesso!",Toast.LENGTH_LONG)
  */
            }
        }

        fun Any?.toString(): String {
            return if (this == null) "Value is null" else "Value is not null"
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
