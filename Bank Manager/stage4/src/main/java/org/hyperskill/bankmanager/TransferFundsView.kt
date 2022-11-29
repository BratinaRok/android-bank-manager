package org.hyperskill.bankmanager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import org.hyperskill.bankmanager.databinding.TransferFundsViewBinding
import org.hyperskill.bankmanager.databinding.UserMenuBinding
import org.hyperskill.bankmanager.model.UserViewModel

class TransferFundsView : Fragment() {

    private var _binding: TransferFundsViewBinding? = null
    val userViewModel by viewModels<UserViewModel>(ownerProducer = { activity as MainActivity })

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,

        ): View? {

        _binding = TransferFundsViewBinding.inflate(inflater, container, false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.transferFundsViewBackButton.setOnClickListener {
            findNavController().navigate(R.id.action_transferFundsView_to_mainMenu)
        }

        binding.transferFundsViewTransferToAccountButton.setOnClickListener {
            findNavController().navigate(R.id.action_transferFundsView_to_transferfundstoaccount)

        }
        binding.transferFundsViewTransferFromAccountButton.setOnClickListener {
            findNavController().navigate(R.id.action_transferFundsView_to_transferfundsfromaccount)

        }
    }
}
