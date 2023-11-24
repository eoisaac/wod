package com.eoisaac.wod.views

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eoisaac.wod.adapters.ExerciseAdapter
import com.eoisaac.wod.database.models.Exercise
import com.eoisaac.wod.databinding.FragmentNewWorkoutBinding
import com.eoisaac.wod.viewModels.NewWorkoutViewModel
import com.google.android.material.snackbar.Snackbar


class NewWorkoutFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentNewWorkoutBinding
    private lateinit var viewModel: NewWorkoutViewModel

    private lateinit var exercisesAdapter: ExerciseAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewWorkoutBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[NewWorkoutViewModel::class.java]

        setupOnClickListeners()
        setupRecyclerView()
        return binding.root
    }

    // Fragment elements
    private val workoutNameInputField by lazy { binding.nameTextInputField }
    private val exerciseNameInputField by lazy { binding.exerciseNameTextInputField }
    private val exerciseSetsInputField by lazy { binding.exerciseSetsTextInputField }
    private val createWorkoutButton by lazy { binding.createWorkoutButton }
    private val addExerciseButton by lazy { binding.addExerciseButton }
    private val exercisesRecyclerView by lazy { binding.exercisesRecyclerView }

    private fun setupOnClickListeners() {
        createWorkoutButton.setOnClickListener(this)
        addExerciseButton.setOnClickListener(this)
    }

    override fun onClick(button: View) {
        when (button.id) {
            createWorkoutButton.id -> handleCreateNewWorkout()
            addExerciseButton.id -> handleAddExercise()
        }
    }

    private fun setupRecyclerView() {
        exercisesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        val exercisesRecyclerView: RecyclerView = exercisesRecyclerView

        exercisesAdapter = ExerciseAdapter(viewModel.getNewWorkoutExercises())
        exercisesRecyclerView.adapter = exercisesAdapter
    }

    private fun handleAddExercise() {
        val name = exerciseNameInputField.text.toString()
        val sets = exerciseSetsInputField.text.toString()

        if (name.isEmpty()) {
            exerciseNameInputField.requestFocus()
            Snackbar.make(binding.root, "Exercise name cannot be empty", Snackbar.LENGTH_SHORT).show()
            return
        }

        if (sets.isEmpty()) {
            exerciseSetsInputField.requestFocus()
            Snackbar.make(binding.root, "Sets cannot be empty", Snackbar.LENGTH_SHORT).show()
            return
        }

        viewModel.addExercise(name, sets.toInt())
        exercisesAdapter.notifyItemInserted(exercisesAdapter.itemCount - 1)

        exerciseNameInputField.text?.clear()
        exerciseSetsInputField.text?.clear()
        exerciseSetsInputField.requestFocus()
    }

    private fun handleCreateNewWorkout() {
        val name = workoutNameInputField.text.toString()
        val weekDay = 0

        if (name.isEmpty()) {
            workoutNameInputField.requestFocus()
            Snackbar.make(binding.root, "Workout Name cannot be empty", Snackbar.LENGTH_SHORT).show()
            return
        }

        viewModel.createNewWorkout(name, weekDay)
    }
}