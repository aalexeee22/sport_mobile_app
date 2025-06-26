package com.example.sport_app

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.sport_app.data.models.WorkoutEntityModel
import com.example.sport_app.data.repositories.WorkoutRepository
import com.example.sport_app.databinding.FragmentAddWorkoutBinding
import java.util.Calendar

class AddWorkoutFragment : Fragment() {

    private var _binding: FragmentAddWorkoutBinding? = null
    private val binding get() = _binding!!

    private var selectedWorkoutDate: String? = null  // 🗓️ Added variable to store the selected date

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddWorkoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 📅 Date Picker logic
        binding.buttonSelectDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val datePicker = DatePickerDialog(
                requireContext(),
                { _, year, month, dayOfMonth ->
                    val selectedDate = "$year-${month + 1}-$dayOfMonth"
                    binding.textViewSelectedDate.text = selectedDate
                    selectedWorkoutDate = selectedDate
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            datePicker.show()
        }

        binding.buttonSubmitWorkout.setOnClickListener {
            val title = binding.editTextTitle.text.toString()
            val description = binding.editTextDescription.text.toString()
            val repetitions = binding.editTextRepetitions.text.toString().toIntOrNull()

            val currentUser = ApplicationController.currentUser

            if (title.isNotEmpty() && description.isNotEmpty() && repetitions != null && currentUser != null && selectedWorkoutDate != null) {

                val workout = WorkoutEntityModel(
                    title = title,
                    description = description,
                    repetitions = repetitions,
                    userId = currentUser.id,
                    date = selectedWorkoutDate ?: "No date selected"
                )

                WorkoutRepository.insertWorkout(workout)

                Toast.makeText(requireContext(), "Workout saved: $title", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.homeFragment)

            } else {
                Toast.makeText(requireContext(), "Please fill all fields and select a date", Toast.LENGTH_SHORT).show()
            }
        }

        binding.buttonCancelWorkout.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
