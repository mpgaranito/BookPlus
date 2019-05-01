package garanito.com.br.bookplus.ui.about

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import garanito.com.br.bookplus.R
import kotlinx.android.synthetic.main.activity_about.*


class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        val actionbar = supportActionBar
        actionbar!!.title = "BookPlus"
        actionbar.setDisplayHomeAsUpEnabled(true)
        tvAjuda.setOnClickListener {
            callHelpDesk()
        }
    }

    private fun callHelpDesk() {
        var telephone = "02111981369118"
        val uri = Uri.parse("tel:$telephone")
        val intent = Intent(Intent.ACTION_DIAL, uri)
        startActivity(intent)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
