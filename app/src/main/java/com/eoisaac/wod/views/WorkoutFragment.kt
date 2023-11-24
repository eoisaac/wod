package com.eoisaac.wod.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.eoisaac.wod.R
import com.eoisaac.wod.databinding.FragmentWorkoutBinding
import com.eoisaac.wod.viewModels.WorkoutViewModel


class WorkoutFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentWorkoutBinding

    private lateinit var viewModel: WorkoutViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWorkoutBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[WorkoutViewModel::class.java]

        viewModel.timeBasedGreeting().observe(requireActivity()) {
            binding.timeGreetingTextView.text = it.asString(requireContext())
        }

        viewModel.getTimeBasedGreeting()
        setupOnClickListeners()

        return binding.root
    }

    private fun setupOnClickListeners() {
        binding.navigateToAllWorkoutsButton.setOnClickListener(this)
    }

    override fun onClick(button: View) {
        when (button.id) {
            binding.navigateToAllWorkoutsButton.id -> navigateToAllWorkoutsFragment()
        }
    }

    private fun navigateToAllWorkoutsFragment() {
        val mainActivity = requireActivity() as MainActivity
        mainActivity.navigateToFragment(AllWorkoutsFragment(), R.id.navigation_all_workouts)
    }
}