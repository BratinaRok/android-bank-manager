package org.hyperskill.bankmanager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import org.hyperskill.bankmanager.databinding.LogInBinding


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class LogIn : Fragment() {




    private var _binding: LogInBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        _binding = LogInBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.menuButton.setOnClickListener {
            findNavController().navigate(R.id.userMenu)

        }





    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }




}