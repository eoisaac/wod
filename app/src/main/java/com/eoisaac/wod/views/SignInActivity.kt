package com.eoisaac.wod.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.eoisaac.wod.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupOnClickListeners()
    }


    private fun setupOnClickListeners() {
        binding.navigateToSignUpButton.setOnClickListener(this)
        binding.signInButton.setOnClickListener(this)
    }

    override fun onClick(button: View) {
        when (button.id) {
            binding.navigateToSignUpButton.id -> navigateToSignUpActivity()
            binding.signInButton.id -> navigateToMainActivity()
        }
    }

    private fun navigateToSignUpActivity() {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}