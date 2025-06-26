package com.example.sport_app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.sport_app.data.models.UserEntityModel
import com.example.sport_app.data.repositories.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_register, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nameInput = view.findViewById<EditText>(R.id.name_input)
        val emailInput = view.findViewById<EditText>(R.id.email_input)
        val passwordInput = view.findViewById<EditText>(R.id.password_input)
        val confirmPasswordInput = view.findViewById<EditText>(R.id.confirm_password_input)
        val registerButton = view.findViewById<Button>(R.id.register_button)
        val goToLogin = view.findViewById<Button>(R.id.go_to_login)

        registerButton.setOnClickListener {
            val name = nameInput.text.toString()
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()
            val confirmPassword = confirmPasswordInput.text.toString()

            when {
                name.isBlank() || email.isBlank() || password.isBlank() || confirmPassword.isBlank() ->
                    Toast.makeText(
                        requireContext(),
                        "Please fill in all fields!",
                        Toast.LENGTH_SHORT
                    ).show()

                password != confirmPassword ->
                    Toast.makeText(requireContext(), "Passwords do not match!", Toast.LENGTH_SHORT)
                        .show()

                else -> {
                    // Logica de verificare email și salvare user
                    CoroutineScope(Dispatchers.IO).launch {
                        val existingUser = UserRepository.getUserByEmail(email)

                        if (existingUser != null) {
                            launch(Dispatchers.Main) {
                                Toast.makeText(
                                    requireContext(),
                                    "Email already used.",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        } else {
                            UserRepository.insert(
                                UserEntityModel(
                                    fullname = name,
                                    email = email,
                                    password = password
                                )
                            )

                            launch(Dispatchers.Main) {
                                Toast.makeText(
                                    requireContext(),
                                    "Account created for $name",
                                    Toast.LENGTH_SHORT
                                ).show()
                                findNavController().navigate(R.id.action_register_to_login)
                            }
                        }
                    }
                }
            }
        }

        goToLogin.setOnClickListener {
            findNavController().navigate(R.id.action_register_to_login)
        }
    }
}
