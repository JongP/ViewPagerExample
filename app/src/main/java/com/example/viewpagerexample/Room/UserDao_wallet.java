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




    @Query("UPDATE Wallet SET symbol = :p, quantity =:t, value =:s WHERE id =:id")
    void update(String p, int t, Double s, int id);




    @Delete
    void delete(User_wallet user);

    @Query("SELECT * FROM  Wallet")
    List<User_wallet> getAll();

    @Query("DELETE FROM Wallet")
    void deleteAll();

    @Query("SELECT COUNT(*) as cnt FROM Wallet")
    int getDataCount();

}
