package com.otaku.otaku.util;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.otaku.otaku.dao.CartDao;
import com.otaku.otaku.entity.Cart;

@Database(entities = {Cart.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CartDao cartDao();
}
