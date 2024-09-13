package com.example.authontication

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.authontication.databinding.ActivityMainBinding
import com.example.authontication.databinding.ActivityRegistrationBinding
import com.example.authontication.utils.AppUtils

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signup.setOnClickListener {
            var email = binding.editTextTextEmailAddress.text.toString().trim()
            var password = binding.editTextTextPassword.text.toString().trim()

            validationOfEmailPassword(email, password)
        }

//        if not have an account

        binding.textBtn.setOnClickListener {
            var intent = Intent(this,RegistrationActivity::class.java)
            startActivity(intent)
        }
    }

    //App util (function which declare globally)


    private fun validationOfEmailPassword(email: String, password: String) {
        reset()
        var tokenE = false
        var tokenP = false

        // verifying Email

        if (email.isBlank()) {
            var etEmail = binding.editTextTextEmailAddress
            var msgE = " Email is required! "
            errorEventHandler(etEmail, msgE)

        } else {
            if (!AppUtils.isValidEmail(email)) {
                var etEmail = binding.editTextTextEmailAddress
                var msgE = " Invalid Email "
                errorEventHandler(etEmail, msgE)
            } else {
                binding.editTextTextEmailAddress.setBackgroundResource(R.drawable.et_box_bg)
                tokenE = true
            }
        }

//       verifying password

        if (password.isBlank()) {
            var etpass = binding.editTextTextPassword
            var msgp = " Password is required! "
            errorEventHandler(etpass, msgp)

        } else {
            if (!AppUtils.isValidPassword(password)) {
                var etpass = binding.editTextTextPassword
                var msgp = " Invalid Password "
                errorEventHandler(etpass, msgp)
            } else {
                binding.editTextTextPassword.setBackgroundResource(R.drawable.et_box_bg)
                tokenP = true
            }

        }

        var tokenC = false

//        verifying checkBox
        if(!binding.checkBox2.isChecked){

            Toast.makeText(this, "Are you sure password remember you", Toast.LENGTH_SHORT).show()
        }else tokenC=true

//        all is verified

        if (tokenE && tokenP && tokenC)
            Toast.makeText(this, " Login Successfully ", Toast.LENGTH_SHORT).show()
    }

//      reset a background
    private fun reset() {
        binding.editTextTextEmailAddress.setBackgroundResource(R.drawable.et_box_bg)
        binding.editTextTextPassword.setBackgroundResource(R.drawable.et_box_bg)
    }
//      set a error
    private fun errorEventHandler(view: EditText, msg: String) {
        view.setError(msg)
        view.setBackgroundResource(R.drawable.et_box_err_bg)
        view.requestFocus()
    }
}