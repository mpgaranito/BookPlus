package garanito.com.br.bookplus.utils.fcm

import android.util.Log
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

class FirebaseRepository {


    companion object {
        lateinit var db: DocumentReference
        fun GetDocuments(documentPath: String, documentRootPath: String, collectionPath: String) {
            val docRef = db.collection(collectionPath).document(collectionPath)
            docRef.get()
                    .addOnSuccessListener { document ->
                        if (document != null) {
                            Log.d("FirebaseRepository", "DocumentSnapshot data: ${document.data}")
                        } else {
                            Log.d("FirebaseRepository", "No such document")
                        }
                    }
                    .addOnFailureListener { exception ->
                        Log.d("FirebaseRepository", "get failed with ", exception)
                    }
        }
        fun InsertDocument(document: HashMap<String, Any>, documentPath: String, documentRootPath: String, collectionPath: String): Boolean {
            //  var documentPath = "documentos/cidades"
            var rsReturn: Boolean = true
            db = FirebaseFirestore.getInstance().document(documentRootPath)
            db.collection(collectionPath).document(documentPath)
                    .set(document)
                    .addOnSuccessListener {
                        Log.d("FirebaseRepository", "DocumentSnapshot successfully written!")
                    }
                    .addOnFailureListener { e ->
                        Log.w("FirebaseRepository", "Error writing document", e)
                        rsReturn = false
                    }
            return rsReturn
        }
    }
}