package org.hyperskill.bankmanager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.hyperskill.bankmanager.databinding.UserMenuBinding

class UserMenu : Fragment() {



    private var _binding: UserMenuBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        _binding = UserMenuBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val user = LogInUser()
        binding.userMenuUsernameText.text = user.username
        binding.userMenuDepositFundsButton.setOnClickListener{
            findNavController().navigate(R.id.action_userMenu_to_depositFundsScreen)

        }

        binding.userMenuWithdrawFundsButton.setOnClickListener {
            findNavController().navigate(R.id.action_userMenu_to_withdrawFunds)
        }
        binding.userMenuViewBalanceButton.setOnClickListener {


            findNavController().navigate(R.id.action_userMenu_to_viewBalance)

        }

        binding.userMenuConvertFundsButton.setOnClickListener {
            findNavController().navigate(R.id.convertFunds)
        }

        binding.userMenuPayBillsButton.setOnClickListener {
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