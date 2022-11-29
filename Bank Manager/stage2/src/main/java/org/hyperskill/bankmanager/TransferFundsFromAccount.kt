package org.hyperskill.bankmanager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import org.hyperskill.bankmanager.model.UserViewModel

class TransferFundsFromAccount : Fragment() {

    val userViewModel by viewModels<UserViewModel>(ownerProducer = { activity as MainActivity })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,

        ): View? {
        // Inflate the layout for this fragment
        val myView: View = inflater.inflate(R.layout.transfer_funds_from_account, container, false)

        return inflater.inflate(R.layout.transfer_funds_from_account, container, false)
    }

}
