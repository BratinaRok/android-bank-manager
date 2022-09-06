package org.hyperskill.bankmanager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

import androidx.appcompat.app.AppCompatActivity

import com.google.android.material.textfield.TextInputLayout
import org.hyperskill.bankmanager.databinding.SignUpBinding


class SignUp : Fragment() {

    /**
     * A simple [Fragment] subclass as the default destination in the navigation.
     */

        private var _binding: SignUpBinding? = null

        // This property is only valid between onCreateView and
        // onDestroyView.
        private val binding get() = _binding!!

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?

        ): View? {

            _binding = SignUpBinding.inflate(inflater, container, false)
            return binding.root

        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)


        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }







}



