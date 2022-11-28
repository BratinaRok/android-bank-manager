package org.hyperskill.bankmanager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.log_in, container, false)
        _binding = LogInBinding.inflate(inflater, container, false)
        return binding.root;

    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        binding.confirmCodeButton.setOnClickListener {
//            if ((activity as MainActivity).securityCheck(view)) {
//                findNavController().navigate(R.id.action_SecondFragment_to_userMenu)
//            }
//
//
//        }
//    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
