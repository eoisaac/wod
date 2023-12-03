package com.eoisaac.wod.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.eoisaac.wod.R
import com.eoisaac.wod.databinding.ActivityMainBinding
import com.eoisaac.wod.viewModels.MainViewModel
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        setContentView(binding.root)

        validateExercisesReset()

        setupBottomNavigationView()
        replaceFragment(WorkoutFragment())
    }

    private fun validateExercisesReset() {
        viewModel.validateReset()
    }

    private fun setupBottomNavigationView() {
        binding.bottomNavigationView.setOnItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_workout -> replaceFragment(WorkoutFragment())
            R.id.navigation_all_workouts -> replaceFragment(AllWorkoutsFragment())
            R.id.navigation_new_workout -> replaceFragment(NewWorkoutFragment())
        }
        return true
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_activity_frame_layout, fragment)
        transaction.commit()
    }

    fun navigateToFragment(fragment: Fragment, itemId: Int = 0) {
        this.replaceFragment(fragment)
        binding.bottomNavigationView.selectedItemId = itemId
    }
}