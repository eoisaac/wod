package com.eoisaac.wod.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eoisaac.wod.databinding.FragmentExercisesBinding

/**
 * A simple [Fragment] subclass.
 * Use the [ExercisesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ExercisesFragment : Fragment() {
    private lateinit var binding: FragmentExercisesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentExercisesBinding.inflate(inflater, container, false)
        return binding.root
    }
}
