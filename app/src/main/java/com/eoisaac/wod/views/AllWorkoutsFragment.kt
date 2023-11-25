package com.eoisaac.wod.views

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eoisaac.wod.adapters.AllWorkoutsAdapter
import com.eoisaac.wod.database.models.WorkoutWithExercises
import com.eoisaac.wod.databinding.FragmentAllWorkoutsBinding
import com.eoisaac.wod.viewModels.AllWorkoutsViewModel


class AllWorkoutsFragment : Fragment() {
    private lateinit var binding: FragmentAllWorkoutsBinding
    private lateinit var viewModel: AllWorkoutsViewModel

    private lateinit var workoutsAdapter: AllWorkoutsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllWorkoutsBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[AllWorkoutsViewModel::class.java]

        viewModel.getAllWorkouts().observe(viewLifecycleOwner) { workouts ->
            setupRecyclerView(workouts)
        }
        setupWorkoutsFilter()

        return binding.root
    }

    private val workoutsRecyclerView by lazy { binding.workoutsRecyclerView }
    private val searchInputField by lazy { binding.searchTextInputField }

    private fun setupWorkoutsFilter() {
        searchInputField.addTextChangedListener(afterTextChanged = {
            workoutsAdapter.filter.filter(it)
        })
    }

    private fun setupRecyclerView(workouts: List<WorkoutWithExercises>) {
        workoutsRecyclerView.layoutManager = LinearLayoutManager(context)
        val workoutsRecyclerView: RecyclerView = workoutsRecyclerView

        workoutsAdapter = AllWorkoutsAdapter(workouts)
        workoutsRecyclerView.adapter = workoutsAdapter
    }

}
