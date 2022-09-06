package org.hyperskill.bankmanager;


import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.math.BigDecimal;


public class ViewBalance extends Fragment {

      TextView textView;
     View view;
    static BigDecimal balance;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ViewBalance() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ViewBalance.
     */
    // TODO: Rename and change types and number of parameters
    public static ViewBalance newInstance(String param1, String param2) {
        ViewBalance fragment = new ViewBalance();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.view_balance, container, false);
        textView = (TextView) rootView.findViewById(R.id.showBalanceText);
        checkBalance();
        return rootView;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



    }



    public void checkBalance() {

        LogInUser logInUser = new LogInUser();
        UserDataSignUp userDataSignUp = new UserDataSignUp();
        String username = logInUser.getUsername();


        for (int i = 0; i < userDataSignUp.getUserDataArray().size(); i++) {
            if (userDataSignUp.getUserDataArray().get(i).get("userName").equals(username)) {

                String balanceInAccount = userDataSignUp.getUserDataArray().get(i).get("balance").toString();
                balance = new BigDecimal(balanceInAccount);

                try {
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                         textView.setText(balance.toString());
                        }

                    }, 1);

                    String t = textView.getText().toString();
                    System.out.println(t);
                } catch (NullPointerException e) {
                    System.out.println(e.getMessage());
                }


            }
        }


    }


}