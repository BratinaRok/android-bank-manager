package org.hyperskill.bankmanager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import org.hyperskill.bankmanager.R
import org.hyperskill.bankmanager.databinding.UserMenuBinding
import org.hyperskill.bankmanager.model.UserViewModel

class UserMenu : Fragment() {



    private var _binding: UserMenuBinding? = null
    private val userViewModel by viewModels<UserViewModel>(ownerProducer = { activity as MainActivity })

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
        val user = userViewModel.getLoggedUser()
        binding.userMenuUsernameText.text = user.userName

        binding.userMenuTransferFundsButton.setOnClickListener{
            findNavController().navigate(R.id.action_mainMenu_to_transferFundsView)

        }
        binding.userMenuViewBalanceButton.setOnClickListener {
            findNavController().navigate(R.id.action_userMenu_to_viewBalance)

        }

        binding.userMenuConvertFundsButton.setOnClickListener {
            findNavController().navigate(R.id.convertFundsView)
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