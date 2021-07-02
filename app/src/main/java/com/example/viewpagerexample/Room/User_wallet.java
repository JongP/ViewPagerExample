package com.example.viewpagerexample.Room;

import android.util.Log;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Wallet")
public class User_wallet {
    @PrimaryKey(autoGenerate = true) //autoGenerate는 알아서 id를 1씩 증가시켜준다. autoincrement와 똑같
    private int id;

    @ColumnInfo(name = "balance") //컬럼명 변수명과 다르게 사용 가능
    private Double balance;

    @ColumnInfo(name = "BTC") //컬럼명 변수명과 다르게 사용 가능
    private int BTC;
    @ColumnInfo(name = "ETH") //컬럼명 변수명과 다르게 사용 가능
    private int ETH;
    @ColumnInfo(name = "USDT") //컬럼명 변수명과 다르게 사용 가능
    private int USDT;
    @ColumnInfo(name = "BNB") //컬럼명 변수명과 다르게 사용 가능
    private int BNB;
    @ColumnInfo(name = "ADA") //컬럼명 변수명과 다르게 사용 가능
    private int ADA;
    @ColumnInfo(name = "DOGE") //컬럼명 변수명과 다르게 사용 가능
    private int DOGE;
    @ColumnInfo(name = "XRP") //컬럼명 변수명과 다르게 사용 가능
    private int XRP;
    @ColumnInfo(name = "USDC") //컬럼명 변수명과 다르게 사용 가능
    private int USDC;
    @ColumnInfo(name = "DOT") //컬럼명 변수명과 다르게 사용 가능
    private int DOT;
    @ColumnInfo(name = "BUSD") //컬럼명 변수명과 다르게 사용 가능
    private int BUSD;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public int getBTC() {
        return BTC;
    }

    public void setBTC(int BTC) {
        this.BTC = BTC;
    }

    public int getETH() {
        return ETH;
    }

    public void setETH(int ETH) {
        this.ETH = ETH;
    }

    public int getUSDT() {
        return USDT;
    }

    public void setUSDT(int USDT) {
        this.USDT = USDT;
    }

    public int getBNB() {
        return BNB;
    }

    public void setBNB(int BNB) {
        this.BNB = BNB;
    }

    public int getADA() {
        return ADA;
    }

    public void setADA(int ADA) {
        this.ADA = ADA;
    }

    public int getDOGE() {
        return DOGE;
    }

    public void setDOGE(int DOGE) {
        this.DOGE = DOGE;
    }

    public int getXRP() {
        return XRP;
    }

    public void setXRP(int XRP) {
        this.XRP = XRP;
    }

    public int getUSDC() {
        return USDC;
    }

    public void setUSDC(int USDC) {
        this.USDC = USDC;
    }

    public int getDOT() {
        return DOT;
    }

    public void setDOT(int DOT) {
        this.DOT = DOT;
    }

    public int getBUSD() {
        return BUSD;
    }

    public void setBUSD(int BUSD) {
        this.BUSD = BUSD;
    }

    public int getCoinCount(String sym){
        Log.d("mywallet", "getCoinCount: "+sym);
        switch (sym){
            case "BTC":
                return BTC;
            case "ETH":
                return ETH;
            case "USDT":
                return USDT;
            case "BNB":
                return BNB;
            case "ADA":
                return ADA;
            case "DOGE":
                return DOGE;
            case "XRP":
                return XRP;
            case "USDC":
                return USDC;
            case "DOT":
                return DOT;
            case "BUSD":
                return BUSD;
            default:
                Log.d("userwallet", "get default");
                return 0;
        }
    }

    public void setCoinCount(String sym,int num){
        Log.d("mywallet", "setCoinCount: "+sym);

        switch (sym){
            case "BTC":
                this.BTC= num;
                break;
            case "ETH":
                 this.ETH=num;
                 break;
            case "USDT":
                 this.USDT=num;
                 break;
            case "BNB":
                 this.BNB=num;
                 break;
            case "ADA":
                 this.ADA=num;
                 break;
            case "DOGE":
                 this.DOGE=num;
                 break;
            case "XRP":
                 this.XRP=num;
                 break;
            case "USDC":
                 this.USDC=num;
                 break;
            case "DOT":
                 this.DOT=num;
                 break;
            case "BUSD":
                 this.BUSD=num;
                 break;
            default:
                Log.d("userwallet", "set default");
                break;

        }
    }

    public User_wallet(Double balance) {
        this.balance = balance;
        this.BTC = 0;
        this.ETH = 0;
        this.USDT = 0;
        this.BNB = 0;
        this.ADA = 0;
        this.DOGE = 0;
        this.XRP = 0;
        this.USDC = 0;
        this.DOT = 0;
        this.BUSD = 0;
    }


}
