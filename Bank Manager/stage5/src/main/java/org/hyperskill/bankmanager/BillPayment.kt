package org.hyperskill.bankmanager

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import org.hyperskill.bankmanager.model.UserViewModel
import java.io.File
import java.io.IOException
import java.lang.StringBuilder
import kotlin.collections.ArrayList

class BillPayment : Fragment() {
    private var paymentDescTextView: TextView? = null
    private var accNumberTextView: TextView? = null
    private var billPriceTextView: TextView? = null

    val userViewModel by viewModels<UserViewModel>(ownerProducer = { activity as MainActivity })
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val path = File("sdcard/Download/New")
        if (!path.exists()) {
            path.mkdirs()
        }
        return inflater.inflate(R.layout.bill_payment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        paymentDescTextView = view.findViewById<View>(R.id.paymentForField) as TextView
        accNumberTextView = view.findViewById<View>(R.id.accNumberInputField) as TextView
        billPriceTextView = view.findViewById<View>(R.id.priceInputField) as TextView
        val user = userViewModel.getLoggedUser()
        val readFile = getView()!!.findViewById<View>(R.id.readFileButton) as Button
        val spinnerBills: Spinner =
            getView()?.findViewById<View>(R.id.billPaymentSelectBillSpinner) as Spinner
        val path = File("sdcard/Download/New")
        val filesPath: String =
            ".stage5\\src\\test\\java\\org\\hyperskill\\bankmanager\\testfiles\\"
        verifyStoragePermissions(activity)
        if (!path.exists()) {
            path.mkdirs()
            //     copyFilesToSDFolder(filesPath,"rentalbill.txt")
        } else {
            //   copyFilesToSDFolder(filesPath,"rentalbill.txt")
            val adapter2: ArrayAdapter<String> = ArrayAdapter<String>(
                context!!,
                android.R.layout.simple_spinner_item,
                readFiles(path = "sdcard/Download/New")!!

            )
            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerBills.adapter = adapter2
        }

        readFile.setOnClickListener {
            val path = File("sdcard/Download/New")

            @SuppressLint("SdCardPath") val file = File(path, "")
            val text = StringBuilder()
            verifyStoragePermissions(activity)

            try {
                if (!path.exists()) {
                    path.mkdirs()
                }

                billArray = ArrayList<String>()
                readFileLine("sdcard/Download/New/${spinnerBills.selectedItem}")


//
//                val arrayFiles : ArrayList<File> = ArrayList()
//
//                val br = BufferedReader(FileReader(file))
//                val line = br.readLine()
//                while(line != null) {
//                //    arrayFiles.add((line)
//
//                }

                // var line: String = ""
                //while (br.readLine().also { line = it } != null) {
//                while (br.readLines()) {
//                    text.append(line)
//                    billArray.add(line)
//                    text.append('\n')
//                    br.readLine()
//                }
                //   br.close()

            } catch (e: IOException) {
                Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
                println(e.message)
            }

            if (billArray.size > 0) {
                paymentDescTextView =
                    getView()!!.findViewById<View>(R.id.paymentForField) as TextView
                paymentDescTextView!!.text = billArray[0]
                accNumberTextView =
                    getView()!!.findViewById<View>(R.id.accNumberInputField) as TextView
                accNumberTextView!!.text = billArray[1]
                billPriceTextView = getView()!!.findViewById<View>(R.id.priceInputField) as TextView
                billPriceTextView!!.text = billArray[2]
            }
        }

        var payButton = view.findViewById<Button>(R.id.payButton)
        payButton.setOnClickListener {
            if (billArray.size == 0) {
                Toast.makeText(context, "First read bill info", Toast.LENGTH_SHORT).show()
            } else {
                var userBalance = userViewModel.getFundsAsString().toDouble()
                var billPrice = billArray[2].toDouble()
                var billInfo = billArray[0]
                if (userBalance > billPrice) {
                    userViewModel.withdrawFunds(billPrice.toBigDecimal())
                    Toast.makeText(
                        context,
                        "$billInfo was successfully paid",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(context, "Not enough balance in account", Toast.LENGTH_SHORT)
                        .show()
                }
            }
            billArray = ArrayList<String>()
            paymentDescTextView!!.text = ""
            accNumberTextView!!.text = ""
            billPriceTextView!!.text = ""
        }
    }

    companion object {
        var billArray = ArrayList<String>()

        fun newInstance(param1: String?, param2: String?): BillPayment {
            val fragment = BillPayment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }

        private const val REQUEST_EXTERNAL_STORAGE = 1
        private val PERMISSIONS_STORAGE = arrayOf(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )

        fun verifyStoragePermissions(activity: Activity?) {
            // Check if we have write permission
            val permission = ActivityCompat.checkSelfPermission(
                activity!!,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            val readPermission = ActivityCompat.checkSelfPermission(
                activity!!,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
            if (permission != PackageManager.PERMISSION_GRANTED || readPermission != PackageManager.PERMISSION_GRANTED) {
                // We dont have permission so prompt the user
                ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
                )
            }
        }
    }

    fun readFileLine(fileName: String) = File(fileName).forEachLine { Companion.billArray.add(it) }

    fun readFiles(path: String): Array<String>? {
        val directory: Array<out File>? = File(path).listFiles()
        val files: Array<String>? = directory?.size?.let { Array(it) { directory.get(it).name } }

        return files
    }

    fun copyFilesToSDFolder(copyFromPath: String, file: String) {
        val fileToCopy: File = File(copyFromPath + file)
        val toPath = Environment.getExternalStorageState()
        val to: File = File(toPath + file)
        fileToCopy.copyTo(to, true)
    }
}