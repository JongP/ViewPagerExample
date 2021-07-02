package com.example.viewpagerexample;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.viewpagerexample.Room.AppDataBase_wallet;
import com.example.viewpagerexample.Room.User_wallet;


public class WalletActivity extends AppCompatActivity {

    private AppDataBase_wallet db;
    private TextView bal;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
        bal = findViewById(R.id.user_balance);

        db = AppDataBase_wallet.getInstance(this);



        bal.setText("$"+db.userDao().getAll().get(0).getBalance().toString());

    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_wallet, container, false);
    }
}