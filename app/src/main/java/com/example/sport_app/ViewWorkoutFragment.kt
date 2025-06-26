package com.example.sport_app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sport_app.databinding.FragmentViewWorkoutBinding
import com.example.sport_app.data.repositories.WorkoutRepository
import kotlinx.coroutines.launch

class ViewWorkoutFragment : Fragment() {

    private var _binding: FragmentViewWorkoutBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: WorkoutAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentViewWorkoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonBackHome.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        adapter = WorkoutAdapter()
        binding.recyclerViewWorkouts.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewWorkouts.adapter = adapter

        val currentUser = ApplicationController.currentUser
        if (currentUser != null) {
            lifecycleScope.launch {
                val workouts = WorkoutRepository.getWorkoutsByUserId(currentUser.id)
                adapter.submitList(workouts)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
