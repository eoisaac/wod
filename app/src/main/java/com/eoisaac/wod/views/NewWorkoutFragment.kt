package com.eoisaac.wod.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.eoisaac.wod.databinding.FragmentNewWorkoutBinding
import com.eoisaac.wod.viewModels.NewWorkoutViewModel
import com.google.android.material.snackbar.Snackbar


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
        with(binding) {
            if (nameTextInputField.text.isNullOrEmpty()) {
                nameTextInputField.error = "Name cannot be empty"
                Snackbar.make(binding.root, "Name cannot be empty", Snackbar.LENGTH_SHORT)
                    .setAction("OK") { nameTextInputField.requestFocus() }
                    .show()
                return
            }

            val checkboxes = listOf(
                mondaysCheckbox, tuesdaysCheckbox, wednesdaysCheckbox, thursdaysCheckbox,
                fridaysCheckbox, saturdaysCheckbox, sundaysCheckbox
            )

            val checkboxStatuses = mutableListOf<Boolean>()
            checkboxes.forEach { checkbox -> checkboxStatuses.add(checkbox.isChecked) }

            if (checkboxStatuses.all { !it }) {
                Toast.makeText(requireContext(), "Please select at least one day", Toast.LENGTH_SHORT).show()
                return
            }
        }

        val name = binding.nameTextInputField.text.toString()
        val weekDay = 0

        viewModel.createNewWorkout(name, weekDay)
    }

}