package com.example.tracingapp.ui.menu

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.text.Html
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.tracingapp.MainActivity
import com.example.tracingapp.R
import com.example.tracingapp.data.viewmodel.UserViewModel
import com.example.tracingapp.databinding.ActivityDetailsBinding
import com.example.tracingapp.ui.WelcomeActivity
import com.example.tracingapp.ui.history.HistoryActivity
import com.example.tracingapp.ui.menu.faq.FaqActivity
import dagger.hilt.android.AndroidEntryPoint
import org.w3c.dom.Text
import java.util.regex.Pattern

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    private val viewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details)

        val actionBar: ActionBar? = supportActionBar
        actionBar?.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.transparent)))
        actionBar?.setTitle(Html.fromHtml("<big><big>My Details</big></big>"))

        val location_type = resources.getStringArray(R.array.location_type)
        val arrayAdapter = ArrayAdapter(this, R.layout.items_dropdown, location_type)
        binding.autoCompleteTextView.setAdapter(arrayAdapter)
        binding.tvContact.text = "Contact (+60)"

        bindView()

        binding.btnSave.setOnClickListener { saveDetails() }
    }

    private fun bindView() {
        viewModel.getOwnDetails.observe(this) { details ->
            binding.etUsername.setText(details.fullname)
            binding.etUserContact.setText(details.phone)
            binding.etUserContact.isEnabled = false
            binding.etUserIc.setText(details.ic)
            binding.autoCompleteTextView.setHint(details.location)

            if(details.isVerified == true) {
                binding.etUsername.isEnabled = false
                binding.etUserIc.isEnabled = false
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.details_nav_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
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
                Toast.makeText(this@DetailsActivity, "Successfully Logout!", Toast.LENGTH_SHORT).show()
                val intent: Intent = Intent(this, WelcomeActivity::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.slide_in_right, R.anim.fade_out)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun validateIc(ic: String): Boolean {
        val PATTERN: Pattern = Pattern.compile("^" + ".{12}" + "$")
        return PATTERN.matcher(ic).matches()
    }

    fun saveDetails() {
        val fullname = binding.etUsername.text.toString()
        val ic = binding.etUserIc.text.toString()
        val location = binding.autoCompleteTextView.text.toString()
        val password = binding.etUserNewPass.text.toString()
        val confirmPassword = binding.etUserNewConfirmPass.text.toString()

        if(TextUtils.isEmpty(fullname) || TextUtils.isEmpty(ic)) { Toast.makeText(this, "Fullname and IC should not be left empty.", Toast.LENGTH_SHORT).show() }
        else if(password != confirmPassword) { Toast.makeText(this, "Password and Confirm Password do not match! Please try again.", Toast.LENGTH_SHORT).show() }
        else if(!validateIc(ic)){ Toast.makeText(this, "Wrong IC format! Make sure IC have 12 digits!", Toast.LENGTH_SHORT).show() }
        else {
            viewModel.updateOwnDetails(fullname, ic, location, password, confirmPassword).observe(this) {
                if(it) doneActivity()
                else Toast.makeText(this, "Failed to update! Please try again.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun doneActivity() {
        val intent: Intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_right, R.anim.fade_out)
        Toast.makeText(this, "Personal details has been successfully updated!", Toast.LENGTH_LONG).show()
    }

    fun closeActivity(view: View) {
        val intent: Intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_right, R.anim.fade_out)
    }
}