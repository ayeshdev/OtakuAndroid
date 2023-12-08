package com.otaku.otaku.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.otaku.otaku.entity.Cart;

import java.util.List;
@Dao
public interface CartDao {
    @Query("SELECT * FROM cart")
    List<Cart> getAll();

    @Insert
    long insert(Cart cart);

    @Query("SELECT * FROM cart WHERE id IN (:cartIds)")
    List<Cart> loadAllByIds(int[] cartIds);

//    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
//            "last_name LIKE :last LIMIT 1")
//    User findByName(String first, String last);
//
//    @Insert
//    void insertAll(User... users);
//
//    @Delete
//    void delete(User user);
}
