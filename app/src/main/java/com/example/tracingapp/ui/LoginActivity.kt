package com.example.tracingapp.ui

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import com.example.tracingapp.MainActivity
import com.example.tracingapp.R
import com.example.tracingapp.data.viewmodel.UserViewModel
import com.example.tracingapp.databinding.ActivityLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.regex.Pattern

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        val actionBar: ActionBar? = supportActionBar
        actionBar?.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.on_primary_dark)))
        actionBar?.setTitle(Html.fromHtml("<big><big>Login</big></big>"))

        binding.btnLogin.setOnClickListener { bindView() }
    }

    private fun bindView() {
        val phone = binding.editPhoneText.text.toString()
        val password = binding.editPassText.text.toString()

        if(TextUtils.isEmpty(phone) || TextUtils.isEmpty(password)) { Toast.makeText(this, "Please fill up all the fields!", Toast.LENGTH_SHORT).show() }
        else if(!validatePhone(phone)) { Toast.makeText(this, "Wrong phone format! Make sure phone have at least 9-10 digit long", Toast.LENGTH_SHORT).show() }
        else {
            viewModel.login(phone, password).observe(this) {
                if(it) Main()
                else Toast.makeText(this, "There was a problem with login! Please try again", Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun validatePhone(input: String): Boolean {
        val PATTERN: Pattern = Pattern.compile("^" + ".{9,10}" + "$")
        return PATTERN.matcher(input).matches()
    }

    fun ForgotPass(view: View) {
        val intent = Intent(this, ForgotPassActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_left, R.anim.fade_out)
    }

    fun Register(view: View) {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_left, R.anim.fade_out)
    }

    fun Main() {
        Toast.makeText(this, "Successfully login!", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_left, R.anim.fade_out)
        finish()
    }
}