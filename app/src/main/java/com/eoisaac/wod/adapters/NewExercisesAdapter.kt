package com.eoisaac.wod.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eoisaac.wod.R
import com.eoisaac.wod.database.models.Exercise
import com.eoisaac.wod.utils.StringContent
import com.google.android.material.button.MaterialButton

/**
 * Adapter for the list of exercises in a workout
 * It's responsible for displaying the exercises and their sets amount
 * @param exercises List of exercises to display
 */
class NewExercisesAdapter(private val exercises: MutableList<Exercise>) :
    RecyclerView.Adapter<NewExercisesAdapter.ExerciseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.new_exercises_list_item, parent, false)
        return ExerciseViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val exercise = exercises[position]
        holder.deleteButton.setOnClickListener { removeExercise(position) }
        holder.bind(exercise)
    }

    override fun getItemCount(): Int {
        return exercises.size
    }

    /**
     * Remove an exercise from the list, given its position it verifies if it's a valid position
     * @param position Position of the exercise to be removed
     */
    private fun removeExercise(position: Int) {
        if (position < 0 || position >= exercises.size) return
        exercises.removeAt(position)

        notifyItemRemoved(position)
        notifyItemRangeChanged(position, exercises.size)
    }

    inner class ExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.exercise_name)
        private val setsTextView: TextView = itemView.findViewById(R.id.sets_amount)
        val deleteButton: MaterialButton = itemView.findViewById(R.id.delete_exercise_button)

        fun bind(exercise: Exercise) {
            val setsContent = StringContent.StringResource(R.string.exercise_sets_amount, exercise.sets)

            nameTextView.text = exercise.name
            setsTextView.text = setsContent.asString(itemView.context)
        }
    }
}