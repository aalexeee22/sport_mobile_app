package com.example.sport_app

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
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

            // Bold "Repetitions:" label
            val repetitionsLabel = SpannableString("Repetitions: ")
            repetitionsLabel.setSpan(StyleSpan(Typeface.BOLD), 0, repetitionsLabel.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            binding.textViewRepetitions.text = repetitionsLabel
            binding.textViewRepetitions.append("${workout.repetitions}")

            // Bold "Date:" label
            val dateLabel = SpannableString("Date: ")
            dateLabel.setSpan(StyleSpan(Typeface.BOLD), 0, dateLabel.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            binding.textViewDate.text = dateLabel
            binding.textViewDate.append(workout.date)
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
