package com.eoisaac.wod

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.eoisaac.wod.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replaplaceFragment(WorkoutFragment())

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_workout -> replaplaceFragment(WorkoutFragment())
                R.id.navigation_exercises -> replaplaceFragment(ExercisesFragment())
                R.id.navigation_profile -> replaplaceFragment(ProfileFragment())
//                else -> {}
            }
            true
        }
    }

    private fun replaplaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_activity_frame_layout, fragment)
        transaction.commit()
    }
}