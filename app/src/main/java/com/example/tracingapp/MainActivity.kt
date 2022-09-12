package com.example.tracingapp

import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.tracingapp.data.viewmodel.UserViewModel
import com.example.tracingapp.ui.HealthAssessmentActivity
import com.example.tracingapp.ui.WelcomeActivity
import com.example.tracingapp.ui.history.HistoryActivity
import com.example.tracingapp.ui.menu.DetailsActivity
import com.example.tracingapp.ui.menu.faq.FaqActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private val viewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val actionBar: ActionBar? = supportActionBar
        actionBar?.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.transparent)))

        val nav = findViewById<BottomNavigationView>(R.id.bottom_nav)
        val navController = findNavController(R.id.fragment_container)
        nav.setupWithNavController(navController)
        nav.background = null
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_nav_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = findNavController(R.id.fragment_container)

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
                overridePendingTransition(R.anim.slide_in_left, R.anim.fade_out)
                true
            }
            R.id.nav_history -> {
                val intent: Intent = Intent(this, HistoryActivity::class.java)
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
                overridePendingTransition(R.anim.slide_in_left, R.anim.fade_out)
                Toast.makeText(this@MainActivity, "Successfully Logout!", Toast.LENGTH_SHORT).show()
                val intent: Intent = Intent(this, WelcomeActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun HealthAssessment(view: View) {
        val intent: Intent = Intent(this, HealthAssessmentActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_left, R.anim.fade_out)
    }

    fun History(view: View) {
        val intent: Intent = Intent(this, HistoryActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_left, R.anim.fade_out)
    }

    fun makeAppointment(view: View) {
        val view: View = layoutInflater.inflate(R.layout.modal_appointment, null)
        val dialog = BottomSheetDialog(this)
        dialog.setContentView(view)
        dialog.show()

        val date_picker: EditText = view.findViewById(R.id.edit_date_text)
        date_picker.setOnClickListener {
            var calendar: Calendar = Calendar.getInstance()
            var day = calendar[Calendar.DAY_OF_MONTH]
            var month = calendar[Calendar.MONTH]
            var year = calendar[Calendar.YEAR]

            var dpicker: Unit = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener {
                        view, year, month, day ->
                    date_picker.setText(day.toString() + "/" + (month + 1).toString() + "/" + year.toString())
                },
                year,
                month,
                day
            ).show()
        }

        val btn_close = view.findViewById<Button>(R.id.btn_close)
        btn_close?.setOnClickListener {
            dialog.dismiss()
        }

        val btn_confirm = view.findViewById<Button>(R.id.btn_confirm)
        btn_confirm?.setOnClickListener {
            Toast.makeText(this, "You have successfully made an appointment. Please wait for approval.", Toast.LENGTH_LONG).show()
            dialog.dismiss()
        }

        val tv_close = view.findViewById<TextView>(R.id.tv_close)
        tv_close?.setOnClickListener {
            dialog.dismiss()
        }

        val dropdown = view.findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView)

        val vaccine_type = resources.getStringArray(R.array.vaccine_type)
        val arrayAdapter = ArrayAdapter(this, R.layout.items_dropdown, vaccine_type)
        dropdown.setAdapter(arrayAdapter)
    }

    fun verifyProfile(view: View) {
        val view: View = layoutInflater.inflate(R.layout.modal_verification, null)
        val dialog = BottomSheetDialog(this)
        dialog.setContentView(view)
        dialog.show()

        val btn_close = view.findViewById<Button>(R.id.btn_close)
        btn_close?.setOnClickListener {
            dialog.dismiss()
        }

        val btn_submit = view.findViewById<Button>(R.id.btn_submit)
        btn_submit?.setOnClickListener {
            Toast.makeText(this, "Your profile has been successfully verified.", Toast.LENGTH_LONG).show()
            dialog.dismiss()
        }

        val tv_close = view.findViewById<TextView>(R.id.tv_close)
        tv_close?.setOnClickListener {
            dialog.dismiss()
        }
    }

    override fun onItemSelected(p0: AdapterView<*>?, args: View?, position: Int, id: Long) {}

    override fun onNothingSelected(p0: AdapterView<*>?) {}
}