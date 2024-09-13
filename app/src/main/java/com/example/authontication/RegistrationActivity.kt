package com.example.authontication

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.authontication.databinding.ActivityRegistrationBinding
import com.example.authontication.utils.AppUtils

class RegistrationActivity : AppCompatActivity() {
    private lateinit var bind : ActivityRegistrationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        bind = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(bind.root)

        //        drop down list
        
        var list = listOf("Select Course","Android development📱","Web Development💻","Graphic Design🚀")
        var adapterarr = ArrayAdapter(this, android.R.layout.simple_list_item_1,list)
        adapterarr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        bind.spinner.adapter = adapterarr
        var selectedItemInSpinner = list.get(0)
        bind.spinner.onItemSelectedListener= object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                selectedItemInSpinner = list[p2]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }

//        Set back pressed on login from register

        bind.textBtn.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        bind.btnRegister.setOnClickListener {
            var count = 0
            var firstname = bind.etFirstname.text.toString().trim()
            var lastname = bind.etLastname.text.toString().trim()
            var etEmail = bind.etEmail.text.toString().trim()
            var etPassword = bind.etPassword.text.toString().trim()
            var etCmfPassword = bind.etConfirmPassword.text.toString().trim()

            var radioGender = bind.radioGroup.checkedRadioButtonId
            if (radioGender==-1) {
                Toast.makeText(this, "Select Gender", Toast.LENGTH_SHORT).show()
                bind.radioGroup.requestFocus()
            }
            var termsCheck = bind.checkboxTerms.isChecked
            if (!termsCheck) Toast.makeText(this, "Accept a terms and condition", Toast.LENGTH_SHORT).show()
                bind.checkboxTerms.requestFocus()

            var dropDownSelect = list.get(0)
            if (dropDownSelect == selectedItemInSpinner){
                bind.spinner.setBackgroundResource(R.drawable.et_box_err_bg)
                bind.spinner.requestFocus()
                Toast.makeText(this, "Select course first", Toast.LENGTH_SHORT).show()

                validateByPattern(lastname,firstname,etEmail,etPassword,etCmfPassword,)
            }
          }
}

    private fun validateByPattern(
        lastname: String,
        firstname: String,
        etEmail: String,
        etPassword: String,
        etCmfPassword: String,

    ) {
        resetbgs()
        var count =0

        // verifying Email

        if (etEmail.isBlank()) {
            var etEmail = bind.etEmail
            var msgE = " Email is required! "
            errorEventHandler(etEmail, msgE)

        } else {
            if (!AppUtils.isValidEmail(etEmail)) {
                var etEmail = bind.etEmail
                var msgE = " Invalid Email "
                errorEventHandler(etEmail, msgE)
            } else {
                bind.etEmail.setBackgroundResource(R.drawable.bg_et_register)
                count++
            }
        }

//        verifying Password
        if (etPassword.isBlank()) {
            var etPassword = bind.etPassword
            var msgp = " Password is required! "
            errorEventHandler(etPassword, msgp)
        } else {
            if (!AppUtils.isValidPassword(etPassword)) {
                var etPassword = bind.etPassword
                var msgp = " Invalid Password "
                errorEventHandler(etPassword, msgp)
            } else {
                bind.etPassword.setBackgroundResource(R.drawable.bg_et_register)
                count++
            }
        }

//        comparing Password and ConfirmPassword
        if(!etPassword.equals(etCmfPassword) && etCmfPassword.isBlank() ) {
            var etCmfPassword = bind.etConfirmPassword
            var msgp = " Not Password matched "
            errorEventHandler(etCmfPassword, msgp)
        } else {
            bind.etConfirmPassword.setBackgroundResource(R.drawable.bg_et_register)
            count++

        }

//        verifying fname
        if (firstname.isBlank()) {
            var etfname = bind.etFirstname
            var msgfn = " First name is required! "
            errorEventHandler(etfname, msgfn)

        } else {
            if (!AppUtils.isValidName(firstname)) {
                var etfname = bind.etFirstname
                var msgfn = " Invalid First name "
                errorEventHandler(etfname, msgfn)
            } else {
                bind.etFirstname.setBackgroundResource(R.drawable.bg_et_register)
                count++
            }
        }

//        verifying lname

        if (lastname.isBlank()) {
            var etlname = bind.etLastname
            var msgfn = " Last name is required! "
            errorEventHandler(etlname, msgfn)

        } else {
            if (!AppUtils.isValidName(lastname)) {
                var etlname = bind.etLastname
                var msgln = " Invalid First name "
                errorEventHandler(etlname, msgln)
            } else {
                bind.etLastname.setBackgroundResource(R.drawable.bg_et_register)
                count++
            }
        }

    }

    private fun resetbgs() {
        bind.etFirstname.setBackgroundResource(R.drawable.bg_et_register)
        bind.etLastname.setBackgroundResource(R.drawable.bg_et_register)
        bind.etEmail.setBackgroundResource(R.drawable.bg_et_register)
        bind.etPassword.setBackgroundResource(R.drawable.bg_et_register)
        bind.etConfirmPassword.setBackgroundResource(R.drawable.bg_et_register)
        bind.spinner.setBackgroundResource(R.drawable.bg_et_register)
    }

    private fun errorEventHandler(editText: EditText, msgE: String) {
        editText.setError(msgE)
        editText.setBackgroundResource(R.drawable.et_box_err_bg)
        editText.requestFocus()
    }

}