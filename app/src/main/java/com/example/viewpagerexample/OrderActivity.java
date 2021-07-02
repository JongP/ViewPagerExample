package com.example.viewpagerexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.viewpagerexample.Room.AppDataBase_wallet;
import com.example.viewpagerexample.Room.User_wallet;

public class OrderActivity extends AppCompatActivity {

    private TextView tv_symbol,tv_name, tv_price, tv_quantity,tv_total,tv_bal,tv_coinown;
    private Button btn_minus,btn_plus, btn_sell, btn_buy;
    private int quantity;
    Double totalPrice;
    Double balance;
    private AppDataBase_wallet db;
    private int count=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("mylifecycle", "onCreate is openend in orderActivity");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        tv_symbol = findViewById(R.id.tv_coinsymbol);
        tv_name = findViewById(R.id.tv_coinname);
        tv_price = findViewById(R.id.tv_coinrate);
        tv_quantity = findViewById(R.id.tv_qunatity);
        tv_total = findViewById(R.id.tv_coin_totalprice);
        tv_bal =findViewById(R.id.tv_coinbalance);
        tv_coinown = findViewById(R.id.tv_coinown);
        btn_plus = findViewById(R.id.coin_btn_plus);
        btn_minus = findViewById(R.id.coin_btn_minus);
        btn_buy = findViewById(R.id.btn_buy);
        btn_sell = findViewById(R.id.btn_sell);
        quantity = 0;

        db = AppDataBase_wallet.getInstance(getApplicationContext());
        User_wallet my_wallet = db.userDao().getAll().get(0);



        Intent intent = getIntent();
        String symbol = intent.getStringExtra("symbol");
        String name = intent.getStringExtra("name");
        Double price = intent.getDoubleExtra("price",0);

        tv_symbol.setText(symbol);
        tv_name.setText(name);
        tv_price.setText("$ "+String.valueOf(price));
        balance=my_wallet.getBalance();
        tv_bal.setText("$ "+balance.toString());
        count=my_wallet.getCoinCount(symbol);
        tv_coinown.setText("quantity: "+String.valueOf(count));



        btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quantity <10){
                    quantity++;
                    tv_quantity.setText(String.valueOf(quantity));
                    totalPrice = Math.round(quantity*price*100)/100.0;
                    tv_total.setText("$ "+String.valueOf(totalPrice));
                }
                else{
                    Toast.makeText(getApplicationContext(),"풀매수/풀매도는 정신 건강에 해롭다.",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quantity >0){
                    quantity--;
                    tv_quantity.setText(String.valueOf(quantity));
                    totalPrice = Math.round(quantity*price*100)/100.0;
                    tv_total.setText("$ "+String.valueOf(totalPrice));
                }else{
                    Toast.makeText(getApplicationContext(),"no less than zero",Toast.LENGTH_SHORT).show();
                }

            }
        });

        btn_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quantity==0) return;

                if(totalPrice>balance){
                    Toast.makeText(getApplicationContext(),"Not enough cash, go back to work.",Toast.LENGTH_SHORT).show();
                }else{
                    balance = Math.round((balance-totalPrice)*100)/100.0;
                    my_wallet.setBalance(balance);
                    db.userDao().updatebalance(balance,1);

                    count+=quantity;
                    my_wallet.setCoinCount(symbol,count);
                    updateDatabase(symbol,count);

                    Toast.makeText(getApplicationContext(),"We're heading to Mars.",Toast.LENGTH_SHORT).show();
                    tv_bal.setText("$ "+String.valueOf(balance));
                    tv_coinown.setText("quantity: "+String.valueOf(count));
                }
            }
        });

        btn_sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quantity==0) return;
                if(quantity>count){
                    Toast.makeText(getApplicationContext(),"Not enough coins, buy some.",Toast.LENGTH_SHORT).show();
                }
                else{
                    balance = Math.round((balance+totalPrice)*100)/100.0;
                    my_wallet.setBalance(balance);
                    db.userDao().updatebalance(balance,1);

                    count = count-quantity;
                    my_wallet.setCoinCount(symbol,count);
                    updateDatabase(symbol,count);
                    Toast.makeText(getApplicationContext(),"Bye",Toast.LENGTH_SHORT).show();
                    tv_bal.setText("$ "+String.valueOf(balance));
                    tv_coinown.setText("quantity: "+String.valueOf(count));
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void getDatabase(String sym, int count){
        switch (sym){
            case "BTC":
                db.userDao().updateBTC(count,1);
                break;
            case "ETH":
                db.userDao().updateETH(count,1);
                break;
            case "USDT":
                db.userDao().updateUSDT(count,1);
                break;
            case "BNB":
                db.userDao().updateBNB(count,1);
                break;
            case "ADA":
                db.userDao().updateADA(count,1);
                break;
            case "DOGE":
                db.userDao().updateDOGE(count,1);
                break;
            case "XRP":
                db.userDao().updateXRP(count,1);
                break;
            case "USDC":
                db.userDao().updateUSDC(count,1);
                break;
            case "DOT":
                db.userDao().updateDOT(count,1);
                break;
            case "BUSD":
                db.userDao().updateBUSD(count,1);break;
            default:
                Log.d("updateDatabase", "get default");
                return ;
        }
    }

    private void updateDatabase(String sym, int count){
        switch (sym){
            case "BTC":
                db.userDao().updateBTC(count,1);
                break;
            case "ETH":
                db.userDao().updateETH(count,1);
                break;
            case "USDT":
                db.userDao().updateUSDT(count,1);
                break;
            case "BNB":
                db.userDao().updateBNB(count,1);
                break;
            case "ADA":
                db.userDao().updateADA(count,1);
                break;
            case "DOGE":
                db.userDao().updateDOGE(count,1);
                break;
            case "XRP":
                db.userDao().updateXRP(count,1);
                break;
            case "USDC":
                db.userDao().updateUSDC(count,1);
                break;
            case "DOT":
                db.userDao().updateDOT(count,1);
                break;
            case "BUSD":
                db.userDao().updateBUSD(count,1);
                break;
            default:
                Log.d("updateDatabase", "get default");
                return ;
        }
        return;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_order, container, false);
    }
}