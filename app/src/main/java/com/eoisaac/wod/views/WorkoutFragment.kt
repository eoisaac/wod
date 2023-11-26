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
import com.eoisaac.wod.R
import com.eoisaac.wod.adapters.WorkoutsAdapter
import com.eoisaac.wod.database.models.WorkoutWithExercises
import com.eoisaac.wod.databinding.FragmentWorkoutBinding
import com.eoisaac.wod.viewModels.WorkoutViewModel


class WorkoutFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentWorkoutBinding
    private lateinit var viewModel: WorkoutViewModel

    private lateinit var workoutsAdapter: WorkoutsAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWorkoutBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[WorkoutViewModel::class.java]

        viewModel.timeBasedGreeting().observe(viewLifecycleOwner) {
            binding.timeGreetingTextView.text = it.asString(requireContext())
        }

        viewModel.getDayWorkouts().observe(viewLifecycleOwner) { workouts ->
            Log.i("WorkoutFragment", "Workouts: $workouts")
            setupRecyclerView(workouts)
        }

        viewModel.getTimeBasedGreeting()
        setupOnClickListeners()
        return binding.root
    }

    private val circularProgressIndicator by lazy { binding.circularProgressIndicator }
    private val circularProgressIndicatorText by lazy { binding.progressIndicatorTextView }
    private val progressMessage by lazy { binding.progressMessageTextView }
    private val totalExercises by lazy { binding.totalExercisesTextView }

    private val dayWorkoutsRecyclerView by lazy { binding.dayWorkoutsRecyclerView }
    private val navigateToAllWorkoutsButton by lazy { binding.navigateToAllWorkoutsButton }

    private fun setupRecyclerView(workouts: List<WorkoutWithExercises>) {
        dayWorkoutsRecyclerView.layoutManager = LinearLayoutManager(context)
        val recyclerView: RecyclerView = dayWorkoutsRecyclerView

        workoutsAdapter = WorkoutsAdapter(workouts)
        recyclerView.adapter = workoutsAdapter
        workoutsAdapter.showCheckboxes(true)
        workoutsAdapter.showDeleteButton(false)
    }

    private fun setupOnClickListeners() {
        navigateToAllWorkoutsButton.setOnClickListener(this)
    }

    override fun onClick(button: View) {
        when (button.id) {
            navigateToAllWorkoutsButton.id -> navigateToAllWorkoutsFragment()
        }
    }

    private fun navigateToAllWorkoutsFragment() {
        val mainActivity = requireActivity() as MainActivity
        mainActivity.navigateToFragment(AllWorkoutsFragment(), R.id.navigation_all_workouts)
    }
}