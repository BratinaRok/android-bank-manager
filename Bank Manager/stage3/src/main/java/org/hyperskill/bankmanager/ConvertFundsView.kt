package org.hyperskill.bankmanager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.get
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import org.hyperskill.bankmanager.model.UserViewModel
import java.math.BigDecimal

open class ConvertFundsView : Fragment(), AdapterView.OnItemSelectedListener {
    var spinnerConvertFrom: Spinner? = null
    var spinnerConvertTo: Spinner? = null
    var currenciesArray: Array<String> = arrayOf<String>()
    var fundsToConvertEt: EditText? = null
    var buttonConvertFundsView: Button? = null
    var convertedAmount = 0.0
    var fundsToConvert = 0.0


    val userViewModel by viewModels<UserViewModel>(ownerProducer = { activity as MainActivity })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.convert_funds_view, container, false)
        spinnerConvertFrom = rootView.findViewById(R.id.spinnerConvertFrom)
        spinnerConvertTo = rootView.findViewById(R.id.spinnerConvertTo)
        fundsToConvertEt = rootView.findViewById(R.id.inputFundsToConvert)
        buttonConvertFundsView = rootView.findViewById(R.id.buttonConvertFunds)
        setSpinner()
        spinnerConvertFrom?.onItemSelectedListener = this
        spinnerConvertTo?.onItemSelectedListener = this
        buttonConvertFundsView?.setOnClickListener(View.OnClickListener { convert() })

        spinnerConvertFrom?.setSelection(0)
        spinnerConvertTo?.setSelection(1)


        // Inflate the layout for this fragment
        return rootView
    }


    override fun onItemSelected(parent: AdapterView<*>?, view: View, position: Int, id: Long) {
        if (spinnerConvertFrom?.selectedItemPosition?.equals(spinnerConvertTo?.selectedItemPosition) == true) {

            var selectedCurrency = currenciesArray[spinnerConvertFrom!!.selectedItemPosition]

            when(selectedCurrency) {
                "USD" -> spinnerConvertFrom?.setSelection(1)
                "EUR" -> spinnerConvertFrom?.setSelection(2)
                "GBP" -> spinnerConvertFrom?.setSelection(0)
            }

            Toast.makeText(context, "Can't convert to same currency", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    fun setSpinner() {
        currenciesArray = arrayOf(
            "USD", "EUR", "GBP"
        )
        val sadapter = ArrayAdapter(
            activity!!,
            android.R.layout.simple_spinner_item, currenciesArray
        )


        sadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerConvertFrom!!.adapter = sadapter
        spinnerConvertTo!!.adapter = sadapter
    }

    private fun convert() {


        val convertFrom = currenciesArray[spinnerConvertFrom!!.selectedItemPosition]
        val convertTo = currenciesArray[spinnerConvertTo!!.selectedItemPosition]

        if (fundsToConvertEt!!.text.isEmpty()) {
            Toast.makeText(context, "Enter amount", Toast.LENGTH_SHORT).show()
        } else {

            if (convertFrom == "USD") {
                when (convertTo) {
                    "EUR" -> convertedAmount = fundsToConvertEt!!.text.toString().toDouble() * 1.00
                    "GBP" -> convertedAmount = fundsToConvertEt!!.text.toString().toDouble() * 0.877

                }
            } else if (convertFrom == "GBP") {
                when (convertTo) {
                    "EUR" -> convertedAmount = fundsToConvertEt!!.text.toString().toDouble() * 1.14
                    "USD" -> convertedAmount = fundsToConvertEt!!.text.toString().toDouble() * 1.14
                }
            } else if (convertFrom == "EUR") {
                when (convertTo) {
                    "GBP" -> convertedAmount = fundsToConvertEt!!.text.toString().toDouble() * 0.87
                    "USD" -> convertedAmount = fundsToConvertEt!!.text.toString().toDouble() * 1.00
                }
            }


            fundsToConvert = fundsToConvertEt!!.text.toString().toDouble()
            if (BigDecimal.valueOf(fundsToConvert) > userViewModel.getFundsAsString(convertFrom)
                    .toBigDecimal()
            ) {
                Toast.makeText(
                    context,
                    "There are only : " + userViewModel.getFundsAsString(convertFrom) + "funds, available to convert\" ",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                userViewModel.convertFunds(
                    convertFrom,
                    convertTo,
                    fundsToConvert.toBigDecimal(),
                    convertedAmount.toBigDecimal()
                )
                Toast.makeText(
                    context,
                    "$fundsToConvert $convertFrom funds, converted to $convertedAmount $convertTo successfully",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

    }
}