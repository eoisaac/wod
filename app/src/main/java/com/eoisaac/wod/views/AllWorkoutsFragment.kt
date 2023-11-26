package com.eoisaac.wod.views

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eoisaac.wod.adapters.WorkoutsAdapter
import com.eoisaac.wod.database.models.WorkoutWithExercises
import com.eoisaac.wod.databinding.FragmentAllWorkoutsBinding
import com.eoisaac.wod.interfaces.WorkoutPressListener
import com.eoisaac.wod.viewModels.AllWorkoutsViewModel


class AllWorkoutsFragment : Fragment(), WorkoutPressListener {
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

    override fun onDeletePress(workout: WorkoutWithExercises) {
        Log.d("AllWorkoutsFragment", "Deleting workout: ${workout.workout.name}")
        viewModel.deleteWorkout(workout)
    }

    private fun setupWorkoutsFilter() {
        searchInputField.addTextChangedListener(afterTextChanged = {
            workoutsAdapter.filter.filter(it)
        })
    }

    private fun observeWorkouts() {
        viewModel.getAllWorkouts().observe(viewLifecycleOwner) { workouts ->
            setupRecyclerView(workouts)
        }
    }

    private fun setupRecyclerView(workouts: List<WorkoutWithExercises>) {
        allWorkoutsRecyclerView.layoutManager = LinearLayoutManager(context)
        val recyclerView: RecyclerView = allWorkoutsRecyclerView

        workoutsAdapter = WorkoutsAdapter(workouts)
        recyclerView.adapter = workoutsAdapter
        workoutsAdapter.showCheckboxes(false)
        workoutsAdapter.showDeleteButton(true)

        workoutsAdapter.setWorkoutPressListener(this)
    }
}
