package com.example.sport_app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.sport_app.databinding.FragmentHomeBinding
import com.example.sport_app.utils.SessionManager

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Afisam numele utilizatorului curent, daca exista
        val currentUser = ApplicationController.currentUser
        if (currentUser != null) {
            binding.textUsername.text = "Bine ai revenit, ${currentUser.fullname}"
        }

        // Buton Logout
        binding.buttonLogout.setOnClickListener {
            SessionManager.clearSession(requireContext())
            ApplicationController.currentUser = null
            findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
        }

        binding.buttonAddWorkout.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_addWorkoutFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
