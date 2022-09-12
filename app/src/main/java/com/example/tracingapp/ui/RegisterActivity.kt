package com.example.tracingapp.ui

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBar
import androidx.databinding.DataBindingUtil
import com.example.tracingapp.R
import com.example.tracingapp.data.viewmodel.UserViewModel
import com.example.tracingapp.databinding.ActivityRegisterBinding
import dagger.hilt.android.AndroidEntryPoint
import org.w3c.dom.Text
import java.util.regex.Pattern

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private val viewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)

        val actionBar: ActionBar? = supportActionBar
        actionBar?.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.on_primary_dark)))
        actionBar?.setTitle(Html.fromHtml("<big><big>Register</big></big>"))

        val dropdown = binding.autoCompleteTextView

        val location_type = resources.getStringArray(R.array.location_type)
        val arrayAdapter = ArrayAdapter(this, R.layout.items_dropdown, location_type)
        dropdown.setAdapter(arrayAdapter)

        binding.btnRegister.setOnClickListener { bindView() }
    }

    private fun bindView() {
        val fullname = binding.editNameText.text.toString()
        val phone = binding.editPhoneText.text.toString()
        val location = binding.autoCompleteTextView.text.toString()
        val password = binding.editPassText.text.toString()
        val confirmPassword = binding.editConfirmPassText.text.toString()

        if(TextUtils.isEmpty(fullname) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(location) || TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPassword)) {
            Toast.makeText(this, "Please fill up all the fields!", Toast.LENGTH_LONG).show()
        } else {
            if(!validatePhone(phone)) Toast.makeText(this, "Wrong phone format! Make sure phone have at least 9-10 digit long", Toast.LENGTH_SHORT).show()
            else if(!validatePass(password)) Toast.makeText(this, "Wrong password format! Make sure password has no white space, at least one special character and a capital letter", Toast.LENGTH_SHORT).show()
            else if(password != confirmPassword) {
                Toast.makeText(this, "Password and Confirm Password do not match! Please try again.", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.register(fullname, phone, location, password, confirmPassword).observe(this) {
                    if(it) LoginToMain()
                    else Toast.makeText(this, "User with this phone number exists! Please login.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    // phone conditions
    fun validatePhone(input: String): Boolean {
        val PATTERN: Pattern = Pattern.compile("^" + ".{9,10}" + "$")
        return PATTERN.matcher(input).matches()
    }

    // password conditions
    fun validatePass(input: String): Boolean {
        val PATTERN: Pattern = Pattern.compile("^" + "(?=.*[@#$%^&+=])" +  "(?=\\S+$)" + "(?=.*[A-Z])" + ".{6,}" + "$")
        return PATTERN.matcher(input).matches()
    }

    fun Login(view: View) {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_left, R.anim.fade_out)
    }

    fun LoginToMain() {
        Toast.makeText(this, "Successfully register!", Toast.LENGTH_SHORT).show()

        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_left, R.anim.fade_out)
    }
}