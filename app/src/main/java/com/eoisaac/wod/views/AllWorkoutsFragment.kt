package com.eoisaac.wod.views

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.eoisaac.wod.R
import com.eoisaac.wod.databinding.FragmentAllWorkoutsBinding
import com.eoisaac.wod.viewModels.AllWorkoutsViewModel


class AllWorkoutsFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentAllWorkoutsBinding
    private lateinit var viewModel: AllWorkoutsViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllWorkoutsBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[AllWorkoutsViewModel::class.java]

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

}
