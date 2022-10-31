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
import org.hyperskill.bankmanager.R
import org.hyperskill.bankmanager.model.UserViewModel

class WithdrawFunds: Fragment(),OnClickListener {

    val userViewModel by viewModels<UserViewModel>(ownerProducer = { activity as MainActivity })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,

    ): View? {
        // Inflate the layout for this fragment
        val myView: View = inflater.inflate(R.layout.withdraw_funds, container, false)
        val myButton =  myView.findViewById<Button>(R.id.withdrawButton) as Button
        myButton.setOnClickListener {
            onClick(myView)
        }
        myButton.setOnClickListener(this)
        return inflater.inflate(R.layout.withdraw_funds, container, false)
    }

    override fun onClick(v: View?) {
            val text = v?.findViewById<EditText>(R.id.enterAmountWithdraw)
            val toWithdraw = text?.text.toString().toBigDecimal()
            userViewModel.withdrawFunds(toWithdraw)
            Toast.makeText(context, "Funds Withdrawn", Toast.LENGTH_SHORT).show()
        if (v != null) {
            Navigation.findNavController(v).navigate(R.id.action_withdrawFunds_to_mainMenu)
        }
        }
    }
