package org.hyperskill.bankmanager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.math.BigDecimal;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ConvertFunds#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConvertFunds extends Fragment implements AdapterView.OnItemSelectedListener {
    Spinner spinnerConvertFrom;
    Spinner spinnerConvertTo;
    String[] currenciesArray;
    EditText fundsToConvertEt;
    Button buttonConvertFundsView;
    double convertedAmount;
    private BigDecimal balance;
    double fundsToConvert;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ConvertFunds() {
        // Required empty public constructor

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ConvertFunds.
     */
    // TODO: Rename and change types and number of parameters
    public static ConvertFunds newInstance(String param1, String param2) {
        ConvertFunds fragment = new ConvertFunds();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.convert_funds, container, false);
        spinnerConvertFrom = rootView.findViewById(R.id.spinnerConvertFrom);
        spinnerConvertTo = rootView.findViewById(R.id.spinnerConvertTo);
        fundsToConvertEt = rootView.findViewById(R.id.inputFundsToConvert);
        buttonConvertFundsView = rootView.findViewById(R.id.buttonConvertFunds);
        setSpinner();
        spinnerConvertFrom.setOnItemSelectedListener(this);
        spinnerConvertTo.setOnItemSelectedListener(this);

        buttonConvertFundsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convert();
            }
        });
        return rootView;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        if (parent.getId() == R.id.spinnerConvertFrom) {
//            Toast.makeText(getContext(), "Convert from " + currenciesArray[position], Toast.LENGTH_SHORT).show();
//        } else if (parent.getId() == R.id.spinnerConvertTo) {
//            Toast.makeText(getContext(), "Convert to " + currenciesArray[position], Toast.LENGTH_SHORT).show();
//        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    protected void setSpinner() {
        currenciesArray = new String[]{
                "USD", "EUR", "GBP"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, currenciesArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerConvertFrom.setAdapter(adapter);
        spinnerConvertTo.setAdapter(adapter);

    }


    private BigDecimal getCurrentBalance() {
        LogInUser logInUser = new LogInUser();
        UserDataSignUp userDataSignUp = new UserDataSignUp();
        String username = logInUser.getUsername();

        for (int i = 0; i < userDataSignUp.getUserDataArray().size(); i++) {
            if (userDataSignUp.getUserDataArray().get(i).get("userName").equals(username)) {

                String balanceInAccount = userDataSignUp.getUserDataArray().get(i).get("balance").toString();
                balance = new BigDecimal(balanceInAccount);
            }
        }
        return balance;
    }


    private void convert() {
        String convertFrom = currenciesArray[spinnerConvertFrom.getSelectedItemPosition()];
        String convertTo = currenciesArray[spinnerConvertTo.getSelectedItemPosition()];

        if (convertFrom.equals("USD")) {
            switch (convertTo) {
                case "EUR":
                    convertedAmount = Double.parseDouble(fundsToConvertEt.getText().toString()) * 1.00d;
                    break;
                case "GBP":
                    convertedAmount = Double.parseDouble(fundsToConvertEt.getText().toString()) * 0.877d;
                    break;
            }
        } else if (convertFrom.equals("GBP")) {
            switch (convertTo) {
                case "EUR":
                    convertedAmount = Double.parseDouble(fundsToConvertEt.getText().toString()) * 1.14d;
                    break;
                case "USD":
                    convertedAmount = Double.parseDouble(fundsToConvertEt.getText().toString()) * 1.14d;

                    break;
            }
        } else if (convertFrom.equals("EUR")) {
            switch (convertTo) {
                case "GBP":
                    convertedAmount = Double.parseDouble(fundsToConvertEt.getText().toString()) * 0.87d;
                    break;
                case "USD":
                    convertedAmount = Double.parseDouble(fundsToConvertEt.getText().toString()) * 1.00d;
                    break;
            }
        }


        if (fundsToConvertEt.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), "Enter amount", Toast.LENGTH_SHORT).show();
        } else {
            fundsToConvert = Double.parseDouble(fundsToConvertEt.getText().toString());
            if (BigDecimal.valueOf(fundsToConvert).compareTo(getCurrentBalance()) > 0) {
                Toast.makeText(getContext(), "There are only : " + getCurrentBalance() + " funds, available to convert\" ", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getContext(), fundsToConvert + " " + convertFrom + " funds, converted to " + convertedAmount + " " + convertTo + " successfully", Toast.LENGTH_LONG).show();

            }


        }
    }
}