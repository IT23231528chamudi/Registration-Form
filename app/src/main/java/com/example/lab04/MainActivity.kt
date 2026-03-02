package com.example.lab04

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.lab04.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // DataBinding object
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Connect XML using DataBinding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // SUBMIT button click
        binding.btnSubmit.setOnClickListener {

            // Create RegisterForm object (Part 17)
            val form = RegisterForm(
                binding.edtName.text.toString(),
                binding.edtEmail.text.toString(),
                binding.edtPhone.text.toString(),
                binding.edtPassword.text.toString(),
                binding.edtRePassword.text.toString()
            )

            // Validate empty fields (Part 18)

            if (form.name.isBlank()) {
                Toast.makeText(this, "Please enter username", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (form.email.isBlank()) {
                Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (form.phone.isBlank()) {
                Toast.makeText(this, "Please enter phone number", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (form.password.isBlank()) {
                Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (form.rePassword.isBlank()) {
                Toast.makeText(this, "Please retype password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // If all fields are filled → show dialog
            showAlertBox(this, form)
        }

        // CANCEL button click → clear fields
        binding.btnCancel.setOnClickListener {
            binding.edtName.setText("")
            binding.edtEmail.setText("")
            binding.edtPhone.setText("")
            binding.edtPassword.setText("")
            binding.edtRePassword.setText("")
        }
    }

    // Show AlertDialog using RegisterForm object
    private fun showAlertBox(context: Context, form: RegisterForm) {

        val builder = AlertDialog.Builder(context)

        val message = "Email: ${form.email}\n" +
                "Phone: ${form.phone}\n" +
                "Passwords: ${if (form.password == form.rePassword) "Matching" else "Not Matching"}."

        builder.setTitle("Welcome ${form.name}!")
        builder.setMessage(message)

        builder.setPositiveButton("Ok") { _, _ ->
            Toast.makeText(context, "Submitted Successfully", Toast.LENGTH_LONG).show()
        }

        builder.setNegativeButton("Cancel") { _, _ ->
            // Do nothing
        }

        builder.create().show()
    }
}