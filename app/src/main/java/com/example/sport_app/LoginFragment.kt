package com.example.sport_app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.sport_app.data.repositories.UserRepository
import com.example.sport_app.utils.SessionManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_login, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val email = SessionManager.getLoggedInEmail(requireContext())
        if (!email.isNullOrEmpty()) {
            lifecycleScope.launch(Dispatchers.IO) {
                val user = UserRepository.getUserByEmail(email)
                if (user != null) {
                    ApplicationController.currentUser = user
                    withContext(Dispatchers.Main) {
                        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                    }
                }
            }
        }


        val emailInput = view.findViewById<EditText>(R.id.email_input)
        val passwordInput = view.findViewById<EditText>(R.id.password_input)
        val loginButton = view.findViewById<Button>(R.id.login_button)
        val goToRegister = view.findViewById<Button>(R.id.go_to_register)

        loginButton.setOnClickListener {
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()

            if (email.isBlank() || password.isBlank()) {
                Toast.makeText(requireContext(), "Please fill in all fields!", Toast.LENGTH_SHORT).show()
            } else {
                lifecycleScope.launch(Dispatchers.IO) {
                    val user = UserRepository.login(email, password)
                    withContext(Dispatchers.Main) {
                        if (user != null) {
                            ApplicationController.currentUser = user // salveaza userul actual pe sesiune
                            SessionManager.saveLoginSession(requireContext(), user.email)
                            Toast.makeText(requireContext(), "Welcome, ${user.fullname}!", Toast.LENGTH_SHORT).show()
                            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                        } else {
                            Toast.makeText(requireContext(), "Wrong email or password!", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

            }
        }

        goToRegister.setOnClickListener {
            findNavController().navigate(R.id.action_login_to_register)
        }
    }
}
