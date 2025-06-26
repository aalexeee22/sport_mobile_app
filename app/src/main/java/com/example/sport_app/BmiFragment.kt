package com.example.sport_app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.sport_app.databinding.FragmentBmiBinding
import com.example.sport_app.network.RetrofitInstance
import com.example.sport_app.data.models.MathRequest
import com.example.sport_app.data.models.MathResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BmiFragment : Fragment() {

    private var _binding: FragmentBmiBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentBmiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Navigare inapoi la Home
        binding.buttonBackHome.setOnClickListener {
            findNavController().navigate(R.id.action_bmiFragment_to_homeFragment)
        }

        // Calcul BMI
        binding.buttonCalculate.setOnClickListener {
            val weight = binding.editTextWeight.text.toString().toDoubleOrNull()
            val height = binding.editTextHeight.text.toString().toDoubleOrNull()

            if (weight == null || height == null || height == 0.0) {
                Toast.makeText(requireContext(), "Please enter valid values", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val formula = "$weight / (($height/100)^2)"
            val request = MathRequest(expr = formula)

            RetrofitInstance.mathApi.calculateBmi(request).enqueue(object : Callback<MathResponse> {
                override fun onResponse(call: Call<MathResponse>, response: Response<MathResponse>) {
                    if (response.isSuccessful && response.body() != null) {
                        binding.textResult.text = "Your BMI: ${response.body()!!.result}"
                    } else {
                        binding.textResult.text = "Error calculating BMI"
                    }
                }

                override fun onFailure(call: Call<MathResponse>, t: Throwable) {
                    binding.textResult.text = "Request failed: ${t.message}"
                }
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
