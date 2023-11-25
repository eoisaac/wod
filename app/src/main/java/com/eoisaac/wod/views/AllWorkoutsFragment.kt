package com.eoisaac.wod.views

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eoisaac.wod.adapters.WorkoutAdapter
import com.eoisaac.wod.databinding.FragmentAllWorkoutsBinding
import com.eoisaac.wod.viewModels.AllWorkoutsViewModel


class AllWorkoutsFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentAllWorkoutsBinding
    private lateinit var viewModel: AllWorkoutsViewModel

    private lateinit var workoutsAdapter: WorkoutAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllWorkoutsBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[AllWorkoutsViewModel::class.java]

        viewModel.getAllWorkouts().observe(viewLifecycleOwner) { workouts ->
//            Log.i("AllWorkoutsFragment", "Workouts: $workouts")
            workoutsAdapter.updateWorkouts(workouts)
        }

        setupOnClickListeners()
        setupRecyclerView()
        return binding.root
    }

    private val workoutsRecyclerView by lazy { binding.workoutsRecyclerView }

    private fun setupOnClickListeners() {
    }

    override fun onClick(button: View) {
        when (button.id) {
            else -> {}
        }
    }

    private fun setupRecyclerView() {
        workoutsRecyclerView.layoutManager = LinearLayoutManager(context)
        val workoutsRecyclerView: RecyclerView = workoutsRecyclerView

        workoutsAdapter = WorkoutAdapter(emptyList())
        workoutsRecyclerView.adapter = workoutsAdapter
    }

}
