package com.eoisaac.wod.adapters

import android.annotation.SuppressLint
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
import com.eoisaac.wod.interfaces.ExercisePressListener
import com.eoisaac.wod.interfaces.WorkoutPressListener


/**
 * Adapter for the list of workouts
 * It's responsible for displaying the workouts and their exercises
 * @param allWorkouts List of workouts to display
 */
class WorkoutsAdapter(
    private var allWorkouts: List<WorkoutWithExercises>
) :
    RecyclerView.Adapter<WorkoutsAdapter.WorkoutViewHolder>(), Filterable {

    private var workoutPressListener: WorkoutPressListener? = null
    private var exercisePressListener: ExercisePressListener? = null

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

    /**
     * Set the display of the checkbox for each exercise
     * @param show Boolean to show or hide the checkbox
     */
    fun showCheckboxes(show: Boolean) {
        showExercisesCheckbox = show
    }

    /**
     * Set the display of the delete button for each workout
     * @param show Boolean to show or hide the delete button
     */
    fun showDeleteButton(show: Boolean) {
        showDeleteButton = show
    }

    /**
     * Set the listener for the workout press
     * @param listener Listener to be called when the workout is pressed
     */
    fun setWorkoutPressListener(listener: WorkoutPressListener) {
        workoutPressListener = listener
    }

    /**
     * Set the listener for the exercise press
     * @param listener Listener to be called when the exercise is pressed
     */
    fun setExercisePressListener(listener: ExercisePressListener) {
        exercisePressListener = listener
    }

    /**
     * Filter the workouts by name, it's called when the user types in the search input
     * @return Filter object with the filtered workouts
     */
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
            exerciseAdapter.setExercisePressListener(exercisePressListener!!)
            exerciseRecyclerView.adapter = exerciseAdapter
            exerciseAdapter.showCheckboxes(showExerciseCheckbox)
        }
    }

}