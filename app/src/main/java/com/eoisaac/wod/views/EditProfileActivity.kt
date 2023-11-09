package com.eoisaac.wod.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.eoisaac.wod.R
import com.eoisaac.wod.databinding.ActivityEditProfileBinding

class EditProfileActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityEditProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)

        setContentView(binding.root)

        setupOnClickListeners()
    }

    private fun setupOnClickListeners() {
        binding.navigateBackButton.setOnClickListener(this)
    }

    override fun onClick(button: View) {
        when (button.id) {
            binding.navigateBackButton.id -> navigateBack()
        }
    }

    private fun navigateBack() = finish()
}