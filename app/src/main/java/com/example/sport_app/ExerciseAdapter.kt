package com.example.sport_app

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.sport_app.data.models.Exercise
import com.example.sport_app.databinding.ItemExerciseBinding

class ExerciseAdapter : RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder>() {

    private var exerciseList = listOf<Exercise>()

    fun submitList(list: List<Exercise>) {
        exerciseList = list
        notifyDataSetChanged()
    }

    class ExerciseViewHolder(val binding: ItemExerciseBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val binding = ItemExerciseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExerciseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val exercise = exerciseList[position]
        val englishTranslation = exercise.translations.find { it.language == 2 }

        val name = englishTranslation?.name ?: "No name available."
        val description = if (englishTranslation?.description.isNullOrEmpty()) {
            "No description available."
        } else {
            englishTranslation?.description
        }

        holder.binding.textExerciseName.text = name
        holder.binding.textExerciseDescription.text = HtmlCompat.fromHtml(description ?: "", HtmlCompat.FROM_HTML_MODE_LEGACY)
    }

    override fun getItemCount(): Int = exerciseList.size
}
