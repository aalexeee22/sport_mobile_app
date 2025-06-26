package com.example.sport_app

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sport_app.data.models.WorkoutEntityModel
import com.example.sport_app.databinding.ItemWorkoutBinding

class WorkoutAdapter : RecyclerView.Adapter<WorkoutAdapter.WorkoutViewHolder>() {

    private var workoutList: List<WorkoutEntityModel> = emptyList()

    fun submitList(list: List<WorkoutEntityModel>) {
        workoutList = list
        notifyDataSetChanged()
    }

    inner class WorkoutViewHolder(private val binding: ItemWorkoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(workout: WorkoutEntityModel) {
            binding.textViewTitle.text = workout.title
            binding.textViewDescription.text = workout.description
            binding.textViewRepetitions.text = "Repetitions: ${workout.repetitions}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutViewHolder {
        val binding = ItemWorkoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WorkoutViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WorkoutViewHolder, position: Int) {
        holder.bind(workoutList[position])
    }

    override fun getItemCount(): Int = workoutList.size
}
