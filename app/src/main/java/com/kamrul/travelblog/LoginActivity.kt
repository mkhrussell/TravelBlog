package com.kamrul.travelblog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AlertDialog
import com.google.android.material.textfield.TextInputLayout
import com.kamrul.travelblog.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener { onLoginClicked() }
        binding.textUsernameLayout.editText?.addTextChangedListener(createTextWatcher(binding.textUsernameLayout))
        binding.textPasswordInput.editText?.addTextChangedListener(createTextWatcher(binding.textPasswordInput))
    }

    private fun onLoginClicked() {
        val userName = binding.textUsernameLayout.editText?.text.toString()
        val password = binding.textPasswordInput.editText?.text.toString()
        if(userName.isEmpty()) {
            binding.textUsernameLayout.error = "Username must not be empty"
        } else if(password.isEmpty()) {
            binding.textPasswordInput.error = "Password must not be empty"
        } else if(userName != "admin" && password != "admin") {
            showErrorDialog()
        }
    }

    private fun createTextWatcher(textInputLayout: TextInputLayout): TextWatcher {
        return object: TextWatcher {
            override fun beforeTextChanged(text: CharSequence?, start: Int, count: Int, after: Int) {
                //Not needed
            }

            override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
                textInputLayout.error = null
            }

            override fun afterTextChanged(editable: Editable?) {
                //Not needed
            }
        }
    }

    private fun showErrorDialog() {
        AlertDialog.Builder(this)
            .setTitle("Login Failed")
            .setMessage("Username or password was incorrect. Please try again.")
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .show()
    }
}