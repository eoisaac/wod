package com.eoisaac.wod.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eoisaac.wod.databinding.FragmentWorkoutBinding
import com.eoisaac.wod.utils.getTimeBasedGreeting
import java.util.*


class WorkoutFragment : Fragment() {
    private lateinit var binding: FragmentWorkoutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWorkoutBinding.inflate(inflater, container, false)

        val greeting = getTimeBasedGreeting(requireContext(), Date())
        binding.timeGreetingTextView.text = greeting

        return binding.root
    }
}