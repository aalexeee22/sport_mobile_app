package com.example.sport_app

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

class AddWorkoutFragment : Fragment() {

    private var _binding: FragmentAddWorkoutBinding? = null
    private val binding get() = _binding!!

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

        binding.buttonSubmitWorkout.setOnClickListener {
            val title = binding.editTextTitle.text.toString()

            //an modificat numele campului din workout in description
            val description  = binding.editTextDescription.text.toString()

            // am adaugat si repetitions ca sa aibe mai mult sens
            val repetitions = binding.editTextRepetitions.text.toString().toIntOrNull()

            val currentUser = ApplicationController.currentUser //utilizatorul logat

            if (title.isNotEmpty() && description.isNotEmpty() && repetitions != null && currentUser != null) {

                val workout = WorkoutEntityModel(
                    title = title,
                    description = description,
                    repetitions = repetitions,
                    userId = currentUser.id
                )

                WorkoutRepository.insertWorkout(workout)

                Toast.makeText(requireContext(), "Workout saved: $title", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.homeFragment)

            } else {
                Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }

        //cancel workout
        binding.buttonCancelWorkout.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
