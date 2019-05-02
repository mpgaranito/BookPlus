package garanito.com.br.bookplus.ui.about

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import garanito.com.br.bookplus.R

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    val permissoes = listOf(Manifest.permission.ACCESS_FINE_LOCATION)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        //  PermissionUtils.validadePermission(permissoes.toTypedArray(),this,1)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-22.970722, -43.182365)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    object PermissionUtils {
        fun validadePermission(permissoes: Array<String>, activity: Activity, requestCode: Int): Boolean {
            val listaPemissoes = ArrayList<String>()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                for (permissao in permissoes) {
                    val temPermisso = ContextCompat.checkSelfPermission(activity, permissao) == PackageManager.PERMISSION_GRANTED
                    if (!temPermisso) listaPemissoes.add(permissao)
                }
                if (listaPemissoes.isEmpty()) return true
                else {
                    val novasPermissos = arrayOfNulls<String>(listaPemissoes.size)
                    listaPemissoes.toTypedArray()
                    ActivityCompat.requestPermissions(activity, novasPermissos, requestCode)
                }
            }
            return true
        }
    }
}

