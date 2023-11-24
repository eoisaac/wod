package com.eoisaac.wod.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eoisaac.wod.R
import com.eoisaac.wod.database.models.Exercise
import com.google.android.material.button.MaterialButton

class ExerciseAdapter(private val exercises: MutableList<Exercise>) :
    RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.exercise_list_item, parent, false)
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

    private fun removeExercise(position: Int) {
        if (position < 0 || position >= exercises.size) return
        exercises.removeAt(position)
        notifyItemRemoved(position)
    }

    inner class ExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.exercise_name)
        private val setsTextView: TextView = itemView.findViewById(R.id.sets_amount)
        val deleteButton: MaterialButton = itemView.findViewById(R.id.delete_exercise_button)

        fun bind(exercise: Exercise) {
            nameTextView.text = exercise.name
            setsTextView.text = exercise.sets.toString()
        }
    }
}