package garanito.com.br.bookplus.ui.about

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import garanito.com.br.bookplus.R

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {


    private var locationManager: LocationManager? = null

    private val isLocationEnabled: Boolean
        get() {
            locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
            return locationManager!!.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager!!.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        }
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


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val saoPauloFair = LatLng(-23.535308, -46.650649)
        mMap.addMarker(MarkerOptions().position(saoPauloFair).title("Feira de Livros").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)))
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(saoPauloFair, 10f))
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

