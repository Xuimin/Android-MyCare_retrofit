package com.example.tracingapp.ui.menu.faq

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.RecyclerView
import com.example.tracingapp.ui.menu.DetailsActivity
import com.example.tracingapp.R
import com.example.tracingapp.data.viewmodel.UserViewModel
import com.example.tracingapp.ui.WelcomeActivity
import com.example.tracingapp.ui.history.HistoryActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FaqActivity : AppCompatActivity() {
    private val viewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_faq)

        val actionBar: ActionBar? = supportActionBar
        actionBar?.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.transparent)))
        actionBar?.setTitle(Html.fromHtml("<big><big>FAQ</big></big>"))

        val faq: Array<Faq> =
            arrayOf(
                Faq(
                    id = 1,
                    question = "What to do if I was close contact to a COVID-19 person?",
                    answer = "We encourage you to do a self test, and stay quarantine for at least 5 days. However, if you have any symptom you may go for a check up at the hospital."
                ),
                Faq(
                    id = 2,
                    question = "I have took my booster, but my status has yet to update. What should I do?",
                    answer = "You can contact our customer service by going to the helpdesk (menu icon > helpdesk) and submit a request to us. We\\'ll get back to you as soon as possible."
                ),
                Faq(
                    id = 3,
                    question = "There is a problem with the QR scanner. What should I do?",
                    answer = "You can go to your device settings and check whether you have allow camera. If this doesn\\'t solve the problem, try refreshing your device."
                ),
                Faq(
                    id = 4,
                    question = "Should I report to the hospital if I was tested COVID-19 positive?",
                    answer = "Yes, you should. Remember to bring along your self test kit and do another check up at the hospital. Please do not go anywhere else to prevent the spread of COVID-19."
                ),
                Faq(
                    id = 5,
                    question = "What should I take note before going for my vaccine dose?",
                    answer = "Drink plenty of water and have a good night rest is the most essential thing to do."
                ),
                Faq(
                    id = 6,
                    question = "I feel pain on my arm after taking my vaccine. Is it normal?",
                    answer = "It\\'s normal to feel a little pain after taking the vaccine. However, if the pain doesn\\'t subside. Please go to the hospital and have a check up."
                ),
                Faq(
                    id = 7,
                    question = "Can I walk in for my booster dose?",
                    answer = "Yes, you could for some places like (). This is only valid for your booster dose and 1 month after your 2nd vaccine dose."
                )
            )

        val recyclerView: RecyclerView = findViewById(R.id.rv_faq)
        recyclerView.adapter = FaqAdapter(faq)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.faq_nav_menu, menu)
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
            R.id.nav_history -> {
                val intent: Intent = Intent(this@FaqActivity, HistoryActivity::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.slide_in_left, R.anim.fade_out)
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
                Toast.makeText(this@FaqActivity, "Successfully Logout!", Toast.LENGTH_SHORT).show()
                val intent: Intent = Intent(this@FaqActivity, WelcomeActivity::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.slide_in_right, R.anim.fade_out)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}