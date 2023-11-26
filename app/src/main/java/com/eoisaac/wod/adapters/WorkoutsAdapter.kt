package com.eoisaac.wod.adapters

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eoisaac.wod.R
import com.eoisaac.wod.database.models.WorkoutWithExercises
import com.eoisaac.wod.interfaces.WorkoutPressListener


class WorkoutsAdapter(private var allWorkouts: List<WorkoutWithExercises>) :
    RecyclerView.Adapter<WorkoutsAdapter.WorkoutViewHolder>(), Filterable {

    private var workoutPressListener: WorkoutPressListener? = null

    private var filteredWorkouts: List<WorkoutWithExercises> = allWorkouts

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutsAdapter.WorkoutViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.all_workouts_list_item, parent, false)
        return WorkoutViewHolder(view)
    }

    override fun onBindViewHolder(holder: WorkoutsAdapter.WorkoutViewHolder, position: Int) {
        val workout = filteredWorkouts[position]
        holder.bind(workout, showExercisesCheckbox, showDeleteButton)
    }

    override fun getItemCount(): Int {
        return filteredWorkouts.size
    }

    private var showExercisesCheckbox = false
    private var showDeleteButton = false

    @SuppressLint("NotifyDataSetChanged")
    fun showCheckboxes(show: Boolean) {
        showExercisesCheckbox = show
    }

    fun showDeleteButton(show: Boolean) {
        showDeleteButton = show
    }

    fun setWorkoutPressListener(listener: WorkoutPressListener) {
        workoutPressListener = listener
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val searchString = charSequence.toString().lowercase().trim()
                filteredWorkouts = if (searchString.isEmpty()) {
                    allWorkouts
                } else {
                    filteredWorkouts.filter { workout ->
                        workout.workout.name.lowercase().contains(searchString)
                    }
                }
                return FilterResults().apply { values = filteredWorkouts }
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
                filteredWorkouts = filterResults.values as List<WorkoutWithExercises>
                notifyDataSetChanged()
            }
        }
    }

    inner class WorkoutViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val exerciseRecyclerView: RecyclerView = itemView.findViewById(R.id.exercises_recycler_view)

        private val nameTextView: TextView = itemView.findViewById(R.id.workout_name)
        private val deleteButton: TextView = itemView.findViewById(R.id.delete_workout_button)

        init {
            exerciseRecyclerView.layoutManager = LinearLayoutManager(itemView.context)
            exerciseRecyclerView.adapter = ExercisesAdapter(emptyList())
        }

        fun bind(workout: WorkoutWithExercises, showExerciseCheckbox: Boolean, showDeleteButton: Boolean) {
            nameTextView.text = workout.workout.name

            deleteButton.visibility = if (showDeleteButton) View.VISIBLE else View.INVISIBLE

            deleteButton.setOnClickListener {
                workoutPressListener?.onDeletePress(workout)
            }

            val exerciseAdapter = ExercisesAdapter(workout.exercises)
            exerciseRecyclerView.adapter = exerciseAdapter
            exerciseAdapter.showCheckboxes(showExerciseCheckbox)
        }
    }

}