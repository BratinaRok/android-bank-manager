package org.hyperskill.bankmanager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.hyperskill.bankmanager.databinding.MainmenuBinding

class UserMenu : Fragment() {



    private var _binding: MainmenuBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        _binding = MainmenuBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val user = LogInUser()
            binding.textVi.setText(user.username)
        binding.depositFundsButton.setOnClickListener{
            findNavController().navigate(R.id.action_userMenu_to_depositFundsScreen)

        }

        binding.withdrawButton.setOnClickListener {
            findNavController().navigate(R.id.action_userMenu_to_withdrawFunds)
        }
        binding.viewBalanceButton.setOnClickListener {


            findNavController().navigate(R.id.action_userMenu_to_viewBalance)

        }

        binding.convertFundsButton.setOnClickListener {
            findNavController().navigate(R.id.convertFunds)
        }

        binding.payBilsButton.setOnClickListener {
            findNavController().navigate(R.id.billPayment)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}