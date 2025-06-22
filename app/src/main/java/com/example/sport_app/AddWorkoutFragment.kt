package com.example.sport_app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
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
            val workout = binding.editTextWorkout.text.toString()

            if (title.isNotEmpty() && workout.isNotEmpty()) {

                // ✅ TODO: Aici salvează datele local (Room sau SharedPreferences)
                // Sau apelează API-ul pentru salvare pe backend (cerinta cu requests)

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
