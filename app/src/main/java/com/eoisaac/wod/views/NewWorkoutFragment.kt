package com.eoisaac.wod.views

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eoisaac.wod.R
import com.eoisaac.wod.adapters.NewExercisesAdapter
import com.eoisaac.wod.databinding.FragmentNewWorkoutBinding
import com.eoisaac.wod.entities.WeekDays
import com.eoisaac.wod.utils.setShape
import com.eoisaac.wod.utils.setTopGravity
import com.eoisaac.wod.viewModels.NewWorkoutViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText


class NewWorkoutFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentNewWorkoutBinding
    private lateinit var viewModel: NewWorkoutViewModel

    private lateinit var exercisesAdapter: NewExercisesAdapter

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
    private val newExercisesRecyclerView by lazy { binding.newExercisesRecyclerView }
    private val weekDaysCheckboxes by lazy {
        listOf(
            binding.sundaysCheckbox, binding.mondaysCheckbox,
            binding.tuesdaysCheckbox, binding.wednesdaysCheckbox,
            binding.thursdaysCheckbox, binding.fridaysCheckbox, binding.saturdaysCheckbox,
        )
    }

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
        newExercisesRecyclerView.layoutManager = LinearLayoutManager(context) // requireContext()
        val exercisesRecyclerView: RecyclerView = newExercisesRecyclerView

        exercisesAdapter = NewExercisesAdapter(viewModel.getNewWorkoutExercises())
        exercisesRecyclerView.adapter = exercisesAdapter
    }

    private fun handleAddExercise() {
        val name = exerciseNameInputField.text.toString()
        val sets = exerciseSetsInputField.text.toString()

        if (name.isEmpty()) {
            val error = getString(R.string.invalid_name_error)
            setInputFieldError(exerciseNameInputField, error)
            return
        }

        if (sets.isEmpty() || sets.toInt() <= 0) {
            val error = getString(R.string.invalid_exercise_sets_error)
            setInputFieldError(exerciseSetsInputField, error)
            return
        }

        viewModel.addExercise(name, sets.toInt())
        exercisesAdapter.notifyItemInserted(exercisesAdapter.itemCount - 1)

        exerciseNameInputField.text?.clear()
        exerciseSetsInputField.text?.clear()

        exerciseNameInputField.requestFocus()
    }

    private fun getExerciseWeekDays(): List<WeekDays> {
        return mapOf(
            weekDaysCheckboxes[0] to WeekDays.SUNDAY, weekDaysCheckboxes[1] to WeekDays.MONDAY,
            weekDaysCheckboxes[2] to WeekDays.TUESDAY, weekDaysCheckboxes[3] to WeekDays.WEDNESDAY,
            weekDaysCheckboxes[4] to WeekDays.THURSDAY, weekDaysCheckboxes[5] to WeekDays.FRIDAY,
            weekDaysCheckboxes[6] to WeekDays.SATURDAY,
        ).filter { it.key.isChecked }.map { it.value }
    }

    private fun handleCreateNewWorkout() {
        val name = workoutNameInputField.text.toString()
        val weekDays = getExerciseWeekDays()

        if (name.isEmpty() || name.length < 3) {
            val error = getString(R.string.invalid_name_error)
            setInputFieldError(workoutNameInputField, error)
            return
        }

        if (weekDays.isEmpty()) {
            val error = getString(R.string.no_week_days_error)
            binding.mondaysCheckbox.error = error
            binding.mondaysCheckbox.requestFocus()
            return
        }

        if (viewModel.getNewWorkoutExercises().isEmpty()) {
            val error = getString(R.string.no_exercises_error)
            setInputFieldError(exerciseNameInputField, error)
            return
        }

        val workoutId = viewModel.createNewWorkout(name, weekDays)

        var message = getString(R.string.create_workout_error)

        if (workoutId > 0) {
            message = getString(R.string.created_workout)
            resetWorkoutForm()
        }
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).setTopGravity().setShape().show()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun resetWorkoutForm() {
        workoutNameInputField.text?.clear()
        exerciseNameInputField.text?.clear()
        exerciseSetsInputField.text?.clear()

        viewModel.getNewWorkoutExercises().clear()
        exercisesAdapter.notifyDataSetChanged()
        weekDaysCheckboxes.forEach { it.isChecked = false }
    }

    private fun setInputFieldError(inputField: TextInputEditText, error: String) {
        inputField.requestFocus()
        inputField.error = error
    }
}