package garanito.com.br.bookplus.ui.utils.fcm

import android.util.Log
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService

class MyFirebaseInstanceidService : FirebaseInstanceIdService() {
    override fun onTokenRefresh() {
        super.onTokenRefresh()
        Log.i("Token", FirebaseInstanceId.getInstance().token)
    }
}