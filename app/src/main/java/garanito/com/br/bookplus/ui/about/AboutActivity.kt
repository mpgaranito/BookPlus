package garanito.com.br.bookplus.ui.about

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
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
        tvRecommend.setOnClickListener {
            sendMail()
        }
    }

    private fun sendMail() {
        val i = Intent(Intent.ACTION_SEND)
        i.type = "message/rfc822"
        i.putExtra(Intent.EXTRA_EMAIL, arrayOf("recipient@example.com"))
        i.putExtra(Intent.EXTRA_SUBJECT, "Hi, share this software")
        i.putExtra(Intent.EXTRA_TEXT, "Hi, You need to discovery book plus. Share book fair with you friends")
        try {
            startActivity(Intent.createChooser(i, "Hi, share this software"))
        } catch (ex: android.content.ActivityNotFoundException) {
            Toast.makeText(this@AboutActivity, "Error", Toast.LENGTH_SHORT).show()
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
