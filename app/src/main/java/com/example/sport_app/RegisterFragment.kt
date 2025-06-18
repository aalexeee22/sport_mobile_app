package com.example.sport_app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class RegisterFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_register, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
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
                    Toast.makeText(requireContext(), "Completează toate câmpurile", Toast.LENGTH_SHORT).show()

                password != confirmPassword ->
                    Toast.makeText(requireContext(), "Parolele nu coincid", Toast.LENGTH_SHORT).show()

                else -> {
                    // TODO: Salvează datele sau trimite-le la server
                    Toast.makeText(requireContext(), "Cont creat pentru $name", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_register_to_login)
                }
            }
        }

        goToLogin.setOnClickListener {
            findNavController().navigate(R.id.action_register_to_login)
        }
    }
}
