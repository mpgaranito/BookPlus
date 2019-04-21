package garanito.com.br.bookplus.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import garanito.com.br.bookplus.R
import kotlinx.android.synthetic.main.activity_sign_up.*


class SignUpActivity : AppCompatActivity() {

    lateinit var db: DocumentReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        var documentPath = "documentos/cidades"
        db = FirebaseFirestore.getInstance().document(documentPath)

        btCadastrar.setOnClickListener {
            val city = HashMap<String, Any>()
            city["name"] = "Los Angeles"
            city["state"] = "CA"
            city.put("country", "USA")

            db.collection("cidade").document("04")
                    .set(city)
                    .addOnSuccessListener { Log.d("SignUpActivity", "DocumentSnapshot successfully written!") }
                    .addOnFailureListener { e -> Log.w("SignUpActivity", "Error writing document", e) }
        }

    }


}
