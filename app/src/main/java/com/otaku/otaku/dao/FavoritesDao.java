package com.otaku.otaku.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.otaku.otaku.entity.Cart;
import com.otaku.otaku.entity.Favorites;

import java.util.List;

@Dao
public interface FavoritesDao {
    @Query("SELECT * FROM favorites")
    List<Favorites> getAll();

    @Insert
    long insert(Favorites favorites);
}
