package com.example.tracingapp.ui

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBar
import com.example.tracingapp.MainActivity
import com.example.tracingapp.R
import com.example.tracingapp.data.viewmodel.UserViewModel
import com.example.tracingapp.ui.menu.faq.FaqActivity
import com.example.tracingapp.ui.history.HistoryActivity
import com.example.tracingapp.ui.menu.DetailsActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HealthAssessmentActivity : AppCompatActivity() {
    private val viewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_health_assessment)

        val actionBar: ActionBar? = supportActionBar
        actionBar?.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.transparent)))
        actionBar?.setTitle(Html.fromHtml("<big><big>Health Assessment</big></big>"))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_nav_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.nav_details -> {
                val intent: Intent = Intent(this, DetailsActivity::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.slide_in_left, R.anim.fade_out)
                true
            }
            R.id.nav_faq -> {
                val intent: Intent = Intent(this, FaqActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.nav_history -> {
                val intent: Intent = Intent(this, HistoryActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.nav_helpdesk -> {
                val uri: String = "https://mysejahtera.malaysia.gov.my/help_en/"

                val i: Intent = Intent(Intent.ACTION_VIEW)
                i.setData(Uri.parse(uri))
                startActivity(i)
                true
            }
            R.id.nav_logout -> {
                viewModel.logout()
                Toast.makeText(this@HealthAssessmentActivity, "Successfully Logout!", Toast.LENGTH_SHORT).show()
                val intent: Intent = Intent(this, WelcomeActivity::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.slide_in_right, R.anim.fade_out)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun closeActivity(view: View) {
        val intent: Intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun personalStatusUpdate(view: View) {
        val intent: Intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        Toast.makeText(this, "Your status has been successfully updated!", Toast.LENGTH_LONG).show()
    }
}