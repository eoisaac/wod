package com.eoisaac.wod.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.eoisaac.wod.databinding.ActivitySignUpBinding
import com.eoisaac.wod.utils.getTimeBasedGreeting
import com.eoisaac.wod.viewModels.SignUpModelView

class SignUpActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivitySignUpBinding

    private lateinit var viewModel: SignUpModelView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[SignUpModelView::class.java]

        setupOnClickListeners()
    }

    private fun setupOnClickListeners() {
        binding.navigateToSignInButton.setOnClickListener(this)
    }

    override fun onClick(button: View) {
        when (button.id) {
            binding.navigateToSignInButton.id -> navigateToSignInActivity()
        }
    }

    private fun navigateToSignInActivity() {
        val intent = Intent(this, SignInActivity::class.java)
        startActivity(intent)
    }
}