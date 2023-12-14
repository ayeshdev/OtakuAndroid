package com.otaku.otaku.util;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.otaku.otaku.converter.Converters;
import com.otaku.otaku.dao.CartDao;
import com.otaku.otaku.dao.FavoritesDao;
import com.otaku.otaku.entity.Cart;
import com.otaku.otaku.entity.Favorites;

@Database(entities = {Cart.class, Favorites.class},version = 2)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract CartDao cartDao();

    public abstract FavoritesDao favoritesDao();
}
