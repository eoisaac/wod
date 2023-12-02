package com.eoisaac.wod.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eoisaac.wod.R
import com.eoisaac.wod.adapters.WorkoutsAdapter
import com.eoisaac.wod.database.models.Exercise
import com.eoisaac.wod.database.models.WorkoutWithExercises
import com.eoisaac.wod.databinding.FragmentAllWorkoutsBinding
import com.eoisaac.wod.interfaces.ExercisePressListener
import com.eoisaac.wod.interfaces.WorkoutPressListener
import com.eoisaac.wod.utils.setShape
import com.eoisaac.wod.utils.setTopGravity
import com.eoisaac.wod.viewModels.AllWorkoutsViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar


class AllWorkoutsFragment : Fragment(), WorkoutPressListener, ExercisePressListener {
    private lateinit var binding: FragmentAllWorkoutsBinding
    private lateinit var viewModel: AllWorkoutsViewModel

    private lateinit var workoutsAdapter: WorkoutsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllWorkoutsBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[AllWorkoutsViewModel::class.java]

        observeWorkouts()
        setupWorkoutsFilter()
        return binding.root
    }

    private val allWorkoutsRecyclerView by lazy { binding.allWorkoutsRecyclerView }
    private val searchInputField by lazy { binding.searchTextInputField }

    override fun onCheckPress(exercise: Exercise) {}
    override fun onDeletePress(workout: WorkoutWithExercises) {
        MaterialAlertDialogBuilder(requireContext())
            .setShape()
            .setTitle(getString(R.string.delete_workout))
            .setMessage(getString(R.string.confirm_delete_workout_message))
            .setPositiveButton(getString(R.string.delete)) { _, _ -> handleDeleteWorkout(workout) }
            .setNegativeButton(getString(R.string.cancel)) { dialog, _ -> dialog.dismiss() }
            .show()
    }

    private fun handleDeleteWorkout(workout: WorkoutWithExercises) {
        val deleted = viewModel.deleteWorkout(workout)
        if (deleted > 0) {
            val message = getString(R.string.deleted_workout_message)
            Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).setTopGravity().setShape().show()
        }
    }

    private fun setupWorkoutsFilter() {
        searchInputField.addTextChangedListener(afterTextChanged = {
            workoutsAdapter.filter.filter(it)
        })
    }

    private fun observeWorkouts() {
        viewModel.getAllWorkouts().observe(viewLifecycleOwner) { workouts -> setupRecyclerView(workouts) }
    }

    private fun setupRecyclerView(workouts: List<WorkoutWithExercises>) {
        allWorkoutsRecyclerView.layoutManager = LinearLayoutManager(context)
        val recyclerView: RecyclerView = allWorkoutsRecyclerView

        workoutsAdapter = WorkoutsAdapter(workouts)
        recyclerView.adapter = workoutsAdapter
        workoutsAdapter.showCheckboxes(false)
        workoutsAdapter.showDeleteButton(true)

        workoutsAdapter.setWorkoutPressListener(this)
        workoutsAdapter.setExercisePressListener(this)
    }

}
