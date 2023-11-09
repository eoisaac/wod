package com.eoisaac.wod.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.eoisaac.wod.databinding.ActivityNewWorkoutBinding

class NewWorkoutActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityNewWorkoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNewWorkoutBinding.inflate(layoutInflater)

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