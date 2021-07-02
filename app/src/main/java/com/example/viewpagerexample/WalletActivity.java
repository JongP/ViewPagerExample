package com.example.viewpagerexample;

import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.viewpagerexample.Room.AppDataBase_wallet;
import com.example.viewpagerexample.Room.User_wallet;
import com.example.viewpagerexample.adapters.CoinAdapter;
import com.example.viewpagerexample.adapters.ImageAdapter;

import java.util.ArrayList;


public class WalletActivity extends AppCompatActivity {

    private AppDataBase_wallet db;
    private TextView bal;
    private RecyclerView recyclerView;

    ArrayList<CoinItem> List = new ArrayList<>();
    CoinAdapter adapter;  // 리사이클러뷰에 적용시킬 어댑터



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
        bal = findViewById(R.id.user_balance);

        db = AppDataBase_wallet.getInstance(this);
        recyclerView = findViewById(R.id.recyclerView_wallet);

        for(int i=0;i<db.userDao().getDataCount();i++){
            User_wallet user = db.userDao().getAll().get(i);

        }




        adapter = new CoinAdapter(WalletActivity.this,List);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(WalletActivity.this, RecyclerView.HORIZONTAL, false));

    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_wallet, container, false);
    }
}