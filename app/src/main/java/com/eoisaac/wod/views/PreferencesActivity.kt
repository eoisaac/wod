package com.eoisaac.wod.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import com.eoisaac.wod.R
import com.eoisaac.wod.databinding.ActivityPreferencesBinding

class PreferencesActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityPreferencesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPreferencesBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val appLanguages = resources.getStringArray(R.array.languages)
        val adapter = ArrayAdapter(this, R.layout.dropdown_item, appLanguages)
        binding.languageDropdown.setAdapter(adapter)

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