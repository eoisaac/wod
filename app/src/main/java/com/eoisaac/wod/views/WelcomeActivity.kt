package com.eoisaac.wod.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.eoisaac.wod.R
import com.eoisaac.wod.databinding.ActivityWelcomeBinding
import com.eoisaac.wod.utils.PrefsUtils
import com.eoisaac.wod.viewModels.WelcomeViewModel

class WelcomeActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityWelcomeBinding
    private lateinit var viewModel: WelcomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        PrefsUtils.init(this)

        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[WelcomeViewModel::class.java]

        setContentView(binding.root)

        val name = viewModel.getUserName()
        if (name.isNotEmpty()) {
            navigateToMainActivity()
            return
        }
        setupOnClickListeners()
    }

    private val startButton by lazy { binding.startButton }
    private val userNameInputField by lazy { binding.userNameTextInputField }

    private fun setupOnClickListeners() {
        startButton.setOnClickListener(this)
    }

    override fun onClick(button: View) {
        when (button.id) {
            startButton.id -> saveUserName()
        }
    }

    private fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun saveUserName() {
        val name = userNameInputField.text.toString()
        if (name.isEmpty()) {
            userNameInputField.requestFocus()
            userNameInputField.error = getString(R.string.invalid_name_error)
            return
        }

        val saved = viewModel.saveUserName(name)
        if (saved) {
            navigateToMainActivity()
        }
    }
}