package com.example.viewpagerexample.Room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface UserDao_wallet {

    @Insert
    void insert(User_wallet user);

    @Update
    void update(User_wallet user);

    @Query("UPDATE Wallet SET BTC = :p WHERE id =:id")
    void updateBTC(int p, int id);

    @Query("UPDATE Wallet SET ETH = :p WHERE id =:id")
    void updateETH(int p, int id);
    @Query("UPDATE Wallet SET USDC = :p WHERE id =:id")
    void updateUSDC(int p, int id);
    @Query("UPDATE Wallet SET USDT = :p WHERE id =:id")
    void updateUSDT(int p, int id);
    @Query("UPDATE Wallet SET BNB = :p WHERE id =:id")
    void updateBNB(int p, int id);
    @Query("UPDATE Wallet SET ADA = :p WHERE id =:id")
    void updateADA(int p, int id);
    @Query("UPDATE Wallet SET DOGE = :p WHERE id =:id")
    void updateDOGE(int p, int id);
    @Query("UPDATE Wallet SET XRP = :p WHERE id =:id")
    void updateXRP(int p, int id);
    @Query("UPDATE Wallet SET DOT = :p WHERE id =:id")
    void updateDOT(int p, int id);
    @Query("UPDATE Wallet SET BUSD = :p WHERE id =:id")
    void updateBUSD(int p, int id);


    @Query("UPDATE Wallet SET balance = :p WHERE id =:id")
    void updatebalance(Double p, int id);





    @Delete
    void delete(User_wallet user);

    @Query("SELECT * FROM  Wallet")
    List<User_wallet> getAll();

    @Query("DELETE FROM Wallet")
    void deleteAll();

    @Query("SELECT COUNT(*) as cnt FROM Wallet")
    int getDataCount();

}
