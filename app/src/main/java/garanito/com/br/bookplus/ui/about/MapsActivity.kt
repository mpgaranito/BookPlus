package garanito.com.br.bookplus.ui.about

import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.location.Criteria
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import garanito.com.br.bookplus.R


class MapsActivity : AppCompatActivity(), OnMapReadyCallback, LocationListener {
    private var yourLocation = LatLng(0.0, 0.0)
    private lateinit var mMap: GoogleMap
    override fun onLocationChanged(location: Location?) {
        Log.i("LOCATION", "changed" + location?.latitude + "-" + location?.longitude)
        if (location != null) {
            yourLocation = LatLng(location.latitude, location.longitude)
            try {
                if (mMap != null)
                    mMap.addMarker(MarkerOptions().position(yourLocation).title("Seu Local").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)))
            } catch (ex: Exception) {
                Log.i("LOCATION", ex.toString())
            }

        } else
            yourLocation = LatLng(0.0, 0.0)
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
        Log.i("LOCATION", "status changed")
    }

    override fun onProviderEnabled(provider: String?) {
        Log.i("LOCATION", "provider changed")
    }

    override fun onProviderDisabled(provider: String?) {
        Log.i("LOCATION", "disabled")
    }


    private var locationManager: LocationManager? = null
    val MY_PERMISSIONS_REQUEST_LOCATION = 99
    var provider: String = ""

    private val isLocationEnabled: Boolean
        get() {
            locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
            return locationManager?.isProviderEnabled(LocationManager.GPS_PROVIDER)!! || locationManager?.isProviderEnabled(LocationManager.NETWORK_PROVIDER)!!
        }

    val permissoes = listOf(Manifest.permission.ACCESS_FINE_LOCATION)


    fun checkLocationPermission(): Boolean {
        if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)) {
                AlertDialog.Builder(this)
                        .setTitle(R.string.title_location_permission)
                        .setMessage(R.string.text_location_permission)
                        .setPositiveButton(R.string.ok, DialogInterface.OnClickListener { dialogInterface, i ->
                            ActivityCompat.requestPermissions(this@MapsActivity,
                                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                                    MY_PERMISSIONS_REQUEST_LOCATION)
                        })
                        .create()
                        .show()
            } else {
                ActivityCompat.requestPermissions(this,
                        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                        MY_PERMISSIONS_REQUEST_LOCATION)
            }
            return false
        } else {
            return true
        }
    }

    override fun onResume() {
        super.onResume()
        if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            try {
                locationManager?.requestLocationUpdates(provider, 400L, 1F, this)
            } catch (ex: java.lang.Exception) {
            }
        }
    }

    override fun onPause() {
        super.onPause()
        if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            try {
                locationManager?.removeUpdates(this)
            } catch (ex: java.lang.Exception) {
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        checkLocationPermission()
        if (isLocationEnabled) {

            locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
            try {
                provider = locationManager?.getBestProvider(Criteria(), false).toString()
            } catch (ex: java.lang.Exception) {
            }
        }
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val saoPauloFair = LatLng(-23.535308, -46.650649) //LOCAL DA PROXIMA FEIRA DE TROCA DE LIVROS
        mMap.addMarker(MarkerOptions().position(saoPauloFair).title("Ponto de Encontro para Trocar Livros").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)))
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(saoPauloFair, 10f))
    }
}

