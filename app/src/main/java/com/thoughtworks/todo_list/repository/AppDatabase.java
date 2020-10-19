package com.thoughtworks.todo_list.repository;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.thoughtworks.todo_list.repository.user.DBLocalUserDataSource;
import com.thoughtworks.todo_list.repository.user.entity.User;

@Database(entities = {User.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DBLocalUserDataSource userDBDataSource();
}