package com.example.sport_app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sport_app.ExerciseAdapter
import com.example.sport_app.databinding.FragmentExerciseBinding
import com.example.sport_app.network.RetrofitInstance
import com.example.sport_app.data.models.ExerciseResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ExerciseFragment : Fragment() {

    private var _binding: FragmentExerciseBinding? = null
    private val binding get() = _binding!!

    private lateinit var exerciseAdapter: ExerciseAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentExerciseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        exerciseAdapter = ExerciseAdapter()
        binding.recyclerViewExercises.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewExercises.adapter = exerciseAdapter

        loadExercises()

        // Navigare inapoi la Home
        binding.buttonBackHome.setOnClickListener {
            findNavController().navigate(R.id.action_exerciseFragment_to_homeFragment)
        }
    }

    private fun loadExercises() {
        RetrofitInstance.exerciseApi.getExercises().enqueue(object : Callback<ExerciseResponse> {
            override fun onResponse(
                call: Call<ExerciseResponse>,
                response: Response<ExerciseResponse>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    exerciseAdapter.submitList(response.body()!!.results)
                }
            }

            override fun onFailure(call: Call<ExerciseResponse>, t: Throwable) {
                Toast.makeText(requireContext(), "Error while fetching exercises", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
