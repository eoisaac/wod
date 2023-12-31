package com.eoisaac.wod.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eoisaac.wod.R
import com.eoisaac.wod.database.models.Exercise
import com.eoisaac.wod.interfaces.ExercisePressListener
import com.eoisaac.wod.utils.StringContent

/**
 * Adapter for the list of exercises in a workout
 * It's responsible for displaying the exercises and their sets amount
 * @param exercises List of exercises to display
 */
class ExercisesAdapter(private val exercises: List<Exercise>) :
    RecyclerView.Adapter<ExercisesAdapter.ExerciseViewHolder>() {

    private var exercisePressListener: ExercisePressListener? = null
    private var showCheckbox = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.exercise_list_item, parent, false)
        return ExerciseViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val exercise = exercises[position]
        holder.bind(exercise, showCheckbox)
    }

    override fun getItemCount(): Int {
        return exercises.size
    }

    /**
     * Set the listener for the checkbox press
     * @param listener Listener to be called when the checkbox is pressed
     */
    fun setExercisePressListener(listener: ExercisePressListener) {
        exercisePressListener = listener
    }

    /**
     * Show or hide the checkbox for each exercise
     * @param show Boolean to show or hide the checkbox
     */
    fun showCheckboxes(show: Boolean) {
        showCheckbox = show
    }

    inner class ExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.exercise_name)
        private val setsTextView: TextView = itemView.findViewById(R.id.sets_amount)
        private val completeCheckbox: CheckBox = itemView.findViewById(R.id.exercise_complete_checkbox)

        fun bind(exercise: Exercise, showCheckbox: Boolean = false) {
            val setsContent = StringContent.StringResource(R.string.exercise_sets_amount, exercise.sets)

            nameTextView.text = exercise.name
            setsTextView.text = setsContent.asString(itemView.context)

            completeCheckbox.visibility = if (showCheckbox) View.VISIBLE else View.GONE
            completeCheckbox.isChecked = exercise.isCompleted

            completeCheckbox.setOnCheckedChangeListener { _, isChecked ->
                exercise.isCompleted = isChecked
                exercisePressListener?.onCheckPress(exercise)
            }
        }
    }

}