package com.eoisaac.wod.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.eoisaac.wod.databinding.FragmentNewWorkoutBinding
import com.eoisaac.wod.viewModels.NewWorkoutViewModel


class NewWorkoutFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentNewWorkoutBinding
    private lateinit var viewModel: NewWorkoutViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewWorkoutBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[NewWorkoutViewModel::class.java]

        setupOnClickListeners()
        return binding.root
    }

    private fun setupOnClickListeners() {
        binding.createWorkoutButton.setOnClickListener(this)
    }

    override fun onClick(button: View) {
        when (button.id) {
            binding.createWorkoutButton.id -> handleCreateNewWorkout()
        }
    }

    private fun handleCreateNewWorkout() {
        val name = binding.nameTextInputField.text.toString()
        val weekDay = 0

        viewModel.createNewWorkout(name, weekDay)
    }

}