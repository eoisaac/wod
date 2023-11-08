package com.eoisaac.wod.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eoisaac.wod.databinding.FragmentAllWorkoutsBinding


class AllWorkoutsFragment : Fragment() {
    private lateinit var binding: FragmentAllWorkoutsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllWorkoutsBinding.inflate(inflater, container, false)
        return binding.root
    }
}
