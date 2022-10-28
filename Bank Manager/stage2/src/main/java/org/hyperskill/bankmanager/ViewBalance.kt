package org.hyperskill.bankmanager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation.findNavController
import org.hyperskill.bankmanager.model.UserViewModel

class ViewBalance : Fragment() {
    lateinit var textView: TextView
    val userViewModel by viewModels<UserViewModel>(ownerProducer = { activity as MainActivity })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val rootView = inflater.inflate(R.layout.view_balance, container, false)
        textView = rootView.findViewById(R.id.showBalanceText)
        textView.text = userViewModel.getFundsAsString()

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<View>(R.id.backButtonShowBalanceView).setOnClickListener {
            findNavController(
                view
            ).navigate(R.id.action_viewBalance_to_mainMenu)
        }
    }

}

