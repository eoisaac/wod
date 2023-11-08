package com.eoisaac.wod.views

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eoisaac.wod.databinding.FragmentProfileBinding


class ProfileFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        setupOnClickListeners()

        return binding.root
    }

    private fun setupOnClickListeners() {
        binding.navigateToPreferencesButton.setOnClickListener(this)
    }

    override fun onClick(button: View) {
        when (button.id) {
            binding.navigateToPreferencesButton.id -> navigateToPreferencesActivity()
        }
    }

    private fun navigateToPreferencesActivity() {
        val intent = Intent(activity, PreferencesActivity::class.java)
        startActivity(intent)
    }


}
