package com.thoughtworks.todo_list.repository.user;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.thoughtworks.todo_list.repository.user.entity.User;

import io.reactivex.Completable;
import io.reactivex.Maybe;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface DBLocalUserDataSource extends LocalUserDataSource {
    @Query("SELECT * FROM user WHERE name = :name")
    Maybe<User> findByName(String name);

    @Insert(onConflict = REPLACE)
    Completable save(User user);
}
