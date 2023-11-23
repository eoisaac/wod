package com.eoisaac.wod.views

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eoisaac.wod.R
import com.eoisaac.wod.databinding.FragmentAllWorkoutsBinding


class AllWorkoutsFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentAllWorkoutsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllWorkoutsBinding.inflate(inflater, container, false)

        setupOnClickListeners()

        return binding.root
    }

    private fun setupOnClickListeners() {
    }

    override fun onClick(button: View) {
        when (button.id) {
            else -> {}
        }
    }

    private fun navigateToNewWorkoutActivity() {
        val mainActivity = requireActivity() as MainActivity
        mainActivity.navigateToFragment(NewWorkoutFragment(), R.id.navigation_new_workout)
    }
}
