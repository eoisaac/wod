package com.eoisaac.wod.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eoisaac.wod.R
import com.eoisaac.wod.database.models.WorkoutWithExercises


class WorkoutAdapter(private var workouts: List<WorkoutWithExercises>) :
    RecyclerView.Adapter<WorkoutAdapter.WorkoutViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutAdapter.WorkoutViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.workout_list_item, parent, false)
        return WorkoutViewHolder(view)
    }

    override fun onBindViewHolder(holder: WorkoutAdapter.WorkoutViewHolder, position: Int) {
        val workout = workouts[position]
        holder.bind(workout)
    }

    override fun getItemCount(): Int {
        return workouts.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateWorkouts(newWorkouts: List<WorkoutWithExercises>) {
        workouts = newWorkouts
        notifyDataSetChanged()
    }

    inner class WorkoutViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.workout_name)

        fun bind(workout: WorkoutWithExercises) {
            nameTextView.text = workout.workout.name
        }
    }

}