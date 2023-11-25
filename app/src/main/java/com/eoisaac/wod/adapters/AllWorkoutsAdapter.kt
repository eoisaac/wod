package com.eoisaac.wod.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eoisaac.wod.R
import com.eoisaac.wod.database.models.WorkoutWithExercises


class AllWorkoutsAdapter(private var allWorkouts: List<WorkoutWithExercises>) :
    RecyclerView.Adapter<AllWorkoutsAdapter.WorkoutViewHolder>(), Filterable {

    private var filteredWorkouts: List<WorkoutWithExercises> = allWorkouts

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllWorkoutsAdapter.WorkoutViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.workout_list_item, parent, false)
        return WorkoutViewHolder(view)
    }

    override fun onBindViewHolder(holder: AllWorkoutsAdapter.WorkoutViewHolder, position: Int) {
        val workout = filteredWorkouts[position]
        holder.bind(workout)
    }

    override fun getItemCount(): Int {
        return filteredWorkouts.size
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
        private val nameTextView: TextView = itemView.findViewById(R.id.workout_name)

        fun bind(workout: WorkoutWithExercises) {
            nameTextView.text = workout.workout.name
        }
    }

}