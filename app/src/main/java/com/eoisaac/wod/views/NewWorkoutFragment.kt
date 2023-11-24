package com.eoisaac.wod.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eoisaac.wod.databinding.FragmentNewWorkoutBinding


class NewWorkoutFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentNewWorkoutBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewWorkoutBinding.inflate(inflater, container, false)
        setupOnClickListeners()

        return binding.root
    }

    private fun setupOnClickListeners() {

    }

    override fun onClick(button: View) {
        when (button.id) {
            else -> {}
        }
    }

}